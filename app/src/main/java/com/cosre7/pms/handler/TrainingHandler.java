package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.util.Prompt;

public class TrainingHandler {

  static class Training {
    Date date;
    String list;
    int body;
    int training;
    int intensity;
    double runTime;
  }
  static final int LENGTH = 100;
  static Training[] trainings = new Training[LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("[운동일지 작성]");

    Training t = new Training();

    t.date = Prompt.inputDate("날짜 > ");
    while (true) {
      t.list = Prompt.inputString("운동 리스트(취소: 빈 문자열) > ");
      if (t.list.length() == 0) {
        System.out.println("운동일지 작성을 종료합니다");
        return;
      }
      break;
    }

    t.body = Prompt.inputInt("종류1\n1: 상체\n2: 하체\n3: 전신\n> ");
    t.training = Prompt.inputInt("종류2\n1: 근력\n2: 유산소\n> ");
    t.runTime = Prompt.inputDouble("소요시간 > ");
    t.intensity = Prompt.inputInt("운동 강도 (1~5) > ");

    trainings[size++] = t;
  }

  public static void list() {
    System.out.println("[운동일지 목록]");

    for (int i = 0; i < size; i++) {
      Training t = trainings[i];
      String bodyLabel = null;
      switch (t.body) {
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
      switch (t.training) {
        case 1:
          trainingLabel = "근력 운동";
          break;
        case 2:
          trainingLabel = "유산소 운동";
          break;
      }
      String intensityLabel = null;
      switch (t.intensity) {
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
      System.out.printf("날짜: %s\n운동 목록: %s\n종류1: %s\n종류2: %s\n소요 시간: %.2f\n운동 강도: %s\n", 
          t.date, t.list, bodyLabel, trainingLabel, t.runTime, intensityLabel);
    }
  }

}
