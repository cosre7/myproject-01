package com.cosre7.util;

public class StackIterator {
  Stack stack;

  public StackIterator(Stack stack) throws CloneNotSupportedException {
    this.stack = stack.clone();
  }

  public boolean hasNext() {
    return this.stack.size() > 0;
  }

  public Object next() {
    return this.stack.pop();
  }
}
