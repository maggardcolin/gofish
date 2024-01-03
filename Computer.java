import java.util.ArrayList;
import java.util.Collections;

public class Computer extends Player {

  public ArrayList<String> memory; // it remembers what you ask for
  public ArrayList<String> badMemory;

  /**
   * Priority: Cards it has that are stored in memory Cards it has that are only in hand Cards
   * stored in bad memory
   * 
   * @return
   */
  public String strategize() {
    String decision = "";
    boolean foundCard = false;

    for (int i = 0; i < this.hand.size(); i++) {
      for (int j = 0; j < memory.size(); j++) {
        if (this.hand.get(i).value.equals(memory.get(j))) {
          foundCard = true;
          decision = this.hand.get(i).value;
          return decision;
        }
      }
    }

    if (!foundCard) {
      Collections.shuffle(this.hand);
      for (int i = 0; i < this.hand.size(); i++) {
        for (int j = 0; j < badMemory.size(); j++) {
          if (!this.hand.get(i).value.equals(badMemory.get(j))) {
            foundCard = true;
            decision = this.hand.get(i).value;
            return decision;
          }
        }
      }
    }

    if (!foundCard) {
      Collections.shuffle(this.hand);
      decision = this.hand.get(0).value;
      foundCard = true;
    }

    return decision;
  }

}
