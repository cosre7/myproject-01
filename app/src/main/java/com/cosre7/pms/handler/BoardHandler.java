package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.util.Prompt;

public class BoardHandler {
  static class Board {
    int no;
    String title;
    String content;
    String writer;
    Date registeredDate;
    int viewCount;
  }

  static final int LENGTH = 100;
  static Board[] boards = new Board[LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("[게시글 작성]");

    Board b = new Board();

    b.no = Prompt.inputInt("번호 > ");
    b.title = Prompt.inputString("제목 > ");
    b.writer = Prompt.inputString("작성자 > ");
    b.registeredDate = new Date(System.currentTimeMillis());

    boards[size++] = b;
  }

  public static void list() {
    System.out.println("[게시글 목록]");

    for (int i = 0; i < size; i++) {
      Board b = boards[i];
      System.out.printf("번호: %d\n제목: %s\n작성자: %s\n등록일: %s\n조회수: %d",
          b.no, b.title, b.writer, b.registeredDate, b.viewCount);
    }
  }

}
