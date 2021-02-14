package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Body;
import com.cosre7.util.List;
import com.cosre7.util.ListIterator;
import com.cosre7.util.Prompt;

public class BodyHandler {

  private List bodyList = new List();
  private MemberHandler memberHandler;

  public BodyHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void add() {
    System.out.println("[신체지수 작성]");

    Body b = new Body();
    b.setNo(Prompt.inputInt("번호 > "));

    b.setName(memberHandler.inputMember("이름(취소: 빈 문자열) > "));
    if (b.getName() == null) {
      System.out.println("신체지수 작성을 취소합니다.");
      return;
    }

    b.setDate(Prompt.inputDate("날짜 > "));
    b.setHeight(Prompt.inputDouble("키(cm) > "));
    b.setWeight(Prompt.inputDouble("몸무게(kg) > "));
    b.setBust(Prompt.inputDouble("가슴 둘레(cm) > "));
    b.setWaist(Prompt.inputDouble("허리 둘레(cm) > "));
    b.setThigh(Prompt.inputDouble("허벅지 둘레(cm) > "));
    b.setCalf(Prompt.inputDouble("종아리 둘레(cm) > "));

    bodyList.add(b);
  }

  public void list() {
    System.out.println("[신체지수 목록]");

    ListIterator iterator = new ListIterator(this.bodyList);

    while (iterator.hasNext()) {
      Body b = (Body) iterator.next();
      System.out.printf("번호: %d 이름: %s 날짜: %s\n", 
          b.getNo(), 
          b.getName(), 
          b.getDate()); 
    }
  }

  public void detail() {
    System.out.println("[신체지수 상세보기");

    int no = Prompt.inputInt("번호 > ");

    Body body = findByNo(no);
    if (body == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    // 이름, 날짜, 키, 몸무게, 가슴 둘레, 허리 둘레, 허벅지 둘레, 종아리 둘레
    System.out.printf("[%s]\n", body.getDate());
    System.out.printf("-- %s --\n", body.getName());
    System.out.printf("키: %.2f cm\n", body.getHeight());
    System.out.printf("몸무게: %.2f kg\n", body.getWaist());
    System.out.printf("가슴둘레: %.2f cm\n", body.getBust());
    System.out.printf("허리둘레: %.2f cm\n", body.getWaist());
    System.out.printf("허벅지둘레: %.2f cm\n", body.getThigh());
    System.out.printf("종아리둘레: %.2f cm\n", body.getCalf());
  }

  public void update() {
    System.out.println("[신체지수 변경]");

    int no = Prompt.inputInt("번호 > ");

    Body body = findByNo(no);
    if (body == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    String user = memberHandler.inputMember(String.format("이름(%s, 취소: 빈 문자열) > ", body.getName()));
    if (user == null) {
      System.out.println("신체지수 작성을 취소합니다.");
      return;
    }

    Date date = Prompt.inputDate(String.format("날짜(%s) > ", body.getDate()));
    double height = Prompt.inputDouble(String.format("키(%.2f) > ", body.getHeight()));
    double weight = Prompt.inputDouble(String.format("몸무게(%.2f) > ", body.getWeight()));
    double bust = Prompt.inputDouble(String.format("가슴둘레(%.2f) > ", body.getBust()));
    double waist = Prompt.inputDouble(String.format("허리둘레(%.2f) > ", body.getWaist()));
    double thigh = Prompt.inputDouble(String.format("허벅지둘레(%.2f) > ", body.getThigh()));
    double calf = Prompt.inputDouble(String.format("종아리둘레(%.2f) > ", body.getCalf()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      body.setName(user);
      body.setDate(date);
      body.setHeight(height);
      body.setWeight(weight);
      body.setBust(bust);
      body.setWaist(waist);
      body.setThigh(thigh);
      body.setCalf(calf);

      System.out.printf("%s 님의 신체지수를 변경하였습니다.", body.getName());
      System.out.println();

    } else {
      System.out.println("신체지수 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[신체지수 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Body body = findByNo(no);
    if (body == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      bodyList.delete(body);
      System.out.println("신체지수를 삭제하였습니다.");
    } else {
      System.out.println("신체지수 삭제를 취소하였습니다.");
    }
  }

  private Body findByNo(int bodyNo) {
    Object[] list = bodyList.toArray();
    for (Object obj : list) {
      Body b = (Body) obj;
      if (b.getNo() == bodyNo) {
        return b;
      }
    }
    return null;
  }
}
