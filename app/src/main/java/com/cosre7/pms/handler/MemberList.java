package com.cosre7.pms.handler;

import com.cosre7.pms.domain.Member;

public class MemberList {
  Node first;
  Node last;
  int size = 0;

  void add(Member m) {
    Node node = new Node(m);

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

  Member[] toArray() {
    Member[] arr = new Member[this.size];

    Node cursor = this.first;
    int i = 0;

    while (cursor != null) {
      arr[i++] = cursor.member;
      cursor = cursor.next;
    }
    return arr;
  }

  Member get(int memberNo) {
    Node cursor = first;
    while (cursor != null) {
      Member m = cursor.member;
      if (m.no == memberNo) {
        return m;
      }
      cursor = cursor.next;
    }
    return null;
  }

  void delete(int memberNo) {
    Node cursor = first;
    while (cursor != null) {
      if (cursor.member.no == memberNo) {
        this.size--;
        if (first == last) {
          first = last = null;
          break;
        }
        if (cursor != null) {
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

  public boolean exist(String name) {
    Node cursor = first;
    while (cursor != null) {
      Member m = cursor.member;
      if (m.name.equals(name)) {
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }

  static class Node {
    Member member;
    Node next;
    Node prev;

    Node(Member m) {
      this.member = m;
    }
  }
}
