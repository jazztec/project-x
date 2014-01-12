import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
	public static final int BUFFER = 3;
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 3;
	public static final String NAME = "Game";

	private JFrame frame;

	public boolean running = false;
	public int tickCount = 0;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Screen screen;
	public InputHandler input;

	public Game(){
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void init() {
		screen = new Screen(WIDTH,HEIGHT,new SpriteSheet("/Grass_Tile_1.png"));
		input = new InputHandler(this);
	}
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
	}

	public static void main(String[] args){
		new Game().start();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();

		while(running){
			long now = System.nanoTime();
			delta +=(now - lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (delta >= 1){
				ticks++;
				tick();
				delta-=1;
				shouldRender = true;
			}


			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(shouldRender){
			frames++;
			render();
			}

			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				System.out.println("frames = "+frames+".  Ticks = "+ticks);
				frames = 0;
				ticks = 0;
			}

		}
	}
	public void tick(){
		tickCount++;

		if (input.up.isPressed()) {
			screen.yOffset--;
		}
		if (input.down.isPressed()) {
			screen.yOffset++;
		}
		if (input.left.isPressed()) {
			screen.xOffset--;
		}
		if (input.right.isPressed()) {
			screen.xOffset++;
		}
	}
	public void render(){
		BufferStrategy bufferStrategy= getBufferStrategy();
		if(bufferStrategy == null){
			createBufferStrategy(BUFFER);
			return;
		}
		screen.render(pixels,0,WIDTH);
		Graphics g = bufferStrategy.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(image,0,0,getWidth(),getHeight(),null);

		g.dispose();
		bufferStrategy.show();
	}
}