package e.lotz3.researchproject;


import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    LineChart lineChart;//creates linechart
    LineDataSet setComp1, setComp2, setComp3, setComp4, setComp5, setComp6;
    String JSON_STRING = "http://morrowrenewablesflowdata.com/ebr_graph.php";
    String JSON_STRING2 = "http://morrowrenewablesflowdata.com/etr_graph.php";
    String JSON_STRING3 = "http://morrowrenewablesflowdata.com/fsc_graph.php";
    String JSON_STRING4= "http://morrowrenewablesflowdata.com/jdp_graph.php";
    String JSON_STRING5 = "http://morrowrenewablesflowdata.com/phr_graph.php";
    String JSON_STRING6 = "http://morrowrenewablesflowdata.com/mls_graph.php";
    String JSON_STRING7 = "http://morrowrenewablesflowdata.com/tcr_graph.php";
    ArrayList<String> info = new ArrayList<String>();


    String test;
    //creates ArrayList which holds data for each dataset
    List<Entry> valsComp1 = new ArrayList<Entry>();
    List<Entry> valsComp2 = new ArrayList<Entry>();
    List<Entry> valsComp3 = new ArrayList<Entry>();
    List<Entry> valsComp4 = new ArrayList<Entry>();
    List<Entry> valsComp5 = new ArrayList<Entry>();
    List<Entry> valsComp6 = new ArrayList<Entry>();
    private CheckBox buttonebr, buttonetr, buttonfsc, buttonjdp, buttonphr, buttontcr;
    List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        lineChart = (LineChart) findViewById(R.id.lineChart);



        //Entries for worksite 1 (EBR)



        //valsComp1.add(c1e4);
        //Entries for worksite 2 (ETR)
        Entry c2e1 = new Entry(0f, 894.87888888888888888888888888888888f);
        valsComp2.add(c2e1);
        Entry c2e2 = new Entry(1f, 1028.67f);
        valsComp2.add(c2e2);
        Entry c2e3 = new Entry(2f, 947.4f);
        valsComp2.add(c2e3);
        Entry c2e4 = new Entry(3f, 947.4f);
        valsComp2.add(c2e4);
        Entry c2e5 = new Entry(4f, 939.13f);
        valsComp2.add(c2e5);
        Entry c2e6 = new Entry(5f, 939.03f);
        valsComp2.add(c2e6);
        //Entries for worksite 3 (FSC)
        Entry c3e1 = new Entry(0f, 1260.43f);
        valsComp3.add(c3e1);
        Entry c3e2 = new Entry(1f, 1252.97f);
        valsComp3.add(c3e2);
        Entry c3e3 = new Entry(2f, 947.4f);
        valsComp3.add(c3e3);
        Entry c3e4 = new Entry(3f, 1456.43f);
        valsComp3.add(c3e4);
        Entry c3e5 = new Entry(4f, 1520.17f);
        valsComp3.add(c3e5);
        Entry c3e6 = new Entry(5f, 1205f);
        valsComp3.add(c3e6);
        Entry c3e7 = new Entry(6f, 1442.43f);
        valsComp3.add(c3e7);
        //Entries for worksite 4 (JDP)
        Entry c4e1 = new Entry(0f, 1027.66f);
        valsComp4.add(c4e1);
        Entry c4e2 = new Entry(1f, 1044.46f);
        valsComp4.add(c4e2);
        Entry c4e3 = new Entry(2f, 947.4f);
        valsComp4.add(c4e3);
        Entry c4e4 = new Entry(3f, 896.34f);
        valsComp4.add(c4e4);
        Entry c4e5 = new Entry(4f, 1150.21f);
        valsComp4.add(c4e5);
        Entry c4e6 = new Entry(5f, 984.73f);
        valsComp4.add(c4e6);
        Entry c4e7 = new Entry(6f, 999.51f);
        valsComp4.add(c4e7);
        Entry c4e8 = new Entry(7f, 927.13f);
        valsComp4.add(c4e8);

        //Entnries for worksite 5 (PHR)

        Entry c5e2 = new Entry(1f, 1005.8f);
        valsComp5.add(c5e2);
        Entry c5e3 = new Entry(2f, 947.4f);
        valsComp5.add(c5e3);


        //Entries for worksite 6 (TCR)
        Entry c6e1 = new Entry(0f, 524f);
        valsComp6.add(c6e1);
        Entry c6e2 = new Entry(1f, 764.36f);
        valsComp6.add(c6e2);
        Entry c6e3 = new Entry(2f, 947.4f);
        valsComp6.add(c6e3);
        Entry c6e4 = new Entry(3f, 875.24f);
        valsComp6.add(c6e4);
        Entry c7e5 = new Entry(4, 1254.28f);
        valsComp6.add(c7e5);
        Entry c6e6 = new Entry(5, 1175.15f);
        valsComp6.add(c6e6);
        Entry c6e7 = new Entry(6, 1532.33f);
        valsComp6.add(c6e7);


        //Legend setup
        setComp1 = new LineDataSet(valsComp1, "EBR");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(Color.YELLOW);
        setComp1.setDrawCircles(true);
        addListenerebr();


        setComp2 = new LineDataSet(valsComp2, "ETR");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setColor(Color.MAGENTA);
        setComp2.setDrawCircles(true);
        addListeneretr();

        setComp3 = new LineDataSet(valsComp3, "FSC");
        setComp3.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp3.setColor(Color.GREEN);
        setComp3.setDrawCircles(true);
        addListenerfsc();

        setComp4 = new LineDataSet(valsComp4, "JDP");
        setComp4.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp4.setColor(Color.BLUE);
        setComp4.setDrawCircles(true);
        addListenerjdp();

        setComp5 = new LineDataSet(valsComp5, "PHR");
        setComp5.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp5.setColor(Color.BLACK);
        setComp5.setDrawCircles(true);
        addListenerphr();

        setComp6 = new LineDataSet(valsComp6, "TCR");
        setComp6.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp6.setColor(Color.RED);
        setComp6.setVisible(true);
        addListenertcr();



        XAxis xAxis = lineChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        dataSets.add(setComp2);
        dataSets.add(setComp3);
        dataSets.add(setComp4);
        dataSets.add(setComp5);
        dataSets.add(setComp6);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();






    }

    class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        protected String readStream(InputStream in) {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url;
            HttpURLConnection urlConnection;
            String text = null;

            try {
                url = new URL(JSON_STRING);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream in = urlConnection.getInputStream();
                text= readStream(in);

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text;
        }
        //Getting huge String into an Array
        private void loadIntoListView(String json) throws JSONException {
            //creating a json array from the json string
            JSONArray jsonArray = new JSONArray(json);

            //creating a string array for listview
            String [] MMBTU = new String[jsonArray.length()];

            //looping through all the elements in json array
            for (int i = 0; i <MMBTU.length; i++) {

                //getting json object from the json array
                JSONObject obj = jsonArray.getJSONObject(i);

                //getting the name from the json object and putting it inside string array
                MMBTU[i] = obj.getString("30_Day_MA_MMBTU");

            }
            for(int i =0; i<MMBTU.length; i++){
                info.add(MMBTU[i]);

            }
            //Toast.makeText(getApplicationContext(), MMBTU[0], Toast.LENGTH_LONG).show();
















            //Toast.makeText(getApplicationContext(), MMBTU[20], Toast.LENGTH_LONG).show();


        }

        protected void onPostExecute(String results) {
            if (results != null) {
                {
                    super.onPostExecute(results);

                    try {
                        loadIntoListView(results);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


        }
    }
    //action performed when button is pressed
    public void buttonaction(View view) {
        getinfo(JSON_STRING);
        datasetcreator();
        //Toast.makeText(getApplicationContext(), Arrays.toString(new ArrayList[]{info}), Toast.LENGTH_LONG).show();
        dataSets.add(setComp1);


    }
    public void getinfo(String php){
        JSON_STRING=php;
        new LongRunningGetIO().execute();
    }
    public void datasetcreator(){
        for(int i =0; i<info.size(); i++){

            valsComp1.add(new Entry(i, Float.parseFloat(info.get(i))));
        }

        //


    }




    public void addListenerebr(){

        buttonebr = (CheckBox) findViewById(R.id.ebr);

        buttonebr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()){
                    setComp1.setVisible(false);


                }else
                    setComp1.setVisible(true);


            }
        });



    }

    public void addListeneretr(){

        buttonetr = (CheckBox) findViewById(R.id.etr);
        lineChart.invalidate();

        buttonetr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()){
                    setComp2.setVisible(false);
                }else
                    setComp2.setVisible(true);

            }
        });



    }


    public void addListenerfsc(){

        buttonfsc = (CheckBox) findViewById(R.id.fsc);

        buttonfsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()){
                    setComp3.setVisible(false);
                }else
                    setComp3.setVisible(true);

            }
        });



    }

    public void addListenerjdp(){

        buttonjdp = (CheckBox) findViewById(R.id.jdp);

        buttonjdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()){
                    setComp4.setVisible(false);
                }else
                    setComp4.setVisible(true);

            }
        });



    }

    public void addListenerphr(){

        buttonphr = (CheckBox) findViewById(R.id.phr);

        buttonphr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()){
                    setComp5.setVisible(false);
                }else
                    setComp5.setVisible(true);

            }
        });



    }

    public void addListenertcr(){

        buttontcr = (CheckBox) findViewById(R.id.tcr);

        buttontcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()){
                    setComp6.setVisible(false);
                }else
                    setComp6.setVisible(true);

            }
        });



    }


}
