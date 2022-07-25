package a2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordleGame extends JFrame implements ActionListener {

	private static final String[] WORDS = {
			"ABOUT", "BUGGY", "CODES", "DEBUG", "ERROR", "FALSE", "GRAPH",
			"HACKS", "INPUT", "JUDGE", "KITTY", "LOGIC", "MONEY", "NEVER",
			"ONION", "PAUSE", "QUIET", "ROBOT", "SHIFT", "TYPES", "UNTIL",
			"VALUE", "WHILE", "YACHT", "ZEBRA"
	};
	private static final String SUBMIT = "SUBMIT";
	private static final String NEW_GAME = "NEW_GAME";
	private static final String GIVE_UP = "GIVE_UP";
	private static final String EXIT = "EXIT";
	private static final int WORD_LENGTH = 5;
	private static final int MAX_GUESSES = 6;
	
	
	private String answer;
	private JButton[][] guesses;
	private JTextField guess;
	private int guessNumber;
	private boolean gameOver;
	private Random rng;
	
	public WordleGame() {
		super("Wordle");
		this.rng = new Random();
		
		// setup the GUI
		this.guesses = new JButton[MAX_GUESSES][WORD_LENGTH];
		this.setJMenuBar(this.makeMenu());
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(makeLabelPanel());
		contentPanel.add(makeWordEntryPanel());
		this.setContentPane(contentPanel);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.guess.requestFocusInWindow();
		// initialize the game
		init();
	}
	
	/**
	 * Resets the state of the game to the start of a new game.
	 */
	private void init() {
		for (int row = 0; row < MAX_GUESSES; row++) {
			for (int col = 0; col < WORD_LENGTH; col++) {
				this.guesses[row][col].setText("?");
				this.guesses[row][col].setBackground(Color.WHITE);
			}
		}
		this.guess.setText("");
		this.answer = WORDS[rng.nextInt(WORDS.length)];
		System.out.println(this.answer);
		this.guessNumber = 0;
		this.gameOver = false;
	}
	
	private JMenuBar makeMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Wordle");
		bar.add(menu);

		this.addMenuItem(menu, "New Game", NEW_GAME);
		this.addMenuItem(menu, "Give up", GIVE_UP);
		menu.addSeparator();
		this.addMenuItem(menu, "Exit", EXIT);
		return bar;
	}
	
	private void addMenuItem(JMenu menu, String label, String action) {
		JMenuItem item = new JMenuItem(label);
		item.setActionCommand(action);
		item.addActionListener(this);
		menu.add(item);
	}
	
	private JPanel makeLabelPanel() {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(MAX_GUESSES, WORD_LENGTH));
		for (int row = 0; row < MAX_GUESSES; row++) {
			for (int col = 0; col < WORD_LENGTH; col++) {
				JButton b = new JButton("?");
				b.setPreferredSize(new Dimension(100, 100));
				b.setMaximumSize(b.getSize());
				b.setFont(font);
				b.setBackground(Color.WHITE);
				b.setOpaque(true);
				b.setBorderPainted(false);
				this.guesses[row][col] = b;
				p.add(b);
			}
		}
		return p;
	}
	
	private JPanel makeWordEntryPanel() {
		JPanel p = new JPanel();
		//p.setLayout(new GridLayout(MAX_GUESSES, WORD_LENGTH));
		this.guess = new JTextField(WORD_LENGTH + 1);
		p.add(this.guess);
		JButton submit = new JButton ("Enter");
		submit.setActionCommand(SUBMIT);
		submit.addActionListener(this);
		p.add(submit);
		return p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(NEW_GAME)) {
			init();
		}
		else if (action.equals(GIVE_UP)) {
			if (this.gameOver) {
				return;
			}
			// show answer and end game
			JOptionPane.showMessageDialog(this,
				    "Answer was : " + this.answer,
				    "You lose",
				    JOptionPane.PLAIN_MESSAGE);
			this.gameOver = true;
		}
		else if (action.equals(EXIT)) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else if (action.equals(SUBMIT)) {
			if (this.gameOver) {
				return;
			}
			String guessStr = this.guess.getText().trim().toUpperCase();
			if (guessStr.length() != WORD_LENGTH) {
				return;
			}
			ArrayList<WordleColor> colors = WordleUtils.getColors(guessStr, this.answer);
			for (int i = 0; i < colors.size(); i++) {
				JButton b = this.guesses[this.guessNumber][i];
				if (colors.get(i) == WordleColor.GRAY) {
					b.setBackground(Color.GRAY);
				}
				else if (colors.get(i) == WordleColor.GREEN) {
					b.setBackground(Color.GREEN);
				}
				else if (colors.get(i) == WordleColor.YELLOW) {
					b.setBackground(Color.YELLOW);
				}
				b.setText("" + guessStr.charAt(i));
			}
			if (guessStr.equals(this.answer)) {
				JOptionPane.showMessageDialog(this,
					    "Well done!",
					    "You win",
					    JOptionPane.PLAIN_MESSAGE);
				this.gameOver = true;
				return;
			}
			this.guess.setText("");
			this.guessNumber++;
			if (this.guessNumber == MAX_GUESSES) {
				JOptionPane.showMessageDialog(this,
					    "Answer was : " + this.answer,
					    "You lose",
					    JOptionPane.PLAIN_MESSAGE);
				this.gameOver = true;
			}
			this.guess.requestFocusInWindow();
		}
		else {
			;
		}
		
	}

	public static void main(String[] args) {
		WordleGame g = new WordleGame();
		
		g.setVisible(true);
	}
}
