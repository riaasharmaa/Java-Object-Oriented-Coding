///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Ria Sharma
// Campus ID: 908 442 3467
// WiscEmail: rsharma78@wisc.edu
////////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;
public class HolidayPrizeIterator implements Iterator<String>{
  private String[] prizeNames;
  private int index;

  public HolidayPrizeIterator(String[] prizeNames) {
    this.prizeNames = prizeNames;
    this.index = 0;
  }

  @Override
  public boolean hasNext() {
    return index < prizeNames.length;
  }

  @Override
  public String next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    String prizeName = prizeNames[index];
    index++;
    return prizeName + " is our next exclusive holiday prize";
  }
  public static boolean test() {
    String[] prizeNames = {"Barbie", "Teddy Bear", "Hot Wheels"};
    HolidayPrizeIterator hpi = new HolidayPrizeIterator(prizeNames);

    int tracker = 0;
    while (hpi.hasNext()) {
      hpi.next();
      tracker++;
    }

    return tracker == prizeNames.length;
  }
  public static void main(String[] args) {
    System.out.println(HolidayPrizeIterator.test());
  }
}
