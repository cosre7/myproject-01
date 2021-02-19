package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Training;
import com.cosre7.util.Prompt;

public class TrainingDeleteHandler extends AbstractTrainingHandler {

  public TrainingDeleteHandler(List<Training> trainingList) {
    super(trainingList);
  }

  @Override
  public void service() {
    System.out.println("[운동일지 삭제]");

    int no = Prompt.inputInt("번호 > ");

    Training training = findByNo(no);
    if (training == null) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      trainingList.remove(training);
      System.out.println("운동일지를 삭제하였습니다.");
    } else {
      System.out.println("삭제를 취소하였습니다.");
    }
  }
}