package com.cosre7.pms.domain;

import java.sql.Date;

public class Body {
  private int no;
  private String name;
  private Date date;
  private double height;
  private double weight;
  private double bust;
  private double waist;
  private double thigh;
  private double calf;
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
  public double getHeight() {
    return height;
  }
  public void setHeight(double height) {
    this.height = height;
  }
  public double getWeight() {
    return weight;
  }
  public void setWeight(double weight) {
    this.weight = weight;
  }
  public double getBust() {
    return bust;
  }
  public void setBust(double bust) {
    this.bust = bust;
  }
  public double getWaist() {
    return waist;
  }
  public void setWaist(double waist) {
    this.waist = waist;
  }
  public double getThigh() {
    return thigh;
  }
  public void setThigh(double thigh) {
    this.thigh = thigh;
  }
  public double getCalf() {
    return calf;
  }
  public void setCalf(double calf) {
    this.calf = calf;
  }
}
