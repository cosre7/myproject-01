package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Prompt;

public class DietDetailHandler extends AbstractDietHandler {

  public DietDetailHandler(List<Diet> dietList) {
    super(dietList);
  }

  @Override
  public void service() {
    System.out.println("[식단일지 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    Diet diet = findByNo(no);
    if (diet == null) {
      System.out.println("해당 번호의 식단일지가 없습니다.");
      return;
    }

    //이름, 날짜, 시간, 먹은 음식, 포만감 정도, 종류
    System.out.printf("%s [%s 시]\n", diet.getDate(), diet.getTime());
    System.out.printf("이름: %s\n", diet.getName());
    System.out.printf("음식: %s\n", diet.getFood());
    System.out.printf("포만감 정도: [%d] %s\n", diet.getStatus(), getStatusLabel(diet.getStatus()));
    System.out.printf("종류: %s\n", getChoiceLabel(diet.getChoice()));
  }
}





