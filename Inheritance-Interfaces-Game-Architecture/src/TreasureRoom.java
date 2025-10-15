//////////////// TreasureRoom Class //////////////////////////
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

import processing.core.PImage;

/**
 * Class to represent the treasure room of the dungeon as a room
 */
public class TreasureRoom extends Room {
  private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
  private static PImage treasureBackground; // the image ALWAYS used for treasure rooms

  /**
   * 
   * Constructor for a TresureRoom object and have a description of "In the back of this room, you
   * spot a treasure chest
   * 
   * @param ID - the unique ID number for this room
   */
  public TreasureRoom(int ID) {
    super(ID, "In the back of this room, you spot a treasure chest.", treasureBackground);
  }

  /**
   * Getter for TREASURE_WARNING.
   * 
   * @return the string for warning about treasure being nearby.
   */
  public static String getTreasureWarning() {
    return TREASURE_WARNING;
  }

  /**
   * Sets the background image for the TreasureRoom class.
   * 
   * @param treasureBackground - the image to be the background
   */
  public static void setTreasureBackground(processing.core.PImage treasureBackground) {
    TreasureRoom.treasureBackground = treasureBackground;
  }

  /**
   * Determines whether or not the player can open the treasure chest in the room.
   * 
   * @param p - the Player to check if they can open the chest
   * @return true if the player has the key and is in this TreasureRoom, false otherwise
   */
  public boolean playerCanGrabTreasure(Player p) {
    if ((p.hasKey()) && (p.getCurrentRoom() instanceof TreasureRoom)
        && (p.getCurrentRoom().getID() == this.getID())) {
      return true;
    }
    return false;
  }
}
