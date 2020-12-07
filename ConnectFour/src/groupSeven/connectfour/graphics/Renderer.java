package groupSeven.connectfour.graphics;

import groupSeven.connectfour.ConnectFour;

public class Renderer {
	private static int w = ConnectFour.WIDTH, h = ConnectFour.HEIGHT;
	public static int[] pixels = new int[w*h];
	
	public static void renderBackground() {
		for(int i = 0; i<pixels.length; i++) {
			pixels[i] = 0xff999999;
		}
	}
		
		public static void renderSprite(Sprite s, int xp, int yp) {
			if(xp < -s.width || yp < -s.height || xp >= w || yp >= h) return;
			
			for(int y=0; y < s.height; y++) {
				int yy = y+yp;
				if(yy >= h || yy < 0 ) continue;
				for(int x=0; x < s.width; x++) {
					int xx = x + xp;
					if(xx < 0 || xx >= w) continue;
					int col = s.pixels[x + y * s.width];
					if(col == 0xffff00ff) continue;
					pixels[xx + yy * w] = col;
					
			}
		}
	}
		
}
