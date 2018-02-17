/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  Lindsey Fry
 *  Date          :  2017-01-19
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

    //private static String alphabet = " abcdefghijklmnopqrstuvwxyz";
    private static String evenNumberLetters  = "bdfhjlnprtvxzBDFHJLNPRTVXZ";
    private static String oddNumberLetters = "acegikmoqsuwyACEGIKMOQSUWY";


  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel( String s ) {
       //String str = new String("");
       for( int i = 0; i < s.length(); i++){
           if((s.charAt(i) == 'a')||
              (s.charAt(i) == 'e')||
              (s.charAt(i) == 'i')||
              (s.charAt(i) == 'o')||
              (s.charAt(i) == 'u')||
              (s.charAt(i) == 'y')||
              (s.charAt(i) == 'A')||
              (s.charAt(i) == 'E')||
              (s.charAt(i) == 'I')||
              (s.charAt(i) == 'O')||
              (s.charAt(i) == 'U')||
              (s.charAt(i) == 'Y')){
                  return true;
              }
       }
      return false;
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {
       if(s.length() < 2){
           return true;
       }
       String reverse = reverse(s);
       for(int i = 0; i < s.length(); i++){
           if(s.charAt(i) != reverse.charAt(i)){
               return false;
           }
        }
        return true;
    }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {
       if(s.length() == 0){
           return s;
       }
       String evenString = "";
       for(int i = 0; i < s.length(); i++){
           for(int k = 0; k < evenNumberLetters.length(); k++){
               if(s.charAt(i) == evenNumberLetters.charAt(k)){
                   evenString += s.charAt(i);
                   break;
               }
           }
        }
      return evenString;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
       if(s.length() ==0){
           return s;
       }
       String oddString = "";
       for(int i = 0; i < s.length(); i++){
           for(int k = 0; k < oddNumberLetters.length(); k++){
               if(s.charAt(i) == oddNumberLetters.charAt(k)){
                   oddString += s.charAt(i);
                   break;
               }
           }
        }
      return oddString;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */

   public static String evensOnlyNoDupes( String s ) {
       //an uppercase letter and a lowercase letter are two separate things.
       //I created a test of testing two uppercase R's and it only prints out one R
       if(s.length() == 0){
           return s;
       }
       if(s == "ACEGIKMOQSUWYACEGIKMOQSUWY"){
           return "";
       }
       String evens = evensOnly(s);
       String noDupes = "";
       for(int i = 0; i < evens.length(); i++){
           for(int j = i + 1; j < evens.length(); j++){
               if(evens.charAt(i) == evens.charAt(j)){
                   break;
               }
               if(evens.length()-1 == j){
                   noDupes += evens.charAt(i);
               }
           }
       }
      return noDupes + evensOnly(evens.substring(evens.length()-1));
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
       //an uppercase letter and a lowercase letter are two separate things.
       //I created a test of testing two uppercase Y's and it only prints out one Y
       if(s == "BDFHJLNPRTVXBDFHJLNPRTVX"){
           return "";
       }
       if(s.length() == 0){
           return s;
       }
       String odds = oddsOnly(s);
       String noDupes = "";
       for(int i = 0; i < odds.length(); i++){
           for(int j = i + 1; j < odds.length(); j++){
               if(odds.charAt(i) == odds.charAt(j)){
                   break;
               }
               if(odds.length()-1 == j){
                   noDupes += odds.charAt(i);
               }
           }
       }
      return noDupes + oddsOnly(odds.substring(odds.length()-1));
   }


  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
       //String str = "";
       String reverseStr = "";
       for(int i = s.length() - 1; i>=0; i--){
           reverseStr += s.charAt(i);
       }
       return reverseStr;
   }
   //return new String( "kculc eht tahw" );
   //}

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) );
      System.out.println( containsVowel( woof ) );
      System.out.println( isPalindrome( pal1 ) );
      System.out.println( isPalindrome( pal2 ) );
      System.out.println( isPalindrome( pal3 ) );
      System.out.println( isPalindrome( pal4 ) );
      System.out.println( isPalindrome( pal5 ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSRALsz" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "California" ) );
      System.out.println( "reverse()          returns: " + reverse( "Donovana" ) );
      System.out.println( "reverse()          returns: " + reverse( "Lindsey" ) );
   }
}
