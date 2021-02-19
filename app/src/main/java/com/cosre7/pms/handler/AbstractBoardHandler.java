package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Board;

public abstract class AbstractBoardHandler implements Command {

  protected List<Board> boardList;

  public AbstractBoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  protected String getBoardLabel(String category) {
    switch (category) {
      case "1":
        return "추천 식재료";
      case "2":
        return "추천 레시피";
      default:
        return "추천 외식메뉴";
    }
  }

  protected Board findByNo(int boardNo) {
    Board[] list = boardList.toArray(new Board[boardList.size()]);
    for (Board b : list) {
      if (b.getNo() == boardNo) {
        return b;
      }
    }
    return null;
  }
}
