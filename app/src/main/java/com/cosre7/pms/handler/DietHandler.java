package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Prompt;

public class DietHandler {

  static final int DEFAULT_CAPACITY  = 100;

  MemberHandler memberList;

  Diet[] diets = new Diet[DEFAULT_CAPACITY];
  int size = 0;

  public DietHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }

  public void add() {
    System.out.println("[식단일지 작성]");

    Diet d = new Diet();
    d.no = Prompt.inputInt("번호 > ");

    d.name = inputMember("이름(취소: 빈 문자열) ");
    if (d.name == null) {
      System.out.println("식단일지 작성을 취소합니다.");
      return;
    }

    d.date = Prompt.inputDate("날짜 > ");
    d.time = Prompt.inputString("시간 > ");

    d.food = "";
    while (true) {
      String eat = Prompt.inputString("먹은 음식(완료: 빈 문자열) > ");

      if (eat.length() == 0) {
        break;
      } else {
        if (!d.food.isEmpty()) {
          d.food += ", ";
        }
        d.food += eat;
      }
    }

    while (true) {
      d.status = Prompt.inputInt("포만감 정도 (1~5) > ");
      if (d.status < 1 || d.status > 5) {
        System.out.println("다시 입력해주세요");
      } else {
        break;
      }
    }

    while (true) {
      d.choice = Prompt.inputInt("종류\n1: 식단\n2: 치팅\n> ");
      if (d.choice == 1 || d.choice == 2) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }

    if (this.size >= this.diets.length) {
      Diet[] arr = new Diet[this.size + (this.size >> 1)];

      for (int i = 0; i < this.size; i++) {
        arr[i] = this.diets[i];
      }
      diets = arr;
    }
    this.diets[this.size++] = d;
  }

  public void list() {
    System.out.println("[식단일지 목록]");

    for(int i = 0; i < this.size; i++) {
      Diet d = this.diets[i];

      System.out.printf("[%d] %s 날짜: %s 시간: %s시 - [%d] %s\n", 
          d.no, d.name, d.date, d.time, d.status, getChoiceLabel(d.choice));
    }
  }

  public void detail() {
    System.out.println("[식단일지 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    Diet diet = findByNo(no);
    if (diet == null) {
      System.out.println("해당 번호의 식단일지가 없습니다.");
      return;
    }

    //이름, 날짜, 시간, 먹은 음식, 포만감 정도, 종류
    System.out.printf("%s [%s 시]\n", diet.date, diet.time);
    System.out.printf("이름: %s\n", diet.name);
    System.out.printf("음식: %s\n", diet.food);
    System.out.printf("포만감 정도: [%d] %s\n", getStatusLabel(diet.status));
    System.out.printf("종류: %s\n", getChoiceLabel(diet.choice));
  }

  public void update() {
    System.out.println("[식단일지 변경]");

    int no = Prompt.inputInt("번호 > ");

    Diet diet = findByNo(no);
    if (diet == null) {
      System.out.println("해당 번호의 식단일지가 없습니다.");
      return;
    }

    String user = inputMember(String.format("이름(%s, 취소: 빈 문자열) > ", diet.name));
    if (user == null) {
      System.out.println("식단일지 작성을 취소합니다.");
      return;
    }

    //날짜, 시간, 먹은 음식, 포만감 정도, 종류
    Date date = Prompt.inputDate(String.format("날짜(%s) > ", diet.date));
    String time = Prompt.inputString(String.format("시간(%s 시) > ", diet.time));

    String food = "";
    while (true) {
      String eat = Prompt.inputString("먹은 음식(완료: 빈 문자열) > ");
      if (eat.length() == 0) {
        break;
      } else {
        if (!food.isEmpty()) {
          food += ", ";
        }
        food += eat;
      }
    }

    int status = 0;
    while (true) {
      int full = Prompt.inputInt("포만감 정도 (1~5) > ");
      if (full < 1 || full > 5) {
        System.out.println("다시 입력해주세요");
      } else {
        status = full;
        break;
      }
    }

    int choice = 0;
    while (true) {
      int kind = Prompt.inputInt("종류\n1: 식단\n2: 치팅\n> ");
      if (kind == 1 || kind == 2) {
        choice = kind;
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      diet.name = user;
      diet.date = date;
      diet.time = time;
      diet.food = food;
      diet.status = status;
      diet.choice = choice;
      System.out.println("식단일지를 변경하였습니다.");

    } else {
      System.out.println("식단일지 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[식단일지 삭제]");

    int no = Prompt.inputInt("번호 > ");

    int i = indexOf(no);
    if (i == -1) {
      System.out.println("해당 번호의 식단일지가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      for (int x = i + 1; x < this.size; x++) {
        this.diets[x - 1] = this.diets[x];
      }
      diets[--this.size] = null;

      System.out.println("식단일지를 삭제하였습니다.");
    } else {
      System.out.println("식단일지 삭제를 취소하였습니다.");
    }
  }

  int indexOf(int dietNo) {
    for (int i = 0; i < this.size; i++) {
      Diet diet = this.diets[i];
      if (diet.no == dietNo) {
        return i;
      }
    }
    return -1;
  }

  Diet findByNo(int dietNo) {
    int i = indexOf(dietNo);
    if (i == -1)
      return null;
    else
      return this.diets[i];
  }

  String inputMember(String promptTitle) {
    while (true) {
      String name = Prompt.inputString(promptTitle);
      if (name.length() == 0) {
        return null;
      }
      if (this.memberList.exist(name)) {
        return name;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  String getStatusLabel(int status) {
    switch (status) {
      case 1:
        return "나는 아직 배고프다";
      case 2:
        return "먹긴 했어요";
      case 3:
        return "적당해요";
      case 4:
        return "배가 불러요~~";
      default:
        return "터질 것 같아요";
    }
  }

  String getChoiceLabel(int choice) {
    switch (choice) {
      case 1:
        return "훌륭해요!";
      default:
        return "치팅이라니!!!";
    }
  }
}





