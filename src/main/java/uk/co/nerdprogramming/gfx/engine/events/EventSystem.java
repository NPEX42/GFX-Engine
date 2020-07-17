package uk.co.nerdprogramming.gfx.engine.events;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import uk.co.nerdprogramming.gfx.engine.api.GFX;
import uk.co.nerdprogramming.gfx.engine.ui.events.IElementClicked;
import uk.co.nerdprogramming.gfx.engine.ui.events.IElementHovered;

public class EventSystem {
	private static ArrayList<IMouseButtonEventCallback> mouseBtnCB = new ArrayList<IMouseButtonEventCallback>();
	private static ArrayList<IMouseMovedEventCallback> mousePosCB = new ArrayList<IMouseMovedEventCallback>();
	private static ArrayList<IElementHovered> uiHoverCB = new ArrayList<IElementHovered>();
	private static ArrayList<IElementClicked> uiClickCB = new ArrayList<IElementClicked>();
	
	static {
		GLFW.glfwSetMouseButtonCallback(GFX.GetWindowID(), EventSystem::DispatchMouseButtonEvent);
		GLFW.glfwSetCursorPosCallback(GFX.GetWindowID(), EventSystem::DispatchMouseMovedEvent);
	}
	
	public static void RegisterMouseButtonCallback(IMouseButtonEventCallback cb) {
		mouseBtnCB.add(cb);
	}
	
	public static void RegisterMouseMovedCallback(IMouseMovedEventCallback cb) {
		mousePosCB.add(cb);
	}
	
	public static void DispatchMouseButtonEvent(long window, int btn, int action, int mods) {
		for(IMouseButtonEventCallback cb : mouseBtnCB) {
			cb.invoke(btn, action, mods);
		}
	}
	
	public static void DispatchMouseMovedEvent(long window, double x, double y) {
		for(IMouseMovedEventCallback cb : mousePosCB) {
			cb.invoke(x,y);
		}
	}
	
	public static void DispatchUIElementHoveredEvent(String ID) {
		for(IElementHovered cb : uiHoverCB) {
			cb.invoke(ID);
		}
	}
	
	public static void DispatchUIElementClickedEvent(String ID) {
		for(IElementClicked cb : uiClickCB) {
			cb.invoke(ID);
		}
	}
	
	
}
