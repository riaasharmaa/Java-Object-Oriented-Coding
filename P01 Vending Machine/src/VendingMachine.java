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
// Persons: Hobbes LeGault (professor) office hours helped with removeNextItem method.
// Snehal Wadhwani (TA in CS online consulting) helped with removeNextItem method.
// Online Sources: Zybooks (Examples of oversize array methods)
//
///////////////////////////////////////////////////////////////////////////////
/**
 * The vending machine will be represented by an oversize two-dimensional array defined by an array
 * String[][] and a variable of type int keeping track of the number of items available in the
 * vending machine. The VendingMachine defines eight static methods that can alter and describe
 * contents.
 * @version 1.0
 * @author Ria Sharma
 */
public class VendingMachine {
  /**
   * Adds/appends an item defined by its description and expirationDate to a vending machine
   * represented by an oversize array of strings defined by the two-dimensional array items and its
   * size itemsCount. The item will be added to the end of the array. If the vending machine is
   * full, the new item won't be added and the method returns the items count passed as input
   * without making any changes to the contents of the array items.
   * 
   * @param description    description of the item to be added to the vending machine
   * @param expirationDate a string parsable to a positive integer which represents the expiration
   *                       date of the item. The date "0" represents January 1st 2023.
   * @param items          a two-dimensional of strings storing items. items[i][0] and items[i][1]
   *                       respectively represent the description and the expiration date of the
   *                       item stored at index i
   * @param itemsCount     number of items in the vending machine
   * @return the size of the vending machine after trying to add the new item
   */
  public static int addItem(String description, String expirationDate, String[][] items,
      int itemsCount) {
    // Note that we suppose that the expirationDate is valid, meaning it is correctly
    // parsable to a positive integer
    if ((items.length > 0) && (itemsCount < items.length)) {
      items[itemsCount] = new String[] {description, expirationDate};
      itemsCount++;
    }
    return itemsCount;
  }

  /**
   * Returns without removing a string representation of the item at the given index within the
   * vending machine defined by the array items and its size itemsCount. This method does not make
   * any changes to the contents of the vending machine.
   * 
   * @param items      two dimensional array storing items within a vending machine where
   *                   items[i][0] represents the description of the item at index i and items[i][1]
   *                   stores its expiration date.
   * @param itemsCount (size) number of items stored in the vending machine
   * @param index      index of an item within the provided vending machine
   * @return a string representation of the item stored at the given index within the vending
   *         machine defined by items and itemsCount. The returned string must have the following
   *         format: "description (expiration date)". If the provided index is out of the range of
   *         indexes 0..itemsCount-1, the method returns "ERROR INVALID INDEX"
   */
  public static String getItemAtIndex(int index, String[][] items, int itemsCount) {
    // return error message with invalid index
    if (index < 0) {
      String error = new String("ERROR INVALID INDEX");
      return error;
    }
    if (index > (itemsCount - 1)) {
      String error = new String("ERROR INVALID INDEX");
      return error;
    }
    // return string of description and expiration date with valid index
    String info = new String((items[index][0]) + " (" + (items[index][1]) + ")");
    return info;
  }

  /**
   * Returns without removing the index of the item having the provided description and the smallest
   * expiration date within the vending machine defined by the array items and its size itemsCount.
   * 
   * @param description description of the item to get its index
   * @param items       two dimensional array storing items within a vending machine where
   *                    items[i][0] represents the description of the item at index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return the index of the next item, meaning the item with the given description and the
   *         smallest expiration date. If no match found, return -1.
   */
  public static int getIndexNextItem(String description, String[][] items, int itemsCount) {
    // If the vending machine contains more than one item with the given description,
    // return the index of the one with the smallest expiration date.

    // initialize variables to track index to return & smallest expiration date
    int index = -1;
    int smallest = -1;
    // run through each index in array
    for (int x = 0; x < itemsCount; ++x) {
      // see if description matches & update variables
      if (items[x][0].equals(description)) {
        // to maintain return -1 if no match found
        if (index == -1) {
          index = x;
          smallest = Integer.parseInt(items[x][1]);
        }
        // to isolate smallest expiration date with matching description
        if (Integer.parseInt(items[x][1]) < smallest) {
          index = x;
          smallest = Integer.parseInt(items[x][1]);
        }
      }
    }
    // return index of item that matches description with smallest expiration date
    return index;
  }

  /**
   * Removes the item having the provided description with the smallest expiration date within the
   * vending machine defined by the array items and its size itemsCount. This method should maintain
   * the order of precedence of items in the vending machine. This means that the remove operation
   * involves a shift operation.
   * 
   * @param description description of the item to remove or dispense
   * @param items       array storing items within a vending machine
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return size of the vending machine after removing the item with the given description and the
   *         smallest expiration date. If no match found, this method must return the provided
   *         itemsCount without making any change to the contents of the vending machine.
   */
  public static int removeNextItem(String description, String[][] items, int itemsCount) {
    // use method to get index of item with smallest expiration date
    int index = getIndexNextItem(description, items, itemsCount);
    // if there is a match found, shift items to remove the item from array
    if ((index) != (-1)) {
      for (; index < itemsCount - 1; ++index) {
        items[index][0] = items[index + 1][0];
        items[index][1] = items[index + 1][1];
      }
      // return size after removal/if no removal was done
      items[itemsCount - 1] = null;
      return (itemsCount - 1);
    } else {
      return itemsCount;
    }
  }

  /**
   * Returns the number of occurrences of an item with a given description within the vending
   * machine defined by items and itemsCount
   * 
   * @param description description (name) of an item
   * @param items       two dimensional array storing items within a vending machine where
   *                    items[i][0] represents the description of the item at index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return the number of occurrences of an item with a given description within the vending
   *         machine
   */
  public static int getItemOccurrences(String description, String[][] items, int itemsCount) {
    // initialize tracking variables
    int search;
    int count = 0;
    // search through all indexes of array
    for (search = 0; search < itemsCount; ++search) {
      // if there is a matching description add to count for additional occurrence
      if (items[search][0] == description) {
        count++;
      }
    }
    // return number of occurrences
    return count;
  }

  /**
   * Checks whether a vending machine defined by the array items and its size itemsCount contains at
   * least an item with the provided description
   * 
   * @param description description (name) of an item to search
   * @param items       two dimensional array storing items within a vending machine where
   *                    items[i][0] represents the description of the item at index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return true if there is a match with description in the vending machine, false otherwise
   */
  public static boolean containsItem(String description, String[][] items, int itemsCount) {
    // Initialize variables
    boolean itemPresent;
    itemPresent = false;
    int search;
    int count = 0;
    // search each index of array
    for (search = 0; search < itemsCount; ++search) {
      // if description matches, add to count
      if (items[search][0] == description) {
        count++;
      }
    }
    // if there is at least one count
    if (count >= 1) {
      // return true for item contained
      itemPresent = true;
      return itemPresent;
    }
    // else false will return for no item contained
    else {
      return itemPresent;
    }
  }

  /**
   * Returns the number of items in the vending machine with a specific description and whose
   * expiration dates are greater or equal to a specific expiration date
   * 
   * @param description    description of the item to find its number of occurrences
   * @param expirationDate specific (earliest) expiration date
   * @param items          two dimensional array storing items within a vending machine where
   *                       items[i][0] represents the description of the item at index i and
   *                       items[i][1] stores its expiration date.
   * @param itemsCount     (size) number of items stored in the vending machine
   * @return the number of items with a specific description and whose expiration date is greater or
   *         equal to the given one
   */
  public static int getItemsOccurrencesByExpirationDate(String description, String expirationDate,
      String[][] items, int itemsCount) {
    // initialize variables
    int count = 0;
    int exp;
    int search;
    // search through indexes of array
    for (search = 0; search < itemsCount; ++search) {
      // if description matches
      if (items[search][0] == description) {
        // search for expiration date greater or equal to specific expiration date
        for (exp = 0; exp < itemsCount; ++exp) {
          if (Integer.parseInt((items[exp][1])) == (Integer.parseInt(expirationDate))) {
            count++;
          }
          if (Integer.parseInt((items[exp][1])) > (Integer.parseInt(expirationDate))) {
            count++;
          }
        }
      }
    }
    // return occurrences of matching item with equivalent or greater than given expiration date
    return count;
  }

  /**
   * Returns a summary of the contents of a vending machine in the following format: Each line
   * contains the description or item name followed by one the number of occurrences of the item
   * name in the vending machine between parentheses. For instance, if the vending machine contains
   * five bottles of water, ten chocolates, and seven snacks, the summary description will be as
   * follows. "water (5)\nchocolate (10)\nsnack (7)" If the vending machine is empty, this method
   * returns an empty string ""
   * 
   * @param items      two dimensional array storing items within a vending machine where
   *                   items[i][0] represents the description of the item at index i and items[i][1]
   *                   stores its expiration date.
   * @param itemsCount (size) number of items stored in the vending machine
   * @return a descriptive summary of the contents of a vending machine
   */
  public static String getItemsSummary(String[][] items, int itemsCount) {
    // set variable to empty string for return if no items are in machine
    String summary = "";
    // run through each index
    for (int print = 0; print < itemsCount; print++) {
      // if summary contains description, add occurrence
      if (!summary.contains(items[print][0])) {
        if (print == itemsCount - 1) {
          summary += (items[print][0]) + " ("
              + (getItemOccurrences(items[print][0], items, itemsCount) + ")");
        } else {
          summary += ("\n" + (items[print][0]) + " ("
              + (getItemOccurrences(items[print][0], items, itemsCount) + ")"));
        }
      }
    }
    // return string of description and occurrences
    return summary;
  }
}
