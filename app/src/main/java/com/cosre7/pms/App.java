package com.cosre7.pms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.cosre7.pms.handler.Command;
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
    LinkedList<Board> boardList = new LinkedList<>();
    ArrayList<Diet> dietList = new ArrayList<>();
    ArrayList<Training> trainingList = new ArrayList<>();
    ArrayList<Body> bodyList = new ArrayList<>();

    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));
    commandMap.put("/member/detail", new MemberDetailHandler(memberList));
    commandMap.put("/member/update", new MemberUpdateHandler(memberList));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberList));
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    commandMap.put("/board/add", new BoardAddHandler(boardList, memberValidatorHandler));
    commandMap.put("/board/list", new BoardListHandler(boardList));
    commandMap.put("/board/detail", new BoardDetailHandler(boardList));
    commandMap.put("/board/update", new BoardUpdateHandler(boardList));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardList));
    commandMap.put("/board/search", new BoardSearchHandler(boardList));

    commandMap.put("/diet/add", new DietAddHandler(dietList, memberValidatorHandler));
    commandMap.put("/diet/list", new DietListHandler(dietList));
    commandMap.put("/diet/detail", new DietDetailHandler(dietList));
    commandMap.put("/diet/update", new DietUpdateHandler(dietList, memberValidatorHandler));
    commandMap.put("/diet/delete", new DietDeleteHandler(dietList));

    commandMap.put("/training/add", new TrainingAddHandler(trainingList, memberValidatorHandler));
    commandMap.put("/training/list", new TrainingListHandler(trainingList));
    commandMap.put("/training/detail", new TrainingDetailHandler(trainingList));
    commandMap.put("/training/update", new TrainingUpdateHandler(trainingList, memberValidatorHandler));
    commandMap.put("/training/delete", new TrainingDeleteHandler(trainingList));

    commandMap.put("/body/add", new BodyAddHandler(bodyList, memberValidatorHandler));
    commandMap.put("/body/list", new BodyListHandler(bodyList));
    commandMap.put("/body/detail", new BodyDetailHandler(bodyList));
    commandMap.put("/body/update", new BodyUpdateHandler(bodyList, memberValidatorHandler));
    commandMap.put("/body/delete", new BodyDeleteHandler(bodyList));

    loop:
      while (true) {

        String command = Prompt.inputString("메인> ");
        System.out.println();

        if (command.length() == 0) 
          continue;

        commandStack.push(command);
        commandQueue.offer(command);

        try {
          switch (command) {
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
              Command commandHandler = commandMap.get(command);

              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service();
              }
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