package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Diet;

public class DietList {

  private Node first;
  private Node last;
  private int size = 0;

  public void add(Diet d) {
    Node node = new Node(d);

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

  public Diet[] toArray() {
    Diet[] arr = new Diet[this.size];

    Node cursor = this.first;
    int i = 0;

    while (cursor != null) {
      arr[i++] = cursor.diet;
      cursor = cursor.next;
    }
    return arr;
  }

  public Diet get(int dietNo) {
    Node cursor = first;
    while (cursor != null) {
      Diet d = cursor.diet;
      if (d.getNo() == dietNo) {
        return d;
      }
      cursor = cursor.next;
    }
    return null;
  }

  public void delete(int dietNo) {
    Node cursor = first;
    while (cursor != null) {
      if (cursor.diet.getNo() == dietNo) {
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
    Diet diet;
    Node next;
    Node prev;

    Node(Diet d) {
      this.diet = d;
    }
  }
}
