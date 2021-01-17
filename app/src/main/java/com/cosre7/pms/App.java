package com.cosre7.pms;
import java.sql.Date;
import java.util.Scanner;

// 식단 일지
// 1. 날짜
// 2. 시간
// 3. 먹은 음식
// 4. 포만감 정도 
// 5. 치팅? 식단? -> 치팅이면 "좀 더 노력하세요" 식단이면 "훌륭해요!"

public class App {
  public static void main(String[] args) {
    System.out.println("[식단 일지]");

    Scanner keyboardScan = new Scanner(System.in);
    final int LENGTH  = 100;
    Date[] date = new Date[LENGTH];
    String[] time = new String[LENGTH];
    String[] food = new String[LENGTH];
    int[] status = new int[LENGTH];
    int[] choice = new int[LENGTH];

    int size = 0;

    for (int i = 0; i < LENGTH; i++) {
      System.out.print("날짜 > ");
      date[i] = Date.valueOf(keyboardScan.nextLine());

      System.out.print("시간 > ");
      time[i] = keyboardScan.nextLine();

      System.out.print("먹은 음식 > ");
      food[i] = keyboardScan.nextLine();

      System.out.print("포만감 정도(1~5) > ");
      status[i] = keyboardScan.nextInt();     

      keyboardScan.nextLine();

      System.out.println("종류");
      System.out.println("1: 식단");
      System.out.println("2: 치팅");
      System.out.print("> ");
      choice[i] = keyboardScan.nextInt();
      keyboardScan.nextLine();
      size++;
      System.out.println();

      System.out.print("계속 입력하시겠습니까? (y/N) ");
      String response = keyboardScan.nextLine();
      if (response.length() == 0 || response.equalsIgnoreCase("n")) {
        break;
      }
      System.out.println();
    }
    keyboardScan.close();

    System.out.println("-----------------------------------");

    for(int i = 0; i < size; i++) {
      String label = null;
      switch (choice[i]) {
        case 1:
          label = "훌륭해요!";
          break;
        case 2:
          label = "좀 더 노력하세요";   
          break;
      }
      System.out.printf("%s, %s, %s, %s, %s\n", 
          date[i], time[i], food[i], status[i], label);
    }
  }
}