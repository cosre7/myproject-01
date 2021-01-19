package com.cosre7.pms;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  static Scanner keyboardScan = new Scanner(System.in);

  static String inputString (String title) {
    System.out.print(title);
    return keyboardScan.nextLine();
  }

  static Date inputDate (String title) {
    return Date.valueOf(inputString(title));    
  }

  static int inputInt (String title) {
    return Integer.valueOf(inputString(title));
  }

  static double inputDouble (String title) {
    return Double.parseDouble(inputString(title));
  }

  static void close() {
    keyboardScan.close();
  }
}
