package com.cosre7.pms.handler;

import java.sql.Date;
import java.util.List;
import com.cosre7.pms.domain.Member;
import com.cosre7.util.Prompt;

public class MemberUpdateHandler extends AbstractMemberHandler {

  public MemberUpdateHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[회원 변경]");

    int no = Prompt.inputInt("번호 > ");

    Member member = findByNo(no);
    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString(String.format("이름(%s) > ", member.getName()));
    String photo = Prompt.inputString(String.format("사진(%s) > ", member.getPhoto()));
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
}
