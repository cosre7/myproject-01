package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Training;
import com.cosre7.util.Prompt;

public class TrainingHandler {

  MemberHandler memberList;

  Node first;
  Node last;
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

    Node node = new Node(t);

    if (last == null) {
      last = node;
      first = node;
    } else {
      last.next = node;
      node.prev = last;
      last = node;
    }

    this.size++;
  }

  public void list() {
    System.out.println("[운동일지 목록]");

    Node cursor = first;

    while (cursor != null) {
      Training t = cursor.training;
      System.out.printf("번호: %d 이름: %s 날짜: %s\n", t.no, t.name, t.date); 

      cursor = cursor.next;
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

    String list = "";
    while (true) {
      String exercise = Prompt.inputString("운동 리스트(완료: 빈 문자열) > ");
      if (exercise.length() == 0) {
        break;
      } else {
        if (!list.isEmpty()) {
          list += ", ";
        } 
        list += exercise;
      }
    }

    int kind = Prompt.inputInt(String.format("종류1([%d] %s) > ", training.kind, getKindLabel(training.kind)));
    int type = Prompt.inputInt(String.format("종류2([%d] %s) > ", training.type, getTypeLabel(training.type)));
    double time = Prompt.inputDouble(String.format("소요시간(%.2f 시간) > ", training.runTime));
    int intensity = Prompt.inputInt(String.format("운동강도([%d] %s) > ", training.intensity, getIntensityLabel(training.intensity)));

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
    System.out.println("[운동일지 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Training training  = findByNo(no);
    if (training == null) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      Node cursor = first;
      while (cursor != null) {
        if (cursor.training == training) {
          this.size--;
          if (first == last) {
            first = last = null;
            break;
          }
          if (cursor == first) {
            first = cursor.next;
            cursor.prev = null;
          } else {
            cursor.prev.next = cursor.next;
            if (cursor.next != null) {
              cursor.next.prev = cursor.prev;
            }
          }
          if (cursor == last) {
            last = cursor.prev;
          }
          break;
        }
        cursor = cursor.next;
      }
      System.out.println("운동일지를 삭제하였습니다.");
    } else {
      System.out.println("삭제를 취소하였습니다.");
    }
  }

  Training findByNo(int trainingNo) {
    Node cursor = first;
    while (cursor != null) {
      Training t = cursor.training;
      if (t.no == trainingNo) {
        return t;
      }
      cursor = cursor.next;
    }
    return null;
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

  static class Node {
    Training training;
    Node next;
    Node prev;

    Node(Training t) {
      this.training = t;
    }
  }
}