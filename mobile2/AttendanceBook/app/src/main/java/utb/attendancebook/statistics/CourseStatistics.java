package utb.attendancebook.statistics;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import utb.attendancebook.MainActivity;
import utb.attendancebook.R;


public class CourseStatistics extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_statistics);

        //Set the new toolbar to show up here too.
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_course_statistics);
        setSupportActionBar(toolbar);

        //Adding the back button, this needs to be handled in the onOptionsItemSelected
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String course = MainActivity.settings.getString("nrc","");
        String subject_name = MainActivity.settings.getString("subject_name","");

        String content = "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <title></title>\n" +
                "    <script type=\"text/javascript\" src=\"./jquery.min.js\"></script>\t\t\n" +
                "    <script src=\"./highcharts.js\"></script>\n" +
                "    <!--<script src=\"http://code.highcharts.com/modules/exporting.js\"></script>\n-->" +
                "    <script src=\"grid-light.js\"></script>\n" +
                "    <script type=\"text/javascript\">        \n" +
                "        var students_names = new Array();   \n" +
                "        var came = new Array(); \n" +
                "        var didnotcome = new Array(); \n" +
                "        var arrivedlate = new Array(); \n" +
                "        var leftsoon = new Array(); \n" +
                "        var undef = new Array();       \n" +
                "        \n" +
                "        \n" +
                "        $.getJSON('http://104.236.31.197/course/"+course+"/attendance?username="+MainActivity.settings.getString("id","")+"&token="+ MainActivity.settings.getString("token","")+"', function(data) {\n" +
                "            data = data[\"students\"];   \n" +
                "            \n" +
                "            for (i = 0; i < data.length; i++){\n" +
                "                students_names.push(data[i][\"student_name\"]+ \" \"+data[i][\"student_lastname\"]); //NOMBRE DEL ESTUDIANTE\n" +
                "            }\n" +
                "            \n" +
                "            for (i = 0 ; i < 5; i++){                \n" +
                "                for( j = 0; j < data.length; j++){\n" +
                "                    switch(i){\n" +
                "                        case 0:\n" +
                "                            came.push(data[j][\"attendance\"][0][\"value\"]);\n" +
                "                            break;\n" +
                "                        case 1:\n" +
                "                            didnotcome.push(data[j][\"attendance\"][1][\"value\"]);\n" +
                "                            break;\n" +
                "                        case 2:\n" +
                "                            arrivedlate.push(data[j][\"attendance\"][2][\"value\"]);\n" +
                "                            break;\n" +
                "                        case 3:\n" +
                "                            leftsoon.push(data[j][\"attendance\"][3][\"value\"]);\n" +
                "                            break;\n" +
                "                        case 4:\n" +
                "                            undef.push(data[j][\"attendance\"][4][\"value\"]);\n" +
                "                            break;\n" +
                "                    }                    \n" +
                "                }\n" +
                "            }\n" +
                "            // draw charts\n" +
                "            $(function () {\n" +
                "                $('#container').highcharts({\n" +
                "                    chart: {\n" +
                "                        type: 'bar'\n" +
                "                    },\n" +
                "                    title: {\n" +
                "                        text: '"+subject_name+" attendance statistics',\n" +
                "                        style: {\n" +
                "                            color: 'black',\n" +
                "                            fontSize:'20px',\n" +
                "                            fontWeight: 'bold'\n" +
                "                        }\n" +
                "                    },\n" +
                "                    xAxis: {                        \n" +
                "                        categories: students_names,\n" +
                "                        labels: {\n" +
                "                            style: {\n" +
                "                                color: 'black',\n" +
                "                                fontSize:'12px',\n" +
                "                                fontWeight: 'bold'\n" +
                "                            }\n" +
                "                        }\n" +
                "                    },\n" +
                "                    yAxis: {\n" +
                "                        max: 100,\n" +
                "                        title: {\n" +
                "                            text: 'Attendances (%)',\n" +
                "                            style: {\n" +
                "                                color: 'black',\n" +
                "                                fontWeight: 'bold'\n" +
                "                            }\n" +
                "                        },\n" +
                "                        labels: {\n" +
                "                            style: {\n" +
                "                                color: 'black',\n" +
                "                                fontSize:'12px',\n" +
                "                                fontWeight: 'bold'\n" +
                "                            }\n" +
                "                        }\n" +
                "                    },\n" +
                "                    legend: {\n" +
                "                        itemMarginBottom: 15,\n" +
                "                        itemDistance: 10\n" +
                "                    }, \n" +
                "                    plotOptions: {                          \n" +
                "                        bar: {\n" +
                "                            dataLabels: {\n" +
                "                                style: {\n" +
                "                                    color: 'black',\n" +
                "                                    fontSize:'10px'\n" +
                "                                },     \n" +
                "                                enabled: true,\n" +
                "                                formatter: function() {\n" +
                "                                    if (this.y !== 0) {\n" +
                "                                      return this.y;\n" +
                "                                    } else {\n" +
                "                                      return null;\n" +
                "                                    }\n" +
                "                                }\n" +
                "                              \n" +
                "                            }\n" +
                "                        },\n" +
                "                        series: {\n" +
                "                            events: {\n" +
                "                                legendItemClick: function() {\n" +
                "                                    return false;\n" +
                "                                }\n" +
                "                            },\n" +
                "                            stacking: 'normal'\n" +
                "                        }\n" +
                "                    },\n" +
                "                    credits: {\n" +
                "                        enabled: false\n" +
                "                    },\n" +
                "                    tooltip: {\n" +
                "                        backgroundColor: {\n" +
                "                                linearGradient: [0, 0, 0, 60],\n" +
                "                                stops: [\n" +
                "                                    [0, '#FFFFFF'],\n" +
                "                                    [1, '#E0E0E0']\n" +
                "                                ]\n" +
                "                            },\n" +
                "                            borderWidth: 1,\n" +
                "                            borderColor: '#AAA',\n" +
                "                        formatter: function() {\n" +
                "                            return '<strong>'+this.series.name + '</strong>' + '  ' + this.y + ' %';\n" +
                "                        }\n" +
                "                    },\n" +
                "                    series: [{\n" +
                "                        name: 'Came',\n" +
                "                        data: came\n" +
                "                    }, {\n" +
                "                        name: 'Did not come',\n" +
                "                        data: didnotcome\n" +
                "                    }, {\n" +
                "                        name: 'Arrived late',\n" +
                "                        data: arrivedlate\n" +
                "                    }, {\n" +
                "                        name: 'Left soon',\n" +
                "                        data: leftsoon\n" +
                "                    }, {\n" +
                "                        name: 'Undefined',\n" +
                "                        data: undef\n" +
                "                    }]\n" +
                "                });\n" +
                "            });           \n" +
                "        });\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"container\"></div>\n" +
                "</body>\n" +
                "</html>";

        // Step 1: Create WebView
        WebView myWebView = (WebView) findViewById(R.id.course_statistics);

        // Step 2: Load page from assets
        //myWebView.loadUrl("file:///android_asset/chart.html");
        myWebView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);

        // Step 3: Enable Javascript
        myWebView.getSettings().setJavaScriptEnabled(true);
    }
}