///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Ria Sharma
// Campus ID: 908 442 3467
// WiscEmail: rsharma78@wisc.edu
// 
////////////////////////////////////////////////////////////////////////////////

/**
 * This is a static, utility class containing methods to accomplish a recursive
 * sort of an array of Video objects. You do NOT have to implement the Video class;
 * it is provided for you in its entirety at the bottom of this file.
 */
public class MaxSort {

  /**
   * Finds the "largest" Video using a recursive helper method.
   * 
   * @param playlist a full, perfect-size array of Videos with length >= 1
   * @return the Video from that collection which is larger than every other Video
   */
  public static Video getMaxValue(Video[] playlist) {
    // suppose the first element of the playlist is the largest
    Video maxVideo = playlist[0];
    
    // get the larger of maxVideo and the elements in the rest of the array
    return getMaxValueHelper(maxVideo, playlist, 1);
  }
  
  /**
   * A recursive helper method to find the larger of:
   *   - the argument maxVideo, and
   *   - the largest Video in the rest of playlist, from index to the end of the array
   * 
   * @param maxVideo the maximum value over all indexes 0 through index-1 (inclusive)
   * @param playlist a full, perfect-size array of Videos with length >= 1
   * @param index the first index of the un-searched part of the playlist. 
   *     valid values: 1 to playlist.length (inclusive)
   *     If index == playlist.length, the un-searched part of the playlist is empty.
   * @return the maximum value over all indexes
   */
  private static Video getMaxValueHelper(Video maxVideo, Video[] playlist, int index) {
    /////////// BASE CASE ///////////
    // TODO
    // 1. If the un-searched part of the playlist is empty, return the largest video
    if (index == playlist.length) {
      return maxVideo;
    }
    if (playlist[index].compareTo(maxVideo)>0) {
      maxVideo = playlist[index];
    }
    ///////// RECURSIVE CASE ////////
    // TODO
    // 2. Define the recursive case for this algorithm
    maxVideo = getMaxValueHelper(maxVideo,playlist,index+1);
    // 3. Return the largest video 
    return maxVideo; // default return statement added to avoid compiler errors. Feel free to change it.
  }
  
  /**
   * A tester method for getMaxValue, which you must complete. This method WILL be tested.
   * 
   * @return true if getMaxValue produces the expected result; false otherwise
   */
  public static boolean testGetMaxValue() {
    // TODO
    // 4. Create a Video[] array containing at least 3 Video objects with different durations (see 
    // testMaxSort() below for examples)
    Video[] playlist = new Video[3];
    // 5. Create a NEW Video object that matches the Video in the array that has the longest duration
    // (e.g. for Videos with durations [5:32, 7:15, 4:56], you'd create a new Video with duration 7:15).
    playlist[0] = new Video("medium duration", 5,32);
    playlist[1] = new Video("largest duration", 7,15);
    playlist[2] = new Video("shortest duration", 4,56);
    // 6. Call getMaxValue() on your Video[] array and save the result
    Video longest = getMaxValue(playlist);
    // 7. Compare this result against your expected value and return false if they do not match.
    // CAREFUL: the equals() method is NOT overridden in the Video class. To find equivalent Video
    // objects, use Video's compareTo() method.
    if(longest.compareTo(new Video("largest duration", 7,15)) == 0) {
      return true;
  }
    // OPTIONAL: implement additional tests to verify the method works!
    
    // 8. Return true if all tests in this method are passed.
    return false; // default return statement added to avoid compiler errors. Feel free to change it.
  }
  
  /////////////////////////////////////////////////////////////////////////////////////////////
  //                                                                                         //
  // Checkpoint: SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) and RUN THIS TEST before you continue. //
  //                                                                                         //
  /////////////////////////////////////////////////////////////////////////////////////////////
 
  
  /**
   * Returns a new array of size playlist.length-1 which contains every Video in playlist that is NOT
   * the Video to remove. The method should NOT make any changes to the contents of playlist.
   *
   * Note that while there is no equals() method defined in Video, equal Videos will have a compareTo() 
   * value of 0.
   * 
   * @param playlist a full, perfect-size array of Videos with length >= 1
   * @param toRemove the Video to remove from the playlist
   * @return an array containing every Video from playlist EXCEPT toRemove, and NO null values
   */
  public static Video[] removeVideo(Video[] playlist, Video toRemove) {
    // TODO
    // 9. Create a new Video array of the correct size
    Video[] newPlaylist = new Video[playlist.length-1];
    // 10. Iterate through the playlist and add all videos that are NOT the video toRemove
    //     (You may assume there are no duplicates.)
    int index = 0;
    for(int i = 0; i < playlist.length; i++) {
        if(playlist[i].compareTo(toRemove) != 0) {
          newPlaylist[index] = playlist[i];
            index++;
        }
    }
    // 11. Return the resulting array
    return newPlaylist; // default return statement added to avoid compiler errors. Feel free to change it.
  }
  
  /**
   * A tester method for removeVideo, which you must complete. This method WILL be tested.
   * 
   * @return true if removeVideo produces the expected result; false otherwise
   */
  public static boolean testRemoveVideo() {
 // TODO
    // 12. Create a Video[] array containing at least 3 Video objects with different titles (see 
    //     testMaxSort() below for examples)
    Video[] playlist = new Video[3];
    playlist[0] = new Video("medium", 5,32);
    playlist[1] = new Video("long", 7,15);
    playlist[2] = new Video("short", 4,56);

    // 13. Create a NEW Video object that is identical to one of the Video objects from your array
    Video rVid = new Video("long", 7,15);
    // 14. Call removeVideo() on your array and Video object and save the result
    Video[] newPlaylist = removeVideo(playlist, rVid);

    // 15. Verify that the length of the result is 1 less than your input array, and that the Video
    //     you passed in the second argument is NOT present, and return false if either test does
    //     not pass.
    if(newPlaylist.length != 2) {
      return false;
  }

    // OPTIONAL: implement additional tests to verify the method works!
    
    // 16. Return true if all tests in this method are passed.
    return true; // default return statement added to avoid compiler errors. Feel free to change it.
  }
  
  /////////////////////////////////////////////////////////////////////////////////////////////////
  // NOTE: THERE IS NO MORE CODE REQUIRED FROM YOU AFTER THIS POINT!
  //
  // What follows is another test for you to run locally to help assure you that your program
  // works as expected, and a main method. The Video class is included at the bottom of the file.
  //
  // SAVE YOUR CODE (Ctrl/Cmd + S) and RUN ALL TESTS, and SUBMIT YOUR FILE TO GRADESCOPE!!
  /////////////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * A recursive method to sort an array of Videos in-place.
   * 
   * @param playlist a full, perfect-size array of Videos with length >= 1
   */
  public static void maxSort(Video[] playlist) {
    /////////// BASE CASE ///////////
    // an array of length 1 is sorted
    if (playlist.length == 1) return;
    
    ///////// RECURSIVE CASE ////////
    
    // use the helper methods to pre-process the data
    Video maxVideo = getMaxValue(playlist);
    Video[] newPlaylist = removeVideo(playlist, maxVideo);
    maxSort(newPlaylist);
    
    // copy the processed data back into the original playlist
    playlist[0] = maxVideo;
    for(int i=0; i<newPlaylist.length; i++) {
      playlist[i+1] = newPlaylist[i];
    }
  }
  
  /**
   * A tester method for maxSort, provided for you in its entirety. You may add code if you wish,
   * but you are not required to. This method will not be tested.
   * 
   * @return true if maxSort produces the expected result; false otherwise
   */
  public static boolean testMaxSort() {
    // try sorting a one-video playlist:
    Video[] simplePlaylist = new Video[] {new Video("Teddy Bear the Porcupine", 2, 52)};
    Video contents = new Video("Teddy Bear the Porcupine", 2, 52);
    try {
      maxSort(simplePlaylist);
    } catch (NullPointerException npe) {
      System.err.println("maxSort() NullPointerException!");
      return false;
    }
    
    if (simplePlaylist.length != 1) return false;
    if (simplePlaylist[0] == null || simplePlaylist[0].compareTo(contents) != 0) return false;
    
    // try sorting a playlist with multiple videos:
    Video[] testPlaylist = new Video[] {new Video("Le Meilleur Patissier s11e01", 124, 2),
        new Video("Climbing's Secret Curve", 7, 06), 
        new Video("Tomato Soup Cake", 9, 35), 
        new Video("Teddy Bear the Porcupine", 2, 52)};
    Video[] expectedPlaylist = new Video[] {new Video("Le Meilleur Patissier s11e01", 124, 2),
        new Video("Tomato Soup Cake", 9, 35), 
        new Video("Climbing's Secret Curve", 7, 06),
        new Video("Teddy Bear the Porcupine", 2, 52)};
    try {
      maxSort(testPlaylist);
    } catch (NullPointerException npe) {
      System.err.println("maxSort() NullPointerException!");
      return false;
    }
    
    if (testPlaylist.length != expectedPlaylist.length) return false;
    for (int i=0; i<expectedPlaylist.length; i++) {
      if (testPlaylist[i] == null || testPlaylist[i].compareTo(expectedPlaylist[i]) != 0) return false;
    }
    
    return true;
  }
  
  public static void main(String[] args) {
    System.out.println("getMaxValue test result: "+testGetMaxValue());
    System.out.println("removeVideo test result: "+testRemoveVideo());
    System.out.println("maxSort test result: "+testMaxSort());
  }
}

/**
 * The objects to be sorted by the MaxSort class. DO NOT MODIFY THIS CLASS IN ANY WAY.
 */
class Video {
  private String title;
  private int duration;
  
  /**
   * Create a new Video object
   * @param title the title of the video
   * @param numMin the number of minutes in the duration, e.g. for a 4:23 video, this is 4
   * @param numSec the number of seconds in the duration, e.g. for a 4:23 video, this is 23
   */
  public Video(String title, int numMin, int numSec) {
    this.title = title;
    this.duration = numMin*60+numSec;
  }
  
  /**
   * Compare first on the basis of duration, and if the durations are the same, compare
   * on the title.
   * 
   * @param v the Video to compare this to
   * @return value less than 0 if this video is shorter/alphabetically before v, greater
   *    than 0 if this video is longer/alphabetically after v, and 0 if they have the same
   *    title and duration.
   */
  public int compareTo(Video v) {
    if (this.duration != v.duration) return this.duration - v.duration;
    return this.title.compareTo(v.title);
  }
  
  /**
   * For debugging purposes, a way to System.out.println(Video)
   * 
   * Results are formatted roughly as: "Title" (mm:ss)
   */
  @Override
  public String toString() {
    return "\""+this.title+"\" ("+(this.duration/60)+":"+(this.duration%60)+")";
  }
}
