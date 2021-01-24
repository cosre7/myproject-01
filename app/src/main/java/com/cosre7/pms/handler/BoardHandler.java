package com.cosre7.pms.handler;

import com.cosre7.util.Prompt;

public class BoardHandler {
  static class Board {
    int no;
    String title;
    String content;
    String writer;
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

    boards[size++] = b;
  }


}
