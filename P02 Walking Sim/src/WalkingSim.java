//////////////// Walking Sim //////////////////////////
//
// Title: P02 Walking Sim
// Course: CS 300 Fall 2022
//
// Author: Ria Sharma
// Email: rsharma78@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: Zybooks
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class will mimic a walking cycle with features allowing the addition of walkers, start/stop
 * of the walk cycle. It is the graphical implementation of a walking animation.
 * 
 * @version 1.0
 * @author Ria Sharma
 */
import java.util.Random;
import java.io.File;
import processing.core.PImage;

public class WalkingSim {
  private static Random randGen;
  private static int bgColor;
  private static PImage[] frames;
  private static Walker[] walkers;

  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * This static method initializes randomly generated background color, sets up images to upload to
   * the screen, prepares to adds walkers to walker array that feed into animation.
   * 
   * @param none
   * @return none
   */
  public static void setup() {
    randGen = new Random();
    bgColor = randGen.nextInt();
    frames = new PImage[Walker.NUM_FRAMES];
    for (int index = 0; index < frames.length; index++) {
      frames[index] = Utility.loadImage("P02 Images" + File.separator + "walk-" + index + ".png");
    }
    walkers = new Walker[8];
    walkers[0] = new Walker();
    for (int i = 0; i < randGen.nextInt(frames.length + 1); i++) {
      walkers[i] =
          new Walker(randGen.nextInt(Utility.width() + 1), randGen.nextInt(Utility.height()) + 1);
    }
  }

  /**
   * The draw method creates randomly generated background color, displays the walking man images to
   * the screen, forms the loops for walkers to wrap around when they leave the edge of the screen.
   * 
   * @param none
   * @return none
   */
  public static void draw() {
    Utility.background(bgColor);
    for (int i = 0; i < walkers.length; i++) {
      if (walkers[i] != null) {
        if (walkers[i].isWalking()) {
          walkers[i].setPositionX(walkers[i].getPositionX() + 3);
          if (walkers[i].getPositionX() >= 800) {
            walkers[i].setPositionX(0);
          }
        }
        Utility.image(frames[walkers[i].getCurrentFrame()], walkers[i].getPositionX(),
            walkers[i].getPositionY());
      }
    }
    for (int i = 0; i < walkers.length; i++) {
      if (walkers[i] != null) {
        if (walkers[i].isWalking()) {
          walkers[i].update();
        }
      }
    }

  }

  /**
   * The isMouseOver method detects whether a user's mouse is hovering over a walker image to be
   * able to detect the correct range that a mouse can be pressed to allow the walker to start
   * walking.
   * 
   * @param Walker object used to determine the coordinates of the walker
   * @return boolean, true if mouse is over a walker and false if mouse is not over a walker
   */
  public static boolean isMouseOver(Walker walk) {
    int imageW = (frames[0].width) / 2;
    int imageH = (frames[0].height) / 2;
    if ((walk.getPositionX() - imageW < Utility.mouseX())
        && (Utility.mouseX() < walk.getPositionX() + imageW)) {
      if ((walk.getPositionY() - imageH < Utility.mouseY())
          && (Utility.mouseY() < walk.getPositionY() + imageH)) {
        return true;
      }
    }
    return false;
  }

  /**
   * The mousePressed method detects whether a user's mouse is pressed over a walker image to be
   * able to allow the walker to start walking.
   * 
   * @param none
   * @return none
   */
  public static void mousePressed() {
    for (int i = 0; i < walkers.length; i++) {
      if (walkers[i] != null) {
        if (isMouseOver(walkers[i])) {
          walkers[i].setWalking(true);
          System.out.println("Mouse is over a walker!");
        }
      }
    }
  }

  /**
   * The keyPressed method detects whether a user's a/A or s/S key is pressed to add a walker to the
   * screen or stop all walkers from walking.
   * 
   * @param char, processes whether a/A or s/S is pressed to add walkers or stop walking
   * @return none
   */
  public static void keyPressed(char letter) {
    int nullIndex = 0;
    for (int i = 0; i < walkers.length; i++) {
      if (walkers[i] == null) {
        nullIndex = i;
        i = walkers.length;
        if (letter == 'a' || letter == 'A') {
          walkers[nullIndex] = new Walker(randGen.nextInt(Utility.width() + 1),
              randGen.nextInt(Utility.height()) + 1);

        }
      }
    }
    for (int index = 0; index < walkers.length; index++) {
      if (walkers[index] != null) {
        if (letter == 's' || letter == 'S') {
          walkers[index].setWalking(false);
        }
      }
    }
  }
}
