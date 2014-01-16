package TileMap;


import GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;

	private double moveScale;

	public Background(String s, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update(Graphics2D g) {
		x += dx;
		y += dy;
	}
	public void setPosition(double x , double y) {
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}

	public void setVector(double dx, double dy) {
		x +=dx;
		x +=dy;
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int)y, null);
		if(x < 0) {
			g.drawImage(image, (int) x + GamePanel.WIDTH,
					(int) y,
					null
			);
		}
	}
}
