//////////////// MusicPlayer300 Class //////////////////////////
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
// Persons: Snehal TA - runMusicPlayer300 method, Abinayaa TA - playNextSong method
// Online Sources: ZyBooks
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A linked-queue based music player which plays Actual Music Files based on keyboard input in an
 * interactive console method. This music player can load playlists of music or add individual song
 * files to the queue.
 */
public class MusicPlayer300 {
  private String filterArtist; // The artist to play if filterPlayer is true; should be null
                               // otherwise
  private boolean filterPlay; // Whether the current playback mode should be filtered by artist;
                              // false by default
  private Playlist playlist; // The current playlist of Songs

  /**
   * Creates a new MusicPlayer300 with an empty playlist
   */
  public MusicPlayer300() {
    this.playlist = new Playlist();
    this.filterArtist = null;
    this.filterPlay = false;
  }

  /**
   * Stops any song that is playing and clears out the playlist
   */
  public void clear() {
    if (!playlist.isEmpty() && playlist.peek().isPlaying()) {
      playlist.peek().stop();
    }
    while (!playlist.isEmpty()) {
      playlist.dequeue();
    }
  }

  /**
   * Loads a playlist from a provided file, skipping any individual songs which cannot be loaded.
   * Note that filenames in the provided files do NOT include the audio directory, and will need
   * that added before they are loaded. Print "Loading" and the song's title in quotes before you
   * begin loading a song, and an "X" if the load was unsuccessful for any reason.
   * 
   * @param file - the File object to load
   * @throws FileNotFoundException - if the playlist file cannot be loaded
   */
  public void loadPlaylist(File file) throws FileNotFoundException {
    Scanner scan = new Scanner(file);
    while (scan.hasNextLine()) {

      String line = scan.nextLine();
      String[] ar = line.split(",");
      System.out.println("Loading");
      try {
        Song s = new Song(ar[0], ar[1], "audio" + File.separator + ar[2]);
        playlist.enqueue(s);
      } catch (Exception e) {
        System.out.println("X");
      }

    }
    scan.close();
  }

  /**
   * Loads a single song to the end of the playlist given the title, artist, and filepath. Filepaths
   * for P08 must refer to files in the audio directory.
   * 
   * @param title    - the title of the song
   * @param artist   - the artist of this song
   * @param filepath - the full relative path to the song file, begins with the "audio" directory
   *                 for P08
   * @throws IllegalArgumentException - if the song file cannot be read
   */
  public void loadOneSong(String title, String artist, String filepath) {
    Song toAdd = new Song(title, artist, filepath);
    playlist.enqueue(toAdd);
  }

  /**
   * Provides a string representation of all songs in the current playlist
   * 
   * @return a string representation of all songs in the current playlist
   */
  public String printPlaylist() {
    String print = playlist.toString();
    return print;
  }

  /**
   * Creates and returns the menu of options for the interactive console program.
   * 
   * @returns the formatted menu String
   */
  public String getMenu() {
    String str = new String("[A <filename>] to enqueue a new song file to the end of this playlist"
        + "\n" + "[F <filename>] to load a new playlist from the given file" + "\n"
        + "[L] to list all songs in the current playlist" + "/n"
        + "[P] to start playing ALL songs in the playlist from the beginning" + "\n"
        + "[P -t <Title>] to play all songs in the playlist starting from <Title>" + "\n"
        + "[P -a <Artist>] to start playing only the songs in the playlist by Artist" + "\n"
        + "[N] to play the next song" + "\n" + "[Q] to stop playing music and quit the program");
    return str;
  }

  /**
   * Stops playback of the current song (if one is playing) and advances to the next song in the
   * playlist.
   * 
   * @throws IllegalStateException - if the playlist is null or empty, or becomes empty at any time
   *                               during this method
   */
  public void playNextSong() throws IllegalStateException {
    while (playlist.isEmpty() || playlist == null) {
      throw new IllegalStateException("playlist is empty");
    }
    if (filterPlay == false) {
      if (playlist.peek().isPlaying()) {
        playlist.peek().stop();
        playlist.dequeue();
        if (playlist.size() != 0) {
          playlist.peek().play();
        }
        if (playlist.size() == 0) {
          throw new IllegalStateException("playlist is empty");
        }
      }
      if (!playlist.peek().isPlaying()) {
        playlist.dequeue();
        if (playlist.size() == 0) {
          throw new IllegalStateException("playlist is empty");
        }
        playlist.peek().play();
      }
    }
    if (filterPlay == true) {
      if (playlist.peek().isPlaying()) {
        playlist.peek().stop();
        playlist.dequeue();
        if (playlist.size() == 0) {
          throw new IllegalStateException("playlist is empty");
        }
      }
      while (playlist.peek().getArtist() != this.filterArtist) {
        playlist.dequeue();
        if (playlist.size() == 0) {
          throw new IllegalStateException("playlist is empty");
        }
      }
      try {
        playlist.peek().play();
      } catch (Exception e) {
        throw new IllegalStateException("can not play song");
      }
    }
  }

  /**
   * Interactive method to display the MusicPlayer300 menu and get keyboard input from the user. See
   * writeup for details.
   */
  public void runMusicPlayer300(Scanner in) {
    boolean valid = true;
    while (valid) {
      // menu
      System.out.println(getMenu());
      System.out.print("> ");
      String option = in.next();
      // option A
      if (option.equalsIgnoreCase("A")) {
        System.out.print("\nFilepath:");
        String afilepath = in.next();
        System.out.print("\nTitle:");
        String atitle = in.next();
        System.out.print("\nArtist:");
        String aartist = in.next();
        try {
          loadOneSong(atitle, aartist, afilepath);
        } catch (Exception e) {
          System.out.println("Unable to load that Song");
        }
      }
      // option F
      if (option.equalsIgnoreCase("F")) {
        System.out.print("\nFile:");
        String filepath = in.next();
        try {
          File f = new File(filepath);
          loadPlaylist(f);
        } catch (Exception e) {
          System.out.println("Unable to load that Playlist");
        }
      }
      // option L
      if (option.equalsIgnoreCase("L")) {
        printPlaylist();
      }
      // option P
      if (option.equalsIgnoreCase("P")) {
        try {
          playlist.peek().play();
        } catch (Exception e) {
          System.out.println("Unable to play any song");
        }
      }
      // option p-t
      if (option.equalsIgnoreCase("P -t")) {
        System.out.print("\nTitle:");
        String ptitle = in.next();
        while (!playlist.peek().getTitle().equalsIgnoreCase(ptitle)) {
          playlist.dequeue();
        }
        try {
          playlist.peek().play();
        } catch (Exception e) {
          System.out.println("Unable to play any song");
        }
      }
      // option p-a
      if (option.equalsIgnoreCase("P -a")) {
        System.out.print("\nArtist:");
        this.filterArtist = in.next();
        filterPlay = true;
        while (!playlist.peek().getArtist().equalsIgnoreCase(this.filterArtist)) {
          playlist.dequeue();
        }
        try {
          playlist.peek().play();
        } catch (Exception e) {
          System.out.println("Unable to play any song");
        }
      }
      // option n
      if (option.equalsIgnoreCase("N")) {
        playNextSong();
      }
      // option q
      if (option.equalsIgnoreCase("Q")) {
        clear();
        System.out.println("Goodbye!");
        valid = false;
      }
    }
    in.close();
  }
}
