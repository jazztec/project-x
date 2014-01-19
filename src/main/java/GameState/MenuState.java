package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

	private Background bg;
	private int currentChoice = 0;

	private Color titleColour;
	private Font titleFont;

	private Font font;

	private String[] options = {"Start", "Help", "Exit"};

	public MenuState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
		try {
			bg = new Background("/Resources/Backgrounds/menubg.gif",1);
			bg.setVector(-0.1, 0);

			titleColour = new Color(128,0,0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init() {

	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);

		g.setColor(titleColour);
		g.setFont(titleFont);
		//need a function to centralise strings
		g.drawString("Dragon Tale", 80,70);

		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.RED);
			}
			//draw options one after another
			g.drawString(options[i], 145, 140 + i * 15);
		}

	}

	private void select() {
		switch (currentChoice) {
			case 0: gameStateManager.setState(gameStateManager.LEVEL1STATE); break;
			case 1:break;
			case 2:System.exit(0); break;
			default:break;
		}
	}
	@Override
	public void keyPressed(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()){
			case KeyEvent.VK_ENTER:
				select();
				break;
			case KeyEvent.VK_UP:
				if(--currentChoice == -1) {
					currentChoice = options.length -1;
				}
				break;
			case KeyEvent.VK_DOWN:
				if(++currentChoice == options.length) {
					currentChoice = 0;
				}
		}
	}

	public void keyReleased(KeyEvent keyEvent) {

	}
}