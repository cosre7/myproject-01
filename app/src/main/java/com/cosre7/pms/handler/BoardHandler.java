package com.cosre7.pms.handler;

import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import com.cosre7.pms.domain.Board;
import com.cosre7.util.Prompt;

public class BoardHandler {

  private LinkedList<Board> boardList = new LinkedList<>();

  private MemberHandler memberHandler;

  public BoardHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void add() {
    System.out.println("[게시글 작성]");

    Board b = new Board();

    b.setNo(Prompt.inputInt("번호 > "));
    b.setName(memberHandler.inputMember("이름(취소: 빈 문자열) > "));
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

  public void list() throws CloneNotSupportedException {
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

  public void detail() {
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

  public void update() {
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

  public void delete() {
    System.out.println("[게시글 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      boardList.remove(board);

      System.out.println("게시글을 삭제하였습니다.");
    } else {
      System.out.println("게시글 삭제를 취소하였습니다.");
    }
  }

  private String getBoardLabel(String category) {
    switch (category) {
      case "1":
        return "추천 식재료";
      case "2":
        return "추천 레시피";
      default:
        return "추천 외식메뉴";
    }
  }

  private Board findByNo(int boardNo) {
    Board[] list = boardList.toArray(new Board[boardList.size()]);
    for (Board b : list) {
      if (b.getNo() == boardNo) {
        return b;
      }
    }
    return null;
  }
}
