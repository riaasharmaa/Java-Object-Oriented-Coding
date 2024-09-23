//////////////// Dragon Class //////////////////////////
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
import java.util.Random;

/**
 * Class to represent a dragon in the game. Extends character and implements moveable interface.
 */
public class Dragon extends Character implements Moveable {
  private Random randGen; // random num generator used for moving
  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
  private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire breathing dragon!\n";

  /**
   * Constructor for a Dragon object. Initializes all instance fields. The label should be "DRAGON"
   * by default.
   * 
   * @param currentRoom - the room that the Dragon starts in
   * @throws IllegalArgumentException - with a descriptive message if currentRoom is not a
   *                                  TreasureRoom
   */
  public Dragon(Room currentRoom) {
    super(currentRoom, "DRAGON");
    randGen = new Random();
    if (!(currentRoom instanceof TreasureRoom)) {
      throw new IllegalArgumentException("currentRoom is not a treasure room");
    }
  }

  /**
   * Moves the Dragon to the destination room. Specified by: changeRoom in interface Moveable
   * 
   * @param destination - the Room to change it to
   * @return true if the change was successful, false otherwise
   */
  public boolean changeRoom(Room destination) {
    if (canMoveTo(destination)) {
      setCurrentRoom(destination);
      return true;
    }
    return false;
  }

  /**
   * Checks if the dragon can move to the given destination. A valid move is the destination not a
   * PortalRoom. Specified by: canMoveTo in interface Moveable
   * 
   * @param destination - the room to check if the dragon can move towards
   * @return true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination) {
    if (destination instanceof PortalRoom || !(destination.isAdjacent(getCurrentRoom()))) // can't
                                                                                          // go into
                                                                                          // portal
                                                                                          // room
    {
      return false;
    }
    return true;
  }

  /**
   * Picks randomly ONCE an adjacent room to move into.
   * 
   * @return the room that this Dragon should try to move into
   */
  public Room pickRoom() {
    ArrayList<Room> adjRooms = getCurrentRoom().getAdjacentRooms();
    int randInt;
    Room destRoom;
    // randomly pick an adj room, go there! as long as it is not portal room and exists
    do {
      int numOfRooms = getCurrentRoom().getAdjacentRooms().size();
      randInt = randGen.nextInt(numOfRooms);
      destRoom = adjRooms.get(randInt);
    } while (!canMoveTo(destRoom));

    return destRoom;
  }

  /**
   * Getter for DRAGON_WARNING.
   * 
   * @return the string for warning about a dragon being nearby.
   */
  public static String getDragonWarning() {
    return DRAGON_WARNING;
  }

  /**
   * Getter for DRAGON_ENCOUNTER.
   * 
   * @return the string for letting the player know they ran into the dragon.
   */
  public static String getDragonEncounter() {
    return DRAGON_ENCOUNTER;
  }
}
