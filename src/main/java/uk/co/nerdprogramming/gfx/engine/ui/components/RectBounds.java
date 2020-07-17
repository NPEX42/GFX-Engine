package uk.co.nerdprogramming.gfx.engine.ui.components;

public class RectBounds {
	public int x, y, w, h;
	
	public RectBounds(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean PointInBounds(double px, double py) {
		return 
				 px > x - w/2 && px < x + w/2 &&
				 py > (y - h/2) && py < (y + h/2);
	}
	
	public boolean PointInYBounds(double py) {
		return py > (y - h/2) && py < (y + h/2);
	}
	
	public boolean PointInXBounds(double px) {
		return px > x - w/2 && px < x + w/2;
	}
}
