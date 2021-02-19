package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Training;
import com.cosre7.util.Prompt;

public abstract class AbstractTrainingHandler implements Command {

  protected List<Training> trainingList;

  public AbstractTrainingHandler(List<Training> trainingList) {
    this.trainingList = trainingList;
  }

  protected String getKindLabel(int kind) {
    switch (kind) {
      case 1:
        return "상체";
      case 2:
        return "하체";
      default :
        return "전신";
    }
  }

  protected String getTypeLabel(int type) {
    switch (type) {
      case 1:
        return "근력";
      case 2:
        return "유산소";
      default:
        return "혼합";
    }
  }

  protected String getIntensityLabel(int intensity) {
    switch (intensity) {
      case 1:
        return "껌이에요";
      case 2:
        return "한 듯 안한 듯";
      case 3:
        return "적당해요";
      case 4:
        return "근육통 예상";
      default:
        return "조상님을 뵈었어요";
    }
  }

  protected String inputExercise(String promptTitle) {
    String list = "";
    while (true) {
      String exercise = Prompt.inputString(promptTitle);
      if (exercise.length() == 0) {
        break;
      } else {
        if (!list.isEmpty()) {
          list += ", ";
        }
        list += exercise;
      }
    }
    return list;
  }

  protected Training findByNo(int trainingNo) {
    Training[] list = trainingList.toArray(new Training[trainingList.size()]);
    for (Training t : list) {
      if (t.getNo() == trainingNo) {
        return t;
      }
    }
    return null;
  }
}