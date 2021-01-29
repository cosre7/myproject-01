package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Prompt;

public class DietHandler {

  static final int LENGTH  = 100;
  Diet[] diets = new Diet[LENGTH];
  int size = 0;
  int no = 1;

  public void service() {
    loop:
      while (true) {
        System.out.println("--메인 / 식단일지---------------------");
        System.out.println("1. 작성");
        System.out.println("2. 목록");
        System.out.println("3. 상세보기");
        System.out.println("4. 변경");
        System.out.println("5. 삭제");
        System.out.println("0. 이전 메뉴");

        String input = Prompt.inputString("식단일지> ");
        System.out.println();

        switch (input) {
          case "1":
            this.add();
            break;
          case "2":
            this.list();
            break;
          case "3":
            this.detail();
            break;
          case "4":
            this.update();
            break;
          case "5":
            //            this.delete();
            break;
          case "0":
            break loop;
          default:
            System.out.println("올바른 번호를 선택해주세요.");
        }
        System.out.println();
      }    
  }

  public void add() {
    System.out.println("[식단일지 작성]");

    Diet d = new Diet();
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

    d.status = Prompt.inputInt("포만감 정도 (1~5) > ");  
    while (true) {
      d.choice = Prompt.inputInt("종류\n1: 식단\n2: 치팅\n> ");
      if (d.choice == 1 || d.choice == 2) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }
    this.diets[this.size++] = d;
    d.no = no++;
  }

  public void list() {
    System.out.println("[식단일지 목록]");

    for(int i = 0; i < this.size; i++) {
      Diet d = this.diets[i];

      System.out.printf("[%d] 날짜: %s 시간: %s시\n", 
          d.no, d.date, d.time, d.food);
    }
  }

  public void detail() {
    System.out.println("[식단일지 상세보기]");

    for(int i = 0; i < this.size; i++) {
      Diet d = this.diets[i];
      System.out.printf("[%d] 날짜: %s 시간: %s시\n", 
          d.no, d.date, d.time);
    }

    int no = Prompt.inputInt("번호 > ");
    System.out.println();

    for(int i = 0; i < this.size; i++) {
      Diet d = this.diets[i];

      String label = null;
      switch (d.choice) {
        case 1:
          label = "훌륭해요!";
          break;
        case 2:
          label = "치팅이라니..";   
          break;

      }
      String statusLabel = null;
      switch (d.status) {
        case 1:
          statusLabel = "나는 아직 배고프다..";
          break;
        case 2:
          statusLabel = "배가 반정도 찬 느낌";
          break;
        case 3:
          statusLabel = "적당해요";
          break;
        case 4:
          statusLabel = "조금 과해요";
          break;
        case 5:
          statusLabel = "터질 것 같아요";
      }

      if (d.no == no) {
        System.out.printf("날짜: %s\n", d.date);
        System.out.printf("시간: %s\n", d.time);
        System.out.printf("먹은 음식: %s\n", d.food);
        System.out.printf("포만감: %s\n", statusLabel);
        System.out.printf("결론: %s\n", label);
        return;
      }
    }
    System.out.println("해당 번호의 식단 일지가 없습니다.");
  }

  public void update() {
    System.out.println("[식단 일지 변경]");
    int no = Prompt.inputInt("번호 > ");

    for(int i = 0; i < this.size; i++) {
      Diet d = this.diets[i];
      if (d.no == no) {
        Date date = Prompt.inputDate(String.format("날짜(%s)? ", d.date));
        String time = Prompt.inputString(String.format("시간(%s)? ", d.time));
        String food = Prompt.inputString(String.format("먹은 음식(%s)? ", d.food));

        String statusLabel = null;
        switch (d.status) {
          case 1:
            statusLabel = "나는 아직 배고프다..";
            break;
          case 2:
            statusLabel = "배가 반정도 찬 느낌";
            break;
          case 3:
            statusLabel = "적당해요";
            break;
          case 4:
            statusLabel = "조금 과해요";
            break;
          case 5:
            statusLabel = "터질 것 같아요";
        }
        int status = Prompt.inputInt(String.format("포만감(%d. %s)? ", d.status, statusLabel));

        String label = null;
        switch (d.choice) {
          case 1:
            label = "훌륭해요!";
            break;
          case 2:
            label = "치팅이라니..";   
        }
        int choice = 0;
        while (true) {
          choice = Prompt.inputInt(String.format("종류(%d. %s)? ", d.choice, label));
          if (choice != 1 || choice != 2) {
            System.out.println("올바른 번호가 아닙니다.");
          } else {
            break;
          }
        }
        String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

        if (input.equalsIgnoreCase("Y")) {
          d.date = date;
          d.time = time;
          d.food = food;
          d.status = status;
          d.choice = choice;
          System.out.println("식단 일지를 변경하였습니다.");
        } else {
          System.out.println("식단 일지 변경을 취소하였습니다.");
        }
        return;
      }
    }
    System.out.println("해당 번호의 식단 일지가 없습니다.");
  }
}





