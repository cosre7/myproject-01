package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Prompt;

public class DietHandler {

  static final int LENGTH  = 100;

  MemberHandler memberList;

  Diet[] diets = new Diet[LENGTH];
  int size = 0;

  public DietHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }

  public void add() {
    System.out.println("[식단일지 작성]");

    Diet d = new Diet();

    d.no = Prompt.inputInt("번호 > ");
    while (true) {
      String name = Prompt.inputString("이름(취소: 빈 문자열) ");
      if (name.length() == 0) {
        System.out.println("식단일지 작성을 취소합니다.");
        return;
      }
      if (this.memberList.exist(name)) {
        d.name = name;
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
    d.date = Prompt.inputDate("날짜 > ");
    d.time = Prompt.inputString("시간 > ");

    d.food = "";
    while (true) {
      String eat = Prompt.inputString("먹은 음식(완료: 빈 문자열) > ");

      if (eat.length() == 0) {
        break;
      } else {
        if (!d.food.isEmpty()) {
          d.food += ", ";
        }
        d.food += eat;
      }
    }

    d.status = Prompt.inputInt("포만감 정도 (1~5) > ");  
    while (true) {
      d.choice = Prompt.inputInt("종류\n1: 식단\n2: 치팅\n> ");
      if (d.choice == 1 || d.choice == 2) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }
    this.diets[this.size++] = d;
  }

  public void list() {
    System.out.println("[식단일지 목록]");

    for(int i = 0; i < this.size; i++) {
      Diet d = this.diets[i];

      System.out.printf("[%d] %s\n날짜: %s 시간: %s시\n", 
          d.no, d.name, d.date, d.time);
    }
  }
}





