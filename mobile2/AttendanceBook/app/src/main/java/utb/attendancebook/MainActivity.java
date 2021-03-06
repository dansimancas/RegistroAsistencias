package utb.attendancebook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.RelativeLayout;
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

import utb.attendancebook.courses.CourseAdapter;
import utb.attendancebook.courses.CourseItem;
import utb.attendancebook.students.StudentListActivity;


public class MainActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private static final String TAG = "Ejemplo de listas";
    private List<CourseItem> mCourseItemList = new ArrayList<>();
    public static SharedPreferences settings;
    private RecyclerView mRecyclerView;
    private CourseAdapter adapter;
    private String mID;
    private ProgressDialog pd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the toolbar
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Setting the navigation drawer fragment
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

        /* Initialize recycler view */
        mRecyclerView = (RecyclerView) findViewById(R.id.courses_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (mID == null) {
            SharedPreferences settings = getSharedPreferences("TokenStorage", 0);
            mID = settings.getString("id", "");
        }
        settings = getSharedPreferences("TokenStorage", 0);
        /*Downloading data from below url*/
        final String url = getResources().getString(R.string.server_hostname) + "/teacher/" + mID + "/courses?username=" + mID + "&token=" + settings.getString("token", "");
        Log.e("URL Main", url);

        /*New spinner*/
        this.pd = ProgressDialog.show(this, getResources().getText(R.string.process_dialog_title), getResources().getText(R.string.process_dialog_text), true, false);

        /*New Async task*/
        new AsyncHttpTask().execute(url);
    }

    public void onCourseItemClick(View vv) {
        ViewParent parent = vv.getParent();
        View v;
        if (vv instanceof RelativeLayout) {
            v = vv;
        } else {
            v = (View) parent;
        }
        String nrc = ((TextView) v.findViewById(R.id.nrc)).getText().toString();
        String subject_name = ((TextView) v.findViewById(R.id.subject_name)).getText().toString();
        Log.d("Click", nrc);
        Intent intent = new Intent(this, StudentListActivity.class);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("nrc", nrc);
        editor.putString("subject_name", subject_name);
        editor.commit();
        Log.d("nrc main: ", settings.getString("nrc", ""));
        startActivity(intent);
    }

    //Pasar el resultado del parsing a json
    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("courses");

            /*Initialize array if null*/
            if (null == mCourseItemList) {
                mCourseItemList = new ArrayList<>();
            }

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);

                CourseItem item = new CourseItem();
                item.setSubjectName(post.optString("subject_name"));
                item.setNrc(post.optString("nrc"));
                item.setUri(post.optString("resource_uri"));

                mCourseItemList.add(item);
            }
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
                Log.e("Respuesta Main", "ID = " + urlConnection.getResponseCode());

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
                adapter = new CourseAdapter(MainActivity.this, mCourseItemList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Log.e(TAG, "Failed to fetch data!");
            }

            if (MainActivity.this.pd != null) {
                MainActivity.this.pd.dismiss();
            }
        }
    }

}
