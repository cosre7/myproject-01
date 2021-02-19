package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Body;

public abstract class AbstractBodyHandler implements Command {

  protected List<Body> bodyList;

  public AbstractBodyHandler(List<Body> bodyList) {
    this.bodyList = bodyList;
  }

  protected Body findByNo(int bodyNo) {
    Body[] list = bodyList.toArray(new Body[bodyList.size()]);
    for (Body b : list) {
      if (b.getNo() == bodyNo) {
        return b;
      }
    }
    return null;
  }
}
