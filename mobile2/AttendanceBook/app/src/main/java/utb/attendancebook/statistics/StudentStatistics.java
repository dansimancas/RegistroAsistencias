package utb.attendancebook.statistics;
import utb.attendancebook.MainActivity;
import utb.attendancebook.R;
import utb.attendancebook.courses.CourseAdapter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class StudentStatistics extends ActionBarActivity {

    private ProgressDialog pd = null;
    private static final String TAG = "Student statistics: ";
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_statistics);

        //Set the new toolbar to show up here too.
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_student_statistics);
        setSupportActionBar(toolbar);

        //Adding the back button, this needs to be handled in the onOptionsItemSelected
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String course = MainActivity.settings.getString("nrc", "");
        String name = MainActivity.settings.getString("current_student_name", "");
        String student = MainActivity.settings.getString("current_student", "");;

        String content = "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <title>Pie from JSON Response</title>\n" +
                "    <script type=\"text/javascript\" src=\"./jquery.min.js\"></script>\t\t\n" +
                "    <script src=\"./highcharts.js\"></script>\n" +
                "    <!--<script src=\"http://code.highcharts.com/modules/exporting.js\"></script>\n-->" +
                "    <script src=\"grid-light.js\"></script>\n" +
                "    <script type=\"text/javascript\">        \n" +
                "        var json = new Array();   \n" +
                "        $.getJSON('http://104.236.31.197/student/"+student+"/course/"+course+"/attendance?username="+MainActivity.settings.getString("id","")+"&token="+ MainActivity.settings.getString("token","")+"', function(data) {\n" +
                "            data = data[\"attendance\"];\n" +
                "            // Populate series\n" +
                "            for (i = 0; i < data.length; i++){\n" +
                "                json.push([data[i].key, data[i].value]);\n" +
                "            }\n" +
                "\n" +
                "            // draw chart\n" +
                "            $('#container').highcharts({\n" +
                "                chart: {\n" +
                "                    plotBackgroundColor: null,\n" +
                "                    plotBorderWidth: null,\n" +
                "                    plotShadow: false\n" +
                "                },\n" +
                "                title: {\n" +
                "                    text: 'Attendances of "+name+"'\n" +
                "                },\n" +
                "                tooltip: { \n" +
                "                    backgroundColor: {\n" +
                "                               linearGradient: [0, 0, 0, 60],\n" +
                "                               stops: [\n" +
                "                                   [0, '#FFFFFF'],\n" +
                "                                   [1, '#E0E0E0']\n" +
                "                               ]\n" +
                "                           },\n" +
                "                           borderWidth: 1,\n" +
                "                           borderColor: '#AAA',\n" +
                "                       formatter: function() {\n" +
                "                           return '<strong>'+this.point.name + '</strong>' + '  ' + this.y + ' %';\n" +
                "                       }\n" +
                "                },\n" +
                "                plotOptions: {\n" +
                "                    pie:{                        \n" +
                "                        showInLegend: true,\n" +
                "                        dataLabels: {\n" +
                "                            style: {\n" +
                "                                color: 'black',\n" +
                "                                fontSize:'12px',\n" +
                "                                fontWeight: 'bold'\n" +
                "                            },     \n" +
                "                            enabled: true,\n" +
                "                            formatter: function() {\n" +
                "                                if (this.y !== 0) {\n" +
                "                                  return this.y+'%';\n" +
                "                                } else {\n" +
                "                                  return null;\n" +
                "                                }\n" +
                "                            }\n" +
                "\n" +
                "                        }\n" +
                "                    },\n" +
                "                    series: {\n" +
                "                        point: {\n" +
                "                            events: {\n" +
                "                                legendItemClick: function () {\n" +
                "                                    return false; // <== returning false will cancel the default action\n" +
                "                                }\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                },\n" +
                "                credits: {\n" +
                "                    enabled: false\n" +
                "                },    \n" +
                "                legend: {\n" +
                "                    itemMarginBottom: 15,\n" +
                "                    itemDistance: 10\n" +
                "                },\n" +
                "                series: [{\n" +
                "                    type: 'pie',\n" +
                "                    name: 'Attendance',\n" +
                "                    data: json\n" +
                "                }]\n" +
                "            });\n" +
                "        });    \n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"container\"></div>\n" +
                "</body>\n" +
                "</html>";

        // Step 1: Create WebView
        myWebView = (WebView) findViewById(R.id.student_statistics);
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        /*New spinner*/
        this.pd = ProgressDialog.show(this, getResources().getText(R.string.process_dialog_title), getResources().getText(R.string.process_dialog_text), true, false);

        myWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
            }
        });
        // Step 2: Load page from assets
        myWebView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);

    }

}