package com.cosre7.pms;

import java.sql.Date;
import java.util.Scanner;

// 식단 일지
// 1. 날짜
// 2. 시간
// 3. 먹은 음식
// 4. 포만감 정도 (1~5 별)
// 5. 치팅? 식단? -> 치팅이면 "좀 더 노력하세요" 식단이면 "훌륭해요!"

public class App {
  public static void main(String[] args) {
    Scanner keyboardScan = new Scanner(System.in);

    String time = keyboardScan.nextLine();
    String food = keyboardScan.nextLine();
    int status = keyboardScan.nextInt();
    String choice = keyboardScan.nextLine();

    System.out.println("[식단 일지]");

    System.out.print("날짜 > ");
    Date date = Date.valueOf(keyboardScan.nextLine());

    System.out.print("시간 > ");
    time = keyboardScan.nextLine();

    System.out.print("먹은 음식 > ");
    food = keyboardScan.nextLine();

    System.out.print("포만감 정도(1~5) > ");
    status = keyboardScan.nextInt();

    System.out.print("치팅 / 식단 > ");
    choice = keyboardScan.nextLine();

    System.out.printf("%s, %s, %s, %s\n", date, time, food, choice);
  }
}
