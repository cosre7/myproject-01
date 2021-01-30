package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Training;
import com.cosre7.util.Prompt;

public class TrainingHandler {

  static final int LENGTH = 100;
  Training[] trainings = new Training[LENGTH];
  int size = 0;

  public void add() {
    System.out.println("[운동일지 작성]");

    Training t = new Training();

    t.no = Prompt.inputInt("번호 > ");
    while (true) {
      String name = Prompt.inputString("이름(취소: 빈 문자열) > ");
      if (isMember(name)) {
        t.name = name;
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
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
      t.body = Prompt.inputInt("종류1\n1: 상체\n2: 하체\n3: 전신\n> ");
      if (t.body == 1 || t.body == 2 || t.body == 3) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }

    while (true) {
      t.training = Prompt.inputInt("종류2\n1: 근력\n2: 유산소\n3: 혼합\n> ");
      if (t.training == 1 || t.training == 2 || t.training == 3) {
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
      String bodyLabel = null;
      switch (t.body) {
        case 1:
          bodyLabel = "상체 운동";
          break;
        case 2:
          bodyLabel = "하체 운동";
          break;
        default :
          bodyLabel = "전신 운동";
      }
      String trainingLabel = null;
      switch (t.training) {
        case 1:
          trainingLabel = "근력 운동";
          break;
        case 2:
          trainingLabel = "유산소 운동";
          break;
        case 3:
          trainingLabel = "혼합 운동";
      }
      String intensityLabel = null;
      switch (t.intensity) {
        case 1:
          intensityLabel = "껌이에요";
          break;
        case 2:
          intensityLabel = "한 듯 안한 듯";
          break;
        case 3:
          intensityLabel = "적당해요";
          break;
        case 4:
          intensityLabel = "근육통 예상";
          break;
        case 5:
          intensityLabel = "조상님을 뵈었어요";
      }
      System.out.printf("번호: %d\n이름: %s\n날짜: %s\n운동 목록: %s\n종류1: %s\n종류2: %s\n소요 시간: %.1f 시간\n운동 강도: %s[%d]\n", 
          t.no,
          t.name,
          t.date, 
          t.list, 
          bodyLabel, 
          trainingLabel, 
          t.runTime, 
          intensityLabel, 
          t.intensity);
      System.out.println();
    }
  }

  static boolean isMember(String name) {
    for (int i = 0; i < MemberHandler.size; i++) {
      if (name.equals(MemberHandler.members[i].name)) {
        return true;
      }
    }
    return false;
  }

}
