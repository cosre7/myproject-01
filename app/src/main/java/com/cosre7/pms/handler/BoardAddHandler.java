package com.cosre7.pms.handler;

import java.sql.Date;
import java.util.List;
import com.cosre7.pms.domain.Board;
import com.cosre7.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler {

  protected MemberValidatorHandler memberValidatorHandler;

  public BoardAddHandler(List<Board> boardList, MemberValidatorHandler memberValidatorHandler) {
    super(boardList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  public void add() {
    System.out.println("[게시글 작성]");

    Board b = new Board();

    b.setNo(Prompt.inputInt("번호 > "));
    b.setName(memberValidatorHandler.inputMember("이름(취소: 빈 문자열) > "));
    if (b.getName() == null) {
      System.out.println("게시글 작성을 취소합니다.");
      return;
    }

    while (true) {
      b.setCategory(Prompt.inputString("카테고리를 선택해주세요\n1: 추천 식재료\n2: 추천 레시피\n3: 추천 외식메뉴\n> "));
      if (b.getCategory().equals("1") || b.getCategory().equals("2") || b.getCategory().equals("3")) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }
    b.setTitle(Prompt.inputString("제목 > "));
    b.setContent(Prompt.inputString("내용 > "));                            
    b.setRegisteredDate(new Date(System.currentTimeMillis()));

    boardList.add(b);

    System.out.println("게시글을 등록하였습니다.");
  }
}
