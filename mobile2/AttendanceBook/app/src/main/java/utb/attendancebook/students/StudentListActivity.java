package utb.attendancebook.students;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import utb.attendancebook.NavigationDrawerFragment;
import utb.attendancebook.R;
import utb.attendancebook.statistics.WebStatistics;


/**
 * Created by daniela on 28/05/15.
 */
public class StudentListActivity extends ActionBarActivity{

    private Toolbar mToolbar;
    private static final String TAG = "Estudiantes";
    private List<StudentItem> mStudentItemList = new ArrayList<StudentItem>();

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
        setContentView(R.layout.activity_student_list);

        //Setting the toolbar
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Setting the navigation drawer fragment
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

        /* Initialize recycler view */
        mRecyclerView = (RecyclerView) findViewById(R.id.students_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle b = getIntent().getExtras();
        nrc = b.getString("nrc");

        Log.d("Tag", nrc);

        /*Downloading data from below url*/
        final String url = "http://104.236.31.197/course/"+nrc+"/students";

        new AsyncHttpTask().execute(url);
    }

    public void onStudentItemClick(View vv) {

        //String tag = v.getTag().toString();
        ViewParent parent = vv.getParent();
        View v = (View) parent;
        TextView studentNameView = (TextView) v.findViewById(R.id.student_name);
        TextView studentIdView = (TextView) v.findViewById(R.id.id);
        String studentId = studentIdView.getText().toString();
        TextView attendanceStatus = (TextView) v.findViewById(R.id.attendance_status);
        int attendanceValue = 0;
        int currentColor = (v.getBackground() != null) ? ((ColorDrawable) v.getBackground()).getColor() : getResources().getColor(R.color.undefined);

        if(currentColor == getResources().getColor(R.color.undefined)){

            studentNameView.setTextColor(getResources().getColor(R.color.came_text));
            studentIdView.setTextColor(getResources().getColor(R.color.came_text));
            attendanceStatus.setTextColor(getResources().getColor(R.color.came_text));
            attendanceStatus.setText(getString(R.string.came_string));
            v.setBackgroundColor(getResources().getColor(R.color.came));
            attendanceValue = 0;

        }else if(currentColor == getResources().getColor(R.color.came)){

            studentNameView.setTextColor(getResources().getColor(R.color.notcame_text));
            studentIdView.setTextColor(getResources().getColor(R.color.notcame_text));
            studentIdView.setTextColor(getResources().getColor(R.color.notcame_text));
            attendanceStatus.setTextColor(getResources().getColor(R.color.notcame_text));
            attendanceStatus.setText(getString(R.string.notcame_string));
            v.setBackgroundColor(getResources().getColor(R.color.notcame));
            attendanceValue = 1;

        }else if(currentColor == getResources().getColor(R.color.notcame)){

            studentNameView.setTextColor(getResources().getColor(R.color.late_text));
            studentIdView.setTextColor(getResources().getColor(R.color.late_text));
            studentIdView.setTextColor(getResources().getColor(R.color.late_text));
            attendanceStatus.setTextColor(getResources().getColor(R.color.late_text));
            attendanceStatus.setText(getString(R.string.late_string));
            v.setBackgroundColor(getResources().getColor(R.color.late));
            attendanceValue = 2;

        }else if(currentColor == getResources().getColor(R.color.late)){
            studentNameView.setTextColor(getResources().getColor(R.color.leftsoon_text));
            studentIdView.setTextColor(getResources().getColor(R.color.leftsoon_text));
            studentIdView.setTextColor(getResources().getColor(R.color.leftsoon_text));
            attendanceStatus.setTextColor(getResources().getColor(R.color.leftsoon_text));
            attendanceStatus.setText(getString(R.string.leftsoon_string));
            v.setBackgroundColor(getResources().getColor(R.color.leftsoon));
            attendanceValue = 3;

        }else if(currentColor == getResources().getColor(R.color.leftsoon)){
            studentNameView.setTextColor(getResources().getColor(R.color.undefined_text));
            studentIdView.setTextColor(getResources().getColor(R.color.undefined_text));
            studentIdView.setTextColor(getResources().getColor(R.color.undefined_text));
            attendanceStatus.setTextColor(getResources().getColor(R.color.undefined_text));
            attendanceStatus.setText(getString(R.string.undefined_string));
            v.setBackgroundColor(getResources().getColor(R.color.undefined));
            attendanceValue = 4;

        }
        if(studentAttendances.length() == 0) {

            JSONObject student = new JSONObject();
            try {
                student.put("ID", studentId);
                student.put("ATTENDANCE", attendanceValue);
                studentAttendances.put(student);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            for(int i = 0; i < studentAttendances.length(); i++){
                try {
                    JSONObject student = (JSONObject) studentAttendances.get(i);
                    String id = student.getString("ID");
                    if(id.equals(studentId)) {
                        student.remove("ATTENDANCE");
                        student.put("ATTENDANCE", attendanceValue);
                        studentAttendances.remove(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                JSONObject student = new JSONObject();
                student.put("ID", studentId);
                student.put("ATTENDANCE", attendanceValue);
                studentAttendances.put(student);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.v("font", v.getClass().toString());
    }

    /*public void onClickInfo(View v) {

        final Context context = this;

        Intent intent = new Intent(context, WebStatistics.class);
        startActivity(intent);
    }*/
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

    public void onInfoIconClick(View vv){
        ViewParent grandparent = vv.getParent();
        View v = (View) grandparent;
        String studentId = ((TextView) v.findViewById(R.id.id)).getText().toString();
        if(studentId != null){
            Log.d("ClickStudentId", studentId);
            Intent intent = new Intent(StudentListActivity.this, StudentInfoActivity.class);
            intent.putExtra("id", studentId);
            startActivity(intent);
        }
    }

    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("students");

            /*Initialize array if null*/
            if (null == mStudentItemList) {
                mStudentItemList = new ArrayList<StudentItem>();
            }

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);

                StudentItem item = new StudentItem();
                item.setStudentName(post.optString("names") + " " + post.optString("lastnames"));
                item.setId(post.optString("id"));
                item.setUri(post.optString("resource_uri"));

                mStudentItemList.add(item);
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
                adapter = new StudentListAdapter(StudentListActivity.this, mStudentItemList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }
}
