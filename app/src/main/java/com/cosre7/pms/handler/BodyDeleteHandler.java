package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Body;
import com.cosre7.util.Prompt;

public class BodyDeleteHandler extends AbstractBodyHandler {

  public BodyDeleteHandler(List<Body> bodyList) {
    super(bodyList);
  }

  @Override
  public void service() {
    System.out.println("[신체지수 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Body body = findByNo(no);
    if (body == null) {
      System.out.println("해당 번호의 신체지수가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      bodyList.remove(body);
      System.out.println("신체지수를 삭제하였습니다.");
    } else {
      System.out.println("신체지수 삭제를 취소하였습니다.");
    }
  }
}
