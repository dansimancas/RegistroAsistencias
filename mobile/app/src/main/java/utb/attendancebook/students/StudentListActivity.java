package utb.attendancebook.students;

import android.app.Activity;
import android.content.Context;
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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import utb.attendancebook.R;
import utb.attendancebook.WebStatistics;

public class StudentListActivity extends Activity{

    private static final String TAG = "Estudiantes";
    private List<StudentItem> studentItemList = new ArrayList<StudentItem>();

    private RecyclerView mRecyclerView;
    private StudentListAdapter adapter;
    private JSONArray studentAttendances = new JSONArray();
    private JSONObject courseAttendances = new JSONObject();
    private JSONArray Attendances = new JSONArray();
    private String nrc = "";
    private String uri="http://104.236.31.197/attendance";

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

    public void onClickStudentName(View v, TextView t) {
        String initial ="";
        String tag = v.getTag().toString();
        ViewParent grandparent = v.getParent();
        View vv = (View) grandparent;
        TextView text = (TextView) findViewById(R.id.student_name);
        int attendanceValue = 0;
        int currentColor = ((ColorDrawable) vv.getBackground()).getColor();
        if(currentColor == getResources().getColor(R.color.undefined)){
            text.setTextColor(getResources().getColor(R.color.textDefined));
            vv.setBackgroundColor(getResources().getColor(R.color.came));
            attendanceValue = 0;
        }else if(currentColor == getResources().getColor(R.color.came)){
            text.setTextColor(getResources().getColor(R.color.textDefined));
            vv.setBackgroundColor(getResources().getColor(R.color.notcame));
            attendanceValue = 1;
        }else if(currentColor == getResources().getColor(R.color.notcame)){
            text.setTextColor(getResources().getColor(R.color.textDefined));
            vv.setBackgroundColor(getResources().getColor(R.color.late));
            attendanceValue = 2;
        }else if(currentColor == getResources().getColor(R.color.late)){
            text.setTextColor(getResources().getColor(R.color.textDefined));
            vv.setBackgroundColor(getResources().getColor(R.color.leftsoon));
            attendanceValue = 3;
        }else if(currentColor == getResources().getColor(R.color.leftsoon)){
            text.setTextColor(getResources().getColor(R.color.textUndefined));
            vv.setBackgroundColor(getResources().getColor(R.color.undefined));
            attendanceValue = 4;
        }
        if(studentAttendances.length() == 0) {
            JSONObject student = new JSONObject();
            try {
                student.put("ID", tag);
                student.put("ATTENDANCE", attendanceValue);
                Toast.makeText(getApplicationContext(),"The student "+getAttendanceStatus(attendanceValue),Toast.LENGTH_SHORT).show();
                studentAttendances.put(student);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            for(int i = 0; i < studentAttendances.length(); i++){
                try {
                    JSONObject student = (JSONObject) studentAttendances.get(i);
                    String id = student.getString("ID");
                    if(id.equals(tag)) {
                        student.remove("ATTENDANCE");
                        student.put("ATTENDANCE", attendanceValue);
                        studentAttendances.remove(i);
                        Toast.makeText(getApplicationContext(),"The student "+getAttendanceStatus(attendanceValue),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                JSONObject student = new JSONObject();
                student.put("ID", tag);
                student.put("ATTENDANCE", attendanceValue);
                Toast.makeText(getApplicationContext(),"The student "+getAttendanceStatus(attendanceValue),Toast.LENGTH_SHORT).show();
                studentAttendances.put(student);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.v("font", v.getClass().toString());
    }

    /*public void onClickStudentName(View v) {
        String initial ="";
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
                student.put("ID", tag);
                student.put("ATTENDANCE", attendanceValue);
                Toast.makeText(getApplicationContext(),"The student "+getAttendanceStatus(attendanceValue),Toast.LENGTH_SHORT).show();
                studentAttendances.put(student);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            for(int i = 0; i < studentAttendances.length(); i++){

                try {
                    JSONObject student = (JSONObject) studentAttendances.get(i);
                    String id = student.getString("ID");
                    if(id.equals(tag)) {

                        student.remove("ATTENDANCE");
                        student.put("ATTENDANCE", attendanceValue);
                        studentAttendances.remove(i);
                        Toast.makeText(getApplicationContext(),"The student "+getAttendanceStatus(attendanceValue),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
            try {

                JSONObject student = new JSONObject();

                    student.put("ID", tag);
                    student.put("ATTENDANCE", attendanceValue);


                Toast.makeText(getApplicationContext(),"The student "+getAttendanceStatus(attendanceValue),Toast.LENGTH_SHORT).show();
                studentAttendances.put(student);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.v("font", v.getClass().toString());
    }*/

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
    public void onClickInfo(View v) {

        final Context context = this;

        Intent intent = new Intent(context, WebStatistics.class);
        startActivity(intent);
    }
    public void onClickSave(){
        for(int i = 0; i < studentAttendances.length(); i++){
            try {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                JSONObject json = (JSONObject) studentAttendances.get(i);
                Log.i("ID: ",""+json.getString("id"));
                Log.i("ATTENDANCE: ",""+json.getString("attendance"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            courseAttendances.put("NRC", nrc);
            courseAttendances.put("ESTUDIANTES", studentAttendances);
            Attendances.put(courseAttendances);
            System.out.print(courseAttendances);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private class UploadASyncTask extends AsyncTask<JSONObject, Void, Void> {

        @Override
        protected Void doInBackground(JSONObject... auth) {
            try {
                HttpParams params = new BasicHttpParams();
                HttpClient httpclient = new DefaultHttpClient(params);

                HttpPost httpPost = new HttpPost(uri);


                httpPost.setEntity(new StringEntity(Attendances.toString(), "UTF8"));
                httpPost.setHeader("Content-type", "application/json");


                HttpResponse httpResponse = httpclient.execute(httpPost);

                InputStream inputStream = httpResponse.getEntity().getContent();
                String result = "";

                if (inputStream != null) {
                    result = inputStream.toString();

                } else {
                    result = "Did not work!";

                }

                Log.d("RESULT", result);


            } catch (Exception e) {

                Log.e("ERROR IN SEVER UPLOAD", e.getMessage());
            }
            return null;


        }
    }
    @Override
    public void onBackPressed() {

        onClickSave();
        Log.d("otro1: ",Attendances.toString());
        UploadASyncTask upload = new UploadASyncTask();
        upload.execute();
        super.onBackPressed();
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
