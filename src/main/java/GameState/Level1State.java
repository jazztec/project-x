package GameState;

import Entity.Player;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;
	private Player player;

	public Level1State(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
		init();
	}


	@Override
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Resources/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Resources/Maps/level1-1.map");
		tileMap.setPosition(0,0);

		bg = new Background("/Resources/Backgrounds/grassbg1.gif", 0.1);
		player = new Player(tileMap);
		player.setPosition(100,190);

	}

	@Override
	public void update() {
		player.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT /2 - player.gety());

	}

	@Override
	public void draw(Graphics2D g) {
	//clear screen
		bg.draw(g);

		tileMap.draw(g);
		player.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()){
			case KeyEvent.VK_LEFT:
				player.setLeft(true);
				break;
			case KeyEvent.VK_RIGHT:
				player.setRight(true);
				break;
			case KeyEvent.VK_UP:
				player.setUp(true);
				break;
			case KeyEvent.VK_DOWN:
				player.setDown(true);
				break;
			case KeyEvent.VK_SPACE:
				player.setJumping(true);
			case KeyEvent.VK_E:
				player.setGliding(true);
				break;
			case KeyEvent.VK_R:
				player.setScratching(true);
				break;
			case KeyEvent.VK_F:
				player.setFiring(true);
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()){
			case KeyEvent.VK_LEFT:
				player.setLeft(false);
				break;
			case KeyEvent.VK_RIGHT:
				player.setRight(false);
				break;
			case KeyEvent.VK_UP:
				player.setUp(false);
				break;
			case KeyEvent.VK_DOWN:
				player.setDown(false);
				break;
			case KeyEvent.VK_SPACE:
				player.setJumping(false);
			case KeyEvent.VK_E:
				player.setGliding(false);
				break;
			case KeyEvent.VK_R:
				player.setScratching(false);
				break;
			case KeyEvent.VK_F:
				player.setFiring(false);
				break;
			default:
				break;
		}
	}
}
