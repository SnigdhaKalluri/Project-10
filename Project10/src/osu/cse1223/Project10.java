/*
 * Project10.java
 * 
 *   A program that plays and scores a round of the game Poker Dice.  In this game,
 *   five dice are rolled.  The player is allowed to select a number of those five dice
 *   to re-roll.  The dice are re-rolled and then scored as if they were a poker hand.  
 *   The following hands MUST be scored in this assignment:
 *   	* High card
 *   	* One Pair
 *   	* Two Pair
 *   	* Three of a Kind
 *   	* Full House
 *   	* Four of a Kind
 *   	* Five of a Kind
 *   For extra credit, you may also implement:
 *   	* Straight
 * 
 * @author Snigdha Kalluri
 * @version 20190326
 * 
 */
package osu.cse1223;

import java.util.Scanner;

public class Project10 {
	
	public static void main(String[] args) {
		// Fill in the body
		Scanner inScanner = new Scanner(System.in);
		int [] dice = new int [5];
		rollDice(dice);
		promptForReroll(dice, inScanner);
		
		
		while (promptForPlayAgain(inScanner)==true)
		{
			resetDice(dice);
			rollDice(dice);
			promptForReroll(dice, inScanner);
			System.out.println();
		}
		System.out.println("Goodbye!");
	}
	
	// Given an array of integers as input, sets every element of the array to zero.
	public static void resetDice(int[] dice) {
		// Fill in the body
		int i = 0;
		while (i<dice.length)
		{
			dice[i] = 0;
			i++;
		}
	}
	
	// Given an array of integers as input, checks each element of the array.  If the value
	// of that element is zero, generate a number between 1 and 10 and replace the zero with
	// it.  Otherwise, leave it as is and move to the next element.
	public static void rollDice(int[] dice) {
		// Fill in the body
		int i = 0;
		while (i<dice.length)
		{
			if (dice[i] == 0)
			{
				dice[i] = (int)(Math.random()*6)+1;
			}
			i++;
		}
			
	}
	
	// Given an array of integers as input, create a formatted String that contains the
	// values in the array in the order they appear in the array.  For example, if the 
	// array contains the values [0, 3, 7, 5, 2] then the String returned by this method
	// should be "0 3 7 5 2".
	public static String diceToString(int[] dice) {
		// Fill in the body
		String array = "";
		int i = 0;
		while (i<dice.length)
		{
			array = array + dice[i] + " ";
			i++;
		}
		return array;
		
	}
	
	
	// Given an array of integers and a Scanner as input, prompt the user with a message
	// to indicate which dice should be re-rolled.  If the user enters a valid index (between
	// 0 and the total number of dice -1) then set the die at that index to zero.  If the 
	// user enters a -1, end the loop and return to the calling program.  If the user enters
	// any other invalid index, provide an error message and ask again for a valid index.
	public static void promptForReroll(int[] dice, Scanner inScanner) {
		// Fill in the body
		String array = diceToString(dice);
		
		System.out.println("Your current dice: " + array);
		System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
		int input = inScanner.nextInt();
		
		
		
		while (input!=-1)
		{ 
			while (!(input>=0 && input<=dice.length-1))
			{
		
			System.out.println("Error: Index must be between 0 and " + (dice.length -1) + " (-1 to quit)!");
			System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
			input = inScanner.nextInt();
			}
		
			if  (input>=0 && input<=dice.length-1)
			{
			dice[input]=0;
			array = diceToString(dice);
			System.out.println("Your current dice: " + array);
			System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
			input = inScanner.nextInt();
			}
		}
		

				System.out.println("Keeping remaining dice...");
				System.out.println("Rerolling...");
				rollDice(dice);
				System.out.println("Your final dice: " + diceToString(dice));
				System.out.println(getResult(dice));
				
		}
			
		
		
		
	
	
	// Given a Scanner as input, prompt the user to play again.  The only valid entries
	// from the user are 'Y' or 'N', in either upper or lower case.  If the user enters
	// a 'Y' the method should return a value of true to the calling program.  If the user
	// enters a 'N' the method should return a value of false.  If the user enters anything
	// other than Y or N (including an empty line), an error message should be displayed
	// and the user should be prompted again until a valid response is received.
	public static boolean promptForPlayAgain(Scanner inScanner) {
		// Fill in body
		
		boolean play = true;
		System.out.print("Would you like to play again [Y/N]?: ");
		char again = inScanner.next().charAt(0);
		
		
		while (again !='Y' && again !='y' && again !='N' && again !='n')
		{
		System.out.println("ERROR! Only 'Y' or 'N' allowed as input!");
		System.out.print("Would you like to play again [Y/N]?: ");
		again = inScanner.next().charAt(0);
		}
		
		if (again =='N' ||again =='n')
		{
			play = false;
		}
		else 
		{
			play = true;
		}
		
		return play;
		
		
	}
	
	// Given an array of integers, determines the result as a hand of Poker Dice.  The
	// result is determined as:
	//	* Five of a kind - all five integers in the array have the same value
	//	* Four of a kind - four of the five integers in the array have the same value
	//	* Full House - three integers in the array have the same value, and the remaining two
	//					integers have the same value as well (Three of a kind and a pair)
	//	* Three of a kind - three integers in the array have the same value
	//	* Two pair - two integers in the array have the same value, and two other integers
	//					in the array have the same value
	//	* One pair - two integers in the array have the same value
	//	* Highest value - if none of the above hold true, the Highest Value in the array
	//						is used to determine the result
	//
	//	The method should evaluate the array and return back to the calling program a String
	//  containing the score from the array of dice.
	//
	//  EXTRA CREDIT: Include in your scoring a Straight, which is 5 numbers in sequence
	//		[1,2,3,4,5] or [2,3,4,5,6] or [3,4,5,6,7] etc..
	public static String getResult(int[] dice) {
		// Fill in the body
		int [] count2 = getCounts(dice);
		
		String score = "";
		
		
		for (int i = 0; i<count2.length && score.length()==0; i++)
		{ // checks for five of a kind
			if (count2[i]==5)
			{
				score = "Five of a Kind!";
			}
			else if (count2[i]==4)
			{//checks for fours of a kind
				score = "Four of a Kind!";
					
			}
			else if (count2[i]==3)
			{int z=0;
				for (int k = 0; k<count2.length; k++)
				{//if count is equal two three, the score can either be full house or three of a kind
					if (count2[k]==2)
					{z++;
					}
					if(z==1)
					{//checks for full house
						score = "Full House!";
					}
					else
					{//checks for three of a kind
						score = "Three of a Kind!";
					}
				}
			}
			else if (count2[i]==1)
			{
				for (int j = 0; j<count2.length; j++)
				{//EXTRA CREDIT -  checks for straight by making sure that there is one of each number that increments by 1 
					if (count2[j]==1 && count2[j+1]==1&&count2[j+2]==1 && count2[j+3]==1&&count2[j+4]==1)
					{
						score = "Straight!";
					}
				}
			}
			else if (count2[i]==2)
			{int j=0;
			int s=0; //if the counts is equal to 2, the score can still be full house, just depending on the order of numbers
					// of score can just me one pair or two pairs
				for (int k = 0; k<count2.length; k++)
				{
					if (count2[k]==3)
					{s++;
					}
					else if(count2[k]==2)
					{
					j++;
					}
				}
				if (s==1)
				{
					score = "Full House!";	
				}
				else if(j==2)
				{//checks for two pairs
						score = "Two Pairs!";
				}
					else
					{//checks for one pair
						score = "One Pair!";
					}
				}
			}
		
		int j=count2.length-1;
		 if(score.contentEquals(""))
			     {//the last option is for the code to be a highest card, since all the numbers are different and not a full house
			 
			     j=count2.length-1;
			         while(count2[j]==0)
			         {//goes though array and keeps setting j as the number that is the highest compared to the previous positions in the array
			             j--;
			         }
			         String high=String.valueOf(j);
			    score="Highest Value "+high;
			     }
			    return score;    
			        
	}
	
	// Given an array of integers as input, return back an array with the counts of the
	// individual values in it.  You may assume that all elements in the array will have 
	// a value between 1 and 10.  For example, if the array passed into the method were:
	//   [1, 2, 3, 3, 7]
	// Then the array of counts returned back by this method would be:
	// [1, 1, 2, 0, 0, 0, 1, 0, 0, 0]
	// (Where index 0 holds the counts of the value 1, index 1 holds the counts of the value
	//  2, index 2 holds the counts of the value 3, etc.)
	// HINT:  This method is very useful for determining the score of a particular hand
	//  of poker dice.  Use it as a helper method for the getResult() method above.
	public static int[] getCounts(int[] dice) {
		// Fill in the body
		
		 int[] count=new int[10];
		       int i=0;
		       int zero=0;
		       while(i<dice.length)
		        {if(dice[i]==0)
		             {zero++;}
		        i++;
		         }
		        i=0;
		         int one=0;
		         while(i<dice.length)
		         {if(dice[i]==1)
		             {one++;
		             }
		         i++;
		         }
		        int two=0;
		         i=0;
		         while(i<dice.length)
		         {if(dice[i]==2)
		             {two++;
		             }
		         i++;
		         }
		         int three=0;
		         i=0;
		         while(i<dice.length)
		         {if(dice[i]==3)
		             {three++;
		             }
		         i++;
		         }
		         int four=0;
		         i=0;
		         while(i<dice.length)
		         {if(dice[i]==4)
		             {four++;
		             }
		         i++;
		         }
		         int five=0;
		         i=0;
		         while(i<dice.length)
		         {if(dice[i]==5)
		             {five++;
		             }
		         i++;
		         }
		        int six=0;
		         i=0;
		         while(i<dice.length)
		         {if(dice[i]==6)
		            {six++;
		             }
		         i++;
		         }
		         int seven=0;
		         i=0;
		         while(i<dice.length)
		         {if(dice[i]==7)
		             {seven++;
		             }
		         i++;
		         }
		         int eight=0;
		         i=0;
		        while(i<dice.length)
		         {if(dice[i]==8)
		             {eight++;
		            }
		        i++;
		        }
		        int nine=0;
		        i=0;
		        while(i<dice.length)
		        {if(dice[i]==9)
		            {nine++;
		            }
		       i++;
		      }
		         count[0]=zero;
		         count[1]=one;
		        count[2]=two;
		        count[3]=three;
		        count[4]=four;
		        count[5]=five;
		        count[6]=six;
		        count[7]=seven;
		       count[8]=eight;
		        count[9]=nine;
		        return count;
		}
	}

