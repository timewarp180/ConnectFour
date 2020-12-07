package groupSeven.connectfour.game;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import groupSeven.connectfour.ConnectFour;
import groupSeven.connectfour.gameobjects.Ball;
import groupSeven.connectfour.graphics.Renderer;
 import groupSeven.connectfour.graphics.Sprite;
import groupSeven.connectfour.input.Mouse;

public class Game {
	public static List<Ball> balls;
	
	int turn =0;
	public boolean gameOver = false;
	
	
	public Game() {
		
		init();
	}
	
	public void init() {
		balls= new ArrayList<Ball>();
		gameOver = false;
		
		
	}
	
	public void update() {
	/*	if(Mouse.button(MouseEvent.BUTTON1)) {
			System.out.println("Mouse pressed");
		} */
		
		if(gameOver) {
			if(Mouse.buttonUp(MouseEvent.BUTTON1)){
				init();
				
			}
		return;
		}
		for(int i = 0; i< balls.size(); i++) {
			balls.get(i).update();
			
		}
		
		if(Mouse.buttonDown(MouseEvent.BUTTON1)) {
			if(balls.size() == 0 || balls.get(balls.size() - 1).inPosition) {
				int x= (Mouse.getX() / 100) *100;
				boolean valid = true;
				for(int i = 0; i< balls.size(); i++) {
					if(balls.get(i).x ==x && balls.get(i).y == 0) valid = false;
					
				}
				if(valid) {
					if(turn ==0) {
						turn = 1;
						balls.add(new Ball(x, -100, Sprite.ball));
						
					}else {
						turn = 0;
						balls.add(new Ball(x, -100, Sprite.ball2));
					}
				}
			}
			
		}
		checkForGameOver();
	}
	private void checkForGameOver() {
		// TODO Auto-generated method stub
		if(balls.size() <= 6) return;
		Ball b = balls.get(balls.size()-1);
		if(!b.inPosition) return;
		int x= (int) b.x;
		int y = (int) b.y;
		int xAxis = 1, xPlus = 1, xMinus = 1;
		int yAxis = 1, yPlus = 1, yMinus = 1;
		int xyAxis = 1, xyPlus = 1, xyMinus = 1;
		int yxAxis = 1, yxPlus = 1, yxMinus = 1;
		boolean streak = true, changed = false;
		
		
		while(streak) {
			for(int i = 0; i < balls.size(); i++) {
				Ball ball = balls.get(i);
				if(ball.x == x + xPlus * 100 && ball.y == y && samePlayer(ball)) {
					xPlus++;
					xAxis++;
					changed = true;
				}
				if(ball.x == x - xMinus * 100 && ball.y == y && samePlayer(ball)) {
					xMinus++;
					xAxis++;
					changed = true;
				}
				if(ball.y == y + yPlus * 100 && ball.x == x && samePlayer(ball)) {
					yPlus++;
					yAxis++;
					changed = true;
				}
				if(ball.y == y - yMinus * 100 && ball.x == x && samePlayer(ball)) {
					yMinus++;
					yAxis++;
					changed = true;
				}
				
				if(ball.x == x + xyPlus * 100 && ball.y == y + xyPlus * 100 && samePlayer(ball)) {
					xyPlus++;
					xyAxis++;
					changed = true;
				}
				if(ball.x == x - xyMinus * 100 && ball.y == y - xyMinus * 100 && samePlayer(ball)) {
					xyMinus++;
					xyAxis++;
					changed = true;
				}
				if(ball.x == x + yxPlus * 100 && ball.y == y - yxPlus * 100 && samePlayer(ball)) {
					yxPlus++;
					yxAxis++;
					changed = true;
				}
				if(ball.x == x - yxMinus * 100 && ball.y == y + yxMinus * 100 && samePlayer(ball)) {
					yxMinus++;
					yxAxis++;
					changed = true;
				}		
			}
			if(!changed) streak = false;
			else changed = false;
		}
		if(xAxis >= 4 || yAxis >= 4 || xyAxis >= 4 || yxAxis >= 4) gameOver = true;
		
		if(balls.size() >= 42) {
			turn = -1;
			gameOver = true;
		}
	}

	private boolean samePlayer(Ball ball) {
		if(turn == 0 && ball.sprite == Sprite.ball2 || turn == 1 && ball.sprite == Sprite.ball) return true;	

		return false;
	}
	
	public void render() {
		Renderer.renderBackground();
		
		for(int i = 0; i< balls.size(); i++) {
			balls.get(i).render();
			
		}

		Renderer.renderSprite(Sprite.overlay, 0,0);
		
		for(int i = 0; i < ConnectFour.pixels.length; i++) {
			ConnectFour.pixels[i] = Renderer.pixels[i];
			
		}
		
	}
	
	public void renderText(Graphics2D g) {
		
		if(gameOver) {
			
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Ariel", 1, 120));
			String sGameOver = "Game Over";
			String sWinner;
			if(turn == 0 ) sWinner = "Red W0n";
			else if(turn ==1) sWinner = "Green won";
			
			else sWinner = "Its a tie";
			int ig = g.getFontMetrics().stringWidth(sGameOver) / 2;
			g.drawString(sGameOver,  ConnectFour.WIDTH / 2 - ig, 250);
			g.setFont(new Font ("Ariel", 1, 100));
			
			int it = g.getFontMetrics().stringWidth(sWinner) /2;
			g.drawString(sWinner, ConnectFour.WIDTH / 2 - it, 450);
			
		}
	}
}
