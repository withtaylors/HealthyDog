package com.example.myapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Care extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_care, container, false);

        LineChart teeth_chart = view.findViewById(R.id.teeth_chart);
        LineChart eye_chart = view.findViewById(R.id.eye_chart);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("total_result", MODE_PRIVATE );
        //세션을 위한 세팅
        String day_l = sharedPreferences.getString("day_l", "");
        String l_result = sharedPreferences.getString("l_result", "");
//
//        float f=Float.parseFloat(day_l);
        int i = (int) Float.parseFloat(l_result);
        System.out.print("-----------------"+i);
//        int d = (int) Float.parseFloat(day_l);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry( 100, i));


        System.out.print("데이터 출력"+l_result);

        LineDataSet lineDataSet1 = new LineDataSet(entries, "왼쪽눈 측정값"); // 라인추가
        ArrayList<String> dataSets = new ArrayList<>();
        dataSets.add(day_l);
        System.out.print("날짜출력"+day_l);

        LineData data = new LineData(dataSets, lineDataSet1);

        System.out.print("dataSets 출력"+dataSets);
        System.out.print("lineDataSet1 출력"+lineDataSet1);

        YAxis leftYAxis = eye_chart.getAxisLeft();
//        leftYAxis.setAxisMaxValue(0.2f);
//        leftYAxis.setAxisMinValue(0);

        YAxis rightYAxis = eye_chart.getAxisRight();
//        rightYAxis.setAxisMaxValue(0.2f);
//        rightYAxis.setAxisMinValue(0);

        eye_chart.setData(data);
        eye_chart.animateY(1000); // 데이타값 Y축으로 올라가는 시간
        eye_chart.animateX(1500); // 이건 X축

        return view;
    }
}