package com.cosre7.pms;

import java.sql.Date;
import java.util.Scanner;

//운동 일지
//1. 날짜
//2. 운동 리스트
//3. 상체/하체
//4. 근력/유산소
//5. 운동 강도
public class App3 {    
  public static void main(String[] args) {
    System.out.println("[운동 일지]");

    Scanner keyboardScan = new Scanner(System.in);

    final int LENGTH3 = 100;
    Date[] date = new Date[LENGTH3];
    String[] list = new String[LENGTH3];
    int[] body = new int[LENGTH3];
    int[] training = new int[LENGTH3];
    int[] intensity = new int[LENGTH3];
    int size3 = 0;

    for (int i = 0; i < LENGTH3; i++) {
      System.out.print("날짜 > ");
      date[i] = Date.valueOf(keyboardScan.nextLine());

      System.out.print("운동 리스트 > ");
      list[i] = keyboardScan.nextLine();

      System.out.println("종류1");
      System.out.println("1: 상체");
      System.out.println("2: 하체");
      System.out.print("> ");
      body[i] = Integer.valueOf(keyboardScan.nextLine());

      System.out.println("종류2");
      System.out.println("1: 근력");
      System.out.println("2: 유산소");
      System.out.print("> ");
      training[i] = Integer.valueOf(keyboardScan.nextLine());

      System.out.print("운동 강도 (1~5) > ");
      intensity[i] = keyboardScan.nextInt();
      keyboardScan.nextLine();
      size3++;
      System.out.println();

      System.out.print("계속 입력하시겠습니까? (y/N) ");
      String response = keyboardScan.nextLine();
      if (response.length() == 0 || response.equalsIgnoreCase("n")) {
        break;
      }
      System.out.println();

    }
    keyboardScan.close();

    System.out.println("--------------------------------------");

    for (int i = 0; i < size3; i++) {
      String bodyLabel = null;
      switch (body[i]) {
        case 1:
          bodyLabel = "상체 운동";
          break;
        case 2:
          bodyLabel = "하체 운동";
          break;
      }
      String trainingLabel = null;
      switch (training[i]) {
        case 1:
          trainingLabel = "근력 운동";
          break;
        case 2:
          trainingLabel = "유산소 운동";
          break;
      }
      System.out.printf("%s, %s, %s, %s, %d\n", 
          date[i], list[i], bodyLabel, trainingLabel, intensity[i]);
    }
  }
}