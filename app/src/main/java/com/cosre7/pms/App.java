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
      System.out.println("1. 식단일지 작성");
      System.out.println("2. 식단일지 목록");
      System.out.println("3. 추천 게시글 작성");
      System.out.println("4. 추천 게시글 목록");
      System.out.println("5. 운동일지 작성");
      System.out.println("6. 운동일지 목록");
      System.out.println("7. 신체지수 작성");
      System.out.println("8. 신체지수 목록");
      System.out.println();

      System.out.print("명령> ");
      String input = Prompt.keyboardScan.nextLine();

      if (input.equalsIgnoreCase("exit") ||
          input.equalsIgnoreCase("quit")) {
        break;
      } else if (input.equalsIgnoreCase("1")) {
        dietList.add();

      } else if (input.equalsIgnoreCase("2")) {
        dietList.list();

      } else if (input.equalsIgnoreCase("5")) {
        trainingList.add();

      } else if (input.equalsIgnoreCase("6")) {
        trainingList.list();

      } else if (input.equalsIgnoreCase("7")) {
        indexList.add();

      } else if (input.equalsIgnoreCase("8")) {
        indexList.list();

      } else if (input.equalsIgnoreCase("3")) {
        boardList.add();

      } else if (input.equalsIgnoreCase("4")) {
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