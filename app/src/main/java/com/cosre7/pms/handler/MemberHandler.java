package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.util.Prompt;

public class MemberHandler {

  static class Member {
    int no;
    String name;
    String password;
    String photo;
    String tel;
    Date registeredDate;
  }

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
}
