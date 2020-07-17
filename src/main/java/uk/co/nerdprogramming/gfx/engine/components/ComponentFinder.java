package uk.co.nerdprogramming.gfx.engine.components;

public interface ComponentFinder<T extends Component> {
	public T[] invoke(ComponentRegistery reg);
}
