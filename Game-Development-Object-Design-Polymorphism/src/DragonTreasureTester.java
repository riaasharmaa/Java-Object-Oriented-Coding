import java.util.ArrayList;

//////////////// Dragon Treasure Tester Class //////////////////////////
//
// Title: Dragon Treasure Tester Class
// Course: CS 300 Fall 2022
//
// Author: Ria Sharma
// Email: rsharma78@wisc.edu
// Lecturer: Hobbes LeGault
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Snehal (online consulting) - room testers
// Online Sources: zybooks -refer to examples
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class tests methods within the Dragon, Player, and Room Classes.
 */
public class DragonTreasureTester {
  /**
   * main method to run all tests and print results
   */
  public static void main(String[] args) {

    System.out.println(testRoomInstanceMethods());
    System.out.println(testRoomStaticMethods());
    System.out.println(testPlayerCanMoveTo());
    System.out.println(testPlayerShouldTeleport());
    System.out.println(testPlayerDetectNearbyRooms());
    System.out.println(testDragonChangeRooms());
  }

  /**
   * tests instance methods in Room class
   */
  public static boolean testRoomInstanceMethods() {

    Room startRoom = new Room(1, "starting point");
    Room room2 = new Room(2, "room next to startroom");

    if (startRoom.getType() != RoomType.NORMAL) {
      System.out.println("get type error");
      return false;
    }

    if (startRoom.getID() != 1) {
      System.out.println("get id error");
      return false;
    }

    if (!startRoom.getRoomDescription().equals("starting point")) {
      System.out.println("get description error");
      return false;
    }
    try {
      startRoom.addToAdjacentRooms(room2);
    } catch (NullPointerException E) {
      return false;
    }
    ArrayList<Room> checkrooms = new ArrayList<>();
    checkrooms.add(room2);
    if (!startRoom.getAdjacentRooms().equals(checkrooms)) {
      System.out.println("add/get adj rooms error");
      return false;
    }
    if (!startRoom.isAdjacent(room2)) {
      System.out.println("is adjacent error");
      return false;
    }
    startRoom.setRoomType(RoomType.TREASURE);
    if (startRoom.getType() != RoomType.TREASURE) {
      System.out.println("set type error");
      return false;
    }


    return true;
  }

  /**
   * tests static methods in Room class
   */
  public static boolean testRoomStaticMethods() {
    Room.assignTeleportLocation(1);
    if (Room.getTeleportationRoom() != 1) {
      System.out.println("assign/get teleportation room error");
      return false;
    }
    if (!Room.getPortalWarning().equals("You feel a distortion in space nearby.\n")) {
      System.out.println("get portal warning error");
      return false;
    }
    if (!Room.getTreasureWarning().equals("You sense that there is treasure nearby.\n")) {
      System.out.println("get treasure warning error");
      return false;
    }
    return true;
  }

  /**
   * tests canMoveTo() method in Player class
   */
  public static boolean testPlayerCanMoveTo() {
    Room room1 = new Room(1, "Start");
    Room room2 = new Room(2, "adjRoom");
    Room room3 = new Room(3, "nonAdjRoom");
    Player player = new Player(room1);
    room1.addToAdjacentRooms(room2);
    if (player.canMoveTo(room3)) {
      return false;
    }
    if (!player.canMoveTo(room2)) {
      return false;
    }

    return true;
  }

  /**
   * tests shouldTeleport() method in Player class
   */
  public static boolean testPlayerShouldTeleport() {
    Room r = new Room(1, "start");
    Room r2 = new Room(2, "portal");
    r2.setRoomType(RoomType.PORTAL);
    Player p = new Player(r);
    r.getAdjacentRooms().add(r2);
    if (p.shouldTeleport()) {
      return false;
    }
    p.changeRoom(r2);
    if (!p.shouldTeleport()) {
      return false;
    }
    return true;

  }

  /**
   * tests isPortalNearby() method and isTreasureNearby() method in Player class
   */
  public static boolean testPlayerDetectNearbyRooms() {
    // tests isPortalNearby() method and isTreasureNearby() method
    Room room1 = new Room(1, "Start");
    Room room2 = new Room(2, "portal room");
    room2.setRoomType(RoomType.PORTAL);
    Room room3 = new Room(3, "treasure room");
    room3.setRoomType(RoomType.TREASURE);
    Player player1 = new Player(room1);
    room1.addToAdjacentRooms(room2);
    room1.addToAdjacentRooms(room3);
    if (!player1.isPortalNearby()) {
      System.out.println("error in detecting nearby portal");
      return false;
    }
    if (!player1.isTreasureNearby()) {
      System.out.println("error in detecting nearby treasure");
      return false;
    }
    return true;
  }

  /**
   * tests changeRooms() method in Dragon class
   */
  public static boolean testDragonChangeRooms() {
    Room room = new Room(1, "start");
    Room room2 = new Room(2, "nonAdj");
    Room room3 = new Room(3, "adjPortal");
    Dragon dragon = new Dragon(room);
    room3.setRoomType(RoomType.PORTAL);
    room.addToAdjacentRooms(room3);
    room.addToAdjacentRooms(room2);
    dragon.changeRooms();

    if (dragon.getCurrentRoom() != room2) {
      return false;
    }
    return true;
  }


}
