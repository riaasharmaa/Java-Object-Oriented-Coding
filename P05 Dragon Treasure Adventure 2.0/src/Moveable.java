//////////////// Moveable Interface //////////////////////////
//
// Title: P04 Exceptional Vending Machine
// Course: CS 300 Fall 2022
//
// Author: Ria Sharma
// Email: rsharma78@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Alexander Kalis
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
// Online Sources: ZyBooks - Overriding examples
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * Interface for things that can move between rooms in the DragonTreasureGame.
 * 
 * @author Michelle
 *
 */
public interface Moveable {
  /**
   * Changes the room where the object is.
   * 
   * @param destination, the Room to change it to
   * @return true if the change is successful (a valid move), and false otherwise
   */
  public boolean changeRoom(Room destination);

  /**
   * Gets the list of rooms adjacent to this movable object.
   * 
   * @return an ArrayList of rooms adjacent to the object
   */
  public ArrayList<Room> getAdjacentRooms();

  /**
   * Checks whether or not this object can move to that room.
   * 
   * @param destination, the Room to check if it can move to
   * @return true if it can move there (a valid move), and false otherwise
   */
  public boolean canMoveTo(Room destination);
}
