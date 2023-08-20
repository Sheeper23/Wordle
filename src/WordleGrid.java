

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class WordleGrid {
	private String[][] board;
	private final ArrayList<String> WORDS;
	private final ArrayList<String> ALLOWED_GUESSES;
	private String word;
	private int guessNum;
	private int colNum;
	
	public WordleGrid() {
		board = new String[6][5];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = " ";
			}
		}
		
		WORDS = new ArrayList<String>();
		ALLOWED_GUESSES = new ArrayList<String>();
		
		InputStream answerStream = getClass().getClassLoader().getResourceAsStream("wordle-answers-alphabetical.txt");
		Scanner answersFile = new Scanner(answerStream);

        while (answersFile.hasNextLine())
        {
            String line = answersFile.nextLine();
            WORDS.add(line.toUpperCase());
        }
        answersFile.close();
		
        
        InputStream masterStream = getClass().getClassLoader().getResourceAsStream("wordle-master-alphabetical.txt");
		Scanner masterFile = new Scanner(masterStream);

        while (masterFile.hasNextLine())
        {
            String line = masterFile.nextLine();
            ALLOWED_GUESSES.add(line.toUpperCase());
        }
        masterFile.close();
        
		word = WORDS.get((int)(Math.random() * WORDS.size()));
		System.out.println(word);
	}
	
	public String[][] getBoard() {
		return board;
	}
	
	public int getGuessNum() {
		return guessNum;
	}
	
	public int getColNum() {
		return colNum;
	}
	
	public void guess(String guess) {
		if (guessNum > 5) return;
		
		if (guess.equals("\n")) {
			if (colNum != 5 || !ALLOWED_GUESSES.contains(board[guessNum][0] + board[guessNum][1] + board[guessNum][2] + board[guessNum][3] + board[guessNum][4])) return;
			
			for (int i = 0; i < board[0].length; i++) {
				if (board[guessNum][i].equals(""+word.charAt(i))) {
					board[guessNum][i] = "g" + board[guessNum][i];
					word = word.substring(0, i) + word.substring(i,i+1).toLowerCase() + word.substring(i+1);
				}
			}
			for (int i = 0; i < board[0].length; i++) {
				for (int j = 0; j < word.length(); j++) {
					if (board[guessNum][i].equals(word.substring(j,j+1))) {
						board[guessNum][i] = "y" + board[guessNum][i];
						word = word.substring(0, j) + word.substring(j,j+1).toLowerCase() + word.substring(j+1);
						break;
					}
				}
			}
			for (int i = 0; i < board[0].length; i++) {
				if (board[guessNum][i].length() == 1) {
					board[guessNum][i] = "n" + board[guessNum][i];
				}
			}
			guessNum++;
			colNum = 0;
			word = word.toUpperCase();
		}
		else if (guess.equals("back")) {
			if (colNum == 0) return;
			colNum--;
			board[guessNum][colNum] = " ";
		}
		else {
			if (colNum == 5) return;
			board[guessNum][colNum] = guess.toUpperCase();
			colNum++;
		}
	}
}
