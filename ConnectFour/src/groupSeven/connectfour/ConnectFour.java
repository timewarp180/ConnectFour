package groupSeven.connectfour;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import groupSeven.connectfour.game.Game;
import groupSeven.connectfour.input.Mouse;

public class ConnectFour extends Canvas implements Runnable {

	public static final int WIDTH = 700, HEIGHT = 600;
	public static float scale = 1f;
	
	private Mouse mouse;
	private Game game;
	
	private JFrame frame;
	private Thread thread;
	private boolean running = false;
	public static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	
	
	
	public ConnectFour() {
		   setPreferredSize(new Dimension((int) (WIDTH * scale), (int) (HEIGHT * scale)));
	        frame = new JFrame();
	        game = new Game();
	        mouse = new Mouse();
	        addMouseListener(mouse);
	        addMouseMotionListener(mouse);
	}
	public void start() {
		running = true;
        thread = new Thread(this, "loop");
        thread.start();
		
	}

    public void stop(){
        try{
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }

    }
	
	@Override
	public void run(){
        long lastTimeInNanoSeconds = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double nanoSecondsPerUpdate = 1000000000.0/ 60.0;
        double updatesToperform = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();

        while(running){
            long currentTimeInNanoSeconds = System.nanoTime(); 
            updatesToperform += (currentTimeInNanoSeconds - lastTimeInNanoSeconds) / nanoSecondsPerUpdate;
            lastTimeInNanoSeconds = currentTimeInNanoSeconds;
            while(updatesToperform >= 1){
                update();
                updates++;
                updatesToperform--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frame.setTitle("Connect Four " + updates + "updates, " + frames + "frames");
                updates = 0;
                frames= 0;
            }
        }
        stop();
    }

	public void update() {
		
		game.update();
		mouse.update();
	}
	public void render () {
		 BufferStrategy bs = getBufferStrategy();
	        if(bs == null){
	            createBufferStrategy(3);
	            return;
	        }
	        
	        game.render();
	        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

	        g.drawImage(image, 0, 0, (int) (WIDTH * scale), (int) (HEIGHT * scale), null);
	        game.renderText(g);
	        g.dispose();
	        bs.show();
		
	}
	
	public static void main(String[] args) {
		
	      ConnectFour c = new ConnectFour();
	        c.frame.setResizable(false);
	        c.frame.setTitle("Connect Four");
	        c.frame.add(c);
	        c.frame.pack();
	        c.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        c.frame.setVisible(true);
	        c.frame.setLocationRelativeTo(null);
	        c.frame.setAlwaysOnTop(true);
	        c.start();
		
	}

}
