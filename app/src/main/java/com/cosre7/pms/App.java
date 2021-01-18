package com.cosre7.pms;
import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyboardScan = new Scanner(System.in);


    final int LENGTH  = 100;
    Date[] date = new Date[LENGTH];
    String[] time = new String[LENGTH];
    String[] food = new String[LENGTH];
    int[] status = new int[LENGTH];
    int[] choice = new int[LENGTH];
    int size = 0;

    final int LENGTH3 = 100;
    Date[] date2 = new Date[LENGTH3];
    String[] list = new String[LENGTH3];
    int[] body = new int[LENGTH3];
    int[] training = new int[LENGTH3];
    int[] intensity = new int[LENGTH3];
    int size3 = 0;

    final int LENGTH2 = 100;
    Date[] date3 = new Date[LENGTH2];
    double[] weight = new double[LENGTH2];
    double[] bust = new double[LENGTH2];
    double[] waist = new double[LENGTH2];
    double[] thigh = new double[LENGTH2];
    double[] calf = new double[LENGTH2];
    int size2 = 0;

    while (true) {
      System.out.print("명령> ");
      String input = keyboardScan.nextLine();

      if (input.equalsIgnoreCase("exit") ||
          input.equalsIgnoreCase("quit")) {
        break;
      } else if (input.equalsIgnoreCase("/diet/add")) {
        System.out.println("[식단 일지 작성]");

        System.out.print("날짜 > ");
        date[size] = Date.valueOf(keyboardScan.nextLine());

        System.out.print("시간 > ");
        time[size] = keyboardScan.nextLine();

        System.out.print("먹은 음식 > ");
        food[size] = keyboardScan.nextLine();

        System.out.print("포만감 정도(1~5) > ");
        status[size] = keyboardScan.nextInt();     

        keyboardScan.nextLine();

        System.out.println("종류");
        System.out.println("1: 식단");
        System.out.println("2: 치팅");
        System.out.print("> ");
        choice[size] = keyboardScan.nextInt();
        keyboardScan.nextLine();
        size++;
      } else if (input.equalsIgnoreCase("/diet/list")) {
        System.out.println("[식단 일지 목록]");

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
          System.out.printf("%s, %s, %s, %s, %s\n", 
              date[i], time[i], food[i], statusLabel, label);
        }
      } else if (input.equalsIgnoreCase("/training/add")) {
        System.out.println("[운동 일지 작성]");

        System.out.print("날짜 > ");
        date2[size3] = Date.valueOf(keyboardScan.nextLine());

        System.out.print("운동 리스트 > ");
        list[size3] = keyboardScan.nextLine();

        System.out.println("종류1");
        System.out.println("1: 상체");
        System.out.println("2: 하체");
        System.out.print("> ");
        body[size3] = Integer.valueOf(keyboardScan.nextLine());

        System.out.println("종류2");
        System.out.println("1: 근력");
        System.out.println("2: 유산소");
        System.out.print("> ");
        training[size3] = Integer.valueOf(keyboardScan.nextLine());

        System.out.print("운동 강도 (1~5) > ");
        intensity[size3] = keyboardScan.nextInt();
        keyboardScan.nextLine();
        size3++;

      } else if (input.equalsIgnoreCase("/training/list")) {
        System.out.println("[운동 일지 목록]");

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
          String intensityLabel = null;
          switch (intensity[i]) {
            case 1:
              intensityLabel = "껌이에요";
              break;
            case 2:
              intensityLabel = "한 듯 안한 듯";
              break;
            case 3:
              intensityLabel = "적당해요";
              break;
            case 4:
              intensityLabel = "근육통 예상";
              break;
            case 5:
              intensityLabel = "헬이에요";
          }
          System.out.printf("%s, %s, %s, %s, %s\n", 
              date2[i], list[i], bodyLabel, trainingLabel, intensityLabel);
        }
      } else if (input.equalsIgnoreCase("/index/add")) {
        System.out.println("[신체 지수 작성]");

        System.out.print("날짜 > ");
        date3[size2] = Date.valueOf(keyboardScan.nextLine());

        System.out.print("몸무게 > ");
        weight[size2] = keyboardScan.nextDouble();

        System.out.print("가슴 둘레 > ");
        bust[size2] = keyboardScan.nextDouble();

        System.out.print("허리 둘레 > ");
        waist[size2] = keyboardScan.nextDouble();

        System.out.print("허벅지 둘레 > ");
        thigh[size2] = keyboardScan.nextDouble();

        System.out.print("종아리 둘레 > ");
        calf[size2] = keyboardScan.nextDouble();
        keyboardScan.nextLine();

        size2++;
      } else if (input.equalsIgnoreCase("/index/list")) {
        System.out.println("[신체 지수 목록]");

        for (int i = 0; i < size2; i++) {
          System.out.printf("%s, %.2f, %.2f, %.2f, %.2f, %.2f\n", 
              date[i], weight[i], bust[i], waist[i], thigh[i], calf[i]);
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      System.out.println();
    }
    keyboardScan.close();
  }
}