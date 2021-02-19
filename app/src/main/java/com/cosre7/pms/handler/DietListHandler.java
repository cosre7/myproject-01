package com.cosre7.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.cosre7.pms.domain.Diet;

public class DietListHandler extends AbstractDietHandler {

  public DietListHandler(List<Diet> dietList) {
    super(dietList);
  }

  @Override
  public void service() {
    System.out.println("[식단일지 목록]");

    Iterator<Diet> iterator = dietList.iterator();

    while (iterator.hasNext()) {
      Diet d = iterator.next();
      System.out.printf("[%d] %s 날짜: %s 시간: %s시 - [%d] %s\n", 
          d.getNo(), 
          d.getName(), 
          d.getDate(), 
          d.getTime(), 
          d.getStatus(), 
          getChoiceLabel(d.getChoice()));
    }
  }
}





