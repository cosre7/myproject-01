package com.cosre7.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Body implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private String name;
  private Date date;
  private double height;
  private double weight;
  private double bust;
  private double waist;
  private double thigh;
  private double calf;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(bust);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(calf);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    temp = Double.doubleToLongBits(height);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + no;
    temp = Double.doubleToLongBits(thigh);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(waist);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(weight);
    result = prime * result + (int) (temp ^ (temp >>> 32));
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
    Body other = (Body) obj;
    if (Double.doubleToLongBits(bust) != Double.doubleToLongBits(other.bust))
      return false;
    if (Double.doubleToLongBits(calf) != Double.doubleToLongBits(other.calf))
      return false;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (no != other.no)
      return false;
    if (Double.doubleToLongBits(thigh) != Double.doubleToLongBits(other.thigh))
      return false;
    if (Double.doubleToLongBits(waist) != Double.doubleToLongBits(other.waist))
      return false;
    if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
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
