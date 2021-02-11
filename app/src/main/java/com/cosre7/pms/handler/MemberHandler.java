package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Member;
import com.cosre7.util.Prompt;

public class MemberHandler {

  private MemberList memberHandler = new MemberList();

  public MemberList getMemberList() {
    return this.memberHandler;
  }

  public void add() {
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.setNo(Prompt.inputInt("번호 > "));
    m.setName(Prompt.inputString("이름 > "));
    m.setPassword(Prompt.inputString("암호 > "));
    m.setPhoto(Prompt.inputString("사진 > "));
    m.setTel(Prompt.inputString("전화 > "));
    m.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberHandler.add(m);

    System.out.println("회원을 등록하였습니다.");
  }

  public void list() {
    System.out.println("[회원 목록]");

    Member[] members = memberHandler.toArray();

    for (Member m : members) {

      //번호, 이름, 전화, 가입일
      System.out.printf("%d, %s, %s, %s\n",
          m.getNo(), 
          m.getName(), 
          m.getTel(), 
          m.getRegisteredDate());
    }
  }

  public void detail() {
    System.out.println("[회원 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    Member member = memberHandler.get(no);
    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    // 이름, 사진, 전화, 가입일
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());
  }

  public void update() {
    System.out.println("[회원 변경]");

    int no = Prompt.inputInt("번호 > ");

    Member member = memberHandler.get(no);
    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString(String.format("이름(%s) > ", member.getName()));
    String photo = Prompt.inputString(String.format("사진 > ", member.getPhoto()));
    String tel = Prompt.inputString(String.format("전화(%s) > ", member.getTel()));
    Date registeredDate = Prompt.inputDate(String.format("가입일(%s) > ", member.getRegisteredDate()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)) > ");

    if (input.equalsIgnoreCase("Y")) {
      member.setName(name);
      member.setPhoto(photo);
      member.setTel(tel);
      member.setRegisteredDate(registeredDate);
      System.out.println("회원을 변경하였습니다.");
    } else {
      System.out.println("회원 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[회원 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Member member = memberHandler.get(no);
    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      memberHandler.delete(no);
      System.out.println("회원을 삭제하였습니다");
    } else {
      System.out.println("회원 삭제를 취소하였습니다.");
    }
  }

  String inputMember(String promptTitle) {
    while (true) {
      String name = Prompt.inputString(promptTitle);
      if (name.length() == 0) {
        return null;
      } else if (this.memberHandler.exist(name)) {
        return name;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
      }
    }
  }
}
