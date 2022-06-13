package com.example.myapplication;

public class VO {
    static private int CheckON; //선택된 눈의 값. 왼쪽 눈 1, 오른쪽 눈 2, 양쪽 눈 (왼쪽먼저) 3, 양쪽 눈 (오른쪽) 4
    static private String result_info = ""; //눈 혼탁률이 높을 경우 안내 문구 전달하기
    static private int list; //어디 페이지에서 시작할지 전해주는 변수

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
}
