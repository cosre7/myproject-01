package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Index;
import com.cosre7.util.Prompt;

public class IndexHandler {

  static final int LENGTH = 100;
  Index[] indexes = new Index[LENGTH]; 
  int size = 0;

  public void add() {
    System.out.println("[신체지수 작성]");

    Index i = new Index();

    i.date = Prompt.inputDate("날짜 > ");
    i.weight = Prompt.inputDouble("몸무게 > ");
    i.bust = Prompt.inputDouble("가슴 둘레 > ");
    i.waist = Prompt.inputDouble("허리 둘레 > ");
    i.thigh = Prompt.inputDouble("허벅지 둘레 > ");
    i.calf = Prompt.inputDouble("종아리 둘레 > ");

    this.indexes[this.size++] = i;
  }

  public void list() {
    System.out.println("[신체지수 목록]");

    for (int j = 0; j < this.size; j++) {
      Index i = this.indexes[j];
      System.out.printf("날짜: %s\n몸무게: %.2f\n가슴 둘레: %.2f\n허리 둘레: %.2f\n허벅지 둘레: %.2f\n종아리 둘레: %.2f\n", 
          i.date, i.weight, i.bust, i.waist, i.thigh, i.calf);
    }
  }

}
