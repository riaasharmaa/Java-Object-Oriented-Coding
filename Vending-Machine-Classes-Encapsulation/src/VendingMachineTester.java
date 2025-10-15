//////////////// Vending Machine //////////////////////////
//
// Title: P01 Vending Machine
// Course: CS 300 Fall 2022
//
// Author: Ria Sharma
// Email: rsharma78@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Snehal Wadhwani (TA in CS online consulting) helped with testRemoveNextItem method.
// Online Sources: Zybooks (Examples of oversize array methods)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;

/**
 * This tester will return true if and only if NO bug is detected. If it detects any bug it must
 * return false.
 * @version 1.0
 * @author Ria Sharma
 */
public class VendingMachineTester {
  // TODO complete the implementation of the following tester methods and add their javadoc style
  // method headers
  // Checks the correctness of getIndexNextItem defined in the VendingMachine class. This method
  // returns true if the test verifies a correct functionality and false if any bug is detected
  public static boolean testGetIndexNextItem() {
    // Test scenarios normal and edge cases
    // Recall that the VendingMachine.getNextItem method gets the next item to be dispensed given
    // its description without removing it.
    // 1. Next item to be dispensed is NOT found: the expected output is -1
    {
      // define the vending machine
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      int itemsCount = 3;
      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("candy", items, itemsCount) != -1) {
        System.out.println(
            "testGetIndexNextItem-scenario 1. Problem detected: Your getIndexNextItem did not return "
                + "-1 when no match found.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 1. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }
    // 2. Next item to be dispensed is at index 0
    {
      String[][] items = new String[][] {{"Water", "1"}, {"Chocolate", "10"}, {"Juice", "20"},
          {"Water", "5"}, {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Water", items, itemsCount) != 0) {
        System.out.println(
            "testGetIndexNextItem-scenario 2. Problem detected: Your getIndexNextItem did not return "
                + "the expected output when the vending machines contains multiple matches with the "
                + "provided item description and the matching item with the soonest expiration date "
                + "is at index 0.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "1"}, {"Chocolate", "10"}, {"Juice", "20"}, {"Water", "5"},
              {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 2. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }
    // 3. Next item to be dispensed is at the end of the array
    {
      String[][] items = new String[][] {{"Water", "15"}, {"Chocolate", "20"}, {"Juice", "20"},
          {"Water", "5"}, {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Chocolate", items, itemsCount) != 6) {
        System.out.println(
            "testGetIndexNextItem-scenario 3. Problem detected: Your getIndexNextItem did not return "
                + "the expected output when the vending machines contains multiple matches with the "
                + "provided item description and the matching item with the soonest expiration date "
                + "is at the end of the array");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "20"}, {"Juice", "20"}, {"Water", "5"},
              {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 3. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }
    // 4. Next item to be dispensed is at the middle of the array
    {
      String[][] items = new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"},
          {"Water", "5"}, {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Water", items, itemsCount) != 3) {
        System.out.println(
            "testGetIndexNextItem-scenario 4. Problem detected: Your getIndexNextItem did not return "
                + "the expected output when the vending machines contains matches with the provided "
                + "item description with different expiration dates.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, {"Water", "5"},
              {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 4. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }
    return true; // No bug detected
  }

  // Checks the correctness of containsItem defined in the VendingMachine class. This method
  // returns true if the test verifies a correct functionality and false if any bug is detected
  public static boolean testContainsItem() {
    String[][] items = new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"},
        {"Water", "5"}, {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
    int itemsCount = 7;
    // (1) successful search returning true
    if ((VendingMachine.containsItem("Water", items, itemsCount)) != true) {
      System.out.println("error: did not detect item that was present");
      return false;
    }
    // (2) invalid search returning true
    if ((VendingMachine.containsItem("Chips", items, itemsCount)) != false) {
      System.out.println("error: detected item that does not exist");
      return false;
    } else {
      return true;
    }
  }

  // Checks the correctness of getItemAtIndex defined in the VendingMachine class. This method
  // returns true if the test verifies a correct functionality and false if any bug is detected
  public static boolean testGetItemAtIndex() {
    String[][] items = new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"},
        {"Water", "5"}, {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
    int itemsCount = 7;
    // (1) the provided index is out of the range
    // 0..itemsCount-1
    if (!(VendingMachine.getItemAtIndex(-1, items, itemsCount)).equals("ERROR INVALID INDEX")) {
      System.out.println("error: did not detect index was not valid");
      return false;
    }
    // (2) the provided index is in bounds [0..itemsCount-1].
    // For each test scenario, ensure that the method returned the exact expected string output
    // without making any changes to the contents of the array.
    if (!(VendingMachine.getItemAtIndex(0, items, itemsCount)).equals("Water (15)")) {
      System.out.println("error: did not get correct item at valid index");
      return false;
    } else {
      return true;
    }
  }

  // Checks the correctness of getItemOccurrences defined in the VendingMachine class.
  public static boolean testGetItemsOccurrences() {
    String[][] items = new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"},
        {"Water", "5"}, {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
    int itemsCount = 7;
    // (1) no match found so that the method returns zero
    if (VendingMachine.getItemOccurrences("Water", items, itemsCount) != 3) {
      System.out.println("error: did not detect correct number of occurances");
      return false;
    }
    // (2) the items array contains multiple occurrences of the provided item description.
    if (VendingMachine.getItemOccurrences("Soda", items, itemsCount) != 0) {
      System.out.println("error: did not detect there are no occurances");
      return false;
    } else {
      return true;
    }
  }

  // Checks the correctness of addItem defined in the VendingMachine class.
  public static boolean testAddItem() {
    String[][] items = new String[][] {null, null};
    int itemsCount = 0;
    // (1) adding a new item to an empty vending machine whose
    // size is zero (provided itemsCount == 0)
    if (VendingMachine.addItem("Chocolate", "4", items, itemsCount) != 1) {
      System.out.println("error: did not add to itemsCount in an empty items array");
      return false;
    }
    // (2) adding a new item to a non-empty non-full
    // vending machine
    itemsCount = itemsCount + 1;
    if (VendingMachine.addItem("Candy", "4", items, itemsCount) != 2) {
      System.out.println("error: did not add to itemsCount in a nonempty items array");
      return false;
    }
    // (3) adding a new item to a full vending machine where the provided
    // itemsCount equals the length of the provided items array
    itemsCount = itemsCount + 1;
    if (VendingMachine.addItem("Candy", "4", items, itemsCount) != 2) {
      System.out.println("error: did not maintain itemCount when machine full");
      return false;
    } else {
      return true;
    }
  }

  // Checks the correctness of removeNextItem defined in the VendingMachine class.
  public static boolean testRemoveNextItem() {
    String[][] items = new String[][] {{"Candy", "15"}, {"Chocolate", "10"}, {"Juice", "20"},
        {"Water", "5"}, {"Soda", "30"}, {"Mints", "15"}, {"Chips", "2"}};
    int itemsCount = 7;
    // (1) trying to remove a non-existing item from a vending machine
    if (VendingMachine.removeNextItem("Cheese", items, itemsCount) != 7) {
      System.out.println("error: did not maintain items when invalid item removed");
      return false;
    }
    // (2) the next item to be removed matching the provided description is at index 0 of the array
    if (VendingMachine.removeNextItem("Candy", items, itemsCount) != 6) {
      System.out.println("error: did not remove item at index 0");
      return false;
    }
    // (4) the next item to be removed matching the provided description is at a middle index of the
    // provided items array.
    itemsCount = itemsCount - 1;
    if (VendingMachine.removeNextItem("Chips", items, itemsCount) != 5) {
      System.out.println("error: did not remove item at end of array");
      return false;
    }
    // (3) the next item to be removed matching the provided description is at index itemsCount of
    // the array (at the end of the array)
    itemsCount = itemsCount - 1;
    if (VendingMachine.removeNextItem("Soda", items, itemsCount) != 4) {
      System.out.println("error: did not remove item at middle of array");
      return false;
    } else {
      return true;
    }
  }

  // Checks the correctness of getItemsSummary defined in the VendingMachine class.
  public static boolean testGetItemsSummary() {
    // Define at least three scenarios:
    // (1) the vending machine is empty
    String[][] items = new String[][] {};
    int itemsCount = 0;
    if (!(VendingMachine.getItemsSummary(items, itemsCount)).equals("")) {
      System.out.println("error: did not detect index was not valid");
      return false;
    }
    // (2) the vending machine contains non duplicate items (no multiple items with the same
    // description),
    VendingMachine.addItem("Chocolate", "4", items, itemsCount);
    VendingMachine.addItem("Soda", "5", items, itemsCount);
    if (!(VendingMachine.getItemsSummary(items, itemsCount)).equals("Chocolate (1)\nSoda (1)")) {
      System.out.println("error: multiple descriptions not summarized");
      return false;
    }
    // (3) the vending machine contains multiple items with the same description at various index
    // locations.
    VendingMachine.addItem("Soda", "4", items, itemsCount);
    if (!(VendingMachine.getItemsSummary(items, itemsCount)).equals("Chocolate (1)\nSoda(2)")) {
      System.out.println("error: duplicate was not accounted for");
      return false;
    } else {
      return true;
    }
  }

  public static boolean runAllTests() {
    if ((VendingMachineTester.testGetIndexNextItem() == true)
        && (VendingMachineTester.testContainsItem() == true)
        && (VendingMachineTester.testGetItemAtIndex() == true)
        && (VendingMachineTester.testGetItemsOccurrences() == true)
        && (VendingMachineTester.testAddItem() == true)
        && (VendingMachineTester.testRemoveNextItem() == true)) {
      return true;
    } else {
      return false;
    }
  }

  // main method to call the tester methods defined in this class
  public static void main(String[] args) {

    System.out.println("runAllTests()" + runAllTests());
  }
}
