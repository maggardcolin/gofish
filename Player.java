import java.util.ArrayList;

public class Player {
  public int points;
  public String name;
  public ArrayList<Card> hand;
  public ArrayList<Pair> pairs;

  public String handToString() {
    String output = "";
    for (int i = 0; i < hand.size(); i++) {
      output += hand.get(i).value + " ";
    }
    return output;
  }

  public void assessPairs() {
    for (int i = 0; i < this.hand.size(); i++) {
      for (int j = i + 1; j < this.hand.size(); j++) {
        if (this.hand.get(i).value.equals(this.hand.get(j).value)) {
          String foundValue = this.hand.get(i).value;
          System.out.println(this.name + " has a pair of " + foundValue + "s!");
          pairs.add(new Pair(this.hand.get(i), this.hand.get(j)));
          this.points++;
          this.hand.remove(j);
          this.hand.remove(i);
          return;
        }
      }
    }
  }

}
