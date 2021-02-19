package com.cosre7.pms.handler;

import java.util.List;
import com.cosre7.pms.domain.Training;
import com.cosre7.util.Prompt;

public class TrainingAddHandler extends AbstractTrainingHandler {

  private MemberValidatorHandler memberValidatorHandler;

  public TrainingAddHandler(List<Training> trainingList, MemberValidatorHandler memberValidatorHandler) {
    super(trainingList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[운동일지 작성]");

    Training t = new Training();
    t.setNo(Prompt.inputInt("번호 > "));
    t.setName(memberValidatorHandler.inputMember("이름(취소: 빈 문자열) > "));
    if (t.getName() == null) {
      System.out.println("운동일지 작성을 취소합니다.");
      return;
    }
    t.setDate(Prompt.inputDate("날짜 > "));

    t.setList(inputExercise("운동 리스트(완료: 빈 문자열) > "));

    while (true) {
      t.setKind(Prompt.inputInt("종류1\n1: 상체\n2: 하체\n3: 전신\n> "));
      if (t.getKind() == 1 || t.getKind() == 2 || t.getKind() == 3) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }

    while (true) {
      t.setType(Prompt.inputInt("종류2\n1: 근력\n2: 유산소\n3: 혼합\n> "));
      if (t.getType() == 1 || t.getType() == 2 || t.getType() == 3) {
        break;
      } else {
        System.out.println("다시 입력해주세요");
      }
    }
    t.setRunTime(Prompt.inputDouble("소요시간 > "));
    t.setIntensity(Prompt.inputInt("운동 강도 (1~5) > "));

    trainingList.add(t);
  }
}