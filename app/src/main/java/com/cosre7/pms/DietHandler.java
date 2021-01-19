package com.cosre7.pms;

import java.sql.Date;

public class DietHandler {
  static final int LENGTH  = 100;
  static Date[] date = new Date[LENGTH];
  static String[] time = new String[LENGTH];
  static String[] food = new String[LENGTH];
  static int[] status = new int[LENGTH];
  static int[] choice = new int[LENGTH];
  static int size = 0;

  static void add() {
    System.out.println("[식단일지 작성]");

    date[size] = Prompt.inputDate("날짜 > ");
    time[size] = Prompt.inputString("시간 > ");
    food[size] = Prompt.inputString("먹은 음식 > ");
    status[size] = Prompt.inputInt("포만감 정도 (1~5) > ");     
    choice[size] = Prompt.inputInt("종류\n1: 식단\n2: 치팅\n> ");
    size++;
  }

  static void list() {
    System.out.println("[식단일지 목록]");

    for(int i = 0; i < size; i++) {
      String label = null;
      switch (choice[i]) {
        case 1:
          label = "훌륭해요!";
          break;
        case 2:
          label = "치팅이라니..";   
          break;
      }
      String statusLabel = null;
      switch (status[i]) {
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
          date[i], time[i], food[i], statusLabel, label);
    }
  }

}
