package utb.attendancebook;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebStatistics extends ActionBarActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_statistics);

        webview= (WebView) findViewById(R.id.webview);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccess(true);

        String summary = "<html>\n" +
                "  <head>\n" +
                "   <script src='http://code.jquery.com/jquery-2.1.4.min.js'></script>"+
                "   <script src='http://code.highcharts.com/highcharts.js'></script>"+
                "   <script>"+
                "   $(function () {" +
                "    $('#container').highcharts({\n" +
                    "        chart: {\n" +
                    "            plotBackgroundColor: null,\n" +
                    "            plotBorderWidth: null,\n" +
                    "            plotShadow: false\n" +
                    "        },\n" +
                    "        title: {\n" +
                    "            text: 'Browser market shares at a specific website, 2014'\n" +
                    "        },\n" +
                    "        tooltip: {\n" +
                    "            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'\n" +
                    "        },\n" +
                    "        plotOptions: {\n" +
                    "            pie: {\n" +
                    "                allowPointSelect: true,\n" +
                    "                cursor: 'pointer',\n" +
                    "                dataLabels: {\n" +
                    "                    enabled: true,\n" +
                    "                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',\n" +
                    "                    style: {\n" +
                    "                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        series: [{\n" +
                    "            type: 'pie',\n" +
                    "            name: 'Browser share',\n" +
                    "            data: [\n" +
                    "                ['Firefox',   45.0],\n" +
                    "                ['IE',       26.8],\n" +
                    "                {\n" +
                    "                    name: 'Chrome',\n" +
                    "                    y: 12.8,\n" +
                    "                    sliced: true,\n" +
                    "                    selected: true\n" +
                    "                },\n" +
                    "                ['Safari',    8.5],\n" +
                    "                ['Opera',     6.2],\n" +
                    "                ['Others',   0.7]\n" +
                    "            ]\n" +
                    "        }]\n" +
                    "    });\n" +
                    "});"+
                "    </script>\n" +
                "  </head>\n" +
                "\n" +
                "  <body>\n" +
                "    <div id=\"container\" style=\"min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto\"></div>\n" +
                "Hola mundo"+
                "  </body>\n" +
                "</html>";
        webview.loadData(summary, "text/html", null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_statistics, menu);
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
}
