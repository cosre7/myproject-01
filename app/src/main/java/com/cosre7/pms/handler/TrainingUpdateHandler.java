package com.cosre7.pms.handler;

import java.sql.Date;
import java.util.List;
import com.cosre7.pms.domain.Training;
import com.cosre7.util.Prompt;

public class TrainingUpdateHandler extends AbstractTrainingHandler {

  private MemberValidatorHandler memberValidatorHandler;

  public TrainingUpdateHandler(List<Training> trainingList, MemberValidatorHandler memberValidatorHandler) {
    super(trainingList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  public void update() {
    System.out.println("[운동일지 변경]");

    int no = Prompt.inputInt("번호 > ");

    Training training = findByNo(no);
    if (training == null) {
      System.out.println("해당 번호의 운동일지가 없습니다.");
      return;
    }

    // 이름, 날짜, 운동 리스트, 종류1, 종류2, 소요시간, 운동강도
    String name = memberValidatorHandler.inputMember(String.format("이름(%s, 취소: 빈 문자열) > ", training.getName()));
    if (name == null) {
      System.out.println("등록된 회원이 아닙니다.");
      return;
    }
    Date date = Prompt.inputDate(String.format("날짜(%s) > ", training.getDate()));
    String list = inputExercise(String.format("운동 리스트(완료: 빈 문자열) > ", training.getList()));
    int kind = Prompt.inputInt(String.format("종류1([%d] %s) > ", training.getKind(), getKindLabel(training.getKind())));
    int type = Prompt.inputInt(String.format("종류2([%d] %s) > ", training.getType(), getTypeLabel(training.getType())));
    double time = Prompt.inputDouble(String.format("소요시간(%.2f 시간) > ", training.getRunTime()));
    int intensity = Prompt.inputInt(String.format("운동강도([%d] %s) > ", training.getIntensity(), getIntensityLabel(training.getIntensity())));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) > ");

    if (input.equalsIgnoreCase("Y")) {
      training.setName(name);
      training.setDate(date);
      training.setList(list);
      training.setKind(kind);
      training.setType(type);
      training.setRunTime(time);
      training.setIntensity(intensity);
      System.out.println("운동일지를 변경하였습니다.");
    } else {
      System.out.println("운동일지 변경을 취소하였습니다.");
    }
  }
}