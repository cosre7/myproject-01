package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Body;

public class BodyList {

  Node first;
  Node last;
  int size = 0;

  void add(Body b) {
    Node node = new Node(b);

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

  Body[] toArray() {
    Body[] arr = new Body[this.size];

    Node cursor = this.first;
    int i = 0;

    while (cursor != null) {
      arr[i++] = cursor.body;
      cursor = cursor.next;
    }
    return arr;
  }

  Body get(int bodyNo) {
    Node cursor = first;
    while (cursor != null) {
      Body b = cursor.body;
      if (b.no == bodyNo) {
        return b;
      }
      cursor = cursor.next;
    }
    return null;
  }

  void delete(int bodyNo) {
    Node cursor = first;
    while (cursor != null) {
      if (cursor.body.no == bodyNo) {
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
    Body body;
    Node next;
    Node prev;

    Node(Body b) {
      this.body = b;
    }
  }
}
