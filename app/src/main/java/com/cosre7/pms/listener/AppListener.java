package com.cosre7.pms.listener;

import com.cosre7.context.ApplicationContextListener;

public class AppListener implements ApplicationContextListener {
  @Override
  public void contextInitialized() {
    System.out.println("건강관리 시스템에 오신 걸 환영합니다!");
  }

  @Override
  public void contextDestroyed() {
    System.out.println("건강관리 시스템 종료!");
  }
}
