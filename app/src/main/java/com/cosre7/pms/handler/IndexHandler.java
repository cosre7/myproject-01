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

    i.no = Prompt.inputInt("번호 > ");
    i.name = Prompt.inputString("이름 > ");
    i.date = Prompt.inputDate("날짜 > ");
    i.height = Prompt.inputDouble("키(cm) > ");
    i.weight = Prompt.inputDouble("몸무게(kg) > ");
    i.bust = Prompt.inputDouble("가슴 둘레(cm) > ");
    i.waist = Prompt.inputDouble("허리 둘레(cm) > ");
    i.thigh = Prompt.inputDouble("허벅지 둘레(cm) > ");
    i.calf = Prompt.inputDouble("종아리 둘레(cm) > ");

    this.indexes[this.size++] = i;
  }

  public void list() {
    System.out.println("[신체지수 목록]");

    for (int j = 0; j < this.size; j++) {
      Index i = this.indexes[j];
      System.out.printf("번호: %d\n이름: %s\n날짜: %s\n키: %.2f (cm)\n몸무게: %.2f (kg)\n가슴 둘레: %.2f (cm)\n허리 둘레: %.2f (cm)\n허벅지 둘레: %.2f (cm)\n종아리 둘레: %.2f (cm)\n", 
          i.no, 
          i.name,
          i.date, 
          i.height, 
          i.weight, 
          i.bust, 
          i.waist, 
          i.thigh, 
          i.calf);
      System.out.println();
    }
  }

}
