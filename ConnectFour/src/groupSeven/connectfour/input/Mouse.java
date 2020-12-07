package groupSeven.connectfour.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import groupSeven.connectfour.ConnectFour;

public class Mouse implements MouseListener, MouseMotionListener{

	
	
	
	private static int x = -1, y = -1;
	private static boolean[] buttons = new boolean[5], lastButtons = new boolean[5];
	
	public static int getX() {
		return x;
		
	}
	public static int getY() {
		return y;
	}
	public void update() {
		
		for(int i=0; i < buttons.length; i++) {
			lastButtons[i] = buttons[i];
			
		}
		
	}
	public static boolean button(int button) {
		
		return buttons[button];
	}
	public static boolean buttonDown(int button) {
		
		return buttons[button] && !lastButtons[button];
	}
	public static boolean buttonUp(int button) {
		
		return buttons[button] && lastButtons[button];		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		x=(int) (e.getX() / ConnectFour.scale);
		y=(int) (e.getY() / ConnectFour.scale);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		x=(int) (e.getX() / ConnectFour.scale);
		y=(int) (e.getY() / ConnectFour.scale);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		buttons[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
