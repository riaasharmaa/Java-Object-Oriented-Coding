//////////////// Dragon Class //////////////////////////
//
// Title: Dragon Class
// Course: CS 300 Fall 2022
//
// Author: Ria Sharma
// Email: rsharma78@wisc.edu
// Lecturer: Hobbes LeGault
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: zybooks
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Random;
import java.lang.Object;

/**
 * The Dragon class will represent the dragon that is lurking in the caves, protecting the treasure.
 * This class will be responsible for keeping track of the dragon’s current location and picking a
 * room to move to and then moving to it.
 */
public class Dragon extends Object {
  private Room currentRoom; // current location of the dragon
  private Random randGen; // random num generator used for moving
  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";

  /**
   * Constructor for a dragon object. Assigns values to all non-static fields.
   * 
   * @param currentRoom - the current location of the dragon
   */
  public Dragon(Room currentRoom) {
    this.currentRoom = currentRoom;
    Random randGen = new Random();
    this.randGen = randGen;
  }

  /**
   * Getter for this Dragon's currentRoom
   * 
   * @return this Dragon's current room
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  /**
   * Dragon picks one of the rooms at random and moves there if possible. If it is not a valid move,
   * then it will pick again. Dragons abide by the follow rules when moving: 1) The dragon can only
   * move into rooms that are adjacent to it. 2) The dragon CANNOT move into portal rooms.
   */
  public void changeRooms() {
    boolean check = true;
    int rand;
    while (check) {
      rand = randGen.nextInt(0, currentRoom.getAdjacentRooms().size());
      if (currentRoom.getAdjacentRooms().get(rand).getType() != RoomType.PORTAL) {
        currentRoom = currentRoom.getAdjacentRooms().get(rand);
        check = false;
      }
    }
  }


  /**
   * Returns the string that is the dragon class's warning, indicating that there is one nearby.
   * 
   * @return The dragon warning message string
   */
  public static String getDragonWarning() {
    return DRAGON_WARNING;
  }
}
