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
import com.cosre7.pms.handler.BoardSearchHandler;
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
import com.cosre7.pms.handler.MemberValidatorHandler;
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
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    LinkedList<Board> boardList = new LinkedList<>();
    BoardAddHandler boardAddHandler = new BoardAddHandler(boardList, memberValidatorHandler);
    BoardListHandler boardListHandler = new BoardListHandler(boardList);
    BoardDetailHandler boardDetailHandler = new BoardDetailHandler(boardList);
    BoardUpdateHandler boardUpdateHandler = new BoardUpdateHandler(boardList);
    BoardDeleteHandler boardDeleteHandler = new BoardDeleteHandler(boardList);
    BoardSearchHandler boardSearchHandler = new BoardSearchHandler(boardList);

    ArrayList<Diet> dietList = new ArrayList<>();
    DietAddHandler dietAddHandler = new DietAddHandler(dietList, memberValidatorHandler);
    DietListHandler dietListHandler = new DietListHandler(dietList);
    DietDetailHandler dietDetailHandler = new DietDetailHandler(dietList);
    DietUpdateHandler dietUpdateHandler = new DietUpdateHandler(dietList, memberValidatorHandler);
    DietDeleteHandler dietDeleteHandler = new DietDeleteHandler(dietList);

    ArrayList<Training> trainingList = new ArrayList<>();
    TrainingAddHandler trainingAddHandler = new TrainingAddHandler(trainingList, memberValidatorHandler);
    TrainingListHandler trainingListHandler = new TrainingListHandler(trainingList);
    TrainingDetailHandler trainingDetailHandler = new TrainingDetailHandler(trainingList);
    TrainingUpdateHandler trainingUpdateHandler = new TrainingUpdateHandler(trainingList, memberValidatorHandler);
    TrainingDeleteHandler trainingDeleteHandler = new TrainingDeleteHandler(trainingList);

    ArrayList<Body> bodyList = new ArrayList<>();
    BodyAddHandler bodyAddHandler = new BodyAddHandler(bodyList, memberValidatorHandler);
    BodyListHandler bodyListHandler = new BodyListHandler(bodyList);
    BodyDetailHandler bodyDetailHandler = new BodyDetailHandler(bodyList);
    BodyUpdateHandler bodyUpdateHandler = new BodyUpdateHandler(bodyList, memberValidatorHandler);
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
              memberAddHandler.service();
              break;
            case "/member/list":
              memberListHandler.service();
              break;
            case "/member/detail":
              memberDetailHandler.service();
              break;
            case "/member/update":
              memberUpdateHandler.service();
              break;
            case "/member/delete":
              memberDeleteHandler.service();
              break;
            case "/diet/add":
              dietAddHandler.service();
              break;
            case "/diet/list":
              dietListHandler.service();
              break;
            case "/diet/detail":
              dietDetailHandler.service();
              break;
            case "/diet/update":
              dietUpdateHandler.service();
              break;
            case "/diet/delete":
              dietDeleteHandler.service();
              break;
            case "/training/add":
              trainingAddHandler.service();
              break;
            case "/training/list":
              trainingListHandler.service();
              break;
            case "/training/detail":
              trainingDetailHandler.service();
              break;
            case "/training/update":
              trainingUpdateHandler.service();
              break;
            case "/training/delete":
              trainingDeleteHandler.service();
              break;
            case "/body/add":
              bodyAddHandler.service();
              break;
            case "/body/list":
              bodyListHandler.service();
              break;
            case "/body/detail":
              bodyDetailHandler.service();
              break;
            case "/body/update":
              bodyUpdateHandler.service();
              break;
            case "/body/delete":
              bodyDeleteHandler.service();
              break;
            case "/board/add":
              boardAddHandler.service();
              break;
            case "/board/list":
              boardListHandler.service();
              break;
            case "/board/detail":
              boardDetailHandler.service();
              break;
            case "/board/update":
              boardUpdateHandler.service();
              break;
            case "/board/delete":
              boardDeleteHandler.service();
              break;
            case "/board/search":
              boardSearchHandler.service();
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