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
      System.out.printf("[%s] %s\n등록일: %s 추천수: %d\n",
          boardLabel, b.title, b.registeredDate, b.likeCount);
    }
    System.out.println();
  }

}
