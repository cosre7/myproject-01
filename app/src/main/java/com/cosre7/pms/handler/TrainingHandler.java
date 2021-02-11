package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Training;
import com.cosre7.util.Prompt;

public class TrainingHandler {

  private TrainingList trainingList = new TrainingList();

  private MemberHandler memberHandler;

  public TrainingHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void add() {
    System.out.println("[운동일지 작성]");

    Training t = new Training();
    t.setNo(Prompt.inputInt("번호 > "));
    t.setName(memberHandler.inputMember("이름(취소: 빈 문자열) > "));
    if (t.getName() == null) {
      System.out.println("운동일지 작성을 취소합니다.");
      return;
    }
    t.setDate(Prompt.inputDate("날짜 > "));

    t.setList(inputExercise("운동 리스트(완료: 빈 문자열) > "));

    while (true) {
      t.setKind(Prompt.inputInt("종류1\n1: 상체\n2: 하체\n3: 전신\n> "));
      if (t.getKind() == 1 || t.getKind() == 2 || t.getKind() == 3) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }

    while (true) {
      t.setType(Prompt.inputInt("종류2\n1: 근력\n2: 유산소\n3: 혼합\n> "));
      if (t.getType() == 1 || t.getType() == 2 || t.getType() == 3) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }
    t.setRunTime(Prompt.inputDouble("소요시간 > "));
    t.setIntensity(Prompt.inputInt("운동 강도 (1~5) > "));

    trainingList.add(t);
  }

  public void list() {
    System.out.println("[운동일지 목록]");

    Training[] trainings = trainingList.toArray();
    for (Training t : trainings) {
      System.out.printf("번호: %d 이름: %s 날짜: %s\n", t.getNo(), t.getName(), t.getDate()); 
    }
  }

  public void detail() {
    System.out.println("[운동일지 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    Training training = trainingList.get(no);
    if (training == null) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    // 이름, 날짜, 운동 리스트, 종류1, 종류2, 소요시간, 운동강도
    System.out.printf("[%d] %s\n", training.getNo(), training.getName());
    System.out.printf("- 운동리스트 - \n %s\n", training.getList());
    System.out.printf("%s | %s\n", getKindLabel(training.getKind()), getTypeLabel(training.getType()));
    System.out.printf("소요시간: %s 시간\n", training.getRunTime());
    System.out.printf("[%d] %s\n", training.getIntensity(), getIntensityLabel(training.getIntensity()));
  }

  public void update() {
    System.out.println("[운동일지 변경]");

    int no = Prompt.inputInt("번호 > ");

    Training training = trainingList.get(no);
    if (training == null) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    // 이름, 날짜, 운동 리스트, 종류1, 종류2, 소요시간, 운동강도
    String name = memberHandler.inputMember(String.format("이름(%s, 취소: 빈 문자열) > ", training.getName()));
    if (name == null) {
      System.out.println("등록된 회원이 아닙니다.");
      return;
    }
    Date date = Prompt.inputDate(String.format("날짜(%s) > ", training.getDate()));
    String list = inputExercise(String.format("운동 리스트(완료: 빈 문자열) > ", training.getList()));
    int kind = Prompt.inputInt(String.format("종류1([%d] %s) > ", training.getKind(), getKindLabel(training.getKind())));
    int type = Prompt.inputInt(String.format("종류2([%d] %s) > ", training.getType(), getTypeLabel(training.getType())));
    double time = Prompt.inputDouble(String.format("소요시간(%.2f 시간) > ", training.getRunTime()));
    int intensity = Prompt.inputInt(String.format("운동강도([%d] %s) > ", training.getIntensity(), getIntensityLabel(training.getIntensity())));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      training.setName(name);
      training.setDate(date);
      training.setList(list);
      training.setKind(kind);
      training.setType(type);
      training.setRunTime(time);
      training.setIntensity(intensity);
      System.out.println("운동일지를 변경하였습니다.");
    } else {
      System.out.println("운동일지 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[운동일지 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Training training  = trainingList.get(no);
    if (training == null) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      trainingList.delete(no);
      System.out.println("운동일지를 삭제하였습니다.");
    } else {
      System.out.println("삭제를 취소하였습니다.");
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

  String inputExercise(String promptTitle) {
    String list = "";
    while (true) {
      String exercise = Prompt.inputString(promptTitle);
      if (exercise.length() == 0) {
        break;
      } else {
        if (!list.isEmpty()) {
          list += ", ";
        }
        list += exercise;
      }
    }
    return list;
  }
}