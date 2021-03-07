package com.cosre7.util;

public interface ObjectFactory<T> {
  T create(String csvStr);
}
