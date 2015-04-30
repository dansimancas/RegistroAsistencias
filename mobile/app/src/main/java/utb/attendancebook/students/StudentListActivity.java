package utb.attendancebook.students;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import utb.attendancebook.R;

public class StudentListActivity extends Activity{

    private static final String TAG = "Estudiantes";
    private List<StudentItem> studentItemList = new ArrayList<StudentItem>();

    private RecyclerView mRecyclerView;
    private StudentListAdapter adapter;
    private JSONArray studentAttendances = new JSONArray();
    private JSONObject courseAttendances = new JSONObject();
    private String nrc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Allow activity to show indeterminate progress-bar */
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_main);

        /* Initialize recycler view */
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle b = getIntent().getExtras();
        nrc = b.getString("nrc");

        Log.d("Tag", nrc);

        /*Downloading data from below url*/
        final String url = "http://104.236.31.197/course/"+nrc+"/students";

        new AsyncHttpTask().execute(url);
    }



    public void onClickStudentName(View v) {

        String tag = v.getTag().toString();
        ViewParent grandparent = v.getParent();
        View vv = (View) grandparent;
        int attendanceValue = 0;

        int currentColor = ((ColorDrawable) vv.getBackground()).getColor();

        if(currentColor == getResources().getColor(R.color.undefined)){

            vv.setBackgroundColor(getResources().getColor(R.color.came));
            attendanceValue = 0;

        }else if(currentColor == getResources().getColor(R.color.came)){

            vv.setBackgroundColor(getResources().getColor(R.color.notcame));
            attendanceValue = 1;

        }else if(currentColor == getResources().getColor(R.color.notcame)){

            vv.setBackgroundColor(getResources().getColor(R.color.late));
            attendanceValue = 2;

        }else if(currentColor == getResources().getColor(R.color.late)){

            vv.setBackgroundColor(getResources().getColor(R.color.leftsoon));
            attendanceValue = 3;

        }else if(currentColor == getResources().getColor(R.color.leftsoon)){

            vv.setBackgroundColor(getResources().getColor(R.color.undefined));
            attendanceValue = 4;

        }

        if(studentAttendances.length() == 0) {
            JSONObject student = new JSONObject();
            try {
                student.put("id", tag);
                student.put("attendance", attendanceValue);
                Toast.makeText(getApplicationContext(),"The student "+getAttendanceStatus(attendanceValue),Toast.LENGTH_SHORT).show();
                studentAttendances.put(student);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            for(int i = 0; i < studentAttendances.length(); i++){

                try {
                    JSONObject student = (JSONObject) studentAttendances.get(i);
                    String id = student.getString("id");
                    if(id.equals(tag)) {

                        student.remove("attendance");
                        student.put("attendance", attendanceValue);
                        studentAttendances.remove(i);
                        studentAttendances.put(student);
                        Toast.makeText(getApplicationContext(),"The student "+getAttendanceStatus(attendanceValue),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
            try {
                JSONObject student = new JSONObject();
                student.put("id", tag);
                student.put("attendance", attendanceValue);
                Toast.makeText(getApplicationContext(),"The student "+getAttendanceStatus(attendanceValue),Toast.LENGTH_SHORT).show();
                studentAttendances.put(student);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.v("font", v.getClass().toString());
    }

    public String getAttendanceStatus(int i){

        String status = "";

        switch(i){
            case 0:
                status = "came";
                break;
            case 1:
                status = "did not come";
                break;
            case 2:
                status = "arrived late";
                break;
            case 3:
                status = "left soon";
                break;
            case 4:
                status = "undefined";
                break;
            default:
                status = "error";
                break;
        }

        return status;
    }

    public void onClickSave(View v){
        for(int i = 0; i < studentAttendances.length(); i++){
            try {
                JSONObject json = (JSONObject) studentAttendances.get(i);
                Log.i("id: ",""+json.getString("id"));
                Log.i("attendance: ",""+json.getString("attendance"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            courseAttendances.put(nrc,studentAttendances);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onClickStudentId(View v){

        Object tag = v.getTag();
        if(tag != null){
            Log.d("ClickStudentId", tag.toString());
            Intent intent = new Intent(StudentListActivity.this, StudentInfoActivity.class);
            String id = v.getTag().toString();
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }

    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("students");

            /*Initialize array if null*/
            if (null == studentItemList) {
                studentItemList = new ArrayList<StudentItem>();
            }

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);

                StudentItem item = new StudentItem();
                item.setStudentName(post.optString("names") + " " + post.optString("lastnames"));
                item.setId(post.optString("id"));
                item.setUri(post.optString("resource_uri"));

                studentItemList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            Integer result = 0;
            HttpURLConnection urlConnection = null;

            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                /* for Get request */
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            setProgressBarIndeterminateVisibility(false);
            /* Download complete. Lets update UI */
            if (result == 1) {
                adapter = new StudentListAdapter(StudentListActivity.this, studentItemList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }

}
