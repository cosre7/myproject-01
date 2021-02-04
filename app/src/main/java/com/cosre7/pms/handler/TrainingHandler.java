package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Training;
import com.cosre7.util.Prompt;

public class TrainingHandler {

  static final int DEFAULT_CAPACITY = 100;

  MemberHandler memberList;

  Training[] trainings = new Training[DEFAULT_CAPACITY];
  int size = 0;

  public TrainingHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }

  public void add() {
    System.out.println("[운동일지 작성]");

    Training t = new Training();
    t.no = Prompt.inputInt("번호 > ");
    t.name = inputMember("이름(취소: 빈 문자열) > ");
    if (t.name == null) {
      System.out.println("운동일지 작성을 취소합니다.");
      return;
    }
    t.date = Prompt.inputDate("날짜 > ");

    t.list = "";
    while (true) {
      String exercise = Prompt.inputString("운동 리스트(완료: 빈 문자열) > ");
      if (exercise.length() == 0) {
        break;
      } else {
        if (!t.list.isEmpty()) {
          t.list += ", ";
        } 
        t.list += exercise;
      }
    }

    while (true) {
      t.kind = Prompt.inputInt("종류1\n1: 상체\n2: 하체\n3: 전신\n> ");
      if (t.kind == 1 || t.kind == 2 || t.kind == 3) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }

    while (true) {
      t.type = Prompt.inputInt("종류2\n1: 근력\n2: 유산소\n3: 혼합\n> ");
      if (t.type == 1 || t.type == 2 || t.type == 3) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }
    t.runTime = Prompt.inputDouble("소요시간 > ");
    t.intensity = Prompt.inputInt("운동 강도 (1~5) > ");

    this.trainings[this.size++] = t;
  }

  public void list() {
    System.out.println("[운동일지 목록]");

    for (int i = 0; i < this.size; i++) {
      Training t = this.trainings[i];
      System.out.printf("번호: %d\n이름: %s\n날짜: %s\n운동 목록: %s\n종류1: %s\n종류2: %s\n소요 시간: %.1f 시간\n운동 강도: %s[%d]\n", 
          t.no,
          t.name,
          t.date, 
          t.list, 
          getKindLabel(t.kind), 
          getTypeLabel(t.type), 
          t.runTime, 
          getIntensityLabel(t.intensity), 
          t.intensity);
    }
  }

  public void detail() {
    System.out.println("[운동일지 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    Training training = findByNo(no);
    if (training == null) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    // 이름, 날짜, 운동 리스트, 종류1, 종류2, 소요시간, 운동강도
    System.out.printf("[%d] %s\n", training.no, training.name);
    System.out.printf("- 운동리스트 - \n %s\n", training.list);
    System.out.printf("%s | %s\n", getKindLabel(training.kind), getTypeLabel(training.type));
    System.out.printf("소요시간: %s 시간\n", training.runTime);
    System.out.printf("[%d] %s\n", training.intensity, getIntensityLabel(training.intensity));
  }

  public void update() {
    System.out.println("[운동일지 변경]");

    int no = Prompt.inputInt("번호 > ");

    Training training = findByNo(no);
    if (training == null) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    // 이름, 날짜, 운동 리스트, 종류1, 종류2, 소요시간, 운동강도
    String name = null;
    while (true) {
      name = Prompt.inputString(String.format("이름(%s, 취소: 빈 문자열) > ", training.name));
      if(name.length() == 0) {
        System.out.println("운동일지 변경을 취소합니다.");
        return;
      } else if (this.memberList.exist(name)) {
        break;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
      }
    }
    Date date = Prompt.inputDate(String.format("날짜(%s) > ", training.date));
    String list = Prompt.inputString(String.format("운동리스트 > ", training.list));
    int kind = Prompt.inputInt(String.format("종류1([%d] %s) > ", training.kind, getKindLabel(training.kind)));
    int type = Prompt.inputInt(String.format("종류2([%d] %s) > ", training.type, getTypeLabel(training.type)));
    double time = Prompt.inputDouble(String.format("소요시간(%.2f 시간) > ", training.runTime));
    int intensity = Prompt.inputInt(String.format("운동강도(%d) > ", getIntensityLabel(training.intensity)));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      training.name = name;
      training.date = date;
      training.list = list;
      training.kind = kind;
      training.type = type;
      training.runTime = time;
      training.intensity = intensity;
      System.out.println("운동일지를 변경하였습니다.");
    } else {
      System.out.println("운동일지 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[운동일지 변경]");

    int no = Prompt.inputInt("번호 > ");

    int i = indexOf(no);
    if (i == -1) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      for (int x = i + 1; x < this.size; x++) {
        this.trainings[x - 1] = this.trainings[x];
      }
      trainings[--this.size] = null;

      System.out.println("운동일지를 삭제하였습니다.");
    } else {
      System.out.println("삭제를 취소하였습니다.");
    }
  }

  int indexOf(int trainingNo) {
    for (int i = 0; i < this.size; i++) {
      Training training = this.trainings[i];
      if (training.no == trainingNo) {
        return i;
      } 
    }
    return -1;
  }

  Training findByNo(int trainingNo) {
    int i = indexOf(trainingNo);
    if (i == -1)
      return null;
    else
      return this.trainings[i];
  }

  String inputMember(String promptTitle) {
    while (true) {
      String name = Prompt.inputString(promptTitle);
      if (name.length() == 0) {
        return null;
      } else if (this.memberList.exist(name)) {
        return name;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
      }
    }
  }

  String getKindLabel(int kind) {
    switch (kind) {
      case 1:
        return "상체";
      case 2:
        return "하체";
      default :
        return "전신";
    }
  }

  String getTypeLabel(int type) {
    switch (type) {
      case 1:
        return "근력";
      case 2:
        return "유산소";
      default:
        return "혼합";
    }
  }

  String getIntensityLabel(int intensity) {
    switch (intensity) {
      case 1:
        return "껌이에요";
      case 2:
        return "한 듯 안한 듯";
      case 3:
        return "적당해요";
      case 4:
        return "근육통 예상";
      default:
        return "조상님을 뵈었어요";
    }
  }
}