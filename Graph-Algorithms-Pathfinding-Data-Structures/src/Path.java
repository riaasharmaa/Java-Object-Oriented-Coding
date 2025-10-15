//////////////// Path Class //////////////////////////
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
import java.util.NoSuchElementException;

/**
 * This class represents a valid path through a grid of city intersections surrounded by streets.
 * That is, one which only moves either one step directly east, or one step directly north at each
 * step, meaning that only northeast paths from one intersection point to another are allowed. A
 * list of intersection elements creates the path.
 */
public class Path {
  private ArrayList<Intersection> intersections; // List of Intersections followed in this Path

  /**
   * Initializes this Path to start as empty
   */
  public Path() {
    intersections = new ArrayList<Intersection>();
  }

  /**
   * Returns the number of Intersections in this Path
   * 
   * @return the number of Intersections in this Path
   */
  public int length() {
    return intersections.size();
  }

  /**
   * Returns the first Intersection in this Path, if it is not empty. Otherwise, throws a
   * NoSuchElementException.
   * 
   * @return the first Intersection in this Path, if it is not empty
   * @throws NoSuchElementException - if this Path is empty
   */
  public Intersection getHead() {
    if (intersections.isEmpty() || intersections == null) {
      throw new NoSuchElementException();
    }
    return intersections.get(0);
  }

  /**
   * Returns the last Intersection in this Path, if it is not empty. Otherwise, throws a
   * NoSuchElementException.
   * 
   * @return the last Intersection in this Path, if it is not empty
   * @throws NoSuchElementException - if this Path is empty
   */
  public Intersection getTail() {
    if (intersections.isEmpty() || intersections == null) {
      throw new NoSuchElementException();
    }
    return intersections.get(intersections.size() - 1);
  }

  /**
   * Adds the given Intersection to the end of this Path if it is a valid addition. A Intersection
   * is a valid addition if the current Path is empty, or the Intersection to add is one step
   * directly east, or one step directly north of the current tail Intersection in this Path. Should
   * throw an IllegalArgumentException if the given Intersection is not a valid addition.
   * 
   * @param toAdd - Intersection to add to the end of this Path
   * @throws IllegalArgumentException - if the Intersection to add is not valid
   */
  public void addTail(Intersection toAdd) {
    if (intersections.isEmpty()) {
      intersections.add(toAdd);
    } else if (getTail().goNorth().equals(toAdd) || getTail().goEast().equals(toAdd)) {
      intersections.add(toAdd);
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Adds the given Intersection to the front of this Path if it is a valid addition. A Intersection
   * is a valid addition if the current Path is empty, or the Intersection to add is one step
   * directly west, or one step directly south of the current head Intersection in this Path. Should
   * throw an * IllegalArgumentException if the given Intersection is not a valid addition.
   * 
   * @param toAdd - Intersection to add to the end of this Path
   * @throws IllegalArgumentException - if the Intersection to add is not valid
   */
  public void addHead(Intersection toAdd) {
    if (intersections.isEmpty()) {
      intersections.add(0, toAdd);
    } else if (toAdd.goEast().equals(getHead()) || toAdd.goNorth().equals(getHead())) {
      intersections.add(0, toAdd);
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Returns a String representing the coordinates taken in this Path. An empty Path should return
   * the String "Empty", while a non-empty Path should return the coordinates of the Intersections
   * it visits separated by a "->". For example: (0,0)->(1,0)->(1,1)->(1,2)
   * 
   * @overrides toString in class Object
   * @return a String representing the coordinates followed by this Path
   */
  public String toString() {
    if (intersections.isEmpty()) {
      return "Empty";
    }
    String coords = "";
    for (int i = 0; (i < intersections.size()); i++) {
      coords = coords + intersections.get(i).toString();
      if (i != intersections.size() - 1) {
        coords = coords + "->";
      }
    }
    return coords;
  }
}
