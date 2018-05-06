
/** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
 * File name  :  DynamicChangeMaker.java<br>
 * Purpose    :  Methods to use Dynamic Programming to find optimal amount of coins of input denominations
 *               to get a wanted value<br>
 * @author    :  Lindsey Fry
 * Date       :  2018-04-29
 * Description:  This program provides methods necessary to find optimal amount of coins to make an input
 *               number using input coin denominations
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-49  Lindsey Fry    Initial writing and release
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class DynamicChangeMaker {
  public static void main(String[] args) {
    if (args.length != 2) {
      printUsage();
      return;
    }

    try {
      String[] denominationStrings = args[0].split(",");
      int[] denominations = new int[denominationStrings.length];

      for (int i = 0; i < denominations.length; i++) {
          denominations[i] = Integer.parseInt(denominationStrings[i]);
          if (denominations[i] <= 0) {
            System.out.println("Denominations must all be greater than zero.\n");
            printUsage();
            return;
          }

          for (int j = 0; j < i; j++) {
            if (denominations[j] == denominations[i]) {
              System.out.println("Duplicate denominations are not allowed.\n");
              printUsage();
              return;
            }
          }
      }

      int amount = Integer.parseInt(args[1]);
      if (amount < 0) {
        System.out.println("Change cannot be made for negative amounts.\n");
        printUsage();
        return;
      }

      Tuple change = makeChangeWithDynamicProgramming(denominations, amount);

      if (change.isImpossible()) {
        System.out.println("It is impossible to make " + amount + " cents with those denominations.");
      } else {
        int coinTotal = change.total();
        System.out.println(amount + " cents can be made with " + coinTotal + " coin" + getSimplePluralSuffix(coinTotal) + " as follows:");

      for (int i = 0; i < denominations.length; i++) {
        int coinCount = change.getElement(i);
        System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" + getSimplePluralSuffix(coinCount));
      }
     }
    } catch (NumberFormatException nfe) {
      System.out.println("Denominations and amount must all be integers.\n");
      printUsage();
    }
}
/**
* [makeChangeWithDynamicProgramming description]
* @param  int[] denominations [An int array of the types of coin to make up change.]
* @param  int   amount        [The total amount of money trying to be made out of the denominations.]
* @return       [returns the optimal tuple which contains the amount of each denomination for the least coins given back in change]
*/
public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int amount) {

  Tuple[][] table = new Tuple[denominations.length][amount+1];

  for(int i=0;i<denominations.length;i++){
    for(int j=0;j<amount+1;j++){
      int[] iArray = new int[denominations.length];
      table[i][j] = new Tuple(iArray);
      if(j != 0){
        if(denominations[i] <= j){
          iArray[i] = 1;
          table[i][j] = new Tuple(iArray);
          if(table[i][j-denominations[i]] != Tuple.IMPOSSIBLE){
            table[i][j] = table[i][j].add(table[i][j-denominations[i]]);
          }else{
            table[i][j] = Tuple.IMPOSSIBLE;
          }
        }else{
          table[i][j] = Tuple.IMPOSSIBLE;
        }
        int coinsBot = 0;
        int coinsTop = 0;
        if(i > 0 && table[i-1][j] != Tuple.IMPOSSIBLE  && table[i][j] != Tuple.IMPOSSIBLE){
          for(int s=0;s<denominations.length;s++){
            coinsBot = coinsBot + table[i][j].getElement(s);
            coinsTop = coinsTop + table[i-1][j].getElement(s);
          }
        }
        if(i > 0 && table[i-1][j] != Tuple.IMPOSSIBLE  && table[i][j] != Tuple.IMPOSSIBLE && coinsTop < coinsBot){
          table[i][j] = table[i-1][j];
        }else if(i > 0 && table[i-1][j] != Tuple.IMPOSSIBLE  && table[i][j] == Tuple.IMPOSSIBLE){
          table[i][j] = table[i-1][j];
        }else if(i > 0 && table[i-1][j] == Tuple.IMPOSSIBLE  && table[i][j] == Tuple.IMPOSSIBLE){
          table[i][j] = Tuple.IMPOSSIBLE;
        }


      }
    }
  }
  //System.out.println("Test Below Result: " + table[denominations.length-1][amount].toString());
  return table[denominations.length-1][amount];
}

private static void printUsage() {
    System.out.println("Usage: java ChangeMaker <denominations> <amount>");
    System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
    System.out.println("  - <amount> is the amount for which to make change");
}

private static String getSimplePluralSuffix(int count) {
    return count == 1 ? "" : "s";
}

}
