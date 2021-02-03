package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Index;
import com.cosre7.util.Prompt;

public class IndexHandler {

  static final int LENGTH = 100;

  MemberHandler memberList;

  Index[] indexes = new Index[LENGTH]; 
  int size = 0;

  public IndexHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }

  public void add() {
    System.out.println("[신체지수 작성]");

    Index i = new Index();

    i.no = Prompt.inputInt("번호 > ");

    while (true) {
      String name = Prompt.inputString("이름(취소: 빈 문자열) > ");
      if (name.length() == 0) {
        System.out.println("신체지수 작성을 취소합니다.");
        return;
      }
      if (this.memberList.exist(name)) {
        i.name = name;
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }

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

  public void detail() {
    System.out.println("[신체지수 상세보기");

    int no = Prompt.inputInt("번호 > ");

    Index index = findByNo(no);
    if (index == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    // 이름, 날짜, 키, 몸무게, 가슴 둘레, 허리 둘레, 허벅지 둘레, 종아리 둘레
    System.out.printf("[%s] %s \n", index.date, index.name);
    System.out.printf("-- %s --", index.date);
    System.out.printf("키: %.2f cm\n", index.height);
    System.out.printf("몸무게: %.2f kg\n", index.waist);
    System.out.printf("가슴둘레: %.2f cm\n", index.bust);
    System.out.printf("허리둘레: %.2f cm\n", index.waist);
    System.out.printf("허벅지둘레: %.2f cm\n", index.thigh);
    System.out.printf("종아리둘레: %.2f cm\n", index.calf);
  }

  public void update() {
    System.out.println("[신체지수 변경");

    int no = Prompt.inputInt("번호 > ");

    Index index = findByNo(no);
    if (index == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    String name = Prompt.inputString(String.format("이름(%s) > ", index.name));
    Date date = Prompt.inputDate(String.format("날짜(%s) > ", index.date));
    double height = Prompt.inputDouble(String.format("키(%.2f) > ", index.height));
    double weight = Prompt.inputDouble(String.format("몸무게(%.2f) > ", index.weight));
    double bust = Prompt.inputDouble(String.format("가슴둘레(%.2f) > ", index.bust));
    double waist = Prompt.inputDouble(String.format("허리둘레(%.2f) > ", index.waist));
    double thigh = Prompt.inputDouble(String.format("허벅지둘레(%.2f) > ", index.thigh));
    double calf = Prompt.inputDouble(String.format("종아리둘레(%.2f) > ", index.calf));


  }

  public void delete() {

  }

  int indexOf(int indexNo) {
    for (int i = 0; i < this.size; i++) {
      Index index = this.indexes[i];
      if (index.no == indexNo) {
        return i;
      }
    }
    return -1;
  }

  Index findByNo(int indexNo) {
    int i = indexOf(indexNo);
    if (i == -1) {
      return null;
    } else {
      return this.indexes[i];
    }
  }
}
