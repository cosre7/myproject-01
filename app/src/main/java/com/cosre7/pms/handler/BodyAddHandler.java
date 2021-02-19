package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Body;
import com.cosre7.util.Prompt;

public class BodyAddHandler extends AbstractBodyHandler {

  private MemberValidatorHandler memberValidatorHandler;

  public BodyAddHandler(List<Body> bodyList, MemberValidatorHandler memberValidatorHandler) {
    super(bodyList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[신체지수 작성]");

    Body b = new Body();
    b.setNo(Prompt.inputInt("번호 > "));

    b.setName(memberValidatorHandler.inputMember("이름(취소: 빈 문자열) > "));
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
}
