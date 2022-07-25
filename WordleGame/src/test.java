import java.lang.reflect.Array;
import java.util.ArrayList;

import wordleGame.WordleColor;

public class test {
	public static ArrayList<Boolean> isGreen(String guess, String answer) {
		
		if (guess.length() < answer.length() || guess.length() > answer.length()) {
			throw new IllegalArgumentException("Guess length different than answer length");
		}
		
		ArrayList<Character> answerList = new ArrayList<>();
		ArrayList<Character> guessList = new ArrayList<>();
		ArrayList<Boolean> trueFalseList = new ArrayList<>();
		for (char ch : guess.toCharArray()) {
			guessList.add(ch);
		}
		for (char ch: answer.toCharArray()) {
			answerList.add(ch);
		}
		for (int i = 0; i < guess.length(); i++ ) {
			 char temp1 = guessList.get(i);
			 char temp2 = answerList.get(i);
			 boolean temp3 = (temp1 == temp2);
			 trueFalseList.add(temp3);
		}
		return trueFalseList;
	}
	public static ArrayList<Boolean> isYellow(String guess, String answer) {
		
		if (guess.length() < answer.length() || guess.length() > answer.length()) {
			throw new IllegalArgumentException("Guess length different than answer length");
		}
		
		ArrayList<Boolean> green = isGreen(guess,answer);
		ArrayList<Character> answerList = new ArrayList<>();
		ArrayList<Character> guessList = new ArrayList<>();
		ArrayList<Boolean> yellowList = new ArrayList<>();
		
		for (char ch : guess.toCharArray()) {
			guessList.add(ch);
		}
		for (char ch: answer.toCharArray()) {
			answerList.add(ch);
		}
		
		for(int i = 0; i < answer.length(); i++) {
			if(green.get(i) == true) {
				answerList.set(i, '%');
			}
		}
		
		
		for (int i = 0; i < guess.length(); i++) {
			char temp1 = guessList.get(i);
			outerloop:
			for(int j = 0; j < answer.length(); j++) {
				char temp2 = answerList.get(j);
				if(temp1 == temp2) {
					yellowList.add(true);
					answerList.set(j, '%');
					j++;
					break outerloop;
				}
				
				else if(j == answer.length() -1){
					yellowList.add(false);
					break outerloop;
					
				}
				
			}
			
		}
		return yellowList;
	}
	public static ArrayList<WordleColor> getColors(String guess, String answer) {
		WordleColor g = WordleColor.GREEN;
		WordleColor a = WordleColor.GRAY;
		WordleColor y = WordleColor.YELLOW;
		ArrayList<WordleColor> c = new ArrayList<>();
		ArrayList<Boolean> green = isGreen(guess,answer);
		ArrayList<Boolean> yellow = isYellow(guess,answer);
		
		for(int i = 0; i < guess.length(); i++) {
			if(green.get(i) == true) {
				c.add(g);
			}
			else if(yellow.get(i) == true) {
				c.add(y);
			}
			else {
				c.add(a);
			}
		}
		
		return c; 
	}
	public static void main(String[] args) {
	String g = "coral";
	String ans = "float";
	ArrayList<WordleColor> t = getColors(g,ans);
	System.out.println(t);
	
	}
}
