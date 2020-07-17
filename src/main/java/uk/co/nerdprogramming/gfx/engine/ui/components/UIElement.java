package uk.co.nerdprogramming.gfx.engine.ui.components;

import org.lwjgl.glfw.GLFW;

import uk.co.nerdprogramming.gfx.engine.events.EventSystem;
import uk.co.nerdprogramming.gfx.engine.input.Mouse;

public abstract class UIElement {
	protected RectBounds bounds;
	protected String ID;
	
	public UIElement(RectBounds bounds, String iD) {
		super();
		this.bounds = bounds;
		ID = iD;
	}

	public abstract void Draw();
	
	public void Update() {
		Draw();
		if(bounds.PointInBounds(Mouse.GetX(), Mouse.GetY())) {
			OnHover();
			EventSystem.DispatchUIElementHoveredEvent(ID);
		} else {
			OnUnHover();
		}
		
		if(bounds.PointInBounds(Mouse.GetX(), Mouse.GetY()) && Mouse.IsButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			OnClick();
			EventSystem.DispatchUIElementHoveredEvent(ID);
		} 
	}
	
	public abstract void OnClick();
	public abstract void OnHover();
	public abstract void OnUnHover();
}
