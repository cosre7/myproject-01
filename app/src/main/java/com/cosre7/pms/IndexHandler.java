package com.cosre7.pms;

import java.sql.Date;

public class IndexHandler {
  static final int LENGTH2 = 100;
  static Date[] date3 = new Date[LENGTH2];
  static double[] weight = new double[LENGTH2];
  static double[] bust = new double[LENGTH2];
  static double[] waist = new double[LENGTH2];
  static double[] thigh = new double[LENGTH2];
  static double[] calf = new double[LENGTH2];
  static int size2 = 0;

  static void add() {
    System.out.println("[신체지수 작성]");

    date3[size2] = Prompt.inputDate("날짜 > ");
    weight[size2] = Prompt.inputDouble("몸무게 > ");
    bust[size2] = Prompt.inputDouble("가슴 둘레 > ");
    waist[size2] = Prompt.inputDouble("허리 둘레 > ");
    thigh[size2] = Prompt.inputDouble("허벅지 둘레 > ");
    calf[size2] = Prompt.inputDouble("종아리 둘레 > ");

    size2++;
  }

  static void list() {
    System.out.println("[신체지수 목록]");

    for (int i = 0; i < size2; i++) {
      System.out.printf("날짜: %s\n몸무게: %.2f\n가슴 둘레: %.2f\n허리 둘레: %.2f\n허벅지 둘레: %.2f\n종아리 둘레: %.2f\n", 
          date3[i], weight[i], bust[i], waist[i], thigh[i], calf[i]);
    }
  }

}
