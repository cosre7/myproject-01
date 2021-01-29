package com.cosre7.pms;

import com.cosre7.pms.handler.BoardHandler;
import com.cosre7.pms.handler.DietHandler;
import com.cosre7.pms.handler.IndexHandler;
import com.cosre7.pms.handler.TrainingHandler;
import com.cosre7.util.Prompt;

public class App {

  public static void main(String[] args) {

    DietHandler dietHandler = new DietHandler();
    TrainingHandler trainingHandler = new TrainingHandler();
    IndexHandler indexHandler = new IndexHandler();
    BoardHandler boardHandler = new BoardHandler();

    loop:
      while (true) {
        System.out.println("--메인---------------------");
        System.out.println("1. 식단일지");
        System.out.println("2. 운동일지");
        System.out.println("3. 신체지수");
        System.out.println("4. 추천 게시글");
        System.out.println("0. 종료");

        String input = Prompt.inputString("메인> ");
        System.out.println();

        switch (input) {
          case "1":
            dietHandler.service();
            break;
          case "2":
            //            trainingHandler.service();
            break;
          case "3":
            //            indexHandler.service();
            break;
          case "4":
            //            boardHandler.service();
            break;
          case "0":
            System.out.println("당신의 건강을 응원합니다.");
            break loop;
          default:
            System.out.println("올바른 번호를 선택해주세요.");
        }
      }
    Prompt.close();    
  }

}