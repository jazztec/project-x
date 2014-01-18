package GameState;


import java.awt.event.KeyEvent;

public abstract class GameState {

	protected GameStateManager gameStateManager;

	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(KeyEvent keyEvent);
	public abstract void keyReleased(KeyEvent keyEvent);
}
