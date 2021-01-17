package com.cosre7.pms;
import java.sql.Date;
import java.util.Scanner;

// 신체 지수
// 1. 날짜
// 2. 몸무게
// 3. 가슴둘레
// 4. 허리둘레
// 5. 허벅지둘레
// 6. 종아리둘레

public class App2 {
  public static void main(String[] args) {
    Scanner keyboardScan = new Scanner(System.in);
    final int LENGTH2 = 100;
    Date[] date = new Date[LENGTH2];
    double[] weight = new double[LENGTH2];
    double[] bust = new double[LENGTH2];
    double[] waist = new double[LENGTH2];
    double[] thigh = new double[LENGTH2];
    double[] calf = new double[LENGTH2];
    int size2 = 0;
    System.out.println("[신체 지수]");

    for (int i = 0; i < LENGTH2; i++) {
      System.out.print("날짜 > ");
      date[i] = Date.valueOf(keyboardScan.nextLine());

      System.out.print("몸무게 > ");
      weight[i] = keyboardScan.nextDouble();

      System.out.print("가슴 둘레 > ");
      bust[i] = keyboardScan.nextDouble();

      System.out.print("허리 둘레 > ");
      waist[i] = keyboardScan.nextDouble();

      System.out.print("허벅지 둘레 > ");
      thigh[i] = keyboardScan.nextDouble();

      System.out.print("종아리 둘레 > ");
      calf[i] = keyboardScan.nextDouble();
      keyboardScan.nextLine();

      size2++;
      System.out.println();

      System.out.print("계속 입력하시겠습니까? (y/N) ");
      String response = keyboardScan.nextLine();
      if (response.length() == 0 || response.equalsIgnoreCase("n")) {
        break;
      }
      System.out.println();
    }
    keyboardScan.close();

    System.out.println("---------------------------------");
    for (int i = 0; i < size2; i++) {
      System.out.printf("%s, %.2f, %.2f, %.2f, %.2f, %.2f\n", 
          date[i], weight[i], bust[i], waist[i], thigh[i], calf[i]);
    }
  }
}