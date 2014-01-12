import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {
	private final Game game;

	public InputHandler(Game game) {
		this.game = game;
		game.addKeyListener(this);
	}
	public class Key {
		private boolean pressed = false;
		private int numTimesPressed = 0;
		public boolean isPressed() {
			return pressed;
		}
		public int getNumTimesPressed() {
			return numTimesPressed;
		}

		public void toggle(boolean isPressed) {
			pressed = isPressed;
 	        System.out.println("Just interacted with key.  isPressed : "+isPressed);
			if (isPressed)numTimesPressed++;
		}
	}
	public List<Key> keys = new ArrayList<Key>();

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();

	@Override
	public void keyTyped(KeyEvent e) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(),true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(),false);
	}
	public void toggleKey(int keyCode, boolean isPressed) {
		if (keyCode == KeyEvent.VK_W) {
			up.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_S) {
			down.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_A) {
			left.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_D) {
			right.toggle(isPressed);
		}

	}
}
