package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.util.Prompt;

public class DietHandler {

  static class Diet {
    Date date;
    String time;
    String food;
    int status;
    int choice;
  }
  static final int LENGTH  = 100;
  static Diet[] diets = new Diet[LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("[식단일지 작성]");

    Diet d = new Diet();
    d.date = Prompt.inputDate("날짜 > ");
    d.time = Prompt.inputString("시간 > ");
    d.food = Prompt.inputString("먹은 음식 > ");
    d.status = Prompt.inputInt("포만감 정도 (1~5) > ");     
    d.choice = Prompt.inputInt("종류\n1: 식단\n2: 치팅\n> ");
    diets[size++] = d;
  }

  public static void list() {
    System.out.println("[식단일지 목록]");

    for(int i = 0; i < size; i++) {
      Diet d = diets[i];
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
          statusLabel = "조금 과했나..";
          break;
        case 5:
          statusLabel = "터질 것 같아요";
      }
      System.out.printf("날짜: %s\n시간: %s시\n먹은 음식: %s\n포만감: %s\n결론: %s\n", 
          d.date, d.time, d.food, statusLabel, label);
    }
  }

}
