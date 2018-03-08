package e.lotz3.researchproject;


import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    LineChart lineChart;//creates linechart
    LineDataSet setComp1, setComp2, setComp3, setComp4, setComp5, setComp6, setComp7;
    String JSON_STRING = "http://morrowrenewablesflowdata.com/ebr_graph.php";
    String JSON_STRING2 = "http://morrowrenewablesflowdata.com/etr_graph.php";
    String JSON_STRING3 = "http://morrowrenewablesflowdata.com/fsc_graph.php";
    String JSON_STRING4= "http://morrowrenewablesflowdata.com/jdp_graph.php";
    String JSON_STRING5 = "http://morrowrenewablesflowdata.com/phr_graph.php";
    String JSON_STRING6 = "http://morrowrenewablesflowdata.com/tcr_graph.php";
    String JSON_STRING7 = "http://morrowrenewablesflowdata.com/mls_graph.php";
    ArrayList<String> info = new ArrayList<String>();
    ArrayList<String> info2 = new ArrayList<String>();
    ArrayList<String> info3 = new ArrayList<String>();
    ArrayList<String> info4 = new ArrayList<String>();
    ArrayList<String> info5 = new ArrayList<String>();
    ArrayList<String> info6 = new ArrayList<String>();
    ArrayList<String> info7 = new ArrayList<String>();
    ArrayList<String> maindate = new ArrayList<String>();

    Button buttonLoad;
    //creates ArrayList which holds data for each dataset
    List<Entry> valsComp1 = new ArrayList<Entry>();
    List<Entry> valsComp2 = new ArrayList<Entry>();
    List<Entry> valsComp3 = new ArrayList<Entry>();
    List<Entry> valsComp4 = new ArrayList<Entry>();
    List<Entry> valsComp5 = new ArrayList<Entry>();
    List<Entry> valsComp6 = new ArrayList<Entry>();
    List<Entry> valsComp7 = new ArrayList<Entry>();
    private CheckBox buttonebr, buttonetr, buttonfsc, buttonjdp, buttonphr, buttontcr;
    List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
    Calendar cal = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = (LineChart) findViewById(R.id.lineChart);

        //Entries for worksite 1 (EBR)
        Entry c1e1 = new Entry(0f, 0f);
        valsComp1.add(c1e1);

        //valsComp1.add(c1e4);
        //Entries for worksite 2 (ETR)
        Entry c2e1 = new Entry(0f, 0f);
        valsComp2.add(c2e1);

        //Entries for worksite 3 (FSC)
        Entry c3e1 = new Entry(0f, 1260.43f);
        valsComp3.add(c3e1);

        //Entries for worksite 4 (JDP)
        Entry c4e1 = new Entry(0f, 0f);
        valsComp4.add(c4e1);

        //Entnries for worksite 5 (PHR)
        Entry c5e2 = new Entry(1f, 0f);
        valsComp5.add(c5e2);

        //Entries for worksite 6 (TCR)
        Entry c6e1 = new Entry(0f, 0f);
        valsComp6.add(c6e1);

        //Entries for worksite 7 (MLS)
        Entry c7e1 = new Entry(0f, 0f);
        valsComp7.add(c7e1);

        XAxis xaxis = lineChart.getXAxis();
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yaxis = lineChart.getAxis(YAxis.AxisDependency.LEFT);
        xaxis.setAxisMinimum(0);
        xaxis.setAxisMaximum(365);
        yaxis.setAxisMinimum(0);
        yaxis.setAxisMaximum(1800);

        //Legend setup
        setComp1 = new LineDataSet(valsComp1, "EBR");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(Color.YELLOW);
        setComp1.setDrawCircles(false);
        addListenerebr();


        setComp2 = new LineDataSet(valsComp2, "ETR");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setColor(Color.MAGENTA);
        setComp2.setDrawCircles(false);
        addListeneretr();

        setComp3 = new LineDataSet(valsComp3, "FSC");
        setComp3.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp3.setColor(Color.GREEN);
        setComp3.setDrawCircles(false);
        addListenerfsc();

        setComp4 = new LineDataSet(valsComp4, "JDP");
        setComp4.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp4.setColor(Color.BLUE);
        setComp4.setDrawCircles(false);
        addListenerjdp();

        setComp5 = new LineDataSet(valsComp5, "PHR");
        setComp5.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp5.setColor(Color.BLACK);
        setComp5.setDrawCircles(false);
        addListenerphr();

        setComp6 = new LineDataSet(valsComp6, "TCR");
        setComp6.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp6.setColor(Color.RED);
        setComp6.setDrawCircles(false);
        setComp6.setVisible(true);
        addListenertcr();

        setComp7 = new LineDataSet(valsComp7, "MLS");
        setComp7.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp7.setColor(Color.MAGENTA);
        setComp7.setDrawCircles(false);
        setComp7.setVisible(true);
        addListenermls();

        YAxis rightYAxis = lineChart.getAxisRight();
        rightYAxis.setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        dataSets.add(setComp1);
        dataSets.add(setComp2);
        dataSets.add(setComp3);
        dataSets.add(setComp4);
        dataSets.add(setComp5);
        dataSets.add(setComp6);
        dataSets.add(setComp7);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        date_generator();
        lineChart.invalidate();
        buttonLoad = (Button)findViewById(R.id.action);
        buttonLoad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                info.clear();
                info2.clear();
                info3.clear();
                info4.clear();
                info5.clear();
                info6.clear();
                info7.clear();
                dataSets.clear();
                setComp1.clear();
                datasetcreator_ebr();


                setComp2.clear();
                datasetcreator_etr();


                setComp3.clear();
                datasetcreator_fsc();


                setComp4.clear();
                datasetcreator_jdp();


                setComp5.clear();
                datasetcreator_phr();


                setComp6.clear();
                datasetcreator_tcr();


                setComp7.clear();
                datasetcreator_mls();



            }});





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
            String [] date = new String[jsonArray.length()];

            //looping through all the elements in json array
            for (int i = 0; i<MMBTU.length; i++) {

                //getting json object from the json array
                JSONObject obj = jsonArray.getJSONObject(i);

                    //getting the name from the json object and putting it inside string array
                    MMBTU[i] = obj.getString("30_Day_MA_MMBTU");
                    //date[i]= obj.getString("Date");


            }
            for(int i =0; i<MMBTU.length; i++){


                if(MMBTU[i]==null || MMBTU[i]=="null"){

                    MMBTU[i] = MMBTU[i-1];
                    info.add(MMBTU[i]);
                }else{
                    info.add(MMBTU[i]);
                }

            }

        }

        protected void onPostExecute(String results) {
            if (results != null) {
                {
                    super.onPostExecute(results);

                    try {
                        loadIntoListView(results);
                        Collections.reverse(info);
                        for(int i =0; i<info.size(); i++){

                            valsComp1.add(new Entry(365-(info.size()-i), Float.parseFloat(info.get(i))));
                        }
                        dataSets.add(setComp1);
                        lineChart.invalidate();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


        }
    }
    class LongRunningGetIO2 extends AsyncTask<Void, Void, String> {

        protected String readStream(InputStream in) {
            StringBuilder sb2 = new StringBuilder();

            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String nextLine2 = "";
                while ((nextLine2 = reader.readLine()) != null) {
                    sb2.append(nextLine2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb2.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url2;
            HttpURLConnection urlConnection;
            String text2 = null;

            try {
                url2 = new URL(JSON_STRING2);
                urlConnection = (HttpURLConnection) url2.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream in = urlConnection.getInputStream();
                text2= readStream(in);

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text2;
        }
        //Getting huge String into an Array
        private void loadIntoListView(String json) throws JSONException {
            //creating a json array from the json string
            JSONArray jsonArray2 = new JSONArray(json);

            //creating a string array for listview
            String [] MMBTU2 = new String[jsonArray2.length()];

            //looping through all the elements in json array
            for (int i = 0; i<MMBTU2.length; i++) {

                //getting json object from the json array
                JSONObject obj2 = jsonArray2.getJSONObject(i);

                //getting the name from the json object and putting it inside string array
                MMBTU2[i] = obj2.getString("30_Day_MA_MMBTU");


            }
            for(int i =0; i<MMBTU2.length; i++){
                if(MMBTU2[i]==null || MMBTU2[i]=="null"){
                    MMBTU2[i] = MMBTU2[i-1];
                    info2.add(MMBTU2[i]);
                }else{
                    info2.add(MMBTU2[i]);
                }
            }

        }

        protected void onPostExecute(String results) {
            if (results != null) {
                {
                    super.onPostExecute(results);

                    try {
                        loadIntoListView(results);

                        Collections.reverse(info2);
                        for(int i =0; i<info2.size(); i++){

                            valsComp2.add(new Entry(365-(info2.size()-i), Float.parseFloat(info2.get(i))));
                        }
                        dataSets.add(setComp2);
                        lineChart.invalidate();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


        }
    }
    class LongRunningGetIO3 extends AsyncTask<Void, Void, String> {

        protected String readStream(InputStream in) {
            StringBuilder sb3 = new StringBuilder();

            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String nextLine3 = "";
                while ((nextLine3 = reader.readLine()) != null) {
                    sb3.append(nextLine3);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb3.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url3;
            HttpURLConnection urlConnection;
            String text3 = null;

            try {
                url3 = new URL(JSON_STRING3);
                urlConnection = (HttpURLConnection) url3.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream in = urlConnection.getInputStream();
                text3= readStream(in);

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text3;
        }
        //Getting huge String into an Array
        private void loadIntoListView(String json) throws JSONException {
            //creating a json array from the json string
            JSONArray jsonArray3 = new JSONArray(json);

            //creating a string array for listview
            String [] MMBTU3 = new String[jsonArray3.length()];

            //looping through all the elements in json array
            for (int i = 0; i<MMBTU3.length; i++) {

                //getting json object from the json array
                JSONObject obj2 = jsonArray3.getJSONObject(i);

                //getting the name from the json object and putting it inside string array
                MMBTU3[i] = obj2.getString("30_Day_MA_MMBTU");


            }
            for(int i =0; i<MMBTU3.length; i++){
                if(MMBTU3[i]==null || MMBTU3[i]=="null"){
                    MMBTU3[i] = MMBTU3[i-1];
                    info3.add(MMBTU3[i]);
                }else{
                    info3.add(MMBTU3[i]);
                }
            }

        }

        protected void onPostExecute(String results) {
            if (results != null) {
                {
                    super.onPostExecute(results);

                    try {
                        loadIntoListView(results);

                        Collections.reverse(info3);
                        for(int i =0; i<info3.size(); i++){

                            valsComp3.add(new Entry(365-(info3.size()-i), Float.parseFloat(info3.get(i))));
                        }
                        dataSets.add(setComp3);
                        lineChart.invalidate();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


        }
    }
    class LongRunningGetIO4 extends AsyncTask<Void, Void, String> {

        protected String readStream(InputStream in) {
            StringBuilder sb2 = new StringBuilder();

            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String nextLine2 = "";
                while ((nextLine2 = reader.readLine()) != null) {
                    sb2.append(nextLine2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb2.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url2;
            HttpURLConnection urlConnection;
            String text2 = null;

            try {
                url2 = new URL(JSON_STRING4);
                urlConnection = (HttpURLConnection) url2.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream in = urlConnection.getInputStream();
                text2= readStream(in);

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text2;
        }
        //Getting huge String into an Array
        private void loadIntoListView(String json) throws JSONException {
            //creating a json array from the json string
            JSONArray jsonArray2 = new JSONArray(json);

            //creating a string array for listview
            String [] MMBTU2 = new String[jsonArray2.length()];

            //looping through all the elements in json array
            for (int i = 0; i<MMBTU2.length; i++) {

                //getting json object from the json array
                JSONObject obj2 = jsonArray2.getJSONObject(i);

                //getting the name from the json object and putting it inside string array
                MMBTU2[i] = obj2.getString("30_Day_MA_MMBTU");


            }
            for(int i =0; i<MMBTU2.length; i++){
                if(MMBTU2[i]==null || MMBTU2[i]=="null"){
                    MMBTU2[i] = MMBTU2[i-1];
                    info4.add(MMBTU2[i]);
                }else{
                    info4.add(MMBTU2[i]);
                }
            }

        }

        protected void onPostExecute(String results) {
            if (results != null) {
                {
                    super.onPostExecute(results);

                    try {
                        loadIntoListView(results);
                        Collections.reverse(info4);
                        for(int i =0; i<info4.size(); i++){

                            valsComp4.add(new Entry(365-(info4.size()-i), Float.parseFloat(info4.get(i))));
                        }
                        dataSets.add(setComp4);
                        lineChart.invalidate();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


        }
    }
    class LongRunningGetIO5 extends AsyncTask<Void, Void, String> {

        protected String readStream(InputStream in) {
            StringBuilder sb2 = new StringBuilder();

            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String nextLine2 = "";
                while ((nextLine2 = reader.readLine()) != null) {
                    sb2.append(nextLine2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb2.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url2;
            HttpURLConnection urlConnection;
            String text2 = null;

            try {
                url2 = new URL(JSON_STRING5);
                urlConnection = (HttpURLConnection) url2.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream in = urlConnection.getInputStream();
                text2= readStream(in);

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text2;
        }
        //Getting huge String into an Array
        private void loadIntoListView(String json) throws JSONException {
            //creating a json array from the json string
            JSONArray jsonArray2 = new JSONArray(json);

            //creating a string array for listview
            String [] MMBTU2 = new String[jsonArray2.length()];

            //looping through all the elements in json array
            for (int i = 0; i<MMBTU2.length; i++) {

                //getting json object from the json array
                JSONObject obj2 = jsonArray2.getJSONObject(i);

                //getting the name from the json object and putting it inside string array
                MMBTU2[i] = obj2.getString("30_Day_MA_MMBTU");


            }
            for(int i =0; i<MMBTU2.length; i++){
                if(MMBTU2[i]==null || MMBTU2[i]=="null"){
                    MMBTU2[i] = MMBTU2[i-1];
                    info5.add(MMBTU2[i]);
                }else{
                    info5.add(MMBTU2[i]);
                }
            }
        }

        protected void onPostExecute(String results) {
            if (results != null) {
                {
                    super.onPostExecute(results);

                    try {
                        loadIntoListView(results);
                        Collections.reverse(info5);
                        for(int i =0; i<info5.size(); i++){

                            valsComp5.add(new Entry(365-(info5.size()-i), Float.parseFloat(info5.get(i))));
                        }
                        dataSets.add(setComp5);
                        lineChart.invalidate();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


        }
    }
    class LongRunningGetIO6 extends AsyncTask<Void, Void, String> {

        protected String readStream(InputStream in) {
            StringBuilder sb2 = new StringBuilder();

            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String nextLine2 = "";
                while ((nextLine2 = reader.readLine()) != null) {
                    sb2.append(nextLine2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb2.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url2;
            HttpURLConnection urlConnection;
            String text2 = null;

            try {
                url2 = new URL(JSON_STRING6);
                urlConnection = (HttpURLConnection) url2.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream in = urlConnection.getInputStream();
                text2= readStream(in);

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text2;
        }
        //Getting huge String into an Array
        private void loadIntoListView(String json) throws JSONException {
            //creating a json array from the json string
            JSONArray jsonArray2 = new JSONArray(json);

            //creating a string array for listview
            String [] MMBTU2 = new String[jsonArray2.length()];

            //looping through all the elements in json array
            for (int i = 0; i<MMBTU2.length; i++) {

                //getting json object from the json array
                JSONObject obj2 = jsonArray2.getJSONObject(i);

                //getting the name from the json object and putting it inside string array
                MMBTU2[i] = obj2.getString("30_Day_MA_MMBTU");
            }
            for(int i =0; i<MMBTU2.length; i++){
                if(MMBTU2[i]==null || MMBTU2[i]=="null"){
                    MMBTU2[i] = MMBTU2[i-1];
                    info6.add(MMBTU2[i]);
                }else{
                    info6.add(MMBTU2[i]);
                }
            }
        }

        protected void onPostExecute(String results) {
            if (results != null) {
                {
                    super.onPostExecute(results);

                    try {
                        loadIntoListView(results);
                        Collections.reverse(info6);
                        for(int i =0; i<info6.size(); i++){

                            valsComp6.add(new Entry(365-(info6.size()-i), Float.parseFloat(info6.get(i))));
                        }
                        dataSets.add(setComp6);
                        lineChart.invalidate();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


        }
    }
    class LongRunningGetIO7 extends AsyncTask<Void, Void, String> {

        protected String readStream(InputStream in) {
            StringBuilder sb2 = new StringBuilder();

            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String nextLine2 = "";
                while ((nextLine2 = reader.readLine()) != null) {
                    sb2.append(nextLine2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb2.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url2;
            HttpURLConnection urlConnection;
            String text2 = null;

            try {
                url2 = new URL(JSON_STRING7);
                urlConnection = (HttpURLConnection) url2.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream in = urlConnection.getInputStream();
                text2= readStream(in);

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text2;
        }
        //Getting huge String into an Array
        private void loadIntoListView(String json) throws JSONException {
            //creating a json array from the json string
            JSONArray jsonArray2 = new JSONArray(json);

            //creating a string array for listview
            String [] MMBTU2 = new String[jsonArray2.length()];

            //looping through all the elements in json array
            for (int i = 0; i<MMBTU2.length; i++) {

                //getting json object from the json array
                JSONObject obj2 = jsonArray2.getJSONObject(i);

                //getting the name from the json object and putting it inside string array
                MMBTU2[i] = obj2.getString("30_Day_MA_MMBTU");

            }
            for(int i =0; i<MMBTU2.length; i++){
                if(MMBTU2[i]==null || MMBTU2[i]=="null"){
                    MMBTU2[i] = MMBTU2[i-1];
                    info7.add(MMBTU2[i]);
                }else{
                    info7.add(MMBTU2[i]);
                }
            }
        }

        protected void onPostExecute(String results) {
            if (results != null) {
                {
                    super.onPostExecute(results);

                    try {
                        loadIntoListView(results);
                        Collections.reverse(info7);
                        for(int i =0; i<info7.size(); i++){

                            valsComp7.add(new Entry(365-(info7.size()-i), Float.parseFloat(info7.get(i))));
                        }
                        dataSets.add(setComp7);
                        lineChart.invalidate();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


        }
    }

    public void date_generator(){
        XAxis xAxis = lineChart.getXAxis();
        maindate.add(dateFormat.format(cal.getTime()));
        for(int i = 0; i<=363; i++){
            int a = -1;
            cal.add(Calendar.DATE, a);
            maindate.add(dateFormat.format(cal.getTime()));
            a--;
        }
        Collections.reverse(maindate);
        cal.add(Calendar.DATE, -1);
        maindate.add(dateFormat.format(cal.getTime()));

        Formatter class1 = new Formatter();
        class1.getStringDatesForXAxis(maindate);
        for(int i = 0; i<=365; i++){
            class1.getFormattedValue(i, xAxis);

        }
        xAxis.setValueFormatter(class1);

    }

    public void datasetcreator_ebr(){

        new LongRunningGetIO().execute();




    }
    public void datasetcreator_etr(){

        new LongRunningGetIO2().execute();

    }
    public void datasetcreator_fsc(){
        new LongRunningGetIO3().execute();

    }
    public void datasetcreator_jdp(){
        new LongRunningGetIO4().execute();

    }
    public void datasetcreator_phr(){
        new LongRunningGetIO5().execute();

    }
    public void datasetcreator_tcr(){
        new LongRunningGetIO6().execute();

    }
    public void datasetcreator_mls(){
        new LongRunningGetIO7().execute();

    }




    public void addListenerebr(){

        buttonebr = (CheckBox) findViewById(R.id.ebr);

        buttonebr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()){
                    setComp1.setVisible(false);
                    lineChart.invalidate();



                }else
                    setComp1.setVisible(true);
                lineChart.invalidate();


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
                    lineChart.invalidate();
                }else
                    setComp2.setVisible(true);
                lineChart.invalidate();

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
                    lineChart.invalidate();
                }else
                    setComp3.setVisible(true);
                lineChart.invalidate();

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
                    lineChart.invalidate();
                }else
                    setComp4.setVisible(true);
                lineChart.invalidate();

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
                    lineChart.invalidate();
                }else
                    setComp5.setVisible(true);
                lineChart.invalidate();

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
                    lineChart.invalidate();
                }else
                    setComp6.setVisible(true);
                lineChart.invalidate();

            }
        });



    }
    public void addListenermls(){

        buttontcr = (CheckBox) findViewById(R.id.mls);

        buttontcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()){
                    setComp7.setVisible(false);
                    lineChart.invalidate();
                }else
                    setComp7.setVisible(true);
                lineChart.invalidate();

            }
        });



    }


}