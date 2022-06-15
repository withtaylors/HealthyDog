package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Care extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_care, container, false);
//
////        LineChart teeth_chart = view.findViewById(R.id.teeth_chart);
////        LineChart eye_chart = view.findViewById(R.id.eye_chart);
////        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("total_result", MODE_PRIVATE );
////        //세션을 위한 세팅
////        String day_l = sharedPreferences.getString("day_l", "");
////        String l_result = sharedPreferences.getString("l_result", "");
////
////        eye_chart.setDragEnabled(true);
////        eye_chart.setScaleEnabled(false);
//
////        eye_chart.setDrawGridBackground(false); //격자 구조
////
////        entries.clear();
////        xVals.clear();
////        int i = 0;
////        while (cursor.()) {
////            //string -> date 변환 (문자열을 파싱하려면 문자열 형태와 같은 DateTime 생성해줘야돼
////            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            Date dt = new Date();
////            try {
////                dt = simpleDateFormat.parse(cursor);
////            } catch (ParseException e) {
////                e.printStackTrace();
////            }
////
////
////            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
////
////            String heartFormat = sdf.format(dt);
////
////            entries.add(new Entry(i++, Float.parseFloat(cursor); // 문자형을 실수로 변환 y축
////            xVals.add(heartFormat); // 하나씩 받아와서 넣어줌 (X축 시간으로 나온게 이거 때문)
////
////
//
////
////        float f=Float.parseFloat(day_l);
//
////        int i = (int) Float.parseFloat(l_result);
////        System.out.print("-----------------"+i);
////        int d = (int) Float.parseFloat(day_l);
////
////        ArrayList<Entry> entries = new ArrayList<>();
////        entries.add(new Entry( 0,(int) (Float.parseFloat(l_result))));
////
////        System.out.print("데이터 출력"+entries);
////
////        LineDataSet dataSet = new LineDataSet(entries, "왼쪽눈 측정값"); // 라인추가
////        ArrayList<String> dataSets = new ArrayList<>();
////        dataSets.add(day_l);
////        System.out.print("날짜출력"+day_l);
////
////        // 선 색
////        dataSet.setColor(Color.rgb(251,99,118));
////        // 데이터 수치 텍스트 색
////        dataSet.setValueTextColor(Color.rgb(251,99,118));
////        // 데이터 수치 텍스트 사이즈
////        dataSet.setValueTextSize(10);
////// 선 굵기
////        dataSet.setLineWidth(2);
////// 데이터 표시 원 색
////        dataSet.setCircleColor(Color.BLACK);
////// 데이터 표시 원 둘레 굵기
////// dataSet.setCircleRadius(2);
////
////
////        LineData data = new LineData(dataSets, dataSet);
////        eye_chart.setData(data);
////
////        System.out.print("dataSets 출력"+dataSets);
////        System.out.print("lineDataSet1 출력"+dataSet);
////
//// //       YAxis leftYAxis = eye_chart.getAxisLeft();
//////        leftYAxis.setAxisMaxValue(0.2f);
//////        leftYAxis.setAxisMinValue(0);
////
////        // X축 레퍼런스 가져오기
////        XAxis xAxis;
////        xAxis = eye_chart.getXAxis();
////// X축을 그래프 아래로 위치하기
////        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
////// 레이블 텍스트 사이즈
////        xAxis.setTextSize(10);
////// 레이블 텍스트 색
////        xAxis.setTextColor(Color.BLACK);
////// 축 색
////        xAxis.setAxisLineColor(Color.BLACK);
////// 그래프 뒷 배경의 그리드 표시하지 않기
////        xAxis.setDrawAxisLine(false);
////        xAxis.setDrawGridLines(false);
////
//////        YAxis rightYAxis = eye_chart.getAxisRight();
//////        rightYAxis.setAxisMaxValue(0.2f);
//////        rightYAxis.setAxisMinValue(0);
////
////        YAxis yAxis;
////        yAxis = eye_chart.getAxisLeft();
////        eye_chart.getAxisRight().setEnabled(false);
////        xAxis.setTextSize(10);
////        yAxis.setTextColor(Color.BLACK);
////        yAxis.setAxisLineColor(Color.BLACK);
////        yAxis.setDrawAxisLine(false);
////        yAxis.setDrawGridLines(false);
////// yAxis.setSpaceMax(0.2f);
////// yAxis.setSpaceMin(0.2f);
////
////        eye_chart.setData(data);
////        eye_chart.animateY(1000); // 데이타값 Y축으로 올라가는 시간
////        eye_chart.animateX(1500); // 이건 X축
////
////        // 배경 색 (Adobe에서 색상조합표를 찾아보고 RGB 값을 따 왔음)
////        eye_chart.setBackgroundColor(Color.rgb(254,247,235));
////// description 표시하지 않기
////        eye_chart.setEnabled(false);
////// 그래프 터치 가능
////        eye_chart.setTouchEnabled(true);
////// 확대 불가능
////        eye_chart.setScaleEnabled(false);
////// pinch zoom 가능 (손가락으로 확대축소하는거)
////        eye_chart.setPinchZoom(true);
////// 최대 x좌표 기준으로 몇개를 보여줄지 (최소값, 최대값)
////        eye_chart.setVisibleXRange(5, 5);
//
////        LineChart eye_chart = view.findViewById(R.id.eye_chart);
////        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("total_result", MODE_PRIVATE );
////
////        ArrayList<Entry> entries = new ArrayList<>();
////        LineDataSet dataset = new LineDataSet(entries, "왼쪽눈 측정값"); // 라인추가
////        ArrayList<String> labels = new ArrayList<>();
////        LineData data = new LineData(labels, dataset);
////
////        //배열 선언
////        String[] day_l = new String[1];
////        float[] day_lf = new float[1];
////        String[] l_result = new String[1];
////        float[] l_resultf = new float[1];
////
////        for(int i = 0; i < day_l.length; i ++){
////            day_l[i] = sharedPreferences.getString("day_l", "");
////            l_result[i] = sharedPreferences.getString("l_result", "");
////            if(l_result[i] == ""){
////                l_result[i] = "0";
////            }
////            l_resultf[i] = Float.parseFloat(l_result[i]);
////            System.out.print("날짜출력"+day_l[i]);
////            System.out.print("\n =====결과들출력"+l_resultf[i]);
//////            entries.add(new Entry(l_resultf[i], i);
////            entries.add(new Entry(i++, (int)l_resultf[i])); // 문자형을 실수로 변환 y축
////            labels.add(day_l[i]);
////            System.out.print("------출력"+labels);
////            System.out.print("------출력2"+entries);
//
////        }
////
////        //측정 평균값 라인
////        dataset.setColors(ColorTemplate.COLORFUL_COLORS); // 선들 각각 색상 자동 설정
////        dataset.setDrawCubic(true); //선 둥글게 만들기
////        dataset.setValueTextSize(10f); //값 텍스트 크기
////        dataset.setLineWidth(1); //줄 두께
////        dataset.setFillAlpha(65); //투명도 채우기 65
////
////        XAxis xAxis = eye_chart.getXAxis();
////        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
////
////        YAxis leftYAxis = eye_chart.getAxisLeft();
////        leftYAxis.setAxisMaxValue(100);
////        leftYAxis.setAxisMinValue(0);
////
////        eye_chart.setDragEnabled(true);
////        eye_chart.setScaleEnabled(false);
////
////        eye_chart.setDescription("");
////        eye_chart.setData(data);
////        eye_chart.animateX(1500); // 이건 X축
////        eye_chart.animateY(1500); // 이건 X축
////
//
//        LineChart eye_chart = view.findViewById(R.id.eye_chart);
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("total_result", MODE_PRIVATE );
//
//        String l_result = sharedPreferences.getString("l_result", "");
//        String r_result = sharedPreferences.getString("r_result", "");
//
//        ArrayList<Entry> entries = new ArrayList<>();
//        int i_l = (int) Float.parseFloat(l_result);
//        int i_r = (int) Float.parseFloat(l_result);
//        entries.add(new Entry(i_l, 1));
//        entries.add(new Entry(i_r, 2));
//
//        LineDataSet lineDataSet = new LineDataSet(entries, "왼쪽눈 혼탁률");
//        //LineDataSet lineDataSet2 = new LineDataSet(entries, "속성명2");
//
//        ArrayList<String> labels = new ArrayList<>();
//        String day_l = sharedPreferences.getString("day_l", "");
//
//        labels.add(day_l);
//
//        LineData lineData = new LineData(labels, lineDataSet);
//
//        eye_chart.setData(lineData);
//
////        XAxis xAxis = eye_chart.getXAxis();
////        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
////
////        YAxis leftYAxis = eye_chart.getAxisLeft();
////        leftYAxis.setAxisMaxValue(100);
////        leftYAxis.setAxisMinValue(0);
//
//        XAxis xAxis = eye_chart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTextSize(10f);
//        xAxis.setDrawGridLines(false);
//
//        YAxis leftAxis = eye_chart.getAxisLeft();
//        leftAxis.setDrawGridLines(false);
//        YAxis leftYAxis = eye_chart.getAxisLeft();
//        leftYAxis.setAxisMaxValue(100);
//        leftYAxis.setAxisMinValue(0);
//
//        YAxis rightAxis = eye_chart.getAxisRight();
//        rightAxis.setEnabled(false);
//
//
//        eye_chart.setDragEnabled(true);
//        eye_chart.setScaleEnabled(false);
//
//        eye_chart.setDescription("");
//        eye_chart.setDoubleTapToZoomEnabled(false);
//        eye_chart.setDrawGridBackground(false);
//
//        eye_chart.animateX(1500); // 이건 X축
//        eye_chart.animateY(1500); // 이건 X축
//
//        eye_chart.animateY(2000, Easing.EasingOption.EaseInCubic);
//        eye_chart.invalidate();
//
        return view;
//
//
    }
}
