package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Board;
import com.cosre7.util.Prompt;

public class BoardHandler {

  static final int LENGTH = 100;
  Board[] boards = new Board[LENGTH];
  int size = 0;

  public void add() {
    System.out.println("[게시글 작성]");

    Board b = new Board();

    b.no = Prompt.inputInt("번호 > ");
    while (true) {
      String name = Prompt.inputString("이름(취소: 빈 문자열) > ");
      if (name.length() == 0) {
        System.out.println("게시글 작성을 취소합니다.");
        return;
      }
      if (MemberHandler.exist(name)) {
        b.name = name;
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
    while (true) {
      b.category = Prompt.inputString("카테고리를 선택해주세요\n1: 추천 식재료\n2: 추천 레시피\n3: 추천 외식메뉴\n> ");
      if (b.category.equals("1") || b.category.equals("2") || b.category.equals("3")) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }
    b.title = Prompt.inputString("제목 > ");
    b.content = Prompt.inputString("내용 > ");                            
    b.registeredDate = new Date(System.currentTimeMillis());

    this.boards[this.size++] = b;
  }

  public void list() {
    System.out.println("[게시글 목록]");

    for (int i = 0; i < this.size; i++) {
      Board b = this.boards[i];

      if (b == null)
        continue;

      String boardLabel = null;
      switch (b.category) {
        case "1":
          boardLabel = "추천 식재료";
          break;
        case "2":
          boardLabel = "추천 레시피";
          break;
        case "3":
          boardLabel = "추천 외식메뉴";
      }
      System.out.printf("%d - [%s] %s\n등록일: %s 추천수: %d\n",
          b.no, boardLabel, b.title, b.registeredDate, b.likeCount);
      System.out.println();
    }
  }

  public void detail() {
    System.out.println("[게시글 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    for (int i = 0; i < this.size; i++) {
      Board board = this.boards[i];
      String boardLabel = null;
      switch (board.category) {
        case "1":
          boardLabel = "추천 식재료";
          break;
        case "2":
          boardLabel = "추천 레시피";
          break;
        case "3":
          boardLabel = "추천 외식메뉴";
      }
      if (board != null && board.no == no) {
        System.out.printf("[%s] %s\n", board.title);
        System.out.printf("내용: %s\n", board.content);
        System.out.printf("등록일: %s\n", board.registeredDate);
        return;
      }
    }
    System.out.println("해당 번호의 게시글이 없습니다.");
  }

  public void update() {
    System.out.println("[게시글 변경]");

    int no = Prompt.inputInt("번호 > ");

    for (int i = 0; i < this.size; i++) {
      Board board = this.boards[i];
      if (board != null && board.no == no) {
        String title = Prompt.inputString(String.format("제목(%s) > ",board.title));
        String content = Prompt.inputString(String.format("내용(%s) > ", board.content));

        String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

        if (input.equalsIgnoreCase("Y")) {
          board.title = title;
          board.content = content;
          System.out.println("게시글을 변경하였습니다.");
        } else {
          System.out.println("게시글 변경을 취소하였습니다.");
        }
        return;
      }
    }
    System.out.println("해당 번호의 게시글이 없습니다.");
  }

  public void delete() {
    System.out.println("[게시글 삭제]");

    int no = Prompt.inputInt("번호 > ");

    for (int i = 0; i < this.size; i++) {
      Board board = this.boards[i];
      if (board != null && board.no == no) {
        String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

        if (input.equalsIgnoreCase("Y")) {
          this.boards[i] = null;
          System.out.println("게시글을 삭제하였습니다.");

        } else {
          System.out.println("게시글 삭제를 취소하였습니다.");
        }
        return;
      }
    }
    System.out.println("해당 번호의 게시글이 없습니다.");
  }

}
