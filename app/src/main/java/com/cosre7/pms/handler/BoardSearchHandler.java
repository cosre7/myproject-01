package com.cosre7.pms.handler;

import java.util.ArrayList;
import java.util.List;
import com.cosre7.pms.domain.Board;
import com.cosre7.util.Prompt;

public class BoardSearchHandler extends AbstractBoardHandler {

  public BoardSearchHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    String keyword = Prompt.inputString("검색어 > ");

    if (keyword.length() == 0) {
      System.out.println("검색어를 입력하세요.");
      return;
    }

    ArrayList<Board> list = new ArrayList<>();

    Board[] boards = boardList.toArray(new Board[boardList.size()]);
    for (Board b : boards) {
      if (b.getTitle().contains(keyword) ||
          b.getContent().contains(keyword) ||
          b.getName().contains(keyword)) {
        list.add(b);
      }
    }

    if (list.isEmpty()) {
      System.out.println("검색어에 해당하는 게시글이 없습니다.");
      return;
    }

    for (Board b : list) {
      System.out.printf("%d - [%s] %s |등록일: %s 추천수: %d\n",
          b.getNo(), 
          getBoardLabel(b.getCategory()), 
          b.getTitle(), 
          b.getRegisteredDate(), 
          b.getLikeCount());
    }
  }
}
