package com.cosre7.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Training implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private String name;
  private Date date;
  private String list;
  private int kind;
  private int type;
  private int intensity;
  private double runTime;

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
