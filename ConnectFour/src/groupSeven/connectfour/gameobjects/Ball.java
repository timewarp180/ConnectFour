package groupSeven.connectfour.gameobjects;

import groupSeven.connectfour.ConnectFour;
import groupSeven.connectfour.game.Game;
import groupSeven.connectfour.graphics.Renderer;
import groupSeven.connectfour.graphics.Sprite;

public class Ball {
	
	public double x,y;
	public int width, height;
	public Sprite sprite;
	private double yVel = 0.0;
	private double gravity = 3.0;
	private int bounceCounter = 0;
	public boolean inPosition = false;
	
	public Ball(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.width = sprite.width;
		this.sprite = sprite;
		this.height = sprite.height;
		
		
	}
	
	
	public void update() {
		if(!inPosition) {
			yVel +=gravity;
			y += yVel;		
		}
		if(checkCollision()) {
			yVel *= -0.25;
			bounceCounter++;
			y= Math.floor(y/100) *100;
			
			if(bounceCounter ==3) {
				inPosition = true;
				yVel = 0;
			}
			
		}
		
	}
	
	
	private boolean checkCollision() {
		// TODO Auto-generated method stub
		if(y + height >= ConnectFour.HEIGHT) {
			return true;
			
		}
		for(int i=0; i<Game.balls.size(); i++) {
			if (this == Game.balls.get(i)) continue;
			if(Game.balls.get(i).x == this.x && this.y + this.height >= Game.balls.get(i).y) {
				return true;
				
			}
			
		}
		
		return false;
	}


	public void render() {
		Renderer.renderSprite(sprite, (int) x, (int) y);
		
		
	}
	
}
