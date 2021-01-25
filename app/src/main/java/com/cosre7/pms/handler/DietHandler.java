package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Prompt;

public class DietHandler {

  static final int LENGTH  = 100;
  Diet[] diets = new Diet[LENGTH];
  int size = 0;

  public void add() {
    System.out.println("[식단일지 작성]");

    Diet d = new Diet();
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
      String label = null;
      switch (d.choice) {
        case 1:
          label = "훌륭해요!";
          break;
        case 2:
          label = "치팅이라니..";   
          break;
      }
      String statusLabel = null;
      switch (d.status) {
        case 1:
          statusLabel = "나는 아직 배고프다..";
          break;
        case 2:
          statusLabel = "배가 반정도 찬 느낌";
          break;
        case 3:
          statusLabel = "적당해요";
          break;
        case 4:
          statusLabel = "조금 과해요";
          break;
        case 5:
          statusLabel = "터질 것 같아요";
      }
      System.out.printf("날짜: %s\n시간: %s시\n먹은 음식: %s\n포만감: %s\n결론: %s\n", 
          d.date, d.time, d.food, statusLabel, label);
    }
  }

}
