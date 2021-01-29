package com.cosre7.pms.handler;

import java.sql.Date;
import com.cosre7.util.Prompt;

public class MemberHandler {
  static final int LENGTH = 100;
  static int[] no = new int[LENGTH];
  static String[] name = new String[LENGTH];
  static String[] password = new String[LENGTH];
  static String[] photo = new String[LENGTH];
  static String[] tel = new String[LENGTH];
  static Date[] registeredDate = new Date[LENGTH];
  static int size = 0;

  static void add() {
    System.out.println("[회원 등록]");

    no[size] = Prompt.inputInt("번호? ");
    name[size] = Prompt.inputString("이름? ");
    password[size] = Prompt.inputString("암호? ");
    photo[size] = Prompt.inputString("사진? ");
    tel[size] = Prompt.inputString("전화? ");
    registeredDate[size] = new Date(System.currentTimeMillis());
    size++;
  }

  static void list() {
    System.out.println("[회원 목록]");

    //번호, 이름, 전화, 가입일
    for (int i = 0; i < size; i++) {
      System.out.printf("%d, %s, %s, %s\n",
          no[i], name[i], tel[i], registeredDate[i]);
    }
  }
}
