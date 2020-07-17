package uk.co.nerdprogramming.gfx.engine.components;

import java.util.ArrayList;

public class ComponentRegistery {
	private ArrayList<Component> components = new ArrayList<Component>();
	public <T extends Component> ArrayList<T> GetComponents(int type) {
		ArrayList<T> temp = new ArrayList<T>();
		for(Component comp : components) {
			try {
				if(comp.GetComponentType() == type) temp.add((T) comp); 
			} catch(ClassCastException cce) {
				System.err.println("Component 0x"+comp.hashCode()+" (Type: "+comp.componentTypeID+") is not compatible with type #"+type);
			}
		}
		return temp;
	}
	
	public void Add(Component comp) {
		components.add(comp);
	}
}
