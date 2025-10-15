//////////////// Song Class //////////////////////////
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
 * A representation of a single Song. Interfaces with the provided AudioUtility class, which uses
 * the javax.sound.sampled package to play audio to your computer's audio output device
 */
public class Song {
  private String artist; // The artist of this song
  public AudioUtility audioClip; // This song's AudioUtility interface to javax.sound.sampled
  private int duration; // The duration of this song in number of seconds
  private String title; // The title of this song

  /**
   * Initializes all instance data fields according to the provided values
   * 
   * @param title    - the title of the song, set to empty string if null
   * @param artist   - the artist of this song, set to empty string if null
   * @param filepath - the full relative path to the song file, begins with the "audio" directory
   *                 for P08
   * @throws IllegalArgumentException - if the song file cannot be read
   */
  public Song(String title, String artist, String filepath) throws IllegalArgumentException {
    // this.title = (title == null) ? "" : title;
    // this.artist = (artist == null) ? "" : artist;
    if (title == null) {
      this.title = "";
    }
    if (title != null) {
      this.title = title;
    }
    if (artist == null) {
      this.artist = "";
    }
    if (title != null) {
      this.artist = artist;
    }
    try {
      audioClip = new AudioUtility(filepath);
      this.duration = audioClip.getClipLength();
    } catch (Exception e) {
      throw new IllegalArgumentException("not valid filepath");
    }
  }

  /**
   * Tests whether this song is currently playing using the AudioUtility
   * 
   * @return true if the song is playing, false otherwise
   */
  public boolean isPlaying() {
    return audioClip.isRunning();
  }

  /**
   * Accessor method for the song's title
   * 
   * @return the title of this song
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Accessor method for the song's artist
   * 
   * @return the artist of this song
   */
  public String getArtist() {
    return this.artist;
  }

  /**
   * Uses the AudioUtility to start playback of this song, reopening the clip for playback if
   * necessary
   */
  public void play() {
    try {
      Thread.sleep(1000);
      if (this.audioClip.isReadyToPlay()) {
        this.audioClip.startClip();
      }
      if (!this.audioClip.isReadyToPlay()) {
        this.audioClip.reopenClip();
        this.audioClip.startClip();
      }
    } catch (Exception e) {

    }
    System.out.println("Playing ..." + toString());
  }

  /**
   * Uses the AudioUtility to stop playback of this song
   */
  public void stop() {
    this.audioClip.stopClip();
  }

  /**
   * Creates and returns a string representation of this Song, for example: "Africa" (4:16) by Toto
   * The title should be in quotes, the duration should be split out into minutes and seconds
   * (recall it is stored as seconds only!), and the artist should be preceded by the word "by". It
   * is intended for this assignment to leave single-digit seconds represented as 0:6, for example,
   * but if you would like to represent them as 0:06, this is also allowed.
   * 
   * @overrides toString in class Object
   * @return a formatted string representation of this Song
   * 
   */
  @Override
  public String toString() {
    int min = this.duration / 60;
    int sec = this.duration % 60;
    String songstr = "\"" + getTitle() + "\" " + "(" + min + ":" + sec + ") by " + getArtist();
    return songstr;

  }
}
