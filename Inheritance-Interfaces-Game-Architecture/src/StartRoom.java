//////////////// StartRoom Class //////////////////////////
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
 * Class to represent the starting room of the dungeon as a room
 */
public class StartRoom extends Room {
  /**
   * Constructor for the StartRoom object. Assigns values to the fields.
   * 
   * @param id    the unique id number for this room
   * @param image processing.core.PImage
   */
  public StartRoom(int ID, PImage image) {
    super(ID, "You find yourself in the entrance to a cave holding treasure.", image);
  }

}
