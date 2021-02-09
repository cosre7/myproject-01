package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Diet;

public class DietList {
  static final int DEFAULT_CAPACITY = 100;
  Diet[] diets = new Diet[DEFAULT_CAPACITY];
  int size = 0;

  void add(Diet d) {
    this.diets[this.size++] = d;
  }

  Diet[] toArray() {
    Diet[] arr = new Diet[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.diets[i];
    }
    return arr;
  }

  Diet get(int dietNo) {
    int i = indexOf(dietNo);
    if (i == -1) 
      return null;
    return diets[i];
  }

  void delete(int dietNo) {
    int index = indexOf(dietNo);

    if (index == -1)
      return;

    for (int x = index + 1; x < this.size; x++) {
      this.diets[x - 1] = this.diets[x];
    }
    this.diets[--this.size] = null;
  }

  int indexOf(int dietNo) {
    for (int i = 0; i < this.size; i++) {
      Diet diet = this.diets[i];
      if (diet.no == dietNo) {
        return i;
      }
    }
    return -1;
  }
}
