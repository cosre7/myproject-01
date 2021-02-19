package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Board;
import com.cosre7.util.Prompt;

public class BoardDetailHandler extends AbstractBoardHandler {

  public BoardDetailHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    // 카테고리, 제목, 내용, 이름, 등록일
    System.out.printf("%d번\n", board.getNo());
    System.out.printf("[%s] %s\n", getBoardLabel(board.getCategory()), board.getTitle());
    System.out.printf("> %s\n", board.getContent());
    System.out.printf("%s - %s\n", board.getName(), board.getRegisteredDate());
  }
}
