package com.cosre7.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.cosre7.pms.domain.Body;

public class BodyListHandler extends AbstractBodyHandler {

  public BodyListHandler(List<Body> bodyList) {
    super(bodyList);
  }

  @Override
  public void service() {
    System.out.println("[신체지수 목록]");

    Iterator<Body> iterator = bodyList.iterator();

    while (iterator.hasNext()) {
      Body b = iterator.next();
      System.out.printf("[%d] 이름: %s 날짜: %s\n", 
          b.getNo(), 
          b.getName(), 
          b.getDate()); 
    }
  }
}
