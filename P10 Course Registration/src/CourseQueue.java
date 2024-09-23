//////////////// CourseQueue Class //////////////////////////
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


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Courses. Guarantees the max-heap
 * invariant, so that the Course at the root should have the highest score, and all children always
 * have a score lower than or equal to their parent's.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class CourseQueue implements Iterable<Course>, PriorityQueueADT<Course> {

  // data fields
  private Course[] queue; // array max-heap of courses representing this priority queue
  private int size; // number of courses currently in this priority queue

  /**
   * Creates a new, empty CourseQueue with the given capacity
   * 
   * @param capacity the capacity of this CourseQueue
   * @throws IllegalArgumentException if the capacity is not a positive integer
   */
  public CourseQueue(int capacity) {
    // throw exception
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity is not posative");
    }
    // initialize data fields
    this.size = 0;
    this.queue = new Course[capacity];
  }

  /**
   * Returns a deep copy of this CourseQueue containing all of its elements in the same order. This
   * method does not return the deepest copy, meaning that you do not need to duplicate courses.
   * Only the instance of the heap (including the array and its size) will be duplicated.
   * 
   * @return a deep copy of this CourseQueue, which has the same capacity and size as this queue.
   */
  public CourseQueue deepCopy() {
    // create course queueobject
    CourseQueue copy = new CourseQueue(this.size);
    // enqueue courses
    for (int i = 0; i < this.size; i++) {
      copy.enqueue(queue[i]);
    }
    return copy;
  }

  /**
   * Returns an Iterator for this CourseQueue which proceeds from the highest-priority to the
   * lowest-priority Course in the queue. Note that this should be an iterator over a DEEP COPY of
   * this queue.
   * 
   * @see CourseIterator
   * @return an Iterator for this CourseQueue
   */
  @Override
  public Iterator<Course> iterator() {
    // create deep copy
    CourseQueue deepcopy = this.deepCopy();
    // create iterator
    Iterator<Course> iterator = new CourseIterator(deepcopy);
    return iterator;
  }

  /**
   * Checks whether this CourseQueue is empty
   * 
   * @return {@code true} if this CourseQueue is empty
   */
  @Override
  public boolean isEmpty() {
    // check size
    if (this.size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the size of this CourseQueue
   * 
   * @return the size of this CourseQueue
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Adds the given Course to this CourseQueue and use the percolateUp() method to maintain max-heap
   * invariant of CourseQueue. Courses should be compared using the Course.compareTo() method.
   * 
   * 
   * @param toAdd Course to add to this CourseQueue
   * @throws NullPointerException  if the given Course is null
   * @throws IllegalStateException with a descriptive error message if this CourseQueue is full
   */
  @Override
  public void enqueue(Course toAdd) throws NullPointerException, IllegalStateException {
    // throw exceptions
    if (toAdd == null) {
      throw new NullPointerException("toAdd is null");
    }
    if (size == queue.length) {
      throw new IllegalStateException("CourseQueue is full");
    }
    if (size == 0) {
      queue[size] = toAdd;
      size++;
    }
    // call helper method
    else {
      queue[size] = toAdd;
      size++;
      percolateUp(size - 1);
    }
  }


  /**
   * Removes and returns the Course at the root of this CourseQueue, i.e. the Course with the
   * highest priority. Use the percolateDown() method to maintain max-heap invariant of CourseQueue.
   * Courses should be compared using the Course.compareTo() method.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException with a descriptive error message if this CourseQueue is empty
   */
  @Override
  public Course dequeue() throws NoSuchElementException {
    // throw exception
    if (isEmpty()) {
      throw new NoSuchElementException("courses are empty");
    }
    // call helper method
    Course root = queue[0];
    queue[0] = queue[size - 1];
    percolateDown(0);
    size--;
    return root;
  }

  /**
   * Returns the Course at the root of this CourseQueue, i.e. the Course with the highest priority.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException if this CourseQueue is empty
   */
  @Override
  public Course peek() throws NoSuchElementException {
    // throw exception
    if (this.isEmpty()) {
      throw new NoSuchElementException("course queue is empty");
    }
    // return highest priority course
    else {
      return this.queue[0];
    }
  }

  ///////////////////////////// TODO: QUEUE HELPER METHODS //////////////////////////////////

  /**
   * Restores the max-heap invariant of a given subtree by percolating its root down the tree. If
   * the element at the given index does not violate the max-heap invariant (it is higher priority
   * than its children), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, then swap the element with the correct child and
   * continue percolating the element down the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int index) throws IndexOutOfBoundsException {
    // set child and value
    int child = index * 2 + 1;
    Course value = queue[index];
    // keep index in bounds
    while (index < size()) {
      Course t = value;
      int max = -1;
      for (int i = 0; i < 2 && i + child < size(); i++) {
        if (queue[i + child].compareTo(t) > 0) {
          t = queue[i + child];
          max = i + child;
        }
      }
      if (value == t) {
        return;
      } else {
        Course t1 = queue[index];
        queue[index] = queue[max];
        queue[max] = t1;
        index = max;
        child = 2 * index + 1;
      }
    }
  }

  /**
   * Restores the max-heap invariant of the tree by percolating a leaf up the tree. If the element
   * at the given index does not violate the max-heap invariant (it is lower priority than its
   * parent), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int index) throws IndexOutOfBoundsException {
    // keep index in bounds
    while (index > 0) {
      int parent = (index - 1) / 2;
      if (queue[index].compareTo(queue[parent]) < 0) {
        return;
      } else {
        Course t = queue[index];
        queue[index] = queue[parent];
        queue[parent] = t;
        index = parent;
      }
    }
  }

  ////////////////////////////// PROVIDED: TO STRING ////////////////////////////////////

  /**
   * Returns a String representing this CourseQueue, where each element (course) of the queue is
   * listed on a separate line, in order from the highest priority to the lowest priority.
   * 
   * @author yiwei
   * @see Course#toString()
   * @see CourseIterator
   * @return a String representing this CourseQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Course c : this) {
      val.append(c).append("\n");
    }

    return val.toString().trim();
  }

}
