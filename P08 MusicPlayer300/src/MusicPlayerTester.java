//////////////// Music Player Tester Class //////////////////////////
//
// Title: P08 MusicPlayer300
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
import java.lang.Exception;

/**
 * A tester class
 */
public class MusicPlayerTester {
  public static void main(String[] args) {
    System.out.println(testSongConstructor());
    System.out.println(testSongPlayback());
    System.out.println(testSongNode());
    System.out.println(testEnqueue());
    System.out.println(testDequeue());
  }

  /**
   * A tester for song constructor Test a valid file with toString() and getTitle() and getArtist()
   * accessor methods.
   * 
   * @return true if constructors and accessor methods work correctly
   */
  public static boolean testSongConstructor() {
    boolean check = false;
    try {
      // wrong song
      Song s1 = new Song("Staying Alive", "Drake", null);
    } catch (IllegalArgumentException e) {
      check = true;
      System.out.println("correct implementation, IllegalArgumentException");
    } catch (Exception e) {
      System.out.println("wrong implementation, other exception thrown");
      return false;
    }
    // correct song
    Song s2 = new Song("Africa", "Toto", ("audio/toto-africa.mid"));
    // test get artist
    if (!s2.getArtist().equals("Toto")) {
      System.out.println("Invalid artist instance");
      return false;
    }
    // test get title
    if (!s2.getTitle().equals("Africa")) {
      System.out.println("Invalid title instance");
      return false;
    }
    // test toString
    // TODO
    if (check != true) {
      return false;
    }
    return true; // No errors detected, return true
  }

  /**
   * A tester for song playback methods
   * 
   * @return true if the Playback methods work correctly
   */
  public static boolean testSongPlayback() {
    Song s1 = new Song("Africa", "Toto", "audio/toto-africa.mid");
    // test is playing
    if (s1.isPlaying()) {
      System.out.println("isPlaying() method incorrectly determines whether audio is playing");
      return false;
    }
    // test play
    s1.play();
    if (!s1.isPlaying()) {
      System.out.println("play() method doesn't start the audio correctly");
      return false;
    }
    // Thread.sleep(1000);
    // test stop
    s1.stop();
    if (s1.isPlaying()) {
      System.out.println("stop() method doesn't stop the audio correctly");
    }

    return true; // returns true if no errors are detected
  }

  /**
   * A tester for song node methods and constructors
   * 
   * @return true if SongNode works correctly
   */
  public static boolean testSongNode() {
    // test song
    Song s1 = new Song("Africa", "Toto", ("audio/toto-africa.mid"));
    Song s2 = new Song("Thing", "Person", ("audio/1.mid"));
    // test songNode
    SongNode sN2 = new SongNode(s2);
    SongNode sN1 = new SongNode(s1, sN2);
    if (s1 != sN1.getSong()) {
      System.out.println("Error getting data");
      return false;
    }
    if (sN2 != sN1.getNext()) {
      System.out.println("Error in getNext method");
      return false;
    }
    sN2.setNext(sN1);
    if (sN1 != sN2.getNext()) {
      System.out.println("Error in setNext method");
      return false;
    }

    return true; // returns true if passes all test cases
  }


  /**
   * Tests the constructors, enqueue, and accessors
   * 
   * @return true if Enqueue, constructor, and accessor methods work correctly
   */
  public static boolean testEnqueue() {
    Playlist p = new Playlist();
    Song s1 = new Song("Africa", "Toto", ("audio/toto-africa.mid"));
    Song s2 = new Song("test1", "hobbes", ("audio/1.mid"));
    // test enqueue in empty playlist
    p.enqueue(s1);
    if (p.peek() != s1 && p.size() != 1) {
      System.out.println("enqueue error - empty");
      return false;
    }
    // test enqueue in not empty arraylist
    p.enqueue(s2);
    if (p.peek() != s1 && p.size() != 2) {
      System.out.println("enqueue error - not empty");
      return false;
    }
    return true;
  }

  /**
   * Tests whether the dequeue method works correctly
   * 
   * @return true if dequeue works correctly
   */
  public static boolean testDequeue() {
    Playlist p = new Playlist();
    Song s1 = new Song("Africa", "Toto", ("audio/toto-africa.mid"));
    Song s2 = new Song("test1", "hobbes", ("audio/1.mid"));
    // enqueue
    p.enqueue(s1);
    p.enqueue(s2);
    if (p.peek() != s1 && p.size() != 2) {
      System.out.println("enqueue error");
      return false;
    }
    // test dequeue
    p.dequeue();
    if (p.peek() != s2 && p.size() != 1) {
      System.out.println("dequeue error");
      return false;
    }
    p.dequeue();
    if (!p.isEmpty()) {
      System.out.println("dequeue error");
      return false;
    }
    return true;
  }
}


