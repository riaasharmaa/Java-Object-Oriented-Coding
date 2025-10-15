//////////////// Player Class //////////////////////////
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
 * Class to represent a player in the game. Extends character and implements moveable interface.
 */
public class Player extends Character implements Moveable {
  private boolean hasKey;

  /**
   * Constructor for player object. The label should be "PLAYER" and not have a key by default.
   * 
   * @param currentRoom - the room that the player should start in
   * @throws IllegalArgumentException - if the currentRoom is not a StartRoom
   */
  public Player(Room currentRoom) {
    super(currentRoom, "PLAYER");
  }

  /**
   * Determines if the player has the key.
   * 
   * @return true if the player has the key, false otherwise
   */
  public boolean hasKey() {
    return hasKey;
  }

  /**
   * Gives player the key.
   */
  public void obtainKey() {
    hasKey = true;
  }

  /**
   * Moves the Player to the destination room. Specified by - changeRoom in interface Moveable
   * 
   * @param destination - the Room to change it to
   * @return true if the change was successful, false otherwise
   */
  public boolean changeRoom(Room destination) {
    System.out.println(destination);
    try {
      if (canMoveTo(destination)) {
        this.setCurrentRoom(destination);
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Checks if the player can move to the given destination. A valid move is the destination is a
   * room adjacent to the player. Specified by - canMoveTo in interface Moveable
   * 
   * @param destination - the room to check if the player can move towards
   * @return true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination) {
    boolean can = false;
    try {
      ArrayList<Room> adjRooms = this.getCurrentRoom().getAdjacentRooms();
      if (adjRooms.contains(destination)) {
        can = true;
      }
    } catch (Exception e) {
      return false;
    }
    return can;
  }

  /**
   * Checks if the player needs to teleport and move them if needed.
   * 
   * @return true if a teleport occurred, false otherwise
   */
  public boolean teleport() {
    if (getCurrentRoom() instanceof PortalRoom) {
      PortalRoom portal = (PortalRoom) getCurrentRoom();
      setCurrentRoom(portal.getTeleportLocation());
      return true;
    }
    return false;
  }

  /**
   * Determines whether or not a portal room is nearby. A portal room is considered nearby if it is
   * one of the adjacent rooms.
   * 
   * @return true if a portal room is nearby, false otherwise
   */
  public boolean isPortalNearby() {
    ArrayList<Room> adjRooms = this.getCurrentRoom().getAdjacentRooms();
    for (Room r : adjRooms) {
      if (r instanceof PortalRoom)
        return true;
    }

    return false;
  }

  /**
   * Determines whether or not the treasure room is nearby. The treasure room is considered nearby
   * if it is one of the adjacent rooms.
   * 
   * @return true if the treasure room is nearby, false otherwise
   */
  public boolean isTreasureNearby() {
    ArrayList<Room> adjRooms = this.getCurrentRoom().getAdjacentRooms();
    for (Room r : adjRooms) {
      if (r instanceof TreasureRoom)
        return true;
    }

    return false;
  }

  /**
   * Determines whether or not the given dragon is nearby. A dragon is considered nearby if it is in
   * one of the adjacent rooms.
   * 
   * @param d - the dragon to check if nearby
   * @return true if the dragon is nearby, false otherwise
   */
  public boolean isDragonNearby(Dragon d) {
    ArrayList<Room> adjRooms = this.getCurrentRoom().getAdjacentRooms();
    for (Room r : adjRooms) {
      if (r == d.getCurrentRoom())
        return true;
    }

    return false;
  }
}
