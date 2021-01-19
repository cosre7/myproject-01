package com.cosre7.pms;

import java.sql.Date;

public class TrainingHandler {
  static final int LENGTH3 = 100;
  static Date[] date2 = new Date[LENGTH3];
  static String[] list = new String[LENGTH3];
  static int[] body = new int[LENGTH3];
  static int[] training = new int[LENGTH3];
  static int[] intensity = new int[LENGTH3];
  static double[] runTime = new double[LENGTH3];
  static int size3 = 0;

  static void add() {
    System.out.println("[운동일지 작성]");

    date2[size3] = Prompt.inputDate("날짜 > ");
    list[size3] = Prompt.inputString("운동 리스트 > ");
    body[size3] = Prompt.inputInt("종류1\n1: 상체\n2: 하체\n3: 전신\n> ");
    training[size3] = Prompt.inputInt("종류2\n1: 근력\n2: 유산소\n> ");
    runTime[size3] = Prompt.inputDouble("소요시간 > ");
    intensity[size3] = Prompt.inputInt("운동 강도 (1~5) > ");
    size3++;
  }

  static void list() {
    System.out.println("[운동일지 목록]");

    for (int i = 0; i < size3; i++) {
      String bodyLabel = null;
      switch (body[i]) {
        case 1:
          bodyLabel = "상체 운동";
          break;
        case 2:
          bodyLabel = "하체 운동";
          break;
        default :
          bodyLabel = "전신 운동";
      }
      String trainingLabel = null;
      switch (training[i]) {
        case 1:
          trainingLabel = "근력 운동";
          break;
        case 2:
          trainingLabel = "유산소 운동";
          break;
      }
      String intensityLabel = null;
      switch (intensity[i]) {
        case 1:
          intensityLabel = "껌이에요";
          break;
        case 2:
          intensityLabel = "한 듯 안한 듯";
          break;
        case 3:
          intensityLabel = "적당해요";
          break;
        case 4:
          intensityLabel = "근육통 예상";
          break;
        case 5:
          intensityLabel = "헬이에요";
      }
      System.out.printf("날짜: %s\n운동 목록: %s\n종류1: %s\n종류2: %s\n소요 시간: %f\n운동 강도: %s\n", 
          date2[i], list[i], bodyLabel, trainingLabel, runTime[i], intensityLabel);
    }
  }

}
