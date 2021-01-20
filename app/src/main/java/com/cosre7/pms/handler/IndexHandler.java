package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.util.Prompt;

public class IndexHandler {

  static class Index {
    Date date;
    double weight;
    double bust;
    double waist;
    double thigh;
    double calf;
  }
  static final int LENGTH = 100;
  static Index[] indexes = new Index[LENGTH]; 
  static int size = 0;

  public static void add() {
    System.out.println("[신체지수 작성]");

    Index i = new Index();

    i.date = Prompt.inputDate("날짜 > ");
    i.weight = Prompt.inputDouble("몸무게 > ");
    i.bust = Prompt.inputDouble("가슴 둘레 > ");
    i.waist = Prompt.inputDouble("허리 둘레 > ");
    i.thigh = Prompt.inputDouble("허벅지 둘레 > ");
    i.calf = Prompt.inputDouble("종아리 둘레 > ");

    indexes[size++] = i;
  }

  public static void list() {
    System.out.println("[신체지수 목록]");

    for (int j = 0; j < size; j++) {
      Index i = indexes[j];
      System.out.printf("날짜: %s\n몸무게: %.2f\n가슴 둘레: %.2f\n허리 둘레: %.2f\n허벅지 둘레: %.2f\n종아리 둘레: %.2f\n", 
          i.date, i.weight, i.bust, i.waist, i.thigh, i.calf);
    }
  }

}
