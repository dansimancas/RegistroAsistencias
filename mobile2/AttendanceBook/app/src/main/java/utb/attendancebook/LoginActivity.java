package utb.attendancebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class LoginActivity extends ActionBarActivity {

    private String mTeacherID;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Setting the toolbar
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences settings = getSharedPreferences("TokenStorage", 0);

        if(settings.getString("token", "") != ""){
            Intent intent = new Intent(this, MainActivity.class);
            mTeacherID = settings.getString("id","");
            //@TODO: Cambiar codigo por el mTeacherID
            intent.putExtra("id", mTeacherID);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginClick(View v) {

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        mTeacherID = username.getText().toString();

        final String url = getResources().getString(R.string.server_hostname)+"/token";

        new AsyncHttpTask().execute(url, username.getText().toString(), password.getText().toString());


    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        Context context;
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Integer doInBackground(String... params) {
            URL url;
            HttpURLConnection connection = null;
            try {
                //Create connection
                String urlParameters = "username=" + URLEncoder.encode(params[1], "UTF-8") +
                        "&password=" + URLEncoder.encode(params[2], "UTF-8");

                url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");

                connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");

                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                PrintWriter out = new PrintWriter(connection.getOutputStream());
                out.print(urlParameters);
                out.close();

                //Leer la respuesta del servidor
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                    StringBuilder sb = new StringBuilder();
                    String output;
                    while ((output = br.readLine()) != null) {
                        sb.append(output);
                    }

                    //guardar token y el usuario en la app
                    SharedPreferences.Editor editor = MainActivity.settings.edit();
                    editor.putString("token", sb.toString());
                    editor.putString("id", mTeacherID);

                    // Commit the edits!
                    editor.commit();

                    Log.e("Respuesta",sb.toString());
                } catch (Exception e){
                    e.printStackTrace();
                }

                //return response.toString();
                Log.e("Respuesta", "ID = "+connection.getResponseCode());
                return 1;

            } catch (Exception e) {
                Log.e("onExecute", "Error de app");
                e.printStackTrace();
                return null;

            } finally {

                if(connection != null) {
                    connection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(Integer result) {

            Log.e("onPostExecute", "on PostExec");

            SharedPreferences settings = getSharedPreferences("TokenStorage", 0);
            Log.e("onPostExecute", "TokenSaved:" + settings.getString("token", ""));
            Log.e("onPostExecute", "IdSaved:" + settings.getString("id", ""));

            Intent intent_name = new Intent();
            intent_name.setClass(getApplicationContext(), MainActivity.class);
            startActivity(intent_name);
        }
    }

}