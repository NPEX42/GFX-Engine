package uk.co.nerdprogramming.gfx.engine.input;

import org.lwjgl.glfw.GLFW;

import uk.co.nerdprogramming.gfx.engine.api.GFX;
import uk.co.nerdprogramming.gfx.engine.events.EventSystem;

public class Mouse {
	static {
		EventSystem.RegisterMouseButtonCallback(Mouse::OnMouseButton);
		EventSystem.RegisterMouseMovedCallback(Mouse::OnMouseMoved);
	}
	
	private static double mx, my;
	private static boolean[] btns = new boolean[16];
	
	public static void OnMouseMoved(double x, double y) {
		mx = x;
		my = y;
	}
	
	public static void OnMouseButton(int button, int action, int mods) {
		if(action == GLFW.GLFW_PRESS) btns[button] = true;
		if(action == GLFW.GLFW_RELEASE) btns[button] = false;
	}
	
	public static boolean IsButtonDown(int btn) {
		return btns[btn];
	}
	
	public static double GetX() {
		return mx;
	}
	
	public static double GetY() {
		return my;// * 1 + GFX.GetHeight() / 4;
	}
}
