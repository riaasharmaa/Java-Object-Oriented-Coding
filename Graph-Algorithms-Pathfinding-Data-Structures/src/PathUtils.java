//////////////// PathUtils Class //////////////////////////
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
// Online Sources: ZyBooks, office hours online consulting
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * Utility methods for planning a trip through a grid of city intersections
 */
public class PathUtils {
  /**
   * Constructor
   */
  public PathUtils() {
  }

  /**
   * Finds the number of valid Paths between the given start and end Intersections. If it is not
   * possible to get from the start to the end intersection by moving up or right, then 0 should be
   * returned. For example, if start is Intersection(0, 0) and end is Intersection(2, 1), this
   * method should return 3. If start is Intersection(1, 0) and end is Intersection(0, 0), this
   * method should return 0. MUST be implemented recursively. If you wish, you can use a call to a
   * private static helper method which is recursive.
   * 
   * @param start - Intersection to start at
   * @param end   - Intersection to end at
   * @return the number of valid Paths which start and end at the given Intersections
   */
  public static int countPaths(Intersection start, Intersection end) {
    int count = 0;
    if (start.equals(end)) {
      count = 1;
      return count;
    } else if (start.getX() > end.getX() || start.getY() > end.getY()) {
      count = 0;
      return count;
    } else {
      count = (countPaths(start.goEast(), end)) + (countPaths(start.goNorth(), end));
      return count;
    }
  }

  /**
   * Finds all valid Paths between the given start and end Intersections. If it is not possible to
   * get from the start to the end intersection by moving up or right, then an empty ArrayList
   * should be returned. For example, if start is Intersection(0, 0) and end is Intersection(2, 1),
   * this method should return an ArrayList consisting of the following Paths:
   * (0,0)->(1,0)->(2,0)->(2,1) (0,0)->(1,0)->(1,1)->(2,1) (0,0)->(0,1)->(1,1)->(2,1) If start is
   * Intersection(1, 0) and end is Intersection(0, 0), this method should return an empty ArrayList.
   * MUST be implemented recursively. If you wish, you can use a call to a private static helper
   * method which is recursive.
   * 
   * @param start - Intersection to start at
   * @param end   - Intersection to end at
   * @return an ArrayList containing all valid Paths which start and end at the given Intersections
   */
  public static ArrayList<Path> findAllPaths(Intersection start, Intersection end) {
    ArrayList<Path> paths = new ArrayList<Path>();
    Path path = new Path();
    if (start.getX() > end.getX() || start.getY() > end.getY()) {
      return paths; // returns empty arraylist
    }
    if (start.equals(end)) { // base case
      path.addTail(start);
      paths.add(path);
      return paths;
    } else {
      path.addTail(start);
      if (start.getX() < end.getX()) { // can move East
        paths.addAll(findAllPaths(start.goEast(), end));
      }
      if (start.getY() < end.getY()) { // can move North
        paths.addAll(findAllPaths(start.goNorth(), end));
      }
      paths.forEach((p) -> p.addHead(start)); // Iteratively adds start and paths
      return paths;
    }
  }
}

