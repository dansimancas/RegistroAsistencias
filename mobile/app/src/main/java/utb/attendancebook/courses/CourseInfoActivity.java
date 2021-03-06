package utb.attendancebook.courses;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import utb.attendancebook.R;

/**
 * Created by daniela on 17/04/15.
 */
public class CourseInfoActivity extends Activity {

    private static final String TAG = "CourseInfoActivity: ";
    private CourseItem course = new CourseItem();

    private RecyclerView mRecyclerView;
    private CourseInfoAdapter adapter;

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
        String nrc = b.getString("nrc");

        /*Downloading data from below url*/
        final String url = "http://104.236.31.197/course/"+nrc;

        new AsyncHttpTask().execute(url);
    }

    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);

            course.setSubjectName(response.optString("subject_name"));
            course.setNrc(response.optString("nrc"));
            course.setPeriod(response.optString("period"));
            course.setCredits(response.optString("credits"));
            course.setWeekHours(response.optString("week_hours"));
            course.setSubject(response.optString("subject"));
            course.setSection(response.optString("section"));
            course.setCourse(response.optString("course"));
            JSONObject posts = new JSONObject(response.optString("links"));
            course.setUri(posts.optString("statistics_uri"));

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
                adapter = new CourseInfoAdapter(CourseInfoActivity.this, course);
                mRecyclerView.setAdapter(adapter);
            } else {
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }

}