//////////////// Room Class //////////////////////////
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
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class to represent each room of the dungeon as an object
 */
public class Room {
  private String description; // verbal description of the room
  private ArrayList<Room> adjRooms; // list of all rooms directly connect
  private final int ID; // a "unique" identifier for each room
  protected static PApplet processing; // PApplet object which the rooms will use to
  // draw stuff to the GUI
  private PImage image; // stores the image that corresponds to the background of a room

  /**
   * Constructor for the room object. Assigns values to the non-static fields. The default type
   * should be RoomType.NORMAL.
   * 
   * @param id          the unique id number for this room
   * @param description a brief description of this room
   * @param image       processing.core.PImage
   */
  public Room(int ID, String description, processing.core.PImage image) {
    adjRooms = new ArrayList<Room>();
    this.ID = ID;
    this.description = description;
    this.image = image;
  }

  /**
   * Getter for ID.
   *
   * @return the ID of this Room
   */
  public int getID() {
    return this.ID;
  }

  /**
   * Getter for description.
   *
   * @return the verbal description of this Room
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter for the list of adjacentRooms.
   *
   * @return the list of adjacent rooms
   */
  public ArrayList<Room> getAdjacentRooms() {
    return this.adjRooms;
  }

  /**
   * Sets the processing for the class.
   *
   * @param processing - the PApplet that this room will use to draw to the window
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Room.processing = processing;
  }

  /**
   * Adds the given room to the list of rooms adjacent to this room.
   * 
   * @param toAdd - the room to be added
   */
  public void addToAdjacentRooms(Room toAdd) {

    this.adjRooms.add(toAdd);
  }

  /**
   * Checks whether or not the given room is adjacent to this room.
   * 
   * @param r - the room to check for adjacency
   * @return true if it is adjacent, false otherwise
   */
  public boolean isAdjacent(Room r) {
    return adjRooms.contains(r);
  }

  /**
   * Overrides Object.equals(). Determines if two objects are equal.
   * 
   * @override equals in class Object
   * @param other - the object to check against this Room
   * @return true if other is of type Room and has the same ID, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Room) {
      Room otherRoom = (Room) other;
      return this.ID == otherRoom.ID;
    }

    return false;
  }

  /**
   * Overrides Object.toString(). Returns a string representation of a Room object.
   * 
   * @override toString in class Object
   * @return Returns a string in the form of "<ID>: <description>\n Adjacent Rooms: <r1's ID> <r2's
   *         ID>" list of adjacent room IDs continues for all rooms adjacent to this Room.
   */
  @Override
  public String toString() {
    String s = this.ID + ": " + this.description + "\n Adjacent Rooms: ";
    for (int i = 0; i < adjRooms.size(); i++) {
      s += adjRooms.get(i).ID + " ";
    }

    return s;
  }

  /**
   * Draws this Room to the window by drawing the background image, a rectangle, and some text.
   */
  public void draw() {
    // 1. Use the PApplet’s image() instance method to draw the image at (0,0).
    processing.image(image, 0, 0);
    // 2. Use the PApplet’s fill() instance method to change the draw color to giving it a value of
    // −7028. This will change it to Flavescent a light yellow-brown color.
    processing.fill(-7028);
    // 3. Use the PApplet’s rect() instance method to draw a rectangle. The first two arguments are
    // the xy-coordinates of the top left corner respectively. The third and fourth arguments are
    // the xy-coordinates of the bottom right corner respectively. Place the upper left corner at
    // (0,500) and the other at (800,600).
    processing.rect(0, 500, 800, 600);
    // 4. Use the PApplet’s fill() instance method again to change the draw color to giving it a
    // value of 0. This will change it to black.
    processing.fill(0);
    // 5. Use the PApplet’s text() instance method to draw the Room’s toString() at (300,525).
    processing.text(toString(), 300, 525);
  }
}
