package com.Others;

import java.util.Scanner;

public class GeneralFunction {
  static Scanner sc = new Scanner(System.in);

  public static int takeInputInt(String msg) {
    int input;
    System.out.print(msg);
    input = sc.nextInt();
    return input;
  }

  public static double takeInputDouble(String msg) {
    double input;
    System.out.print(msg);
    input = sc.nextDouble();
    return input;
  }

  public static String takeInputString(String msg) {
    String input;
    System.out.print(msg);
    input = sc.nextLine();
    return input;
  }
}
