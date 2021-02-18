package com.cosre7.pms;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import com.cosre7.pms.handler.BoardHandler;
import com.cosre7.pms.handler.BodyHandler;
import com.cosre7.pms.handler.DietHandler;
import com.cosre7.pms.handler.MemberHandler;
import com.cosre7.pms.handler.TrainingHandler;
import com.cosre7.util.Prompt;

public class App {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) throws CloneNotSupportedException {

    MemberHandler memberHandler = new MemberHandler();
    DietHandler dietHandler = new DietHandler(memberHandler);
    TrainingHandler trainingHandler = new TrainingHandler(memberHandler);
    BodyHandler bodyHandler = new BodyHandler(memberHandler);
    BoardHandler boardHandler = new BoardHandler(memberHandler);

    loop:
      while (true) {

        String input = Prompt.inputString("메인> ");
        System.out.println();

        if (input.length() == 0) 
          continue;

        commandStack.push(input);
        commandQueue.offer(input);

        switch (input) {
          case "/member/add":
            memberHandler.add();
            break;
          case "/member/list":
            memberHandler.list();
            break;
          case "/member/detail":
            memberHandler.detail();
            break;
          case "/member/update":
            memberHandler.update();
            break;
          case "/member/delete":
            memberHandler.delete();
            break;
          case "/diet/add":
            dietHandler.add();
            break;
          case "/diet/list":
            dietHandler.list();
            break;
          case "/diet/detail":
            dietHandler.detail();
            break;
          case "/diet/update":
            dietHandler.update();
            break;
          case "/diet/delete":
            dietHandler.delete();
            break;
          case "/training/add":
            trainingHandler.add();
            break;
          case "/training/list":
            trainingHandler.list();
            break;
          case "/training/detail":
            trainingHandler.detail();
            break;
          case "/training/update":
            trainingHandler.update();
            break;
          case "/training/delete":
            trainingHandler.delete();
            break;
          case "/body/add":
            bodyHandler.add();
            break;
          case "/body/list":
            bodyHandler.list();
            break;
          case "/body/detail":
            bodyHandler.detail();
            break;
          case "/body/update":
            bodyHandler.update();
            break;
          case "/body/delete":
            bodyHandler.delete();
            break;
          case "/board/add":
            boardHandler.add();
            break;
          case "/board/list":
            boardHandler.list();
            break;
          case "/board/detail":
            boardHandler.detail();
            break;
          case "/board/update":
            boardHandler.update();
            break;
          case "/board/delete":
            boardHandler.delete();
            break;
          case "history":
            printCommandHistory(commandStack.iterator());
            break;
          case "history2":
            printCommandHistory(commandQueue.iterator());
            break;
          case "quit":
          case "exit":
            System.out.println("당신의 건강을 응원합니다.");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println();
      }
    Prompt.close();    
  }

  static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if ((++count % 5) == 0) {
        String input = Prompt.inputString(": ");
        if (input.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}