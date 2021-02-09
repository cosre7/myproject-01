package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Body;
import com.cosre7.util.Prompt;

public class BodyHandler {

  BodyList bodyList = new BodyList();
  MemberList memberList;

  public BodyHandler(MemberList memberList) {
    this.memberList = memberList;
  }

  public void add() {
    System.out.println("[신체지수 작성]");

    Body b = new Body();
    b.no = Prompt.inputInt("번호 > ");

    b.name = inputMember("이름(취소: 빈 문자열) > ");
    if (b.name == null) {
      System.out.println("신체지수 작성을 취소합니다.");
      return;
    }

    b.date = Prompt.inputDate("날짜 > ");
    b.height = Prompt.inputDouble("키(cm) > ");
    b.weight = Prompt.inputDouble("몸무게(kg) > ");
    b.bust = Prompt.inputDouble("가슴 둘레(cm) > ");
    b.waist = Prompt.inputDouble("허리 둘레(cm) > ");
    b.thigh = Prompt.inputDouble("허벅지 둘레(cm) > ");
    b.calf = Prompt.inputDouble("종아리 둘레(cm) > ");

    bodyList.add(b);
  }

  public void list() {
    System.out.println("[신체지수 목록]");

    Body[] bodys = bodyList.toArray();
    for (Body b : bodys) {
      System.out.printf("번호: %d 이름: %s 날짜: %s\n", b.no, b.name, b.date); 
    }
  }

  public void detail() {
    System.out.println("[신체지수 상세보기");

    int no = Prompt.inputInt("번호 > ");

    Body body = bodyList.get(no);
    if (body == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    // 이름, 날짜, 키, 몸무게, 가슴 둘레, 허리 둘레, 허벅지 둘레, 종아리 둘레
    System.out.printf("[%s]\n", body.date);
    System.out.printf("-- %s --\n", body.name);
    System.out.printf("키: %.2f cm\n", body.height);
    System.out.printf("몸무게: %.2f kg\n", body.waist);
    System.out.printf("가슴둘레: %.2f cm\n", body.bust);
    System.out.printf("허리둘레: %.2f cm\n", body.waist);
    System.out.printf("허벅지둘레: %.2f cm\n", body.thigh);
    System.out.printf("종아리둘레: %.2f cm\n", body.calf);
  }

  public void update() {
    System.out.println("[신체지수 변경]");

    int no = Prompt.inputInt("번호 > ");

    Body body = bodyList.get(no);
    if (body == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    String user = inputMember(String.format("이름(%s, 취소: 빈 문자열) > ", body.name));
    if (user == null) {
      System.out.println("신체지수 작성을 취소합니다.");
      return;
    }

    Date date = Prompt.inputDate(String.format("날짜(%s) > ", body.date));
    double height = Prompt.inputDouble(String.format("키(%.2f) > ", body.height));
    double weight = Prompt.inputDouble(String.format("몸무게(%.2f) > ", body.weight));
    double bust = Prompt.inputDouble(String.format("가슴둘레(%.2f) > ", body.bust));
    double waist = Prompt.inputDouble(String.format("허리둘레(%.2f) > ", body.waist));
    double thigh = Prompt.inputDouble(String.format("허벅지둘레(%.2f) > ", body.thigh));
    double calf = Prompt.inputDouble(String.format("종아리둘레(%.2f) > ", body.calf));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      body.name = user;
      body.date = date;
      body.height = height;
      body.weight = weight;
      body.bust = bust;
      body.waist = waist;
      body.thigh = thigh;
      body.calf = calf;

      System.out.printf("%s 님의 신체지수를 변경하였습니다.", body.name);
      System.out.println();

    } else {
      System.out.println("신체지수 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[신체지수 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Body body = bodyList.get(no);
    if (body == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      bodyList.delete(no);
      System.out.println("신체지수를 삭제하였습니다.");
    } else {
      System.out.println("신체지수 삭제를 취소하였습니다.");
    }
  }

  String inputMember(String promptTitle) {
    while (true) {
      String name = Prompt.inputString(promptTitle);
      if (name.length() == 0) {
        return null;
      }
      if (this.memberList.exist(name)) {
        return name;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }
}
