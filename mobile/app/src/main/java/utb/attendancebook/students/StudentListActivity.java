package utb.attendancebook.students;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

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

import utb.attendancebook.R;

public class StudentListActivity extends Activity {

    private static final String TAG = "Estudiantes";
    private List<StudentItem> studentItemList = new ArrayList<StudentItem>();

    private RecyclerView mRecyclerView;
    private StudentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_student_list);

        /* Allow activity to show indeterminate progress-bar */
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_main);

        /* Initialize recycler view */
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle b = getIntent().getExtras();
        String id = b.getString("nrc");

        Log.d("Tag", id);
        //String course = mRecyclerView.getTag().toString();

        /*Downloading data from below url*/
        final String url = "http://104.236.31.197/course/"+id+"/students";


        //final String url = "http://104.236.31.197/course/2028-201510/students";
        new AsyncHttpTask().execute(url);
        /*
        * TextView tx = (TextView) findViewById(R.id.id);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Hola mundo",Toast.LENGTH_SHORT).show();
            }
        });
        * */
    }

    public void onClickStudentName(View v) {
        Toast.makeText(getApplicationContext(),"Nombre del estudiante",Toast.LENGTH_SHORT).show();
        /*
        * Log.d("ClickStudentName", v.getTag().toString());
         Intent intent = new Intent(StudentListActivity.this, StudentListActivity.class);
         startActivity(intent);
        * */
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
