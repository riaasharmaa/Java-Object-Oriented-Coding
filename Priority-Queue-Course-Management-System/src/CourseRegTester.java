import java.util.NoSuchElementException;

//////////////// CourseRegTester Class //////////////////////////
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
 * This class implements unit test methods to check the correctness of Course, CourseIterator,
 * CourseQueue and CourseReg classes in P10.
 * 
 * Be aware that all methods in this class will be run against not only your code, but also our own
 * working and broken implementations to verify that your tests are working appropriately!
 */
public class CourseRegTester {

  /**
   * START HERE, and continue with testCompareTo() after this.
   * 
   * This method must test the Course constructor, accessor, and mutator methods, as well as its
   * toString() implementation. The compareTo() method will get its own test.
   * 
   * @see Course
   * @return true if the Course implementation is correct; false otherwise
   */
  public static boolean testCourse() {
    int check = 0;
    // constructor
    try {
      Course blankdept = new Course("", 300, 4, 10);
    } catch (IllegalArgumentException e) {
      check++;
    }
    try {
      Course nulldept = new Course(null, 300, 4, 10);
    } catch (IllegalArgumentException e) {
      check++;
    }
    try {
      Course zerocoursenum = new Course("CS", 0, 4, 10);
    } catch (IllegalArgumentException e) {
      check++;
    }
    try {
      Course negcoursenum = new Course("CS", -2, 4, 10);
    } catch (IllegalArgumentException e) {
      check++;
    }
    try {
      Course lesscredits = new Course("CS", 300, 0, 10);
    } catch (IllegalArgumentException e) {
      check++;
    }
    try {
      Course morecredits = new Course("CS", 300, 6, 10);
    } catch (IllegalArgumentException e) {
      check++;
    }
    try {
      Course negseats = new Course("CS", 300, 4, -2);
    } catch (IllegalArgumentException e) {
      check++;
    }
    Course validcourse = new Course("CS", 300, 4, 10);
    if (check != 7) {
      System.out.println(check + "/7 invalid constructors handled correctly");
      return false;
    }
    // accessors
    // getnumcredits
    if (validcourse.getNumCredits() != 4) {
      System.out.println("error in getNumCredits");
      return false;
    }
    // equals
    Course equalcourse = new Course("CS", 300, 4, 10);
    if (validcourse.equals(equalcourse) != true) {
      System.out.println("error in equals - 2 equal courses");
      return false;
    }
    Course difdept = new Course("LIT", 300, 4, 10);
    Course difnum = new Course("CS", 200, 4, 10);
    Course difcredit = new Course("CS", 300, 3, 10);
    if (validcourse.equals(difdept) != false) {
      System.out.println("error in equals - diff dept");
      return false;
    }
    if (validcourse.equals(difnum) != false) {
      System.out.println("error in equals - diff num");
      return false;
    }
    if (validcourse.equals(difcredit) != false) {
      System.out.println("error in equals - diff credit");
      return false;
    }
    // tostring
    Course toprint = new Course("CS", 300, 4, 0);
    toprint.setProfessor("Hobbes", 1.4);
    if (!toprint.toString().equalsIgnoreCase("CS 300 (closed) with Hobbes (1.4)")) {
      System.out.println("error in toString - w prof");
      return false;
    }
    Course toprint2 = new Course("CS", 300, 4, 11);
    if (!toprint2.toString().equalsIgnoreCase("CS 300 (11 seats)")) {
      System.out.println("error in toString - w/o prof");
      return false;
    }
    // mutators
    // setprof
    Course c1 = new Course("CS", 300, 4, 10);
    try {
      c1.setProfessor(null, 3);
    } catch (Exception e) {
      System.out.println("error setprof - null prof w rating");
      return false;
    }
    try {
      c1.setProfessor(null, -1);
    } catch (Exception e) {
      System.out.println("error setprof - null prof w wrong rating");
      return false;
    }
    int profcheck = 0;
    try {
      c1.setProfessor("Hobbes", -1);
    } catch (IllegalArgumentException e) {
      profcheck++;
    }
    try {
      c1.setProfessor("Hobbes", 6);
    } catch (IllegalArgumentException e) {
      profcheck++;
    }
    if (profcheck != 2) {
      System.out.println("accepted invalid prof rating");
      return false;
    }
    try {
      c1.setProfessor("Hobbes", 3);
    } catch (Exception e) {
      System.out.println("failed to set valid prof/rating");
      return false;
    }
    // setseats available
    boolean confirm = false;
    Course course1 = new Course("CS", 300, 4, 10);
    try {
      course1.setSeatsAvailable(-2);
    } catch (IllegalArgumentException e) {
      confirm = true;
    }
    if (confirm != true) {
      System.out.println("negative seats available was set");
      return false;
    }
    try {
      course1.setSeatsAvailable(4);
    } catch (Exception e) {
      System.out.println("failed to set valid seat num");
      return false;
    }
    return true;
  }

  /**
   * This method must test the Course compareTo() implementation. Be sure to test ALL FOUR levels of
   * the comparison here!
   * 
   * Once you complete this test, finish the Course implementation if you have not done so already,
   * then move to testCourseQueue() and testEnqueueDequeue().
   * 
   * @see Course#compareTo(Course)
   * @return true if the compareTo() implementation is correct; false otherwise
   */
  public static boolean testCompareTo() {
    // equal
    Course og = new Course("CS", 300, 4, 10);
    Course copy = new Course("CS", 300, 4, 10);
    if (og.compareTo(copy) != 0) {
      System.out.println("failed to return 0 when courses are equal");
      return false;
    }
    // major dept
    Course samedept = new Course("CS", 200, 4, 10);
    if (og.compareTo(samedept) != 0) {
      System.out.println("failed to return 0 when courses are same dept");
      return false;
    }
    Course difdept = new Course("LIT", 300, 4, 10);
    if (og.compareTo(difdept) != 1) {
      System.out.println("failed to return 1 when this is same as major dept");
      return false;
    }
    // seat availability
    Course hasseats = new Course("CS", 300, 4, 4);
    if (og.compareTo(hasseats) != 0) {
      System.out.println("failed to return 0 when both have seats");
      return false;
    }
    Course noseats = new Course("CS", 300, 4, 0);
    if (og.compareTo(noseats) != 1) {
      System.out.println("failed to return 1 when this has seats and other doesnt");
      return false;
    }
    // known prof
    Course knownprof = new Course("CS", 300, 4, 4);
    knownprof.setProfessor("Hobbes", 3);
    if (og.compareTo(knownprof) != -1) {
      System.out.println("failed to return -1 when other has known prof");
      return false;
    }
    // prof ranking
    Course high = new Course("CS", 300, 4, 4);
    high.setProfessor("hobbes", 4);
    Course low = new Course("CS", 300, 4, 4);
    low.setProfessor("tina", 2);
    if (high.compareTo(low) != 1) {
      System.out.println("failed to return 1 when this has higher prof rating");
      return false;
    }
    return true;
  }

  /**
   * This method must test the other methods in CourseQueue (isEmpty, size, peek). Verify normal
   * cases and error cases, as well as a filled and re-emptied queue.
   * 
   * Once you have completed this method, implement the required methods in CourseQueue and verify
   * that they work correctly.
   * 
   * @see CourseQueue
   * @return true if CourseQueue's other methods are implemented correctly; false otherwise
   */
  public static boolean testCourseQueue() {
    CourseQueue emptyqueue = new CourseQueue(1);
    CourseQueue notemptyqueue = new CourseQueue(5);
    Course c1 = new Course("CS", 300, 4, 10);
    Course c2 = new Course("LIT", 100, 4, 0);
    Course c3 = new Course("CS", 220, 4, 0);
    notemptyqueue.enqueue(c1);
    notemptyqueue.enqueue(c2);
    notemptyqueue.enqueue(c3);
    // isempty
    if (emptyqueue.isEmpty() != true) {
      System.out.println("is empty did not return true when queue is empty");
      return false;
    }
    if (notemptyqueue.isEmpty() != false) {
      System.out.println("is empty did not return false when queue is not empty");
      return false;
    }
    // size
    if (emptyqueue.size() != 0) {
      System.out.println("queue size did not return 0 when size is 0");
      return false;
    }
    if (notemptyqueue.size() != 3) {
      System.out.println("queue size did not return 3 when size is 3");
      return false;
    }
    // peek
    if (notemptyqueue.peek() != c1) {
      System.out.println("peek error");
      return false;
    }
    boolean check = false;
    try {
      emptyqueue.peek();
    } catch (NoSuchElementException e) {
      check = true;
    }
    if (check != true) {
      return false;
    }
    try {
      notemptyqueue.dequeue();
      notemptyqueue.dequeue();
      notemptyqueue.dequeue();
    } catch (Exception e) {
      return false;
    }
    if (!notemptyqueue.isEmpty()) {
      return false;
    }
    boolean checks = false;
    try {
      notemptyqueue.peek();
    } catch (Exception e) {
      checks = true;
    }
    if (checks != true) {
      return false;
    }
    return true;
  }

  /**
   * This method must test the enqueue and dequeue methods in CourseQueue. Verify normal cases and
   * error cases, as well as filling and emptying the queue.
   * 
   * You may also test the percolate methods directly, though this is not required.
   * 
   * Once you have completed this method, implement the enqueue/dequeue and percolate methods in
   * CourseQueue and verify that they work correctly, then move on to testCourseIterator().
   * 
   * @see CourseQueue#enqueue(Course)
   * @see CourseQueue#dequeue()
   * @return true if the CourseQueue enqueue/dequeue implementations are correct; false otherwise
   */
  public static boolean testEnqueueDequeue() {
    CourseQueue queue = new CourseQueue(4);
    Course c1 = new Course("CS", 300, 4, 10);
    Course c2 = new Course("CS", 220, 4, 0);
    Course c3 = new Course("LIT", 100, 4, 10);
    Course c4 = new Course("STAT", 100, 4, 0);
    // enqueue
    try {
      queue.enqueue(c1);
    } catch (Exception e) {
      return false;
    }
    if (queue.size() != 1) {
      System.out.println("failed to 1st enque an empty queue");
      return false;
    }
    try {
      queue.enqueue(c2);
    } catch (Exception e) {
      return false;
    }
    if (queue.size() != 2) {
      System.out.println("failed to 2nd enque a nonempty queue");
      return false;
    }
    try {
      queue.enqueue(c3);
    } catch (Exception e) {
      return false;
    }
    if (queue.size() != 3) {
      System.out.println("failed to 3rd enque a nonempty queue");
      return false;
    }
    if (!queue.peek().toString().equalsIgnoreCase(c1.toString())) {
      System.out.println("did not enque in priority order");
      return false;
    }
    try {
      queue.enqueue(c4);
    } catch (Exception e) {
      return false;
    }

    try {
      queue.enqueue(c4);
      return false;
    } catch (IllegalStateException e) {

    } catch (Exception e) {
      return false;
    }
    // dequeue

    Course cd = queue.dequeue();
    if (queue.size() != 3) {
      System.out.println("failed 1st dequeue a nonempty queue");
      return false;
    }
    if (!cd.equals(c1)) {
      return false;
    }
    Course cd2 = queue.dequeue();
    if (queue.size() != 2) {
      System.out.println("failed 2nd dequeue a nonempty queue");
      return false;
    }
    if (!cd2.equals(c2)) {
      return false;
    }
    Course cd3 = queue.dequeue();
    if (queue.size() != 1) {
      System.out.println("failed 3nd dequeue a nonempty queue");
      return false;
    }
    if (!cd3.equals(c3)) {
      return false;
    }
    return true;
  }

  /**
   * This method must test the CourseIterator class. The CourseIterator iterates through a deep copy
   * of a CourseQueue in decreasing order of priority, returning each Course object in turn.
   * 
   * Once you have completed this method, implement the CourseIterator class and make CourseQueue
   * into an Iterable class. Verify that this works correctly, and then move on to the final test
   * method: testCourseReg().
   * 
   * @see CourseIterator
   * @return true if the CourseIterator implementation is correct; false otherwise
   */
  public static boolean testCourseIterator() {
    // create objects
    CourseQueue queue = new CourseQueue(5);
    Course c1 = new Course("CS", 300, 3, 20);
    queue.enqueue(c1);
    Course c2 = new Course("MATH", 220, 4, 0);
    queue.enqueue(c2);
    Course c3 = new Course("LIT", 100, 3, 40);
    c3.setProfessor("Jordan", 3.5);
    queue.enqueue(c3);
    Course c4 = new Course("ART", 150, 3, 88);
    c4.setProfessor("John", 4.5);
    queue.enqueue(c4);
    Course c5 = new Course("LIT", 500, 4, 41);
    queue.enqueue(c5);
    // iterator
    CourseIterator it;
    try {
      it = new CourseIterator(queue);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // has next and next
    if (!it.hasNext()) {
      return false;
    }
    Course c;
    try {
      c = it.next();
    } catch (Exception e) {
      return false;
    }

    if (!c.equals(c1)) {
      return false;
    }

    if (!it.hasNext()) {
      return false;
    }

    c = it.next();
    if (!c.equals(c4)) {
      return false;
    }

    if (!it.hasNext()) {
      return false;
    }

    c = it.next();
    if (!c.equals(c3)) {
      return false;
    }

    if (!it.hasNext()) {
      return false;
    }

    c = it.next();
    if (!c.equals(c5)) {
      return false;
    }

    if (!it.hasNext()) {
      return false;
    }

    c = it.next();
    if (!c.equals(c2)) {
      return false;
    }

    if (it.hasNext()) {
      return false;
    }

    try {
      c = it.next();
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      return false;
    }

    return true;
  }


  /**
   * This method must test the constructor and three methods of the CourseReg class (setCreditLoad,
   * add, and getRecommendedCourses). Verify normal cases and error cases.
   * 
   * Once you have completed this method, implement CourseReg and verify that it works correctly.
   * Then you're DONE! Save and submit to gradescope, and enjoy being DONE with programming
   * assignments in CS 300 !!!
   * 
   * @see CourseReg
   * @return true if CourseReg has been implemented correctly; false otherwise
   */
  public static boolean testCourseReg() {
    // create objects
    CourseReg cr;
    Course c1 = new Course("CS", 300, 3, 100);
    Course c2 = new Course("LIT", 240, 4, 0);
    Course c3 = new Course("MATH", 100, 3, 20);
    c3.setProfessor("Pac", 3);
    Course c4 = new Course("HIST", 150, 3, 75);
    c4.setProfessor("Angie", 4);
    Course c5 = new Course("SCI", 461, 4, 20);
    Course c6 = new Course("MATH", 221, 4, 55);
    // test course reg
    int count = 0;
    try {
      cr = new CourseReg(5, -15);
      return false;
    } catch (IllegalArgumentException e) {
      count++;
    } catch (Exception e) {
      return false;
    }
    try {
      cr = new CourseReg(5, 20);
    } catch (Exception e) {
      return false;
    }
    if (count != 1) {
      return false;
    }
    // test add
    try {
      if (cr.add(null) == true) {
        return false;
      }
      if (cr.add(c1) == false) {
        return false;
      }
      if (cr.add(c2) == false) {
        return false;
      }
      if (cr.add(c3) == false) {
        return false;
      }
      if (cr.add(c4) == false) {
        return false;
      }
      if (cr.add(c5) == false) {
        return false;
      }
      if (cr.add(c6) == true) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    // test set credit load
    try {
      cr.setCreditLoad(-5);
      return false;
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      return false;
    }
    try {
      cr.setCreditLoad(14);
    } catch (Exception e) {
      return false;
    }

    // test get recommended courses
    String expect = "";
    expect += c1.toString() + "\n";
    expect += c4.toString() + "\n";
    expect += c3.toString() + "\n";
    expect += c5.toString();

    if (!cr.getRecommendedCourses().trim().equals(expect.trim())) {
      return false;
    }

    CourseReg cr2 = new CourseReg(3, 4);
    Course cswithseats = new Course("CS", 300, 3, 30);
    Course cswithoutseats = new Course("CS", 220, 3, 0);
    Course notcs = new Course("LIT", 200, 1, 10);
    cr2.add(cswithseats);
    cr2.add(cswithoutseats);
    cr2.add(notcs);

    if (!cr2.getRecommendedCourses().trim().equals(cswithseats.toString().trim())) {
      return false;
    }
    return true;
  }


  /**
   * This method calls all test methods defined by us; you may add additional methods to this if you
   * like. All test methods must be public static boolean.
   * 
   * @return true if all tests in this class return true; false otherwise
   */
  public static boolean runAllTests() {
    boolean testVal = true;

    // test Course
    System.out.print("testCourse(): ");
    if (!testCourse()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test compareTo
    System.out.print("testCompareTo(): ");
    if (!testCompareTo()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseIterator
    System.out.print("testCourseIterator(): ");
    if (!testCourseIterator()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseQueue enqueue/dequeue
    System.out.print("testEnqueueDequeue(): ");
    if (!testEnqueueDequeue()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseQueue
    System.out.print("testCourseQueue(): ");
    if (!testCourseQueue()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseReg
    System.out.print("testCourseReg(): ");
    if (!testCourseReg()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    return testVal;
  }

  /**
   * Calls runAllTests() so you can verify your program
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
