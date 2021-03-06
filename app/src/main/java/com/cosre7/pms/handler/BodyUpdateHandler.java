package com.cosre7.pms.handler;

import java.sql.Date;
import java.util.List;
import com.cosre7.pms.domain.Body;
import com.cosre7.util.Prompt;

public class BodyUpdateHandler extends AbstractBodyHandler {

  private MemberValidatorHandler memberValidatorHandler;

  public BodyUpdateHandler(List<Body> bodyList, MemberValidatorHandler memberValidatorHandler) {
    super(bodyList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[신체지수 변경]");

    int no = Prompt.inputInt("번호 > ");

    Body body = findByNo(no);
    if (body == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    String user = memberValidatorHandler.inputMember(String.format("이름(%s, 취소: 빈 문자열) > ", body.getName()));
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
}
