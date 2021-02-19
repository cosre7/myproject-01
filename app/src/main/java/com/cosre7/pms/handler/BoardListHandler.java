package com.cosre7.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.cosre7.pms.domain.Board;

public class BoardListHandler extends AbstractBoardHandler {

  public BoardListHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 목록]");

    Iterator<Board> iterator = boardList.iterator();

    while (iterator.hasNext()) {
      Board b = iterator.next();
      System.out.printf("%d - [%s] %s |등록일: %s 추천수: %d\n",
          b.getNo(), 
          getBoardLabel(b.getCategory()), 
          b.getTitle(), 
          b.getRegisteredDate(), 
          b.getLikeCount());
    }
  }
}
