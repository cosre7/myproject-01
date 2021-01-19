package com.cosre7.pms;

public class App {

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
      String input = Prompt.keyboardScan.nextLine();

      if (input.equalsIgnoreCase("exit") ||
          input.equalsIgnoreCase("quit")) {
        break;
      } else if (input.equalsIgnoreCase("/diet/add")) {
        DietHandler.add();

      } else if (input.equalsIgnoreCase("/diet/list")) {
        DietHandler.list();

      } else if (input.equalsIgnoreCase("/training/add")) {
        TrainingHandler.add();

      } else if (input.equalsIgnoreCase("/training/list")) {
        TrainingHandler.list();

      } else if (input.equalsIgnoreCase("/index/add")) {
        IndexHandler.add();

      } else if (input.equalsIgnoreCase("/index/list")) {
        IndexHandler.list();

      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      System.out.println();
    }
    Prompt.close();    
  }

}