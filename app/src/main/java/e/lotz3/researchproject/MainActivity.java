package e.lotz3.researchproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    LineChart lineChart;//creates linechart


    //creates ArrayList which holds data for each dataset
    List<Entry> valsComp1 = new ArrayList<Entry>();
    List<Entry> valsComp2 = new ArrayList<Entry>();
    List<Entry> valsComp3 = new ArrayList<Entry>();
    List<Entry> valsComp4 = new ArrayList<Entry>();
    List<Entry> valsComp5 = new ArrayList<Entry>();
    List<Entry> valsComp6 = new ArrayList<Entry>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = (LineChart)findViewById(R.id.lineChart);

        //Entries for worksite 1 (EBR)
        Entry c1e1 = new Entry(0f, 875.86f);
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(1f, 1252.61f);
        valsComp1.add(c1e2);
        Entry c1e3 = new Entry(2f, 1305.12f);
        valsComp1.add(c1e3);
        Entry c1e4 = new Entry(3f, 1305.12f);
        valsComp1.add(c1e4);



        //Entries for worksite 2 (ETR)

        Entry c2e1 = new Entry(0f, 894.87f);
        valsComp2.add(c2e1);
        Entry c2e2 = new Entry(1f, 1028.67f);
        valsComp2.add(c2e2);
        Entry c2e3 = new Entry(2f, 947.4f);
        valsComp2.add(c2e3);
        Entry c2e4 = new Entry(3f, 947.4f);
        valsComp2.add(c2e4);
        Entry c2e5 = new Entry(4f, 939.13f);
        valsComp2.add(c2e5);
        Entry c2e6 = new Entry(5f,939.03f);
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
        LineDataSet setComp1 = new LineDataSet(valsComp1, "EBR");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(Color.YELLOW);
        setComp1.setDrawCircles(true);
        LineDataSet setComp2 = new LineDataSet(valsComp2, "ETR");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setColor(Color.MAGENTA);
        setComp2.setDrawCircles(true);
        LineDataSet setComp3 = new LineDataSet(valsComp3, "FSC");
        setComp3.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp3.setColor(Color.GREEN);
        setComp3.setDrawCircles(true);
        LineDataSet setComp4 = new LineDataSet(valsComp4, "JDP");
        setComp4.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp4.setColor(Color.BLUE);
        setComp4.setDrawCircles(true);
        LineDataSet setComp5 = new LineDataSet(valsComp5, "PHR");
        setComp5.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp5.setColor(Color.BLACK);
        setComp5.setDrawCircles(true);
        LineDataSet setComp6 = new LineDataSet(valsComp6, "TCR");
        setComp6.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp6.setColor(Color.RED);





        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);
        dataSets.add(setComp3);
        dataSets.add(setComp4);
        dataSets.add(setComp5);
        dataSets.add(setComp6);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();


















    }

}
