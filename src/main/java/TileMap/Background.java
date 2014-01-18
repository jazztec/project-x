package TileMap;


import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;

	private int width;
	private int height;

	private double moveScale;

	public Background(String s, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			width = image.getWidth();
			height = image.getHeight();
			moveScale = ms;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update() {
		x += dx;
		y += dy;
		fixPosition();
	}
	public void setPosition(double x , double y) {
		this.x = (x * moveScale);
		this.y = (y * moveScale);
		fixPosition();
	}

	private void fixPosition() {
		while(x <= -width) x += width;
		while(x >= width) x -= width;
		while(y <= -height) y += height;
		while(y >= height) y -= height;
	}

	public void setVector(double dx, double dy) {
		this.dx +=dx;
		this.dy +=dy;
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int)y, null);
		if(x < 0) {
			g.drawImage(image, (int) x + GamePanel.WIDTH,
					(int) y,
					null
			);
		}
		if (x > 0) {
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
		}
	}
}
