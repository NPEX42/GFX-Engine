package uk.co.nerdprogramming.gfx.engine.ui;

import java.util.ArrayList;

import uk.co.nerdprogramming.gfx.engine.ui.components.UIElement;

public class UIScene {
	private ArrayList<UIElement> elements = new ArrayList<UIElement>();
	
	public void Add(UIElement elem) { elements.add(elem); }
	
	public void Draw() {
		for(UIElement elem : elements) {
			elem.Update();
			elem.Draw();
		}
	}
}
