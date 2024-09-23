//////////////// Intersection Class //////////////////////////
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

/**
 * This class represents a single intersection point where two streets laid out on a grid plan cross
 * at specified x and y coordinate positions.
 */
public class Intersection {
  private final int x; // X-axis coordinate of this intersection
  private final int y; // Y-axis coordinate of this intersection

  /**
   * Initializes this intersection with the given coordinates
   * 
   * @param x - Horizontal position of this Intersection
   * @param y - Vertical position of this Intersection
   */
  public Intersection(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the horizontal position of this Intersection
   * 
   * @return the horizontal position of this Intersection
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the vertical position of this Intersection
   * 
   * @return the vertical position of this Intersection
   */
  public int getY() {
    return y;
  }

  /**
   * Returns a coordinate-pair representation of this Intersection in the form "(x,y)"
   * 
   * @overrides toString in class Object
   * @return a coordinate-pair representation of this Intersection
   */
  @Override
  public String toString() {
    String coord = "(" + this.x + "," + this.y + ")";
    return coord;
  }

  /**
   * Returns true if the given Object is identical to this Intersection
   * 
   * @overrides equals in class Object
   * @param o - object to compare for equality
   * @return true if the given Object is an Intersection object which has the same x and y
   *         coordinates as this Intersection
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Intersection) {
      Intersection otherRoom = (Intersection) o;
      if (this.getX() == otherRoom.getX() && this.getY() == otherRoom.getY()) {
        return true;
      }
    }

    return false;
  }

  /**
   * Creates a new Intersection instance which is one step directly above this Intersection. Should
   * not modify the original Intersection object.
   * 
   * @return a new Intersection instance which is one step directly above this Intersection
   */
  public Intersection goNorth() {
    Intersection north = new Intersection((this.x), (this.y + 1));
    return north;
  }

  /**
   * Creates a new Intersection instance which is one step directly below this Intersection. Should
   * not modify the original Intersection object.
   * 
   * @return a new Intersection instance which is one step directly above this Intersection
   */
  public Intersection goSouth() {
    Intersection south = new Intersection((this.x), (this.y - 1));
    return south;
  }

  /**
   * Creates a new Intersection instance which is one step directly to the right of this
   * Intersection object. Should not modify the original Intersection object.
   * 
   * @return a new Intersection instance which is one step directly to the right of this
   *         Intersection
   */
  public Intersection goEast() {
    Intersection east = new Intersection((this.x + 1), (this.y));
    return east;
  }

  /**
   * Creates a new Intersection instance which is one step directly to the left of this Intersection
   * object. Should not modify the original Intersection object.
   * 
   * @return a new Intersection instance which is one step directly to the left of this Intersection
   */
  public Intersection goWest() {
    Intersection west = new Intersection((this.x - 1), (this.y));
    return west;
  }
}
