package com.cosre7.pms.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.cosre7.context.ApplicationContextListener;
import com.cosre7.pms.domain.Board;
import com.cosre7.pms.domain.Body;
import com.cosre7.pms.domain.Diet;
import com.cosre7.pms.domain.Member;
import com.cosre7.pms.domain.Training;
import com.cosre7.util.CsvObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileListener implements ApplicationContextListener {

  File memberFile = new File("members.json");
  File boardFile = new File("boards.json");
  File dietFile = new File("diets.json");
  File trainingFile = new File("trainings.json");
  File bodyFile = new File("bodys.json");

  List<Member> memberList;
  List<Board> boardList;
  List<Diet> dietList;
  List<Training> trainingList;
  List<Body> bodyList;

  @Override
  public void contextInitialized(Map<String,Object> context) {

    memberList = loadObjects(memberFile, Member.class);
    boardList = loadObjects(boardFile, Board.class);
    dietList = loadObjects(dietFile, Diet.class);
    trainingList = loadObjects(trainingFile, Training.class);
    bodyList = loadObjects(bodyFile, Body.class);

    context.put("memberList", memberList);
    context.put("boardList", boardList);
    context.put("dietList", dietList);
    context.put("trainingList", trainingList);
    context.put("bodyList", bodyList);
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
    saveObjects(memberFile, memberList);
    saveObjects(boardFile, boardList);
    saveObjects(dietFile, dietList);
    saveObjects(trainingFile, trainingList);
    saveObjects(bodyFile, bodyList);
  }

  private <T> List<T> loadObjects(File file, Class<T> elementType) {

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      StringBuilder strBuilder = new StringBuilder();
      String str = null;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }

      Type listType = TypeToken.getParameterized(ArrayList.class, elementType).getType();
      List<T> list = new Gson().fromJson(strBuilder.toString(), listType);
      System.out.printf("%s 파일 로딩성공\n", file.getName());

      return list;

    } catch (Exception e) {
      System.out.printf("%s 파일 로딩실패\n", file.getName());
      return new ArrayList<T>();
    }
  }

  private <T extends CsvObject> void saveObjects(File file, List<T> list) {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(list));
      System.out.printf("파일 %s 저장성공\n", file.getName());

    } catch (Exception e) {
      System.out.printf("파일 %s 저장실패\n", file.getName());
    }
  }
}
