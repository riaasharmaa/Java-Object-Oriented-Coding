//////////////// Playlist Class //////////////////////////
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
/**
 * A FIFO linked queue of SongNodes, conforming to our QueueADT interface.
 */
public class Playlist implements QueueADT<Song> {
  private SongNode first; // The current first song in the queue; the next one up to play (front of
                          // the queue)
  private SongNode last; // The current last song in the queue; the most-recently added one (back of
                         // the queue)
  private int numSongs; // The number of songs currently in the queue

  /**
   * Constructs a new, empty playlist queue
   */
  public Playlist() {
    this.first = null;
    this.last = null;
    this.numSongs = 0;
  }

  /**
   * Adds a new song to the end of the queue Specified by: enqueue in interface QueueADT<Song>
   * 
   * @param element - the song to add to the Playlist
   */
  public void enqueue(Song element) {
    SongNode node = new SongNode(element, null);
    if (this.isEmpty()) {
      this.first = node;
      this.last = node;
    } else {
      this.last.setNext(node);
      this.last = node;
    }
    numSongs++;
  }

  /**
   * Removes the song from the beginning of the queue Specified by: dequeue in interface
   * QueueADT<Song>
   * 
   * @return the song that was removed from the queue, or null if the queue is empty
   */
  public Song dequeue() {
    if (this.isEmpty()) {
      return null;
    }
    SongNode temp = this.first;
    this.first = this.first.getNext();
    if (this.first == null) {
      this.last = null;
    }
    numSongs = numSongs - 1;
    return temp.getSong();
  }

  /**
   * Returns the song at the front of the queue without removing it Specified by: peek in interface
   * QueueADT<Song>
   * 
   * @return the song that is at the front of the queue, or null if the queue is empty
   */
  public Song peek() {
    if (this.isEmpty()) {
      return null;
    }
    return first.getSong();
  }

  /**
   * Returns true if and only if there are no songs in this queue Specified by: isEmpty in interface
   * QueueADT<Song>
   * 
   * @return true if this queue is empty, false otherwise
   */
  public boolean isEmpty() {
    if (this.numSongs == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the number of songs in this queue Specified by: size in interface QueueADT<Song>
   * 
   * @return the number of songs in this queue
   */
  public int size() {
    return this.numSongs;
  }

  /**
   * Creates and returns a formatted string representation of this playlist, with the string version
   * of each song in the list on a separate line. For example: "He's A Pirate" (1:21) by Klaus
   * Badelt "Africa" (4:16) by Toto "Waterloo" (2:45) by ABBA "All I Want For Christmas Is You"
   * (4:10) by Mariah Carey "Sandstorm" (5:41) by Darude "Never Gonna Give You Up" (3:40) by Rick
   * Astley Specified by: toString in interface QueueADT<Song>
   * 
   * @overrides toString in class Object
   * @return the string representation of this playlist
   */
  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "";
    }
    String str = "";
    SongNode s = this.first;
    SongNode l = this.last;
    while (s != l) {
      str = str + s.getSong().toString() + "\n";
      s = s.getNext();
    }
    if (s == l) {
      return str + s.getSong().toString();
    }
    return str;
  }
}
