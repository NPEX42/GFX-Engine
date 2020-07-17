package uk.co.nerdprogramming.gfx.engine.ui.components;

import java.awt.Color;
import uk.co.nerdprogramming.gfx.engine.rendering.Renderer2D;

public class UIButton extends UIElement {
	
	Color highlight, def, current;
	
	public UIButton(RectBounds bounds, String iD) {
		super(bounds, iD);
		highlight = new Color(127,127,127);
		def = new Color(65, 65, 65);
		
		current = def;
	}
	
	public UIButton(String ID, int x, int y, int w, int h) {
		this(new RectBounds(x, y, w, h), ID);
	}

	@Override
	public void Draw() {
		Renderer2D.DrawQuad(bounds.x, bounds.y, bounds.w, bounds.h, current);
	}

	@Override
	public void OnClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnHover() {
		// TODO Auto-generated method stub
		current = highlight;
	}

	@Override
	public void OnUnHover() {
		current = def;
	}

}
