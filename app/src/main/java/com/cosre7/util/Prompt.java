package com.cosre7.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  public static Scanner keyboardScan = new Scanner(System.in);

  public static String inputString (String title) {
    System.out.print(title);
    return keyboardScan.nextLine();
  }

  public static Date inputDate (String title) {
    return Date.valueOf(inputString(title));    
  }

  public static int inputInt (String title) {
    return Integer.valueOf(inputString(title));
  }

  public static double inputDouble (String title) {
    return Double.parseDouble(inputString(title));
  }

  public static void close() {
    keyboardScan.close();
  }
}
