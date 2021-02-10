package com.cosre7.pms.domain;

import java.sql.Date;

public class Training {
  private int no;
  private String name;
  private Date date;
  private String list;
  private int kind;
  private int type;
  private int intensity;
  private double runTime;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public String getList() {
    return list;
  }
  public void setList(String list) {
    this.list = list;
  }
  public int getKind() {
    return kind;
  }
  public void setKind(int kind) {
    this.kind = kind;
  }
  public int getType() {
    return type;
  }
  public void setType(int type) {
    this.type = type;
  }
  public int getIntensity() {
    return intensity;
  }
  public void setIntensity(int intensity) {
    this.intensity = intensity;
  }
  public double getRunTime() {
    return runTime;
  }
  public void setRunTime(double runTime) {
    this.runTime = runTime;
  }
}
