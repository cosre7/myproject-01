package com.cosre7.pms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
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

  static ArrayList<Member> memberList = new ArrayList<>();
  static ArrayList<Board> boardList = new ArrayList<>();
  static ArrayList<Diet> dietList = new ArrayList<>();
  static ArrayList<Training> trainingList = new ArrayList<>();
  static ArrayList<Body> bodyList = new ArrayList<>();

  public static void main(String[] args) {

    loadMembers();
    loadBoards();
    loadDiets();
    loadTrainings();
    loadBodys();

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
    saveMembers();
    saveBoards();
    saveDiets();
    saveTrainings();
    saveBodys();

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

  static void loadMembers() {
    try (BufferedReader in = new BufferedReader(new FileReader("members.csv"))) {
      String record = null;
      while ((record = in.readLine()) != null) {
        String[] fields = record.split(",");
        Member member = new Member();
        member.setNo(Integer.parseInt(fields[0]));
        member.setName(fields[1]);
        member.setPassword(fields[2]);
        member.setPhoto(fields[3]);
        member.setTel(fields[4]);
        member.setRegisteredDate(Date.valueOf(fields[5]));
        memberList.add(member);
      }
      System.out.println("멤버 로딩성공");

    } catch (Exception e) {
      System.out.println("멤버 로딩실패");
    }
  }

  static void saveMembers() {
    try (BufferedWriter out = new BufferedWriter(new FileWriter("members.csv"))) {
      for (Member member : memberList) {
        out.write(String.format("%d,%s,%s,%s,%s,%s\n",
            member.getNo(),
            member.getName(),
            member.getPassword(),
            member.getPhoto(),
            member.getTel(),
            member.getRegisteredDate()));
      }
      System.out.println("멤버 저장성공");

    } catch (Exception e) {
      System.out.println("멤버 저장실패");
    }
  }

  static void loadBoards() {
    try (BufferedReader in = new BufferedReader(new FileReader("boards.csv"))) {
      String record = null;
      while ((record = in.readLine()) != null) {
        String[] fields = record.split(",");
        Board board = new Board();
        board.setNo(Integer.parseInt(fields[0]));
        board.setName(fields[1]);
        board.setCategory(fields[2]);
        board.setTitle(fields[3]);
        board.setContent(fields[4]);
        board.setRegisteredDate(Date.valueOf(fields[5]));
        board.setLikeCount(Integer.parseInt(fields[6]));
        boardList.add(board);
      }
      System.out.println("게시글 로딩성공");

    } catch (Exception e) {
      System.out.println("게시글 로딩실패");
    }
  }

  static void saveBoards() {
    try (BufferedWriter out = new BufferedWriter(new FileWriter("boards.csv"))) {
      for (Board board : boardList) {
        out.write(String.format("%d,%s,%s,%s,%s,%s,%d\n", 
            board.getNo(),
            board.getName(),
            board.getCategory(),
            board.getTitle(),
            board.getContent(),
            board.getRegisteredDate(),
            board.getLikeCount()));
      }
      System.out.println("게시글 저장성공");

    } catch (Exception e) {
      System.out.println("게시글 저장실패");
    }
  }

  static void loadDiets() {
    // 식단일지
    try (BufferedReader in = new BufferedReader(new FileReader("diets.csv"))) {
      String record = null;
      while ((record = in.readLine()) != null) {
        String[] fields = record.split(",");
        Diet diet = new Diet();
        diet.setNo(Integer.parseInt(fields[0]));
        diet.setName(fields[1]);
        diet.setDate(Date.valueOf(fields[2]));
        diet.setTime(fields[3]);
        diet.setFood(fields[4]);
        diet.setStatus(Integer.parseInt(fields[5]));
        diet.setChoice(Integer.parseInt(fields[6]));
        dietList.add(diet);
      }
      System.out.println("식단일지 로딩성공");

    } catch (Exception e) {
      System.out.println("식단일지 로딩실패");
    }
  }

  static void saveDiets() {
    try (BufferedWriter out = new BufferedWriter(new FileWriter("diets.csv"))) {
      for (Diet diet : dietList) {
        out.write(String.format("%d,%s,%s,%s,%s,%d,%d", 
            diet.getNo(),
            diet.getName(),
            diet.getDate(),
            diet.getTime(),
            diet.getFood(),
            diet.getStatus(),
            diet.getChoice()));
      }
      System.out.println("식단일지 저장성공");

    } catch (Exception e) {
      System.out.println("식단일지 저장실패");
    }
  }

  static void loadTrainings() {
    // 운동일지
    try (BufferedReader in = new BufferedReader(new FileReader("trainings.csv"))) {
      String record = null;
      while ((record = in.readLine()) != null) {
        String[] fields = record.split(",");
        Training training = new Training();
        training.setNo(Integer.parseInt(fields[0]));
        training.setName(fields[1]);
        training.setDate(Date.valueOf(fields[2]));
        training.setList(fields[3]);
        training.setKind(Integer.parseInt(fields[4]));
        training.setType(Integer.parseInt(fields[5]));
        training.setIntensity(Integer.parseInt(fields[6]));
        training.setRunTime(Double.parseDouble(fields[7]));
        trainingList.add(training);
      }
      System.out.println("운동일지 로딩성공");

    } catch (Exception e) {
      System.out.println("운동일지 로딩실패");
    }
  }

  static void saveTrainings() {
    try (BufferedWriter out = new BufferedWriter(new FileWriter("trainings.csv"))) {
      for (Training training : trainingList) {
        out.write(String.format("%d,%s,%s,%s,%d,%d,%d,%f\n", 
            training.getNo(), 
            training.getName(),
            training.getDate(),
            training.getList(),
            training.getKind(),
            training.getType(),
            training.getIntensity(),
            training.getRunTime()));
      }
      System.out.println("운동일지 저장성공");

    } catch (Exception e) {
      System.out.println("운동일지 저장실패");
      e.printStackTrace();
    }
  }

  static void loadBodys() {
    // 신체지수
    try (BufferedReader in = new BufferedReader(new FileReader("bodys.csv"))) {
      String record = null;
      while ((record = in.readLine()) != null) {
        String[] fields = record.split(",");
        Body body = new Body();
        body.setNo(Integer.parseInt(fields[0]));
        body.setName(fields[1]);
        body.setDate(Date.valueOf(fields[2]));
        body.setHeight(Double.parseDouble(fields[3]));
        body.setWeight(Double.parseDouble(fields[4]));
        body.setBust(Double.parseDouble(fields[5]));
        body.setWaist(Double.parseDouble(fields[6]));
        body.setThigh(Double.parseDouble(fields[7]));
        body.setCalf(Double.parseDouble(fields[8]));
        bodyList.add(body);
      }
      System.out.println("신체지수 로딩성공");

    } catch (Exception e) {
      System.out.println("신체지수 로딩실패");
    }
  }

  static void saveBodys() {
    try (BufferedWriter out = new BufferedWriter(new FileWriter("bodys.csv"))) {
      for (Body body : bodyList) {
        out.write(String.format("%d,%s,%f,%f,%f,%f,%f,%f\n", 
            body.getNo(),
            body.getName(),
            body.getDate(),
            body.getHeight(),
            body.getWeight(),
            body.getBust(),
            body.getWaist(),
            body.getThigh(),
            body.getCalf()));
      }
      System.out.println("신체지수 저장성공");

    } catch (Exception e) {
      System.out.println("신체지수 저장실패");
    }
  }
}