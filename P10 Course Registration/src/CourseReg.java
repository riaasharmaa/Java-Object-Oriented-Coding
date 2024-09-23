//////////////// CourseReg Class //////////////////////////
//
// Title: P10 Course Registration
// Course: CS 300 Fall 2022
//
// Author: Ria Sharma
// Email: rsharma78@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:
// Online Sources: ZyBooks
//
///////////////////////////////////////////////////////////////////////////////


/**
 * A application handler for course registration using a priority queue.
 */
public class CourseReg {

  // data fields
  private CourseQueue courses; // the priority queue of all courses
  private int creditLoad; // the maximum number of credits you want to take

  /**
   * Creates a new course registration object
   * 
   * @param capacity   the maximum number of courses to store in the priority queue
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if either capacity or creditLoad are not a positive integer
   */
  public CourseReg(int capacity, int creditLoad) throws IllegalArgumentException {
    // throw exception
    if (capacity <= 0 || creditLoad <= 0) {
      throw new IllegalArgumentException("parameters are not posative ints");
    }
    // initialize variable
    this.creditLoad = creditLoad;
    this.courses = new CourseQueue(capacity);
  }

  /**
   * Returns a string representation of the highest-priority courses with a total number of credits
   * less than or equal to the creditLoad of this CourseReg object. Use the Iterable property of the
   * CourseQueue to help you out!
   * 
   * Note that this is NOT a "knapsack" problem - you're trying to maximize priority, not number of
   * credits. Just add courses to your result String until adding the next would take you over this
   * CourseReg object's creditLoad limit.
   * 
   * @return a string representation with one course on each line, where the total number of credits
   *         represented is less than or equal to the current creditLoad value
   */
  public String getRecommendedCourses() {
    // set values to hold info
    String recommended = courses.peek().toString();
    int numCreds = courses.peek().getNumCredits();
    CourseIterator look = new CourseIterator(courses.deepCopy());
    look.next();
    // while loop to ensure the credits dont overload and there is a next course
    while (look.hasNext() && numCreds <= this.creditLoad) {
      Course temp = look.next();
      numCreds = numCreds + temp.getNumCredits();
      // break loop
      if (numCreds > creditLoad) {
        break;
      }
      recommended = recommended + "\n" + temp.toString();
    }
    return recommended;
  }

  /**
   * Tries to add the given course to the priority queue; return false if the queue is full
   * 
   * @param toAdd the course to add
   * @return true if the course was successfully added to the queue
   */
  public boolean add(Course toAdd) {
    // ensure the credits dont overload
    if (courses.size() == creditLoad) {
      return false;
    } else {
      // try to enqueue course
      try {
        courses.enqueue(toAdd);
      } catch (Exception e) {
        return false;
      }
      return true;
    }
  }

  /**
   * Updates the creditLoad data field to the provided value
   * 
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if creditLoad is not a positive integer
   */
  public void setCreditLoad(int creditLoad) throws IllegalArgumentException {
    // throw exception
    if (creditLoad <= 0) {
      throw new IllegalArgumentException("creditLoad not posative int");
    }
    // initialize creditload
    this.creditLoad = creditLoad;
  }
}
