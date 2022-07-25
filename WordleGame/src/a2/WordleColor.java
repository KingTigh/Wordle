package a2;

/**
 * Colors used in the Wordle game to indicate the status of a letter in the
 * guessed word. 
 *
 */
public enum WordleColor {
	/**
	 * Color for a correct letter in the correct location. 
	 */
	GREEN,
	
	/**
	 * Color for a letter that is in the answer but in the wrong location.
	 */
	YELLOW,
	
	/**
	 * Color for a letter that is not in the answer.
	 */
	GRAY
}
