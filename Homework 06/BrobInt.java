/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Contains Big Integer Methods and Constructor
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

public class BrobInt{
  public static final BrobInt ZERO = new BrobInt("0");
  public static final BrobInt ONE = new BrobInt("1");
  public static final BrobInt TEN = new BrobInt("10");
  private static final BrobInt NEGATIVE_ONE = new BrobInt( "-1" );
  private String gStr;
  private int gInt;
  private String sign = "";
  private int[] intArray;
  private boolean greaterThan = false;
  private boolean lessThan = false;
  private int equalCount = 0;
  private String intString = "";
  private String intToString;
  char[] charArrayNoSign;
  private String myNumber = "";
  private String reversed = "";
  private byte[] a = null;
  private byte[] b = null;
  private boolean subFlag = false;
  private boolean addFlag = false;

  //Constructor
  public BrobInt(String value){
    //get rids of spaces
    gStr = value.trim();
    //creates character array
    char[] charArray = gStr.toCharArray();
    if(charArray[0] == '+' || charArray[0] == '-'){
      //gets the sign
      sign = String.valueOf(charArray[0]);
      //deletes sign
      gStr = gStr.substring(1,charArray.length);
      charArrayNoSign = gStr.toCharArray();
      //split string into parts
      intArray = new int[charArrayNoSign.length];
      for(int i=0; i<intArray.length;i++){
        intArray[i] = Character.getNumericValue(charArrayNoSign[i]);
        intString = intString + Character.toString(charArrayNoSign[i]);
      }
    }else{
      charArrayNoSign = gStr.toCharArray();
      intArray = new int[charArrayNoSign.length];
      for(int n=0; n<intArray.length; n++){
        intArray[n] = Character.getNumericValue(charArrayNoSign[n]);
        intString = intString + Character.toString(charArrayNoSign[n]);
      }
    }
  }

  public int compareTo(BrobInt value){
    int var = intString.compareTo(value.intString);
    if(intString.compareTo(value.intString) == 4){
      var = 1;
    }else if(intString.compareTo(value.intString) == -4){
      var = -1;
    }
    return var;
  }


  public boolean equals(BrobInt x){
    if(intArray.length != x.intArray.length){
      return false;
    }else{
      for(int i=0;i<intArray.length;i++){
        if(intArray[i] == x.intArray[i]){
          equalCount += 1;
        }else{
          equalCount = equalCount;
        }
      }
      if(equalCount == intArray.length){
        return true;
      }else{
        return false;
      }
    }
  }

  public BrobInt add(BrobInt value){
    if((value.sign.equals("-") && this.sign.equals("")) || ((value.sign.equals("") && this.sign.equals("-")) && subFlag == false)){
      addFlag = true;
      return this.subtract(value);
    }else{
    int addCarry = 0;
    int piece = 0;
    String pieceString = "";
    String addAnswerString = "";
    String specialCarryMarker = "";
    if(intString.length() > value.intString.length()){
      for(int i=0;i<charArrayNoSign.length-value.charArrayNoSign.length;i++){
        value.intString = "0" + value.intString;
      }
    }else if(intString.length() < value.intString.length()){
      for(int i=0;i<value.charArrayNoSign.length-charArrayNoSign.length;i++){
        intString  = "0" + intString;
      }
    }
    String[] intStringArray1 = intString.split("");
    String[] intStringArray2 = value.intString.split("");
    for(int k=0;k<intStringArray1.length;k++){
      piece = addCarry + Integer.parseInt(intStringArray1[intStringArray1.length-(k+1)]) + Integer.parseInt(intStringArray2[intStringArray2.length-(k+1)]);
      pieceString = Integer.toString(piece);
      addCarry = 0;
      if(pieceString.length() > 1){
        String[] parts = pieceString.split("");
        addCarry = Integer.parseInt(parts[0]);
        piece = Integer.parseInt(parts[1]);
        pieceString = parts[1];
        if(k==intStringArray1.length-1){
          specialCarryMarker = Integer.toString(addCarry);
        }
      }
      addAnswerString = specialCarryMarker + pieceString + addAnswerString;
    }
    return new BrobInt(sign + addAnswerString);
  }
  }

  public BrobInt subtract(BrobInt value){
    if(sign.equals("-") && value.sign.equals("") && compareTo(value) < 0 && addFlag == false){
      subFlag = true;
      this.sign = "-";
      value.sign = "-";
      return this.add(value);
    }
    if(sign.equals("-") && value.sign.equals("-") && compareTo(value) < 0){
      this.sign = "";
    }else if(compareTo(value) < 0){
      this.sign = "-";
    }
    int piece = 0;
    String pieceString = "";
    String subAnswerString = "";
    if(intString.length() > value.intString.length()){
      for(int i=0;i<charArrayNoSign.length-value.charArrayNoSign.length;i++){
        value.intString = "0" + value.intString;
      }
    }else if(intString.length() < value.intString.length()){
      for(int i=0;i<value.charArrayNoSign.length-charArrayNoSign.length;i++){
        intString  = "0" + intString;
      }
    }
    String[] intStringArrayTop;
    String[] intStringArrayBot;
    if(compareTo(value) > 0){
      intStringArrayTop = intString.split("");
      intStringArrayBot = value.intString.split("");
    }else if(compareTo(value) < 0){
      intStringArrayTop = value.intString.split("");
      intStringArrayBot = intString.split("");
    }else{
      intStringArrayTop = intString.split("");
      intStringArrayBot = value.intString.split("");
    }
    for(int i=0; i<intStringArrayTop.length;i++){
      if(Integer.parseInt(intStringArrayTop[intStringArrayTop.length-(i+1)]) < Integer.parseInt(intStringArrayBot[intStringArrayBot.length-(i+1)])){
        intStringArrayTop[intStringArrayTop.length-(i+2)] = Integer.toString(Integer.parseInt(intStringArrayTop[intStringArrayTop.length-(i+2)]) - 1);
        intStringArrayTop[intStringArrayTop.length-(i+1)] = Integer.toString(Integer.parseInt(intStringArrayTop[intStringArrayTop.length-(i+1)]) + 10);
      }
      piece = Integer.parseInt(intStringArrayTop[intStringArrayTop.length-(i+1)]) - Integer.parseInt(intStringArrayBot[intStringArrayBot.length-(i+1)]);
      pieceString = Integer.toString(piece);
      subAnswerString = pieceString + subAnswerString;
    }
    return new BrobInt(sign + subAnswerString);

  }

  public BrobInt multiply(BrobInt value){
    BrobInt n1 = new BrobInt(this.intString);
    BrobInt n2 = new BrobInt(value.intString);
    char[] n1Char = n1.intString.toCharArray();
    String n2Value = "";
    int total = 0;
    while(!n1.intString.equals("1")){
      if(n1Char[n1.intString.length()-1]%2 != 0){
        total += Integer.parseInt(n2.intString);
      }
      if(Integer.parseInt(n1.intString)%(20) == 0){
        n1.intString = Integer.toString(Integer.parseInt(n1.halve(n1.intString)) * 10);
      }else{
        n1 = new BrobInt(n1.halve(n1.intString));
      }
      n2 = new BrobInt((n2.add(n2)).intString);
      n1Char = n1.intString.toCharArray();
    }
    total += Integer.parseInt(n2.intString);
    if(n2.sign.equals("-") || n1.sign.equals("-")){
      sign = "-";
    }

    String totalStr = sign + Integer.toString(total);
    return new BrobInt(totalStr);
  }

  public BrobInt divide(BrobInt value){
    if ( value.equals( ZERO ) ) {
      System.out.println( "Cannot divide by 0 " );
      System.exit( 0 );
    }

    boolean n1IsPositive = this.compareTo( ZERO ) >= 0;
    boolean n2IsPositive = value.compareTo( ZERO ) >= 0;

    BrobInt quotient = ZERO;
    BrobInt dividend = ( n1IsPositive ) ? this : this.multiply( NEGATIVE_ONE );
    BrobInt divisor = ( n2IsPositive ) ? value : value.multiply( NEGATIVE_ONE );

     while ( dividend.compareTo( ZERO ) == 1 ) {
       dividend = dividend.subtract( divisor );
       quotient = quotient.add( ONE );
     }

     quotient = quotient.subtract( ONE );
     quotient = ( n1IsPositive && !n2IsPositive || !n1IsPositive && n2IsPositive ) ? quotient.multiply( NEGATIVE_ONE ) : quotient;
     return quotient;
  }

  public BrobInt remainder(BrobInt value){
    return this.subtract( this.divide( value ).multiply( value ) );
  }
  // Constructor in reverse
  // change int array to a string
  public String toString(){
    intToString = Integer.toString(intArray[0]);
    for(int i=1;i<intArray.length;i++){
      intToString = intToString + Integer.toString(intArray[i]);
    }
    if(intToString.length() == charArrayNoSign.length){
      intToString = sign + intToString;
    }
    return intToString;
  }

  public static BrobInt valueOf(long value){
    String longString;
    longString = String.valueOf(value);
    return new BrobInt(longString);
  }

  public String halve( String input ) {

    // declare and initialize the variables
     int          j        = 0;
     String       result_s = null;
     StringBuffer result   = null;

     myNumber = input;
     reversed = new String( new StringBuffer( myNumber ).reverse() );
     a = new byte[myNumber.length() + 1];   // extra place to handle "carry"
     b = new byte[myNumber.length()];

    // assign the values to the byte array
     for( int i = 0; i < a.length - 1; i++ ) {
        a[i] = (byte)((int)(myNumber.charAt(i)) - 48);         // NOTE: only works for ASCII
     }

    // do the division
     for( int i = 0; i < b.length; i++ ) {
        b[i] = (byte)((int)a[i] / 2);
        if( 1 == ((int)a[i] - ((int)b[i] * 2)) ) {
           a[i+1] = (byte)((int)a[i+1] + 10);
        }
     }

    // build the result string to pass back
     result = new StringBuffer();
     for( int i = 0; i < a.length - 1; i++ ) {
        if( 0 == b[i] ) {
           continue;
        }
        result = result.append( (int)b[i] );
     }
     return new String( result );

  }
}
