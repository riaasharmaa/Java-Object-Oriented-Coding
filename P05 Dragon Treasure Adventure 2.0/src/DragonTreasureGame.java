//////////////// Dragon Treasure Game Class //////////////////////////
//
// Title: P04 Exceptional Vending Machine
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
// Persons: (identify each by name and describe how they helped)
// Online Sources: ZyBooks - Overriding examples
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class to represent the game extends PApplet
 */
public class DragonTreasureGame extends PApplet {
  private ArrayList<Room> roomList;
  private File roomInfo;
  private File mapInfo;
  private ArrayList<Character> characters;
  private boolean isDragonTurn;
  private int gameState;


  /**
   * main game
   */
  public static void main(String[] args) {
    PApplet.main("DragonTreasureGame");

  }

  /**
   * sets window size
   * 
   * @override settings()
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * creates and displays game rooms
   * 
   * @override setup()
   */
  @Override
  public void setup() {
    // set up window
    this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
    this.imageMode(PApplet.CORNER); // Images are drawn using the x,y-coordinate
    // as the top-left corner
    this.rectMode(PApplet.CORNERS); // When drawing rectangles interprets args
    // as top-left corner and bottom-right corner respectively
    this.focused = true; // window will be active upon running program
    this.textAlign(CENTER); // sets the text alignment to center
    this.textSize(20); // sets the font size for the text
    // Initialize arraylists
    roomList = new ArrayList<Room>();
    characters = new ArrayList<Character>();
    Room.setProcessing(this);
    PImage image = loadImage("images/1.jpg");
    StartRoom startroom = new StartRoom(1, image);
    // roomList.add(startroom);
    TreasureRoom.setTreasureBackground(this.loadImage("images/treasure.jpg"));
    Room treasureroom = new TreasureRoom(2);
    // roomList.add(treasureroom);
    PortalRoom.setPortalImage(this.loadImage("images/portal.png"));
    Room portalroom = new PortalRoom(3, "test portal room", image);
    // roomList.add(portalroom);
    roomInfo = new File("roominfo.txt");
    mapInfo = new File("map.txt");
    // call load methods
    loadRoomInfo();
    loadMap();
    loadCharacters();
    System.out.println(characters.get(1));
    isDragonTurn = false;
    gameState = 0;
  }

  /**
   * draws rooms
   */
  public void draw() {
    if (gameState == 0) {
      Player p = null;
      Dragon d = null;
      Character c = null;
      for (int i = 0; i < characters.size(); i++) {
        if (characters.get(i) instanceof Character) {
          if (characters.get(i).getLabel().equalsIgnoreCase("KEYHOLDER")) {
            c = (Character) characters.get(i);
          }
        }
        if (characters.get(i) instanceof Dragon) {
          d = (Dragon) characters.get(i);
        }
        if (characters.get(i) instanceof Player) {
          p = (Player) characters.get(i);
        }


      }
      p.getCurrentRoom().draw();
      for (int i = 0; i < p.getAdjacentRooms().size(); i++) {
        if (p.isDragonNearby(d)) {
          System.out.println(Dragon.getDragonWarning());
        }
        if (p.getCurrentRoom().equals(d.getCurrentRoom())) {
          System.out.println("Player Lost!");
          gameState = 2;
        }

        if (p.isTreasureNearby()) {
          for (int t = 0; t < p.getAdjacentRooms().size(); t++) {
            if (p.getAdjacentRooms().get(t) instanceof TreasureRoom) {
              System.out.println(TreasureRoom.getTreasureWarning());
            }
          }
        }
        if (p.isPortalNearby()) {
          for (int k = 0; k < p.getAdjacentRooms().size(); k++) {
            if (p.getAdjacentRooms().get(k) instanceof PortalRoom) {
              System.out.println(PortalRoom.getPortalWarning());
            }
          }
        }
      }
      if (p.getCurrentRoom() == c.getCurrentRoom()) {
        p.obtainKey();
        System.out.println("KEY OBTAINED");
      }
      for (int i = 0; i < roomList.size(); i++) {
        if (roomList.get(i) instanceof TreasureRoom) {
          TreasureRoom treasureroom = (TreasureRoom) roomList.get(i);
          if (treasureroom.playerCanGrabTreasure(p)) {
            System.out.println("Player won!");
            gameState = 1;
          }
        }
      }
      if (isDragonTurn) {
        for (int x = 0; x < characters.size(); x++) {
          if (characters.get(x) instanceof Dragon) {
            Dragon dragon = (Dragon) characters.get(x);
            Room room = dragon.pickRoom();
            dragon.changeRoom(room);
            if (dragon.getCurrentRoom() == room) {
              isDragonTurn = false;
            }
          }
        }
      }
    }
  }

  /**
   * Loads in room info using the file stored in roomInfo
   * 
   * @author Michelle
   */
  private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try {

      // scanner to read from file
      fileReader = new Scanner(roomInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();

        // parse info and create new room
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); // get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;

        if (parts.length >= 3) {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);

        }

        if (parts.length == 4) {
          description = parts[3].trim(); // get the room description
        }

        switch (parts[0].trim()) {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            break;
          default:
            break;
        }

        if (newRoom != null) {
          roomList.add(newRoom);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally {
      if (fileReader != null)
        fileReader.close(); // close scanner regardless of what happened for security reasons :)
    }
  }

  /**
   * Loads in room connections using the file stored in mapInfo
   * 
   * @author Michelle
   */
  private void loadMap() {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
      // scanner to read from file
      fileReader = new Scanner(mapInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {

        // parse info
        String nextLine = fileReader.nextLine();
        String parts[] = nextLine.split(" ");
        int id = Integer.parseInt(parts[0]);

        Room toEdit = getRoomByID(id); // get the room we need to update info for adjacent rooms

        // add all the rooms to the adj room list of toEdit
        for (int i = 1; i < parts.length; i++) {
          Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
          toEdit.addToAdjacentRooms(toAdjAdd);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally { // close scanner regardless of what happened for security reasons :)
      if (fileReader != null)
        fileReader.close();
    }
  }

  /**
   * Get the room objected associated with the given ID.
   * 
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */
  private Room getRoomByID(int id) {
    int indexToEdit = roomList.indexOf(new Room(id, "dummy", null));
    Room toEdit = roomList.get(indexToEdit);
    return toEdit;
  }

  /**
   * Helper method to load in 3 required characters into the game
   */
  private void loadCharacters() {
    System.out.println("Adding characters...");
    Character c = new Character(getRoomByID(5), "KEYHOLDER");
    characters.add(c);
    Player p = new Player(getRoomByID(1));
    characters.add(p);
    Dragon d = new Dragon(getRoomByID(9));
    characters.add(d);
  }

  /**
   * Uses the key pressed by the user to determine which room the player should move to check's
   * whether the move was successful and changes turns for the dragon once complete Prints error
   * message if the player chooses an invalid room
   * 
   * @override keyPressed()
   */
  public void keyPressed() {
    int pIndex = -1;
    for (int i = 0; i < characters.size(); i++) {
      if (characters.get(i) instanceof Player) {
        pIndex = i;
        break;
      }
    }
    Player p = (Player) characters.get(pIndex);



    Room d = p.getCurrentRoom();
    int numKey = key - 48;

    for (int i = 0; i < roomList.size(); i++) {
      if (roomList.get(i).getID() == numKey) {
        d = roomList.get(i);
      }
    }

    if (p.canMoveTo(d)) {
      p.changeRoom(d);
    }
    if (p.getCurrentRoom().equals(d)) {
      isDragonTurn = true;

    } else {
      System.out.println("not a valid room");
    }
  }
}

