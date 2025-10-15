//////////////// PortalRoom Class //////////////////////////
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
import java.util.Random;

/**
 * Class to represent the portal room of the dungeon as a room
 */
public class PortalRoom extends Room {
  private Random randGen; // random number generator for location picking
  private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
  private static final String TELEPORT_MESSAGE =
      "The space distortion teleported you to another room!\n";
  private static PImage portalImage; // image of a portal to be shown in all portal rooms

  /**
   * Constructor for a PortalRoom object.
   * 
   * @param ID          - the unique ID number for this room
   * @param description - verbal description of the room
   * @param image       - stores the image that corresponds to the background of a room
   */
  public PortalRoom(int ID, String description, processing.core.PImage image) {
    super(ID, description, image);
  }

  /**
   * Getter for PORTAL_WARNING.
   * 
   * @return static final string representing the PORTAL_WARNING message
   */
  public static String getPortalWarning() {
    return PORTAL_WARNING;
  }

  /**
   * Getter for TELEPORT_MESSAGE.
   * 
   * @return the string for letting the player know they were teleported.
   */
  public static String getTeleportMessage() {
    return TELEPORT_MESSAGE;
  }

  /**
   * Picks an adjacent room at random for the player to teleport into.
   * 
   * @return The room that player should immediately be moved to
   */
  public Room getTeleportLocation() {
    int num = randGen.nextInt(getAdjacentRooms().size());
    return getAdjacentRooms().get(num);
  }

  /**
   * Draws this PortalRoom to the window by drawing the background image, a rectangle, some text,
   * and the portal image.
   * 
   * @overrides draw in class Room
   */
  @Override
  public void draw() {
    super.draw();
    processing.image(portalImage, 325, 225);
  }

  /**
   * Sets the portal image for the PortalRoom class.
   * 
   * @param portalImage - the image to represent the portal
   */
  public static void setPortalImage(processing.core.PImage portalImage) {
    PortalRoom.portalImage = portalImage;
  }
}
