package utb.attendancebook.profile;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import utb.attendancebook.R;
import utb.attendancebook.statistics.StudentStatistics;
import utb.attendancebook.teachers.TeacherItem;

/**
 * Created by daniela on 29/05/15.
 */
public class ProfileActivity extends ActionBarActivity {

    private static final String TAG = "ProfileActivity: ";
    private TeacherItem mTeacher = new TeacherItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Set the new toolbar to show up here too.
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Adding the back button, this needs to be handled in the onOptionsItemSelected
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Downloading data from below url*/
        SharedPreferences settings = getSharedPreferences("TokenStorage", 0);
        String id = settings.getString("id", "");
        final String url = "http://104.236.31.197/teacher/"+id+"/courses?username="+id+"&token="+settings.getString("token","");
        //final String url = "http://104.236.31.197/teacher/"+id;

        new AsyncHttpTask().execute(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        if(id == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickInfo(View v) {

        final Context context = this;

        Intent intent = new Intent(context, StudentStatistics.class);
        startActivity(intent);
    }

    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);

            mTeacher.setTeacherName(response.optString("names") + " " + response.optString("lastnames"));
            mTeacher.setId(response.optString("id"));
            JSONObject posts = new JSONObject(response.optString("links"));
            mTeacher.setUri(posts.optString("attendance_uri"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

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
            /* Download complete. Lets update UI */
            if (result == 1) {
                TextView name = (TextView) findViewById(R.id.teacher_name);
                TextView id = (TextView) findViewById(R.id.id);
                name.setText(mTeacher.getTeacherName());
                id.setText(mTeacher.getId());
            } else {
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }

}