package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Body;
import com.cosre7.util.Prompt;

public class BodyDetailHandler extends AbstractBodyHandler {

  public BodyDetailHandler(List<Body> bodyList) {
    super(bodyList);
  }

  @Override
  public void service() {
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
}
