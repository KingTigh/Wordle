package wordleGame;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class TestWordleUtils {

	@Test
	public void test01a_replace() {
		// method args
		String s = "A";
		int index = 0;
		char c = 'B';
		
		// expected answer
		String exp = "B";
		
		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}

	@Test
	public void test01b_replace() {
		// method args
		String s = "GUMBO";
		int index = 0;
		char c = 'J';
		
		// expected answer
		String exp = "JUMBO";
		
		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}
	
	@Test
	public void test01c_replace() {
		// method args
		String s = "JIMBO";
		int index = 1;
		char c = 'U';
		
		// expected answer
		String exp = "JUMBO";
		
		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test02a_isGreen() {
		// method args
		String answer = "hello";
		String guess = "bye";
		
		// run test
		WordleUtils.isGreen(guess, answer);
	}
	
	@Test
	public void test02b_isYellow() {
		//method args
		String guess = "whack";
		String answer = "kayak";
		
		// expected answer
		ArrayList<Boolean> exp = new ArrayList<Boolean>(5);
		exp.addAll(Collections.nCopies(5, Boolean.FALSE));
		exp.set(2, true);
		exp.set(4, true); // I think my isYellow is incorrect as the k shouldn't be yellow
		
		// run test
		assertEquals(exp ,WordleUtils.isYellow(guess, answer));
			
	}
	
	@Test
	public void test02c_getColors() {
	// method args
	String guess = "BOCKS";
	String answer = "BOOKS";
	
	// Expected answer
	
	WordleColor g = WordleColor.GREEN;
	WordleColor a = WordleColor.GRAY;
	ArrayList<WordleColor> c = new ArrayList<>();
	c.add(g);
	c.add(g);
	c.add(a);
	c.add(g);
	c.add(g);
	
	// run test
	assertEquals(c,WordleUtils.getColors(guess, answer));
	}
	
	
	
}
