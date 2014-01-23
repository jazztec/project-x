package resourcess;

import Entity.Animation;
import Main.GamePanel;
import TileMap.Tile;
import TileMap.TileMap;

import java.awt.*;

public abstract class MapObject {
	protected TileMap tileMap;
	protected int tileSize;
	protected double xMap;
	protected double yMap;

	//position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	//dimensions
	protected int width;
	protected int height;

	//collision box
	protected int cWidth;
	protected int cHeight;

	//collision
	protected int currRow;
	protected int currCol;
	protected double xDest;
	protected double yDest;
	protected double xTemp;
	protected double yTemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomRight;
	protected boolean bottomLeft;

	//animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;

	//movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;

	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	//constructor
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}

	public boolean intersects(MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();

		return r1.intersects(r2);
	}

	public Rectangle getRectangle() {
		return new Rectangle((int)x - cWidth, (int)y - cHeight, cWidth, cHeight);
	}

	protected void calculateCorners(double x, double y) {
		int leftTile = (int)(x - cWidth / 2) / tileSize;
		int rightTile = (int)(x + cWidth / 2 - 1) / tileSize;
		int topTile = (int)(y - cHeight / 2) / tileSize;
		int downTile = (int)(y + cHeight / 2 - 1) / tileSize;

		int t1 = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int b1 = tileMap.getType(downTile, leftTile);
		int br = tileMap.getType(downTile, rightTile);

		topLeft = t1 == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		bottomLeft = b1 == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
	}
	public void checkTileMapCollision() {
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;

		xDest = x + dx;
		yDest = y + dy;

		xTemp = x;
		yTemp = y;

		calculateCorners(x, yDest);
		if (dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
				yTemp = currRow * tileSize + cHeight / 2;
			} else {
				yTemp += dy;
			}
		}
		if ( dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				yTemp = (currRow +1) * tileSize - cHeight / 2;
			} else {
				yTemp += dy;
			}
		}
		calculateCorners(xDest, y);
		if (dx < 0) {
			if (topLeft || bottomLeft) {
				dx = 0;
				xTemp = currCol * tileSize + cWidth / 2;
			} else {
				xTemp += dx;
			}
		}
		if (dx > 0) {
			if (topRight || bottomRight) {
				dx = 0;
				xTemp = (currCol + 1) * tileSize - cWidth / 2;
			} else {
				xTemp += dx;
			}
		}
		if (!falling) {
			calculateCorners(x,yDest +1);
			if (!bottomLeft && !bottomRight) {
				falling = true;
			}
		}
	}

	public int getX(){ return (int)x;}
	public int getY() { return (int)y;}
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cWidth; }
	public int getCHeight() { return cHeight; }

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	public void setMapPosition() {
		xMap = tileMap.getx();
		yMap = tileMap.gety();
	}

	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setJumping(boolean b) { jumping = b; }

	public boolean notOnScreen() {
		return x + xMap + width < 0 || x + xMap - width > GamePanel.WIDTH || y + yMap + height < 0 || y + yMap - height > GamePanel.HEIGHT;
	}

}
