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
    b.title = Prompt.inputString("제목 > ");
    b.writer = Prompt.inputString("작성자 > ");
    b.registeredDate = new Date(System.currentTimeMillis());

    this.boards[this.size++] = b;
  }

  public void list() {
    System.out.println("[게시글 목록]");

    for (int i = 0; i < this.size; i++) {
      Board b = this.boards[i];
      System.out.printf("번호: %d\n제목: %s\n작성자: %s\n등록일: %s\n조회수: %d",
          b.no, b.title, b.writer, b.registeredDate, b.viewCount);
    }
  }

}
