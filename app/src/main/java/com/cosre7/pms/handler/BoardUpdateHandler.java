package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Board;
import com.cosre7.util.Prompt;

public class BoardUpdateHandler extends AbstractBoardHandler {

  public BoardUpdateHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 변경]");

    int no = Prompt.inputInt("번호 > ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    // 카테고리, 제목, 내용
    String category = Prompt.inputString(String.format("카테고리([%d] %s) > ", board.getCategory(), getBoardLabel(board.getCategory())));
    String title = Prompt.inputString(String.format("제목(%s) > ", board.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s) > ", board.getContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      board.setCategory(category);
      board.setTitle(title);
      board.setContent(content);
      System.out.println("게시글을 변경하였습니다.");

    } else {
      System.out.println("게시글 변경을 취소하였습니다.");
    }
  }
}
