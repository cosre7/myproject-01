package com.cosre7.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.cosre7.pms.domain.Training;

public class TrainingListHandler extends AbstractTrainingHandler {

  public TrainingListHandler(List<Training> trainingList) {
    super(trainingList);
  }

  @Override
  public void service() {
    System.out.println("[운동일지 목록]");

    Iterator<Training> iterator = trainingList.iterator();

    while (iterator.hasNext()) {
      Training t = iterator.next();
      System.out.printf("[%d] 이름: %s 날짜: %s\n", t.getNo(), t.getName(), t.getDate()); 
    }
  }
}