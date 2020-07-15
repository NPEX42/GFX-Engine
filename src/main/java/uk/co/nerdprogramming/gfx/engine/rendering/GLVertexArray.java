package uk.co.nerdprogramming.gfx.engine.rendering;

import static org.lwjgl.opengl.GL46.*;


public class GLVertexArray {
	private int ID;
	
	public static GLVertexArray Create() {
		GLVertexArray va = new GLVertexArray();
		va.Bind();
		return va;
	}

	private GLVertexArray() {
		ID = glGenVertexArrays();
	}
	
	public void Bind() {
		glBindVertexArray(ID);
	}
	
	public void Delete() {
		glDeleteVertexArrays(ID);
	}
}
