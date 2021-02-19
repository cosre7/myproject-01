package com.cosre7.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.cosre7.pms.domain.Member;

public class MemberListHandler extends AbstractMemberHandler {

  public MemberListHandler(List<Member> memberList) {
    super(memberList);
  }

  public void list() throws CloneNotSupportedException {
    System.out.println("[회원 목록]");

    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member m = iterator.next();
      //번호, 이름, 전화, 가입일
      System.out.printf("%d, %s, %s, %s\n",
          m.getNo(), 
          m.getName(), 
          m.getTel(), 
          m.getRegisteredDate());
    }
  }
}
