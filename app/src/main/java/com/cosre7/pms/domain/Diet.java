package com.cosre7.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Diet implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private String name;
  private Date date;
  private String time;
  private String food;
  private int status;
  private int choice;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + choice;
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((food == null) ? 0 : food.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + no;
    result = prime * result + status;
    result = prime * result + ((time == null) ? 0 : time.hashCode());
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
    Diet other = (Diet) obj;
    if (choice != other.choice)
      return false;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (food == null) {
      if (other.food != null)
        return false;
    } else if (!food.equals(other.food))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (no != other.no)
      return false;
    if (status != other.status)
      return false;
    if (time == null) {
      if (other.time != null)
        return false;
    } else if (!time.equals(other.time))
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
