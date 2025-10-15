//////////////// Exceptional Vending Machine Tester //////////////////////////
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
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;

/**
 * This class implements testers to check the correctness of the methods implemented in p04
 * Exceptional Vending Machine
 *
 */
public class ExceptionalVendingMachineTester {
  // TODO complete the implementation of all the public static tester methods defined below

  // It is recommended but NOT required to add additional tester methods to check the correctness
  // of loadItems and saveVendingMachineSumary defined in the ExceptionalVendingMachine class.

  /**
   * Checks the correctness of the constructor of the class Item when passed invalid inputs
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemConstructorNotValidInput() {
    // blank description
    int tracker = 0;
    try {
      Item chips = new Item("", 1);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // null description
    try {
      Item soda = new Item(null, 1);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // negative expiration date
    try {
      Item candy = new Item("skittles", -1);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    if (tracker == 3) {
      return true;
    }
    return false;
  }

  /**
   * Checks the correctness of the constructor of the class Item, Item.getDescription(),
   * Item.getExpirationDate(), Item.setDescription(), and Item.toString() when passed valid inputs
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemConstructorGettersSetters() {
    // test constructor
    int tracker = 0;
    // if exp date neg
    try {
      Item neg = new Item("coke", -2);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // if description blank
    try {
      Item blank = new Item("", 4);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // if description null
    try {
      Item itsnull = new Item(null, 4);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // test get description
    Item candy = new Item("MNM", 1);
    if (!candy.getDescription().equals("MNM")) {
      return false;
    }
    // test get expiration date
    if (candy.getExpirationDate() != 1) {
      return false;
    }
    // test set description
    candy.setDescription("snickers");
    if (!candy.getDescription().equals("snickers")) {
      return false;
    }
    // test to string
    if (!candy.toString().equals("snickers: 1")) {
      return false;
    }
    if (tracker == 3) {
      return true;
    }
    return false;
  }

  /**
   * Checks the correctness of the Item.equals() method. You should consider at least the following
   * four scenarios. (1) Create an item with valid description and expiration date, comparing it to
   * itself should return true. (2) Two items having the same description but different expiration
   * dates should be equal. (3) Passing a null reference to the Item.equals() method should return
   * false. (4) An item MUST NOT be equal to an object NOT instance of the class Item, for instance
   * a string object.
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemEquals() {
    int tracker = 0;
    // valid parameters compare to itself
    Item i1 = new Item("chocolate", 4);
    if (i1.equals(i1)) {
      tracker++;
    }
    // same description, different expiration date
    Item i2 = new Item("chocolate", 5);
    if (i1.equals(i2)) {
      tracker++;
    }
    // null reference
    if (!i1.equals(null)) {
      tracker++;
    }
    // item not equal to instance
    String i1string = new String("chocolate: 5");
    if (!i1.equals(i1string)) {
      tracker++;
    }
    if (tracker == 4) {
      return true;
    }
    return false;
  }


  /**
   * Checks the correctness of the constructor of the ExceptionalVendingMachine when passed invalid
   * input
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineConstructor() {
    // negative capacity
    int tracker = 0;
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(-3);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    if (tracker == 1) {
      return true;
    }
    return false;
  }

  /**
   * Checks the correctness of the following methods defined in the ExceptionalVendingMachine class
   * when an exception is expected to be thrown:
   * 
   * addItem(), containsItem(), getIndexNextItem(), getItemAtIndex(), getItemOccurrences(),
   * getItemOccurrencesByExpirationDate(), removeNextItem().
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineAddContainsRemoveGetters() {
    int tracker = 0;
    // test add item
    // if full
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(1);
      v1.addItem("fanta", 3);
      v1.addItem("fanta", 2);
    } catch (IllegalStateException e) {
      tracker++;
    }
    // if exp date neg
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("fanta", -1);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // if description blank
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("", 2);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // if description null
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem(null, 2);
    } catch (IllegalArgumentException e) {
      tracker++;
    }

    // test contains item
    ExceptionalVendingMachine vendcontains = new ExceptionalVendingMachine(10);
    vendcontains.addItem("fanta", 2);
    if (vendcontains.containsItem("fanta")) {
      tracker++;
    }
    // blank description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      boolean blankcontain = v1.containsItem("");
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // null description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      boolean nullcontain = v1.containsItem(null);
    } catch (IllegalArgumentException e) {
      tracker++;
    }

    // test get index next item
    ExceptionalVendingMachine vendgetindnext = new ExceptionalVendingMachine(10);
    vendgetindnext.addItem("fanta", 4);
    vendgetindnext.addItem("fanta", 2);
    if (vendgetindnext.getIndexNextItem("fanta") == 1) {
      tracker++;
    }
    // blank description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("fanta", 4);
      v1.addItem("fanta", 2);
      int blank = v1.getIndexNextItem("");
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // null description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("fanta", 4);
      v1.addItem("fanta", 2);
      int blank = v1.getIndexNextItem(null);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // no match found
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("fanta", 4);
      int blank = v1.getIndexNextItem("coke");
    } catch (NoSuchElementException e) {
      tracker++;
    }
    // test get item at index
    ExceptionalVendingMachine vendgetitemindex = new ExceptionalVendingMachine(10);
    vendgetitemindex.addItem("coke", 4);
    vendgetitemindex.addItem("fanta", 4);
    vendgetitemindex.addItem("skittles", 4);
    if (vendgetitemindex.getItemAtIndex(0).equals("coke: 4")) {
      tracker++;
    }
    // index less than 0
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("fanta", 4);
      String zero = v1.getItemAtIndex(-1);
    } catch (IndexOutOfBoundsException e) {
      tracker++;
    }
    // index >= size
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("fanta", 4);
      String indexsize = v1.getItemAtIndex(1);
    } catch (IndexOutOfBoundsException e) {
      tracker++;
    }
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("fanta", 4);
      String indexsize = v1.getItemAtIndex(4);
    } catch (IndexOutOfBoundsException e) {
      tracker++;
    }

    // test get item occurrences
    ExceptionalVendingMachine vendocc = new ExceptionalVendingMachine(10);
    vendocc.addItem("coke", 4);
    vendocc.addItem("fanta", 4);
    vendocc.addItem("fanta", 2);
    if (vendocc.getItemOccurrences("fanta") == 2) {
      tracker++;
    }
    // blank description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      vendocc.addItem("coke", 4);
      vendocc.addItem("fanta", 4);
      vendocc.addItem("fanta", 2);
      int occur = v1.getItemOccurrences("");
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // null description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      vendocc.addItem("coke", 4);
      vendocc.addItem("fanta", 4);
      vendocc.addItem("fanta", 2);
      int occur = v1.getItemOccurrences(null);
    } catch (IllegalArgumentException e) {
      tracker++;
    }

    // test get item occurrences by expiration date
    ExceptionalVendingMachine vex = new ExceptionalVendingMachine(10);
    vex.addItem("coke", 4);
    vex.addItem("fanta", 4);
    vex.addItem("fanta", 3);
    vex.addItem("fanta", 2);
    if (vex.getItemOccurrencesByExpirationDate("fanta", 3) == 2) {
      tracker++;
    }
    // neg exp
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("coke", 4);
      v1.addItem("fanta", 4);
      v1.addItem("fanta", 3);
      v1.addItem("fanta", 2);
      int occur = v1.getItemOccurrencesByExpirationDate("fanta", -3);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // blank description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("coke", 4);
      v1.addItem("fanta", 4);
      v1.addItem("fanta", 3);
      v1.addItem("fanta", 2);
      int occur = v1.getItemOccurrencesByExpirationDate("", 3);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // null description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("coke", 4);
      v1.addItem("fanta", 4);
      v1.addItem("fanta", 3);
      v1.addItem("fanta", 2);
      int occur = v1.getItemOccurrencesByExpirationDate(null, 3);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // test remove next item
    // null description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("coke", 4);
      v1.addItem("fanta", 4);
      v1.removeNextItem(null);
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // blank description
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("coke", 4);
      v1.addItem("fanta", 4);
      v1.removeNextItem("");
    } catch (IllegalArgumentException e) {
      tracker++;
    }
    // no match
    try {
      ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
      v1.addItem("coke", 4);
      v1.addItem("fanta", 4);
      v1.removeNextItem("skittles");
    } catch (NoSuchElementException e) {
      tracker++;
    }
    if (tracker == 25) {
      return true;
    }
    return false;
  }

  /**
   * Checks the correctness of isEmpty(), size(), and isFull() methods defined in the
   * ExceptionalVendingMachine class
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testEmptySizeFullExceptionalVendingMachine() {
    // test constructor
    ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
    ExceptionalVendingMachine v2 = new ExceptionalVendingMachine(1);
    v2.addItem("soda", 3);
    // test is empty
    if (!v1.isEmpty()) {
      return false;
    }
    // test size
    if (v1.size() != 0) {
      return false;
    }
    if (v2.size() != 1) {
      return false;
    }
    // test is full
    if (!v2.isFull()) {
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of loadOneItem method with respect to its specification. Consider at
   * least the four following scenarios. (1) Successful scenario for loading one item with a valid
   * string representation to a non-full vending machine. (2) Unsuccessful scenario for passing null
   * or a blank string (for instance one space or empty string) to the loadOneItem() method call, an
   * IllegalArgumentEXception is expected to be thrown. (3) Unsuccessful scenario for passing a
   * badly formatted string to the loadOneItem method. A DataFormatException is expected to be
   * thrown. (4) Unsuccessful scenario for trying to load an item with a valid representation to a
   * full vending machine. An IllegalStateException is expected to be thrown.
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testLoadOneItem() {
    // TODO
    ExceptionalVendingMachine v1 = new ExceptionalVendingMachine(10);
    ExceptionalVendingMachine v2 = new ExceptionalVendingMachine(10);
    ExceptionalVendingMachine v3 = new ExceptionalVendingMachine(1);
    v3.addItem("coke", 3);
    int tracker = 0;
    // successful 1 load item
    try {
      v1.loadOneItem("soda: 4");
      if (v1.getItemAtIndex(0) == "soda: 4") {
        tracker++;
      }
    } catch (DataFormatException e) {
      return false;
    }
    // null/blank string
    try {
      v2.loadOneItem("");
    } catch (IllegalArgumentException e) {
      tracker++;
    } catch (DataFormatException e) {
      return false;
    }
    try {
      v2.loadOneItem(null);
    } catch (IllegalArgumentException e) {
      tracker++;
    } catch (DataFormatException e) {
      return false;
    }
    // badly formatted string
    try {
      v2.loadOneItem("coke3");
    } catch (DataFormatException e) {
      tracker++;
    }
    // full machine
    try {
      v3.loadOneItem("sprite:5");
    } catch (IllegalStateException e) {
      tracker++;
    } catch (DataFormatException e) {
      return false;
    }
    if (tracker == 4) {
      return true;
    }
    return false;
  }

  /**
   * Invokes all the public tester methods implemented in this class
   * 
   * @return true if all testers pass with no errors, and false if any of the tester fails.
   */
  public static boolean runAllTests() {
    if (testItemConstructorNotValidInput() && testItemConstructorGettersSetters()
        && testExceptionalVendingMachineConstructor()
        && testEmptySizeFullExceptionalVendingMachine()
        && testExceptionalVendingMachineAddContainsRemoveGetters() && testItemEquals()
        && testLoadOneItem()) {
      return true;
    }
    return false;
  }

  /**
   * Main method for the tester class
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }

}
