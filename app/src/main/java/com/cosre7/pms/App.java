package com.cosre7.pms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import com.cosre7.pms.domain.Board;
import com.cosre7.pms.domain.Body;
import com.cosre7.pms.domain.Diet;
import com.cosre7.pms.domain.Member;
import com.cosre7.pms.domain.Training;
import com.cosre7.pms.handler.BoardAddHandler;
import com.cosre7.pms.handler.BoardDeleteHandler;
import com.cosre7.pms.handler.BoardDetailHandler;
import com.cosre7.pms.handler.BoardListHandler;
import com.cosre7.pms.handler.BoardUpdateHandler;
import com.cosre7.pms.handler.BodyAddHandler;
import com.cosre7.pms.handler.BodyDeleteHandler;
import com.cosre7.pms.handler.BodyDetailHandler;
import com.cosre7.pms.handler.BodyListHandler;
import com.cosre7.pms.handler.BodyUpdateHandler;
import com.cosre7.pms.handler.DietAddHandler;
import com.cosre7.pms.handler.DietDeleteHandler;
import com.cosre7.pms.handler.DietDetailHandler;
import com.cosre7.pms.handler.DietListHandler;
import com.cosre7.pms.handler.DietUpdateHandler;
import com.cosre7.pms.handler.MemberAddHandler;
import com.cosre7.pms.handler.MemberDeleteHandler;
import com.cosre7.pms.handler.MemberDetailHandler;
import com.cosre7.pms.handler.MemberListHandler;
import com.cosre7.pms.handler.MemberUpdateHandler;
import com.cosre7.pms.handler.TrainingAddHandler;
import com.cosre7.pms.handler.TrainingDeleteHandler;
import com.cosre7.pms.handler.TrainingDetailHandler;
import com.cosre7.pms.handler.TrainingListHandler;
import com.cosre7.pms.handler.TrainingUpdateHandler;
import com.cosre7.util.Prompt;

public class App {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) throws CloneNotSupportedException {

    LinkedList<Member> memberList = new LinkedList<>();
    MemberAddHandler memberAddHandler = new MemberAddHandler(memberList);
    MemberListHandler memberListHandler = new MemberListHandler(memberList);
    MemberDetailHandler memberDetailHandler = new MemberDetailHandler(memberList);
    MemberUpdateHandler memberUpdateHandler = new MemberUpdateHandler(memberList);
    MemberDeleteHandler memberDeleteHandler = new MemberDeleteHandler(memberList);

    LinkedList<Board> boardList = new LinkedList<>();
    BoardAddHandler boardAddHandler = new BoardAddHandler(boardList, memberListHandler);
    BoardListHandler boardListHandler = new BoardListHandler(boardList);
    BoardDetailHandler boardDetailHandler = new BoardDetailHandler(boardList);
    BoardUpdateHandler boardUpdateHandler = new BoardUpdateHandler(boardList, memberListHandler);
    BoardDeleteHandler boardDeleteHandler = new BoardDeleteHandler(boardList);

    ArrayList<Diet> dietList = new ArrayList<>();
    DietAddHandler dietAddHandler = new DietAddHandler(dietList, memberListHandler);
    DietListHandler dietListHandler = new DietListHandler(dietList);
    DietDetailHandler dietDetailHandler = new DietDetailHandler(dietList);
    DietUpdateHandler dietUpdateHandler = new DietUpdateHandler(dietList, memberListHandler);
    DietDeleteHandler dietDeleteHandler = new DietDeleteHandler(dietList);

    ArrayList<Training> trainingList = new ArrayList<>();
    TrainingAddHandler trainingAddHandler = new TrainingAddHandler(trainingList, memberListHandler);
    TrainingListHandler trainingListHandler = new TrainingListHandler(trainingList);
    TrainingDetailHandler trainingDetailHandler = new TrainingDetailHandler(trainingList);
    TrainingUpdateHandler trainingUpdateHandler = new TrainingUpdateHandler(trainingList, memberListHandler);
    TrainingDeleteHandler trainingDeleteHandler = new TrainingDeleteHandler(trainingList);

    ArrayList<Body> bodyList = new ArrayList<>();
    BodyAddHandler bodyAddHandler = new BodyAddHandler(bodyList, memberListHandler);
    BodyListHandler bodyListHandler = new BodyListHandler(bodyList);
    BodyDetailHandler bodyDetailHandler = new BodyDetailHandler(bodyList);
    BodyUpdateHandler bodyUpdateHandler = new BodyUpdateHandler(bodyList, memberListHandler);
    BodyDeleteHandler bodyDeleteHandler = new BodyDeleteHandler(bodyList);

    loop:
      while (true) {

        String input = Prompt.inputString("메인> ");
        System.out.println();

        if (input.length() == 0) 
          continue;

        commandStack.push(input);
        commandQueue.offer(input);

        try {
          switch (input) {
            case "/member/add":
              memberAddHandler.add();
              break;
            case "/member/list":
              memberListHandler.list();
              break;
            case "/member/detail":
              memberDetailHandler.detail();
              break;
            case "/member/update":
              memberUpdateHandler.update();
              break;
            case "/member/delete":
              memberDeleteHandler.delete();
              break;
            case "/diet/add":
              dietAddHandler.add();
              break;
            case "/diet/list":
              dietListHandler.list();
              break;
            case "/diet/detail":
              dietDetailHandler.detail();
              break;
            case "/diet/update":
              dietUpdateHandler.update();
              break;
            case "/diet/delete":
              dietDeleteHandler.delete();
              break;
            case "/training/add":
              trainingAddHandler.add();
              break;
            case "/training/list":
              trainingListHandler.list();
              break;
            case "/training/detail":
              trainingDetailHandler.detail();
              break;
            case "/training/update":
              trainingUpdateHandler.update();
              break;
            case "/training/delete":
              trainingDeleteHandler.delete();
              break;
            case "/body/add":
              bodyAddHandler.add();
              break;
            case "/body/list":
              bodyListHandler.list();
              break;
            case "/body/detail":
              bodyDetailHandler.detail();
              break;
            case "/body/update":
              bodyUpdateHandler.update();
              break;
            case "/body/delete":
              bodyDeleteHandler.delete();
              break;
            case "/board/add":
              boardAddHandler.add();
              break;
            case "/board/list":
              boardListHandler.list();
              break;
            case "/board/detail":
              boardDetailHandler.detail();
              break;
            case "/board/update":
              boardUpdateHandler.update();
              break;
            case "/board/delete":
              boardDeleteHandler.delete();
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
        } catch (Exception e) {
          System.out.println("-----------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n",
              e.getClass().getName(), e.getMessage());
          System.out.println("-----------------------------------------");
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