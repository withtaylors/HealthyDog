package com.example.myapplication;

import java.util.ArrayList;

public class VO {
    static private int CheckON; //선택된 눈의 값. 왼쪽 눈 1, 오른쪽 눈 2, 양쪽 눈 (왼쪽먼저) 3, 양쪽 눈 (오른쪽) 4
    static private String result_info = ""; //눈 혼탁률이 높을 경우 안내 문구 전달하기
    static private int list; //어디 페이지에서 시작할지 전해주는 변수
    static private ArrayList<String> rArray = new ArrayList<>(); //결과 값을 저장해 나갈 ArrayList
    static private ArrayList<String> lArray = new ArrayList<>(); //결과 값을 저장해 나갈 ArrayList
    static private ArrayList<String> tArray = new ArrayList<>(); //결과 값을 저장해 나갈 ArrayList


    public static int getCheckON() {
        return CheckON;
    }

    public static void setCheckON(int checkON) {
        CheckON = checkON;
    }

    public static String getResult_info() {
        return result_info;
    }

    public static void setResult_info(String result_info) {
        VO.result_info = result_info;
    }

    public static int getList() {
        return list;
    }

    public static void setList(int list) {
        VO.list = list;
    }

    public static ArrayList<String> getrArray() {
        return rArray;
    }

    public static void setrArray(ArrayList<String> rArray) {
        VO.rArray = rArray;
    }

    public static ArrayList<String> getlArray() {
        return lArray;
    }

    public static void setlArray(ArrayList<String> lArray) {
        VO.lArray = lArray;
    }

    public static ArrayList<String> gettArray() {
        return tArray;
    }

    public static void settArray(ArrayList<String> tArray) {
        VO.tArray = tArray;
    }
}