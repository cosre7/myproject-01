package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.pms.domain.Member;
import com.cosre7.util.Prompt;

public class MemberHandler {

  static final int LENGTH = 100;
  static Member[] members = new Member[LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.no = Prompt.inputInt("번호? ");
    m.name = Prompt.inputString("이름? ");
    m.password = Prompt.inputString("암호? ");
    m.photo = Prompt.inputString("사진? ");
    m.tel = Prompt.inputString("전화? ");
    m.registeredDate = new Date(System.currentTimeMillis());

    members[size++] = m;
  }

  public static void list() {
    System.out.println("[회원 목록]");

    //번호, 이름, 전화, 가입일
    for (int i = 0; i < size; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s\n",
          m.no, 
          m.name, 
          m.tel, 
          m.registeredDate);
    }
  }

  public static boolean exist(String name) {
    for (int i = 0; i < size; i++) {
      if (name.equals(members[i].name)) {
        return true;
      }
    }
    return false;
  }

  public void detail() {
    System.out.println("[회원 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    Member member = findByNo(no);
    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    // 이름, 사진, 전화, 가입일
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("사진: %s\n", member.photo);
    System.out.printf("전화: %s\n", member.tel);
    System.out.printf("가입일: %s\n", member.registeredDate);
  }

  public void update() {
    System.out.println("[회원 변경]");

    int no = Prompt.inputInt("번호 > ");

    Member member = findByNo(no);
    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString(String.format("이름(%s) > ", member.name));
    String photo = Prompt.inputString(String.format("사진 > ", member.photo));
    String tel = Prompt.inputString(String.format("전화(%s) > ", member.tel));
    Date registeredDate = Prompt.inputDate(String.format("가입일(%s) > ", member.registeredDate));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)) > ");

    if (input.equalsIgnoreCase("Y")) {
      member.name = name;
      member.photo = photo;
      member.tel = tel;
      member.registeredDate = registeredDate;
      System.out.println("회원을 변경하였습니다.");
    } else {
      System.out.println("회원 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[회원 삭제]");

    int no = Prompt.inputInt("번호 > ");

    int i = indexOf(no);
    if (i == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      for (int x = i + 1; x < this.size; x++) {
        this.members[x - 1] = this.members[x];
      }
      members[--this.size] = null;
      System.out.println("회원을 삭제하였습니다");
    } else {
      System.out.println("회원 삭제를 취소하였습니다.");
    }
  }

  int indexOf(int memberNo) {
    for (int i = 0; i <this.size; i++) {
      Member member = this.members[i];
      if (member.no == memberNo) {
        return i;
      }
    }
    return -1;
  }

  Member findByNo(int memberNo) {
    int i = indexOf(memberNo);
    if (i == -1)
      return null;
    else
      return this.members[i];
  }



}
