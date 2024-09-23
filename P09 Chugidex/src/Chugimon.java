//////////////// Chugimon Class //////////////////////////
//
// Title: P09 Chugidex
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
// Persons:
// Online Sources: ZyBooks
//
///////////////////////////////////////////////////////////////////////////////
public class Chugimon implements Comparable<Chugimon> {
  private final String NAME; // The name of the Chugimon
  private final ChugiType PRIMARY_TYPE; // The primary type of the Chugimon; cannot
                                        // be null; cannot be the same as the secondary type
  private final ChugiType SECONDARY_TYPE; // The secondary type of the Chugimon;
                                          // may be null; cannot be the same as the primary type
  private final double HEIGHT; // The height of the Chugimon in meters
  private final double WEIGHT; // The weight of the Chugimon in kilograms
  public static final int MIN_ID = 1; // The minimum ID number
  public static final int MAX_ID = 151; // The maximum ID number
  private final int FIRST_ID; // The first ID of the Chugimon
  private final int SECOND_ID; // The second ID of the Chugimon

  /**
   * Creates a new Chugimon with specific first and second IDs and initializes all its data fields
   * accordingly.
   * 
   * @param firstID  the first ID of the Chugimon, between 1-151
   * @param secondID the second ID of the Chugimon, between 1-151
   */
  public Chugimon(int firstID, int secondID) {
    if (firstID < 1 || firstID > 151 || secondID < 1 || secondID > 151 || firstID == secondID) {
      throw new IllegalArgumentException(
          "first and second ID out of bounds or equal to each other");
    }
    this.FIRST_ID = firstID;
    this.SECOND_ID = secondID;
    this.NAME = ChugidexUtility.getChugimonName(firstID, secondID);
    this.HEIGHT = ChugidexUtility.getChugimonHeight(firstID, secondID);
    this.WEIGHT = ChugidexUtility.getChugimonWeight(firstID, secondID);
    this.PRIMARY_TYPE = ChugidexUtility.getChugimonTypes(firstID, secondID)[0];
    this.SECONDARY_TYPE = ChugidexUtility.getChugimonTypes(firstID, secondID)[1];

  }

  /**
   * Gets the name of this Chugimon
   * 
   * @return the name of the Chugimon
   */
  public String getName() {
    return this.NAME;
  }

  /**
   * Gets the first ID of this Chugimon
   * 
   * @return the first ID of the Chugimon
   */
  public int getFirstID() {
    return this.FIRST_ID;
  }

  /**
   * Gets the second ID of this Chugimon
   * 
   * @return the second ID of the Chugimon
   */
  public int getSecondID() {
    return this.SECOND_ID;
  }

  /**
   * Gets the primary type of this Chugimon
   * 
   * @return the primary type of the Chugimon
   */
  public ChugiType getPrimaryType() {
    return this.PRIMARY_TYPE;
  }

  /**
   * Gets the secondary type of this Chugimon
   * 
   * @return the secondary type of the Chugimon
   */
  public ChugiType getSecondaryType() {
    return this.SECONDARY_TYPE;
  }

  /**
   * Gets the height of this Chugimon
   * 
   * @return the height of the Chugimon
   */
  public double getHeight() {
    return this.HEIGHT;
  }

  /**
   * Gets the the weight of the Chugimon.
   * 
   * @return the weight of the Chugimon.
   */
  public double getWeight() {
    return this.WEIGHT;
  }

  /**
   * Determines the ordering of Chugimon. Chugimon are ordered by: 1) name (alphabetical) 2) the
   * first ID (if name is equal). The one with the smaller first ID is less than the other. 3) the
   * second ID (if name and first ID are equal). The one with the smaller second ID is less than the
   * other. A Chugimon with identical #1-3 are considered equal. Specified by: compareTo in
   * interface Comparable<Chugimon>
   * 
   * @param otherChugi - the other Chugimon to compare this Chugimon to.
   * @return a negative int if this Chugimon is less than other, a positive int if this Chugimon is
   *         greater than other, or 0 if this and the other Chugimon are equal.
   */
  public int compareTo(Chugimon otherChugi) {
    int intToReturn;
    // name
    String cName = this.getName();
    String oName = otherChugi.getName();
    int nameCompare = cName.compareTo(oName);
    if (nameCompare == 0) {
      intToReturn = 0;
    }
    if (nameCompare < 0) {
      intToReturn = -1;
      return intToReturn;
    }
    if (nameCompare > 0) {
      intToReturn = 1;
      return intToReturn;
    }
    // first id
    int cfID = this.getFirstID();
    int ofID = otherChugi.getFirstID();
    if (cfID == ofID) {
      intToReturn = 0;
    }
    if (cfID < ofID) {
      intToReturn = -1;
      return intToReturn;
    }
    if (cfID > ofID) {
      intToReturn = 1;
      return intToReturn;
    }
    // second id
    int csID = this.getSecondID();
    int osID = otherChugi.getSecondID();
    if (csID == osID) {
      intToReturn = 0;
    }
    if (csID < osID) {
      intToReturn = -1;
      return intToReturn;
    }
    if (csID > osID) {
      intToReturn = 1;
      return intToReturn;
    }
    return 0;
  }

  /**
   * A Chugimon's String representation is its name followed by "#FIRST_ID.SECOND_ID" -- Example:
   * "Zapchu#145.25"
   * 
   * @overrides toString in class Object
   * @return a String representation of this Chugimon
   */
  @Override
  public String toString() {
    String c = new String(this.getName() + "#" + this.getFirstID() + "." + this.getSecondID());
    return c;
  }

  /**
   * Equals method for Chugimon. This Chugimon equals another object if other is a Chugimon with the
   * exact same name, and their both first and second IDs match.
   * 
   * @overrides equals in class Object
   * @param other - Object to determine equality against this Chugimon
   * @return true if this Chugimon and other Object are equal, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Chugimon) {
      Chugimon otherChugimon = (Chugimon) other;
      if (!this.getName().equalsIgnoreCase(otherChugimon.getName())) {
        return false;
      }
      if (this.getFirstID() != otherChugimon.getFirstID()) {
        return false;
      }
      if (this.getSecondID() != otherChugimon.getSecondID()) {
        return false;
      }
      return true;
    }
    return false;
  }
}

