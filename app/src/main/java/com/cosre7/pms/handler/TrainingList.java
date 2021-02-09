package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Training;

public class TrainingList {
  Node first;
  Node last;
  int size = 0;

  void add(Training t) {
    Node node = new Node(t);

    if (last == null) {
      last = node;
      first = node;
    } else {
      last.next = node;
      node.prev = last;
      last = node;
    }

    size++;
  }

  Training[] toArray() {
    Training[] arr = new Training[this.size];

    Node cursor = this.first;
    int i = 0;

    while (cursor != null) {
      arr[i++] = cursor.training;
      cursor = cursor.next;
    }
    return arr;
  }

  Training get(int trainingNo) {
    Node cursor = first;
    while (cursor != null) {
      Training t = cursor.training;
      if (t.no == trainingNo) {
        return t;
      }
      cursor = cursor.next;
    }
    return null;
  }

  void delete(int trainingNo) {
    Node cursor = first;
    while (cursor != null) {
      if (cursor.training.no == trainingNo) {
        this.size--;
        if (first == last) {
          first = last = null;
          break;
        }
        if (cursor == first) {
          first = cursor.next;
          cursor.prev = null;
        } else {
          cursor.prev.next = cursor.next;
          if (cursor.next != null) {
            cursor.next.prev = cursor.prev;
          }
        }
        if (cursor == last) {
          last = cursor.prev;
        }
        break;
      }
      cursor = cursor.next;
    }
  }

  static class Node {
    Training training;
    Node next;
    Node prev;

    Node(Training t) {
      this.training = t;
    }
  }
}
