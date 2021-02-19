package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Prompt;

public class DietAddHandler extends AbstractDietHandler {

  private MemberValidatorHandler memberValidatorHandler;

  public DietAddHandler(List<Diet> dietList, MemberValidatorHandler memberValidatorHandler) {
    super(dietList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[식단일지 작성]");

    Diet d = new Diet();
    d.setNo(Prompt.inputInt("번호 > "));

    d.setName(memberValidatorHandler.inputMember("이름(취소: 빈 문자열) > "));
    if (d.getName() == null) {
      System.out.println("식단일지 작성을 취소합니다.");
      return;
    }

    d.setDate(Prompt.inputDate("날짜 > "));
    d.setTime(Prompt.inputString("시간 > "));
    d.setFood(inputFoods("먹은 음식(완료: 빈 문자열) > "));

    while (true) {
      d.setStatus(Prompt.inputInt("포만감 정도 (1~5) > "));
      if (d.getStatus() < 1 || d.getStatus() > 5) {
        System.out.println("다시 입력해주세요");
      } else {
        break;
      }
    }

    while (true) {
      d.setChoice(Prompt.inputInt("종류\n1: 식단\n2: 치팅\n> "));
      if (d.getChoice() == 1 || d.getChoice() == 2) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }
    dietList.add(d);
  }
}





