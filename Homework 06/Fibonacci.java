/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Fibonacci.java
 * Purpose    :  Find the fibonacci number at a specified place n
 * @author    :  Lindsey Fry
 * Date       :  2018-04-18
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-04-18  Lindsey Fry   Initial writing and release
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Fibonacci{
  private static BrobInt answer = new BrobInt("0");
  private static BrobInt twoBack = BrobInt.ZERO;
  private static BrobInt oneBack = BrobInt.ONE;

  public static BrobInt getNum(int n){
    if(n == 1){
      return twoBack;
    }else if(n == 2){
      return oneBack;
    }else{
      for(int i=2;i<n;i++){
        answer = twoBack.add(oneBack);
        twoBack = oneBack;
        oneBack = answer;
      }
    }
    return answer;
  }
  public static void main(String args[]){
    System.out.println(getNum(Integer.parseInt(args[0])));
  }
}
