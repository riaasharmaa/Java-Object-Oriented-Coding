///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Ria Sharma
// Campus ID: 9084423467
// WiscEmail: rsharma78@wisc.edu
////////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;
import java.util.NoSuchElementException;

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class. You will need to complete the implementation
// this class with respect to the provided requirements in the TODO tags for
// full credit.
//
// When creating new exception objects, including messages within these objects
// is optional.
////////////////////////////////////////////////////////////////////////////////

/**
 * This class implements methods to manage the list of weekly assignments. The assignments' names
 * are stored in an oversize array defined by a reference to String[] array called assignments and
 * an int variable called size which keeps track of the total number of assessments stored in the
 * array.
 * 
 * The array is an oversize array. This means that the array contains non null references from index
 * 0 to index size-1. All references in the range of indexes size .. assignments.length-1 MUST be
 * null.
 * 
 * New assignments are added to the beginning (index 0) of the oversize array. Assignments at the
 * end of the oversize array should be completed (removed) first.
 * 
 * All string comparisons are CASE-SENSITIVE.
 * 
 */
public class AssignmentList {

  /**
   * Adds a new assignment to the beginning (index 0) of the oversize array defined by assignments
   * and size. The array assignments contains non null references from index 0 to index size-1. All
   * references in the range of indexes size .. assignments.length-1 are null.
   * 
   * If there is no space to add the new assignment, this method prints the following message:
   * 
   * "Error! Full list of assignments."
   * 
   * @param assignments   an array which stores weekly assignments
   * @param size          total number of assignments stored in the array
   * @param newAssignment new assignment to be added. We assume that newAssignment is NOT null
   * @return the size of the oversize array after trying to add the new assignment
   */
  public static int addFirst(String[] assignments, int size, String newAssignment) {
    // TODO: Complete the implementation of this method
    // Hint1: You should consider two cases: trying to add to a full array and adding to a non-full
    // array

    // Hint2: Adding to index 0 of an array should involve a shift operation
    // For instance, considering the following input arguments:
    // original array assignments: {e1, e2, e3, e4, null, null} and size: 4,
    // after calling addFirst(courses, size, e0), the array
    // assignments should contain {e0, e1, e2, e3, e4, null} and the returned value should be 5.
    if ((assignments.length > 0) && (size < assignments.length)) {
      assignments[size] = newAssignment;
      size++;
    } else {
      System.out.println("Error! Full list of assignments.");
    }
    return size;
  }

  /**
   * Returns the total number of quizzes stored in the assignments oversize array. An assignment is
   * a quiz if it contains the "quiz" string.
   * 
   * @param assignments an array which stores weekly assignments
   * @param size        total number of assignments stored in the array
   * @return the number of quizzes stored in the assignments array
   */
  public static int getQuizzesCount(String[] assignments, int size) {
    // TODO complete the implementation of this method
    int count = 0;
    String keyword = "quiz";
    for (int i = 0; i < size; i++) {
      if (assignments[i].contains(keyword)) {
        count = count + 1;
      }
    }
    return count;
  }

  /**
   * Removes the assignment stored at the end of the oversize array defined by assignments and size.
   * 
   * 
   * @param assignments an array which stores weekly assignments
   * @param size        size of the assignments array
   * @return the size of the oversize array after removing the last assessment (stored at the end of
   *         the oversize array).
   * @throws NoSuchElementException if size is negative ( <= 0)
   */
  public static int removeLast(String[] assignments, int size) throws NoSuchElementException {
    // TODO
    // 1. throw a NoSuchElementException if size <= 0
    if (size <= 0) {
      throw new NoSuchElementException();
    }
    // 2. removes the assignment at the end of the oversize array
    int index = 0;
    for (; index < size - 1; ++index) {
      assignments[index] = assignments[index + 1];
    }
    assignments[size - 1] = null;
    return (size - 1);
    // 3. return size
  }

  /**
   * Checks whether the correctness of the AssignmentList.addFirst() method when passed a non-full
   * oversize array.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSuccessfulAddFirst() {
    // TODO
    // 1. Create an NON-full oversize array of Strings which already contains 4 assignments.
    String[] assignments = new String[] {"quiz2", "exam1", "program4", "quiz1", null, null};
    int size = 4;
    // 2. Try to call AssignmentList.addFirst() to add "exam2" to the list of assignments
    // 3. Expected behavior: the new assignment must be added at index 0 of the array and the method
    // should return 5 (new size of the oversize array)
    // Check that the method addFirst() did make the expected changes to the contents of the
    // original
    // array passed as input
    if (AssignmentList.addFirst(assignments, size, "exam2") != 5) {
      System.out.println("error: did not add to size of assignments array");
      return false;
    } else {
      return true;
    }
  }

  /**
   * Checks the correctness of the implementation of the AssignmentList.getQuizzesCount() method
   * when passed an non-empty array containing a few quizzes.
   * 
   * @return true if the method passes this test and false otherwise
   */
  public static boolean testGetQuizzesCount() {
    // This tester method does not check for all test scenarios to check the correctness of
    // getQuizzesCount() method. This is NOT a programming assignment. You do not need to check for
    // everything in an exam.

    // test scenario: non-empty oversize array of assignments
    String[] assignments =
        new String[] {"cquiz1", "p02", "quiz1-2", "p01", "zybooksw01", "quiz1-1", null, null, null};
    int size = 6;
    int expectedQuizCount = 3; // expected output
    String[] original =
        new String[] {"cquiz1", "p02", "quiz1-2", "p01", "zybooksw01", "quiz1-1", null, null, null};
    try {
      // call getQuizzesCount() and check whether it returns the expected output without making any
      // changes to the contents of the array passed as input
      int quizCount = getQuizzesCount(assignments, size);
      if (quizCount != expectedQuizCount) // incorrect output
        return false;
      if (!Arrays.deepEquals(assignments, original)) // bug! changes made to the assignments array
        return false;


    } catch (Exception e) { // catches any unexpected exception
      return false;
    }
    return true; // no bugs detected by this tester
  }

  /**
   * Checks whether the AssignmentList.removeLast method works as expected when called to remove the
   * last assignment from a non-empty oversize array of Strings
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLastAssignmentRemoval() {
    // create an non-empty oversize array
    String[] assignments =
        new String[] {"p02", "quiz1-2", "p01", "quiz1-1", "zybooksw01", null, null, null};
    int size = 5;
    // expected output and array after removing the last assignment
    String[] expectedAssignments =
        new String[] {"p02", "quiz1-2", "p01", "quiz1-1", null, null, null, null};
    int expectedOutput = 4;
    try {
      // try to remove the last assignment
      size = removeLast(assignments, size);
      // check whether the returned size and the content of the array are correct
      if (size != expectedOutput)
        return false;
      if (!Arrays.deepEquals(assignments, expectedAssignments))
        return false;
    } catch (Exception e) {
      return false; // no exception is expected to be thrown
    }

    return true; // no bugs detected by this tester
  }

  /**
   * Main method to call the test methods
   * 
   * @param args - input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testGetQuizzesCount(): " + testGetQuizzesCount());
    System.out.println("testLastAssignmentRemoval(): " + testLastAssignmentRemoval());
    System.out.println("testSuccessfulAddFirst(): " + testSuccessfulAddFirst());
  }

}
