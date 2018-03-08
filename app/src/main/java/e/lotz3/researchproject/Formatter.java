package e.lotz3.researchproject;


import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class Formatter implements IAxisValueFormatter {

    ArrayList<String> tempArray = new ArrayList<>();
    public void getStringDatesForXAxis (ArrayList<String> xlabel){
        tempArray = xlabel;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {



        return tempArray.get(Math.round(value));
    }
}
