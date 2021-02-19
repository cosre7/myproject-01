package com.cosre7.pms.handler;

import java.sql.Date;
import java.util.List;
import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Prompt;

public class DietUpdateHandler extends AbstractDietHandler {

  protected AbstractMemberHandler memberHandler;

  public DietUpdateHandler(List<Diet> dietList, AbstractMemberHandler memberHandler) {
    super(dietList);
    this.memberHandler = memberHandler;
  }

  public void update() {
    System.out.println("[식단일지 변경]");

    int no = Prompt.inputInt("번호 > ");

    Diet diet = findByNo(no);
    if (diet == null) {
      System.out.println("해당 번호의 식단일지가 없습니다.");
      return;
    }

    String user = memberHandler.inputMember(String.format("이름(%s, 취소: 빈 문자열) > ", diet.getName()));
    if (user == null) {
      System.out.println("식단일지 작성을 취소합니다.");
      return;
    }

    //날짜, 시간, 먹은 음식, 포만감 정도, 종류
    Date date = Prompt.inputDate(String.format("날짜(%s) > ", diet.getDate()));
    String time = Prompt.inputString(String.format("시간(%s 시) > ", diet.getTime()));
    String foods = inputFoods(String.format("먹은 음식(완료: 빈 문자열) > ", diet.getFood()));

    int status = 0;
    while (true) {
      int full = Prompt.inputInt("포만감 정도 (1~5) > ");
      if (full < 1 || full > 5) {
        System.out.println("다시 입력해주세요");
      } else {
        status = full;
        break;
      }
    }

    int choice = 0;
    while (true) {
      int kind = Prompt.inputInt("종류\n1: 식단\n2: 치팅\n> ");
      if (kind == 1 || kind == 2) {
        choice = kind;
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      diet.setName(user);
      diet.setDate(date);
      diet.setTime(time);
      diet.setFood(foods);
      diet.setStatus(status);
      diet.setChoice(choice);
      System.out.println("식단일지를 변경하였습니다.");

    } else {
      System.out.println("식단일지 변경을 취소하였습니다.");
    }
  }
}





