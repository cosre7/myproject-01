package com.cosre7.pms.handler;

import java.sql.Date;
import java.util.List;
import com.cosre7.pms.domain.Member;
import com.cosre7.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler {

  public MemberAddHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.setNo(Prompt.inputInt("번호 > "));
    m.setName(Prompt.inputString("이름 > "));
    m.setPassword(Prompt.inputString("암호 > "));
    m.setPhoto(Prompt.inputString("사진 > "));
    m.setTel(Prompt.inputString("전화 > "));
    m.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(m);

    System.out.println("회원을 등록하였습니다.");
  }
}
