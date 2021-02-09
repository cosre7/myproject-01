package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Body;

public class BodyList {
  static final int DEFAULT_CAPACITY = 100;
  Body[] bodys = new Body[DEFAULT_CAPACITY];
  int size = 0;

  void add(Body b) {
    this.bodys[this.size++] = b;
  }

  Body[] toArray() {
    Body[] arr = new Body[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.bodys[i];
    }
    return arr;
  }

  Body get(int bodyNo) {
    int i = indexOf(bodyNo);
    if (i == -1) 
      return null;
    return bodys[i];
  }

  void delete(int bodyNo) {
    int index = indexOf(bodyNo);

    if (index == -1)
      return;

    for (int x = index + 1; x < this.size; x++) {
      this.bodys[x - 1] = this.bodys[x];
    }
    this.bodys[--this.size] = null;
  }

  int indexOf(int bodyNo) {
    for (int i = 0; i < this.size; i++) {
      Body body = this.bodys[i];
      if (body.no == bodyNo) {
        return i;
      }
    }
    return -1;
  }
}
