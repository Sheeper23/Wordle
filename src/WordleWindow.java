

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class WordleWindow extends JFrame {
	private WordleGrid board;
	private JPanel gridPanel;
	
	public WordleWindow() {
		super("Wordle");
		board = new WordleGrid();
		gridPanel = new JPanel();
		
		setSize(500, 600);
        setLocation(450, 50);
        setVisible(true);
        setResizable(false);
        setLayout(new GridLayout(1, 1, 1, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gridPanel.setBackground(new Color(230, 230, 230));
        gridPanel.setLayout(new GridLayout(6, 5, 7, 7));
        
        add(gridPanel);
        drawGrid();
        
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
            	String key = e.getKeyCode() == KeyEvent.VK_BACK_SPACE ? "back" : ""+e.getKeyChar();
                board.guess(key);
                drawGrid();
            }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
        });
	}
	
	public void drawGrid() {
		gridPanel.removeAll();
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				JPanel panel = new JPanel();
				panel.setLayout(new GridBagLayout());
				JLabel text = new JLabel(""+board.getBoard()[i][j]);
				
				panel.setBackground(new Color(255, 255, 255));
				
				if (i == board.getGuessNum() && j == board.getColNum()) {
					panel.setBackground(new Color(243, 159, 147));
				}
				
				if (board.getBoard()[i][j].charAt(0) == 'g') {
					panel.setBackground(new Color(92, 159, 94));
					text = new JLabel(""+board.getBoard()[i][j].charAt(1));
					text.setForeground(Color.WHITE);
				}
				else if (board.getBoard()[i][j].charAt(0) == 'y') {
					panel.setBackground(new Color(195, 173, 82));
					text = new JLabel(""+board.getBoard()[i][j].charAt(1));
					text.setForeground(Color.WHITE);
				}
				else if (board.getBoard()[i][j].charAt(0) == 'n') {
					panel.setBackground(new Color(121, 124, 127));
					text = new JLabel(""+board.getBoard()[i][j].charAt(1));
					text.setForeground(Color.WHITE);
				}
				else {
					text = new JLabel(board.getBoard()[i][j]);
					text.setForeground(Color.BLACK);
				}
				
				
				text.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
				
				
				panel.add(text);
				
				gridPanel.add(panel);
			}
		}
		gridPanel.revalidate();
		gridPanel.repaint();
	}
}

