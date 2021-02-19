package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Diet;
import com.cosre7.util.Prompt;

public abstract class AbstractDietHandler implements Command {

  protected List<Diet> dietList;

  public AbstractDietHandler(List<Diet> dietList) {
    this.dietList = dietList;
  }

  protected String getStatusLabel(int status) {
    switch (status) {
      case 1:
        return "나는 아직 배고프다";
      case 2:
        return "먹긴 했어요";
      case 3:
        return "적당해요";
      case 4:
        return "배가 불러요~~";
      default:
        return "터질 것 같아요";
    }
  }

  protected String getChoiceLabel(int choice) {
    switch (choice) {
      case 1:
        return "훌륭해요!";
      default:
        return "치팅이라니!!!";
    }
  }

  protected String inputFoods(String promptTitle) {
    String foods = "";
    while (true) {
      String eat = Prompt.inputString(promptTitle);
      if (eat.length() == 0) {
        break;
      } else {
        if (!foods.isEmpty()) {
          foods += ", ";
        }
        foods += eat;
      }
    }
    return foods;
  }

  protected Diet findByNo(int dietNo) {
    Diet[] list = dietList.toArray(new Diet[dietList.size()]);
    for (Diet d : list) {
      if (d.getNo() == dietNo) {
        return d;
      }
    }
    return null;
  }
}





