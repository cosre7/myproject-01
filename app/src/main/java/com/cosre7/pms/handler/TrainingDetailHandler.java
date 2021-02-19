package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Training;
import com.cosre7.util.Prompt;

public class TrainingDetailHandler extends AbstractTrainingHandler {

  public TrainingDetailHandler(List<Training> trainingList) {
    super(trainingList);
  }

  @Override
  public void service() {
    System.out.println("[운동일지 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    Training training = findByNo(no);
    if (training == null) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    // 이름, 날짜, 운동 리스트, 종류1, 종류2, 소요시간, 운동강도
    System.out.printf("[%d] %s\n", training.getNo(), training.getName());
    System.out.printf("- 운동리스트 - \n %s\n", training.getList());
    System.out.printf("%s | %s\n", getKindLabel(training.getKind()), getTypeLabel(training.getType()));
    System.out.printf("소요시간: %s 시간\n", training.getRunTime());
    System.out.printf("[%d] %s\n", training.getIntensity(), getIntensityLabel(training.getIntensity()));
  }
}