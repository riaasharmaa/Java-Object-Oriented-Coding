//////////////// PathUtilsTester Class //////////////////////////
//
// Title: P06 City Route Planner
// Course: CS 300 Fall 2022
//
// Author: Ria Sharma
// Email: rsharma78@wisc.edu
// Lecturer: Hobbes Legault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Alex Kalis
// Partner Email: akalis@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: ZyBooks
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Tester class for PathUtils
 */
public class PathUtilsTester {
  /**
   * Constructor
   */
  public PathUtilsTester() {
  }

  /**
   * Main driver method to test implementation
   */
  public static void main(String[] args) {
    /**
    try (Scanner keyboard = new Scanner(System.in)) {
      int startX, startY, endX, endY;
      String input = "Y";
      while (input.equalsIgnoreCase("Y")) {
      System.out.print("Enter starting X coordinate: ");
      startX = keyboard.nextInt();
      System.out.print("Enter starting Y coordinate: ");
      startY = keyboard.nextInt();
      System.out.print("Enter ending X coordinate: ");
      endX = keyboard.nextInt();
      System.out.print("Enter ending Y coordinate: ");
      endY = keyboard.nextInt();
      Intersection start = new Intersection(startX, startY);
      Intersection end = new Intersection(endX, endY);
      System.out.println("Number of paths from start to end: "
      + PathUtils.countPaths(start, end));
      System.out.println("List of possible paths:");
      for (Path p : PathUtils.findAllPaths(start, end)) {
      System.out.println(p);
      }
      do {
      System.out.print("Try another route? (Y/N): ");
      input = keyboard.next();
      } while (!input.equalsIgnoreCase("Y")
      && !input.equalsIgnoreCase("N"));
      }
      }
      }
      */
    System.out.println(testCountPathsNoPath());
    System.out.println(testCountPathsOnePath());
    System.out.println(testCountPathsRecursive());
    System.out.println(testFindAllPathsNoPath());
    System.out.println(testFindAllPathsOnePath());
    System.out.println(testFindAllPathsRecursive());
  }

  /**
   * Tests the case of countPaths() when there are no valid Paths. For example, when the start
   * position is Intersection(1, 1) and the ending position is Intersection(0, 1), there should be
   * no valid Paths, so countPaths() should return 0.
   * 
   * @return true if all test cases are passed
   */
  public static boolean testCountPathsNoPath() {
    Intersection start = new Intersection(1, 1);
    Intersection end = new Intersection(0, 1);
    if (PathUtils.countPaths(start, end) != 0) {
      return false;
    }
    return true;
  }

  /**
   * Tests the case of countPaths() when there is a single valid Path. For example, when the start
   * position is Intersection(1, 1) and the ending position is Intersection(1, 2), there should be a
   * single Path, so countPaths() should return 1.
   * 
   * @return true if all test cases are passed
   */
  public static boolean testCountPathsOnePath() {
    Intersection start = new Intersection(1, 1);
    Intersection end = new Intersection(1, 2);
    if (PathUtils.countPaths(start, end) != 1) {
      return false;
    }
    return true;
  }

  /**
   * Tests the case of countPaths() when there are multiple possible paths. For example, when the
   * start position is Intersection(0, 0) and the ending position is Intersection(1, 2), there
   * should be three possible Paths, so countPaths() should return 3.
   * 
   * @return true if all test cases are passed
   */
  public static boolean testCountPathsRecursive() {
    Intersection start = new Intersection(0, 0);
    Intersection end = new Intersection(1, 2);
    if (PathUtils.countPaths(start, end) != 3) {
      return false;
    }
    return true;
  }

  /**
   * Tests the case of findAllPaths() when there are no valid Paths. For example, when the start
   * position is Intersection(1, 1) and the ending position is Intersection(0, 1), there should be
   * no valid Paths, so findAllPaths() should return an empty ArrayList.
   * 
   * @return true if all test cases are passed
   */
  public static boolean testFindAllPathsNoPath() {
    Intersection start = new Intersection(1, 1);
    Intersection end = new Intersection(0, 1);
    ArrayList<Path> paths = new ArrayList<Path>();
    if (!PathUtils.findAllPaths(start, end).equals(paths)) {
      return false;
    }
    return true;
  }

  /**
   * Tests the case of findAllPaths() when there is a single valid Path. For example, when the start
   * position is Intersection(1, 1) and the ending position is Intersection(1, 2), there should be a
   * single Path. For each of your cases, ensure that there is only a single path, and that the Path
   * exactly matches what you expect to see.
   * 
   * @return true if all test cases are passed
   */
  public static boolean testFindAllPathsOnePath() {
    Intersection start = new Intersection(1, 1);
    Intersection end = new Intersection(1, 2);
    ArrayList<Path> paths = new ArrayList<Path>();
    Path p1 = new Path();
    p1.addTail(start);
    p1.addTail(end);
    paths.add(p1);
    if (!(PathUtils.findAllPaths(start, end).toString()).equals(paths.toString())) {
      return false;
    }
    return true;
  }

  /**
   * Tests the case of findAllPaths() when there are multiple possible paths. For example, when the
   * start position is Intersection(0, 0) and the ending position is Intersection(1, 2), there
   * should be three possible Paths. For each of your cases, ensure that there is both the correct
   * number of Paths, and that the returned Paths exactly match what you expect to see. Remember:
   * The order the Paths appear in the output of findAllPaths() will not necessarily match your own
   * implementation.
   * 
   * @return true if all test cases are passed
   */
  public static boolean testFindAllPathsRecursive() {
    Intersection start = new Intersection(0, 1);
    Intersection end = new Intersection(3, 2);
    ArrayList<Path> paths = new ArrayList<Path>();
    Path p1 = new Path();
    Intersection p1i2 = new Intersection(1, 1);
    Intersection p1i3 = new Intersection(2, 1);
    Intersection p1i4 = new Intersection(3, 1);
    p1.addTail(start);
    p1.addTail(p1i2);
    p1.addTail(p1i3);
    p1.addTail(p1i4);
    p1.addTail(end);
    Path p2 = new Path();
    Intersection p2i2 = new Intersection(1, 1);
    Intersection p2i3 = new Intersection(2, 1);
    Intersection p2i4 = new Intersection(2, 2);
    p2.addTail(start);
    p2.addTail(p2i2);
    p2.addTail(p2i3);
    p2.addTail(p2i4);
    p2.addTail(end);
    Path p3 = new Path();
    Intersection p3i2 = new Intersection(1, 1);
    Intersection p3i3 = new Intersection(1, 2);
    Intersection p3i4 = new Intersection(2, 2);
    p3.addTail(start);
    p3.addTail(p3i2);
    p3.addTail(p3i3);
    p3.addTail(p3i4);
    p3.addTail(end);
    Path p4 = new Path();
    Intersection p4i2 = new Intersection(0, 2);
    Intersection p4i3 = new Intersection(1, 2);
    Intersection p4i4 = new Intersection(2, 2);
    p4.addTail(start);
    p4.addTail(p4i2);
    p4.addTail(p4i3);
    p4.addTail(p4i4);
    p4.addTail(end);
    paths.add(p1);
    paths.add(p2);
    paths.add(p3);
    paths.add(p4);
    if (!(PathUtils.findAllPaths(start, end).toString()).equals(paths.toString())) {
      return false;
    }
    return true;
  }
}
