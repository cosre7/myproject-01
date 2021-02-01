package com.cosre7.pms;

import com.cosre7.pms.handler.BoardHandler;
import com.cosre7.pms.handler.DietHandler;
import com.cosre7.pms.handler.IndexHandler;
import com.cosre7.pms.handler.MemberHandler;
import com.cosre7.pms.handler.TrainingHandler;
import com.cosre7.util.Prompt;

public class App {

  public static void main(String[] args) {

    MemberHandler memberList = new MemberHandler();
    DietHandler dietList = new DietHandler(memberList);
    TrainingHandler trainingList = new TrainingHandler(memberList);
    IndexHandler indexList = new IndexHandler(memberList);
    BoardHandler boardList = new BoardHandler(memberList);

    loop:
      while (true) {

        String input = Prompt.inputString("메인> ");
        System.out.println();

        switch (input) {
          case "/member/add":
            memberList.add();
            break;
          case "/member/list":
            memberList.list();
            break;
          case "/diet/add":
            dietList.add();
            break;
          case "/diet/list":
            dietList.list();
            break;
          case "/training/add":
            trainingList.add();
            break;
          case "/training/list":
            trainingList.list();
            break;
          case "/index/add":
            indexList.add();
            break;
          case "/index/list":
            indexList.list();
            break;
          case "/board/add":
            boardList.add();
            break;
          case "/board/list":
            boardList.list();
            break;
          case "quit":
          case "exit":
            System.out.println("당신의 건강을 응원합니다.");
            break loop;
          default:
            System.out.println("올바른 번호를 선택해주세요.");
        }
        System.out.println();
      }
    Prompt.close();    
  }

}