package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Iterator;
import com.cosre7.util.List;
import com.cosre7.util.Prompt;

public class DietHandler {

  private List<Diet> dietList = new List<>();

  private MemberHandler memberHandler;

  public DietHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void add() {
    System.out.println("[식단일지 작성]");

    Diet d = new Diet();
    d.setNo(Prompt.inputInt("번호 > "));

    d.setName(memberHandler.inputMember("이름(취소: 빈 문자열) > "));
    if (d.getName() == null) {
      System.out.println("식단일지 작성을 취소합니다.");
      return;
    }

    d.setDate(Prompt.inputDate("날짜 > "));
    d.setTime(Prompt.inputString("시간 > "));
    d.setFood(inputFoods("먹은 음식(완료: 빈 문자열) > "));

    while (true) {
      d.setStatus(Prompt.inputInt("포만감 정도 (1~5) > "));
      if (d.getStatus() < 1 || d.getStatus() > 5) {
        System.out.println("다시 입력해주세요");
      } else {
        break;
      }
    }

    while (true) {
      d.setChoice(Prompt.inputInt("종류\n1: 식단\n2: 치팅\n> "));
      if (d.getChoice() == 1 || d.getChoice() == 2) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }
    dietList.add(d);
  }

  public void list() throws CloneNotSupportedException {
    System.out.println("[식단일지 목록]");

    Iterator<Diet> iterator = dietList.iterator();

    while (iterator.hasNext()) {
      Diet d = iterator.next();
      System.out.printf("[%d] %s 날짜: %s 시간: %s시 - [%d] %s\n", 
          d.getNo(), 
          d.getName(), 
          d.getDate(), 
          d.getTime(), 
          d.getStatus(), 
          getChoiceLabel(d.getChoice()));
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
    System.out.printf("%s [%s 시]\n", diet.getDate(), diet.getTime());
    System.out.printf("이름: %s\n", diet.getName());
    System.out.printf("음식: %s\n", diet.getFood());
    System.out.printf("포만감 정도: [%d] %s\n", diet.getStatus(), getStatusLabel(diet.getStatus()));
    System.out.printf("종류: %s\n", getChoiceLabel(diet.getChoice()));
  }

  public void update() {
    System.out.println("[식단일지 변경]");

    int no = Prompt.inputInt("번호 > ");

    Diet diet = findByNo(no);
    if (diet == null) {
      System.out.println("해당 번호의 식단일지가 없습니다.");
      return;
    }

    String user = memberHandler.inputMember(String.format("이름(%s, 취소: 빈 문자열) > ", diet.getName()));
    if (user == null) {
      System.out.println("식단일지 작성을 취소합니다.");
      return;
    }

    //날짜, 시간, 먹은 음식, 포만감 정도, 종류
    Date date = Prompt.inputDate(String.format("날짜(%s) > ", diet.getDate()));
    String time = Prompt.inputString(String.format("시간(%s 시) > ", diet.getTime()));
    String foods = inputFoods(String.format("먹은 음식(완료: 빈 문자열) > ", diet.getFood()));

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
      diet.setName(user);
      diet.setDate(date);
      diet.setTime(time);
      diet.setFood(foods);
      diet.setStatus(status);
      diet.setChoice(choice);
      System.out.println("식단일지를 변경하였습니다.");

    } else {
      System.out.println("식단일지 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[식단일지 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Diet diet = findByNo(no);
    if (diet == null) {
      System.out.println("해당 번호의 식단일지가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      dietList.delete(diet);
      System.out.println("식단일지를 삭제하였습니다.");
    } else {
      System.out.println("식단일지 삭제를 취소하였습니다.");
    }
  }

  private String getStatusLabel(int status) {
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

  private String getChoiceLabel(int choice) {
    switch (choice) {
      case 1:
        return "훌륭해요!";
      default:
        return "치팅이라니!!!";
    }
  }

  private String inputFoods(String promptTitle) {
    String foods = "";
    while (true) {
      String eat = Prompt.inputString(promptTitle);
      if (eat.length() == 0) {
        break;
      } else {
        if (!foods.isEmpty()) {
          foods += ", ";
        }
        foods += eat;
      }
    }
    return foods;
  }

  private Diet findByNo(int dietNo) {
    Diet[] list = dietList.toArray(new Diet[dietList.size()]);
    for (Diet d : list) {
      if (d.getNo() == dietNo) {
        return d;
      }
    }
    return null;
  }
}





