package com.cosre7.pms;

import com.cosre7.pms.handler.BoardHandler;
import com.cosre7.pms.handler.DietHandler;
import com.cosre7.pms.handler.IndexHandler;
import com.cosre7.pms.handler.TrainingHandler;
import com.cosre7.util.Prompt;

public class App {

  public static void main(String[] args) {

    DietHandler dietList = new DietHandler();
    TrainingHandler trainingList = new TrainingHandler();
    IndexHandler indexList = new IndexHandler();
    BoardHandler boardList = new BoardHandler();

    while (true) {
      System.out.println("[명령어]");
      System.out.println("식단일지 작성 : /diet/add");
      System.out.println("식단일지 목록 : /diet/list");
      System.out.println("게시글 작성 : /board/add");
      System.out.println("게시글 목록 : /board/list");
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
        dietList.add();

      } else if (input.equalsIgnoreCase("/diet/list")) {
        dietList.list();

      } else if (input.equalsIgnoreCase("/training/add")) {
        trainingList.add();

      } else if (input.equalsIgnoreCase("/training/list")) {
        trainingList.list();

      } else if (input.equalsIgnoreCase("/index/add")) {
        indexList.add();

      } else if (input.equalsIgnoreCase("/index/list")) {
        indexList.list();

      } else if (input.equalsIgnoreCase("/board/add")) {
        boardList.add();

      } else if (input.equalsIgnoreCase("/board/list")) {
        boardList.list();

      } else {
        System.out.println("실행할 수 없는 명령입니다.");

      }
      System.out.println();
    }
    System.out.println("당신의 건강을 응원합니다.");
    Prompt.close();    
  }

}