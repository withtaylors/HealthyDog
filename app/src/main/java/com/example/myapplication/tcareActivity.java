package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class tcareActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> tArray; //측정 결과 받아온 arraylist
    ArrayList<String> tDayArray; //측정 날짜 받아온 arraylist

    ArrayList<Float> tjsonList = new ArrayList<>(); // 측정 값 ArrayList 선언
    ArrayList<String> tlabelList = new ArrayList<>(); // 측정 날짜 ArrayList 선언

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcare);

        tArray = getStringArray("tjsonArray"); //측정 결과 받아온 arraylist
        tDayArray = getStringArray("tDayjsonArray"); //측정 날짜 받아온 arraylist

        init(tjsonList, tlabelList, tArray, tDayArray); //ArrayList 넣기

        lineChartGraph(tlabelList, tjsonList);
    }

    public void init(ArrayList<Float> list, ArrayList<String> Dlist, ArrayList<String> array, ArrayList<String> Darray){

        //결과 값 넣는 코드 (측정 결과)
        for(int i = 0; i <array.size(); i++){
            list.add(Float.parseFloat(array.get(i)));
        }

        //축 값 넣는 중 (측정 날짜)
        for(int i = 0; i <Darray.size(); i++){
            Dlist.add(Darray.get(i));
        }
    }

    // LineChart 메소드 (오른쪽)
    private void lineChartGraph(ArrayList labelList, ArrayList valList){

        LineChart lineChart = (LineChart) findViewById(R.id.teeth_chart);

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0; i < valList.size();i++){
            entries.add(new Entry((Float) valList.get(i), i));
        }

        LineDataSet depenses = new LineDataSet(entries, "치석률");
        depenses.setAxisDependency(YAxis.AxisDependency.LEFT);
        depenses.setLineWidth(3);
        depenses.setCircleRadius(6);
        depenses.setDrawCircleHole(true);
        depenses.setDrawCircles(true);
        depenses.setDrawHorizontalHighlightIndicator(false);
        depenses.setDrawHighlightIndicators(false);

        for(int i=0; i < valList.size();i++) { //색상 채워가기
            if ((Float) valList.get(i) >= 50) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.gradient_background);
                depenses.setFillDrawable(drawable);
            }
        }

        depenses.setColor(Color.rgb(166, 208, 227));
        depenses.setCircleColor(Color.rgb(166, 208, 227));

        ArrayList<String> labels = new ArrayList<String>();

        for(int i=0; i < labelList.size(); i++){
            labels.add((String) labelList.get(i));
        }

        //y축 설정
        YAxis leftYAxis = lineChart.getAxisLeft();
        leftYAxis.setAxisMaxValue(100);
        leftYAxis.setAxisMinValue(0);
        leftYAxis.setDrawAxisLine(false);
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        //x축 설정
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //description "" 공란처리
        lineChart.setDescription(null);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add((ILineDataSet)depenses);

        LineData data = new LineData(labels,dataSets); // 라이브러리 v3.x 사용하면 에러 발생함

        //depenses.setColors(ColorTemplate.COLORFUL_COLORS); //
        depenses.setDrawCubic(true); //선 둥글게 만들기
        depenses.setDrawFilled(true); //그래프 밑부분 색칠

        lineChart.setData(data);
        lineChart.animateXY(1000,1000);
        lineChart.invalidate();
    }

    //SP에 저장된 jsonArray를 arraylist로 변경하는 함수
    private ArrayList getStringArray(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("t_total_result", Context.MODE_PRIVATE );
        String json = sharedPreferences.getString(key, ""); //가져올 key값
        ArrayList list = new ArrayList();

        if (json != null) { //key값에 값이 저장되어 있다면
            try {
                JSONArray a = new JSONArray(json); //for문 돌면서 list에 저장 ->Arraylist 만들기

                for (int i = 0; i < a.length(); i++) {
                    String d = a.optString(i);
                    list.add(d);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
