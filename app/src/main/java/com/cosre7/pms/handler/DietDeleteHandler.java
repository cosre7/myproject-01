package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Prompt;

public class DietDeleteHandler extends AbstractDietHandler {

  public DietDeleteHandler(List<Diet> dietList) {
    super(dietList);
  }

  @Override
  public void service() {
    System.out.println("[식단일지 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Diet diet = findByNo(no);
    if (diet == null) {
      System.out.println("해당 번호의 식단일지가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      dietList.remove(diet);
      System.out.println("식단일지를 삭제하였습니다.");
    } else {
      System.out.println("식단일지 삭제를 취소하였습니다.");
    }
  }
}





