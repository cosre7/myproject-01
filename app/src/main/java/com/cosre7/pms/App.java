package com.cosre7.pms;
import java.sql.Date;
import java.util.Scanner;

public class App {
  static Scanner keyboardScan = new Scanner(System.in);

  static final int LENGTH  = 100;
  static Date[] date = new Date[LENGTH];
  static String[] time = new String[LENGTH];
  static String[] food = new String[LENGTH];
  static int[] status = new int[LENGTH];
  static int[] choice = new int[LENGTH];
  static int size = 0;

  static void addDiet() {
    System.out.println("[식단일지 작성]");

    date[size] = promptDate("날짜 > ");
    time[size] = promptString("시간 > ");
    food[size] = promptString("먹은 음식 > ");
    status[size] = promptInt("포만감 정도 (1~5) > ");     
    choice[size] = promptInt("종류\n1: 식단\n2: 치팅\n> ");
    size++;
  }

  static void listDiet() {
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

  static final int LENGTH3 = 100;
  static Date[] date2 = new Date[LENGTH3];
  static String[] list = new String[LENGTH3];
  static int[] body = new int[LENGTH3];
  static int[] training = new int[LENGTH3];
  static int[] intensity = new int[LENGTH3];
  static int size3 = 0;

  static void addTraining() {
    System.out.println("[운동일지 작성]");

    date2[size3] = promptDate("날짜 > ");
    list[size3] = promptString("운동 리스트 > ");
    body[size3] = promptInt("종류1\n1: 상체\n2: 하체\n3: 전신\n> ");
    training[size3] = promptInt("종류2\n1: 근력\n2: 유산소\n> ");
    intensity[size3] = promptInt("운동 강도 (1~5) > ");
    size3++;
  }

  static void listTraining() {
    System.out.println("[운동일지 목록]");

    for (int i = 0; i < size3; i++) {
      String bodyLabel = null;
      switch (body[i]) {
        case 1:
          bodyLabel = "상체 운동";
          break;
        case 2:
          bodyLabel = "하체 운동";
          break;
        default :
          bodyLabel = "전신 운동";
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
      System.out.printf("날짜: %s\n운동 목록: %s\n종류1: %s\n종류2: %s\n운동 강도: %s\n", 
          date2[i], list[i], bodyLabel, trainingLabel, intensityLabel);
    }
  }

  static final int LENGTH2 = 100;
  static Date[] date3 = new Date[LENGTH2];
  static double[] weight = new double[LENGTH2];
  static double[] bust = new double[LENGTH2];
  static double[] waist = new double[LENGTH2];
  static double[] thigh = new double[LENGTH2];
  static double[] calf = new double[LENGTH2];
  static int size2 = 0;

  static void addIndex() {
    System.out.println("[신체지수 작성]");

    date3[size2] = promptDate("날짜 > ");
    weight[size2] = promptDouble("몸무게 > ");
    bust[size2] = promptDouble("가슴 둘레 > ");
    waist[size2] = promptDouble("허리 둘레 > ");
    thigh[size2] = promptDouble("허벅지 둘레 > ");
    calf[size2] = promptDouble("종아리 둘레 > ");

    size2++;
  }

  static void listIndex() {
    System.out.println("[신체지수 목록]");

    for (int i = 0; i < size2; i++) {
      System.out.printf("날짜: %s\n몸무게: %.2f\n가슴 둘레: %.2f\n허리 둘레: %.2f\n허벅지 둘레: %.2f\n종아리 둘레: %.2f\n", 
          date3[i], weight[i], bust[i], waist[i], thigh[i], calf[i]);
    }
  }

  public static void main(String[] args) {

    while (true) {
      System.out.println("[명령어]");
      System.out.println("식단일지 작성 : /diet/add");
      System.out.println("식단일지 목록 : /diet/list");
      System.out.println("운동일지 작성 : /training/add");
      System.out.println("운동일지 목록 : /training/list");
      System.out.println("신체지수 작성 : /index/add");
      System.out.println("신체지수 목록 : /index/list");
      System.out.println();
      System.out.print("명령> ");
      String input = keyboardScan.nextLine();

      if (input.equalsIgnoreCase("exit") ||
          input.equalsIgnoreCase("quit")) {
        break;
      } else if (input.equalsIgnoreCase("/diet/add")) {
        addDiet();

      } else if (input.equalsIgnoreCase("/diet/list")) {
        listDiet();

      } else if (input.equalsIgnoreCase("/training/add")) {
        addTraining();

      } else if (input.equalsIgnoreCase("/training/list")) {
        listTraining();

      } else if (input.equalsIgnoreCase("/index/add")) {
        addIndex();

      } else if (input.equalsIgnoreCase("/index/list")) {
        listIndex();

      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      System.out.println();
    }
    keyboardScan.close();    
  }
  static String promptString (String title) {
    System.out.print(title);
    return keyboardScan.nextLine();
  }

  static Date promptDate (String title) {
    String str = promptString(title);
    return Date.valueOf(str);    
  }

  static int promptInt (String title) {
    String str = promptString(title);
    return Integer.valueOf(str);
  }

  static double promptDouble (String title) {
    String str = promptString(title);
    return Double.parseDouble(str);
  }
}