package com.cosre7.pms.domain;

import java.sql.Date;

public class Diet {
  private int no;
  private String name;
  private Date date;
  private String time;
  private String food;
  private int status;
  private int choice;
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
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  public String getFood() {
    return food;
  }
  public void setFood(String food) {
    this.food = food;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public int getChoice() {
    return choice;
  }
  public void setChoice(int choice) {
    this.choice = choice;
  }
}
