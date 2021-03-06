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

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%d,%d,%d,%f\n", 
        this.getNo(), 
        this.getName(),
        this.getDate(),
        this.getList(),
        this.getKind(),
        this.getType(),
        this.getIntensity(),
        this.getRunTime());
  }

  public static Training valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Training training = new Training();
    training.setNo(Integer.parseInt(fields[0]));
    training.setName(fields[1]);
    training.setDate(Date.valueOf(fields[2]));
    training.setList(fields[3]);
    training.setKind(Integer.parseInt(fields[4]));
    training.setType(Integer.parseInt(fields[5]));
    training.setIntensity(Integer.parseInt(fields[6]));
    training.setRunTime(Double.parseDouble(fields[7]));
    return training;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + intensity;
    result = prime * result + kind;
    result = prime * result + ((list == null) ? 0 : list.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + no;
    long temp;
    temp = Double.doubleToLongBits(runTime);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + type;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Training other = (Training) obj;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (intensity != other.intensity)
      return false;
    if (kind != other.kind)
      return false;
    if (list == null) {
      if (other.list != null)
        return false;
    } else if (!list.equals(other.list))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (no != other.no)
      return false;
    if (Double.doubleToLongBits(runTime) != Double.doubleToLongBits(other.runTime))
      return false;
    if (type != other.type)
      return false;
    return true;
  }

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
