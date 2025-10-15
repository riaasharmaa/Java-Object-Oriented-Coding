///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Ria Sharma
// Campus ID: 908 442 3467
// WiscEmail: rsharma78@wisc.edu
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// BE CAREFUL!! This file contains TWO classes. You will need to complete the
// implementation of BOTH classes with respect to the provided requirements
// in the TODO tags for full credit.
//
// When creating new exception objects, including messages within these objects
// is optional.
////////////////////////////////////////////////////////////////////////////////
/**
 * This class models the RunRoute data type, which represents information about a route someone
 * might follow on their run including its name and the length of the route.
 * 
 * NOTES: You may NOT add any additional data fields to this class unless specified in the TODO
 * tags. You may NOT add ANY additional methods to this class, regardless of access modifier. There
 * is no tester or main method for this class.
 */
public class RunRoute {
  // TODO
  // 1. Declare a private instance of type String named routeName
  private String routeName;
  // 2. Declare a protected instance field of type double named routeLength
  protected double routeLength;
  // 3. Declare a private static data field of type double named totalDistance, initialized to zero
  // totalDistance represents the total length of all RunRoute objects created by the constructor
  private static double totalDistance = 0;

  /**
   * Creates a new RunRoute with the given name and length.
   * 
   * @param name   the name of the route
   * @param length the distance covered by this route in kilometers
   * @throws IllegalArgumentException if the route's distance is not positive (i.e., is <= 0), or if
   *                                  the route name is null or blank
   */
  public RunRoute(String name, double length) throws IllegalArgumentException {
    // TODO
    // 4. Check the validity of the input parameters and handle appropriately
    if (length <= 0) {
      throw new IllegalArgumentException("length is not a valid posative number");
    }
    if (name == null) {
      throw new IllegalArgumentException("Name is null");
    }
    if (name.isBlank()) {
      throw new IllegalArgumentException("Name is blank");
    }

    // 5. Set the instance data fields to the provided input parameters
    this.routeName = name;
    this.routeLength = length;
    // 6. Add the length of this route to the total distance of all RunRoutes
    totalDistance += length;
  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

  /**
   * Gets the total distance across all created RunRoute objects
   * 
   * @return the total distance in kilometers
   */
  public static double getTotalDistance() {
    // TODO
    // 7. Complete the implementation of this accessor method

    return totalDistance;
  }

  /**
   * Changes the name of this route, if and only if the provided name is not null or blank
   * 
   * @param name the new name of the route
   */
  public void rename(String name) {
    // TODO
    // 8. Complete the implementation of this mutator method
    // Note: if the provided name is null or blank, do nothing
    if (name == null | name.isBlank()) {
      return;
    }
    this.routeName = name;
  }

  /**
   * Returns a string representation of this RunRoute. The returned string must have the following
   * format: routeName + ": " + routeLength + "km" For example, "crazylegs: 8.0km"
   * 
   * @return a string formatted as routeName + ": " + routeLength + "km"
   */
  @Override
  public String toString() {
    // TODO
    // 9. Complete the implementation of this method
    return this.routeName + ": " + this.routeLength + "km";
  }
}


// Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)
/**
 * This class models the RaceRoute data type, representing the route of a footrace.
 * 
 * NOTES: You may NOT add any additional data fields to this class. You may NOT add ANY additional
 * methods to this class, regardless of access modifier. There is no tester or main method for this
 * class.
 */
class RaceRoute extends RunRoute { // TODO: 10. Set this class to be a subclass of the RunRoute
                                   // class

  private double time; // the official time for the race, in number of minutes (1:05:30->65.5)
  private int placing; // the official placing in the race. defaults to -1 when unknown.

  /**
   * Creates a new RaceRoute with provided name, length, and time.
   * 
   * @param name   the name of the route
   * @param length the total distance of the route in kilometers
   * @param time   the time to complete the race in number of minutes
   * @throws IllegalArgumentException if the route's distance is not positive (i.e., is <= 0), or if
   *                                  the route name is null or blank
   * @throws IllegalStateException    if the time is not positive (i.e. is <= 0)
   */
  public RaceRoute(String name, double length, int time) {
    // TODO
    // 11. Call the constructor of the super class with required arguments.
    // HINT: Do not catch the IllegalArgumentException that it may throw. Let the exception
    // propogate.
    super(name, length);
    // 12. Check the validity of the input time parameter and handle invalid input accordingly
    if (length <= 0) {
      throw new IllegalArgumentException("distance is not a valid posative number");
    }
    if (name == null) {
      throw new IllegalArgumentException("Name is null");
    }
    if (name.isBlank()) {
      throw new IllegalArgumentException("Name is blank");
    }
    if (time <= 0) {
      throw new IllegalArgumentException("time is not a valid posative number");
    }
    // 13. Set the time field to the time passed as input
    this.time = time;
    // 14. Set the placing field to the value indicating unknown (see comment above)
    placing = -1;
  }

  /**
   * Uses the route length and recorded time to calculate the runner's speed in kilometers per hour
   * 
   * @return the runner's speed in kilometers per hour
   */
  public double getSpeed() {
    // TODO
    // 15. Convert the time in minutes to time in hours (divide by 60), then divide the routeLength
    // by this value to calculate the speed in kilometers per hour (and return it).
    double hrs = time / 60.0;
    return ((this.routeLength) / hrs);
  }

  /**
   * Updates the runner's placing in this race
   * 
   * @param placing what place the runner finished in (1, 2, 3, etc)
   */
  public void setPlacing(int placing) {
    // TODO
    // 16. Complete the implementation of this mutator method
    // Note: if the provided placing is 0 or negative, do nothing
    if (placing <= 0) {
      return;
    }
    this.placing = placing;
  }

  /**
   * Returns a string representation of this RaceRoute object. The returned string must have the
   * following format: routeName + ": " + routeLength + "km, " + getSpeed() + "kph" If the placing
   * is known (that is, not -1), include a " *" at the end of the string. For example, a race with a
   * known placing might be "crazylegs: 8.0km, 7.2kph *"
   * 
   * @return a string representation of this RaceRoute object
   */
  @Override
  public String toString() {
    // TODO
    // 17. Complete the implementation of this method.
    // Note: the name of this route is a private data field with no accessor defined in the super
    // class.
    // Use the toString() method from the RunRoute class to start your implementation.

    // remember to add the * if there is a known placing!
    String r = super.toString() + ", " + getSpeed() + "kph";
    return r; // default return statement added to avoid compiler errors. Feel free to change it.
  }

  // MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) before submitting it to Gradescope

}
