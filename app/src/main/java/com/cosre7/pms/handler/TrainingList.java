package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Training;

public class TrainingList {
  static final int DEFAULT_CAPACITY = 100;
  Training[] trainings = new Training[DEFAULT_CAPACITY];
  int size = 0;

  void add(Training t) {
    this.trainings[this.size++] = t;
  }

  Training[] toArray() {
    Training[] arr = new Training[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.trainings[i];
    }
    return arr;
  }

  Training get(int trainingNo) {
    int i = indexOf(trainingNo);
    if (i == -1)
      return null;
    return trainings[i];
  }

  void delete(int trainingNo) {
    int index = indexOf(trainingNo);

    if (index == -1)
      return;

    for (int x = index + 1; x < this.size; x++) {
      this.trainings[x - 1] = this.trainings[x];
    }
    this.trainings[--this.size] = null;
  }

  int indexOf(int trainingNo) {
    for (int i = 0; i < this.size; i++) {
      Training training  = this.trainings[i];
      if (training.no == trainingNo) {
        return i;
      }
    }
    return -1;
  }
}
