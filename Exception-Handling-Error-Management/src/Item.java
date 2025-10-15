//////////////// Item Class //////////////////////////
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
/**
 * This class models an item defined by its description and expiration date.
 */
public class Item {
  private String description; // a human readable description of this item
  private int expirationDate; // a positive integer starting at day 0, which represents Jan 1, 2023

  /**
   * Creates a new Item Object with a specific expiration date and description
   * 
   * @param description    - a human readable description of this item
   * @param expirationDate - a positive integer starting at day 0, which represents Jan 1, 2023
   * @throws IllegalArgumentException - with a descriptive error message if expirationDate is
   *                                  negative (less than zero) or description is null or blank
   */
  public Item(String description, int expirationDate) {
    if (expirationDate < 0) {
      throw new IllegalArgumentException("expiration date is negative");
    }
    if (description == null) {
      throw new IllegalArgumentException("description is null");
    }
    if (description.isBlank()) {
      throw new IllegalArgumentException("description is blank");
    }
    this.description = description;
    this.expirationDate = expirationDate;
  }

  /**
   * Gets the description of this item
   * 
   * @return the description of this item
   */
  public String getDescription() {
    return description;
  }

  /**
   * Changes the description of this item
   * 
   * @param description - new description of the item
   */
  public void setDescription(String description) {
    this.description = description;
    if (description.equals("")) {
      throw new IllegalArgumentException("description is blank");
    }
    if (description.equals(null)) {
      throw new IllegalArgumentException("description is null");
    }
  }

  /**
   * Gets the expiration date of this item
   * 
   * @return the expiration date of this item
   */
  public int getExpirationDate() {
    return expirationDate;
  }

  /**
   * Returns a String representation of this item formatted as "description: expirationDate"
   * 
   * @override toString in class Object
   * @return a String representation of this item
   */
  @Override
  public String toString() {
    String print = new String(description + ": " + expirationDate);
    return print;
  }

  /**
   * Checks whether this item equals another object passed as input.
   * 
   * @override equals in class Object
   * @return true if other is instance of Item and has the same description as this item, false
   *         otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Item) {
      if ((description.equals(((Item) other).getDescription()))) {
        return true;
      }
    }
    return false;
  }
}
