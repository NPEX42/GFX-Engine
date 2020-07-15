package uk.co.nerdprogramming.gfx.engine.rendering;

import static org.lwjgl.opengl.GL46.*;

public class GLIndexBuffer {
	private int ID, length;
	
	//glBindBuffer(GL_ELEMENT_ARRAY,ID);
	//glBufferData(GL_ELEMENT_ARRAY,data,GL_STATIC_DRAW)
	
	private GLIndexBuffer() {
		ID = glGenBuffers();
	}
	
	public void Bind(int glTarget) {
		glBindBuffer(glTarget, ID);
	}
	
	public void SetData(int[] data, int glMode, int glTarget) {
		glBufferData(glTarget,data,glMode);
	}
	
	public void Enable(int index) {
		glEnableVertexAttribArray(index);
	}
	
	public static GLIndexBuffer Create(int[] triangles) {
		GLIndexBuffer vb = new GLIndexBuffer();
		vb.Bind(GL_ELEMENT_ARRAY_BUFFER);
		vb.SetData(triangles, GL_STATIC_DRAW, GL_ELEMENT_ARRAY_BUFFER);
		
		vb.length = triangles.length;
		
		return vb;
	}
	
	public void Delete() {
		glDeleteBuffers(ID);
	}
	
	public int GetVertexCount() {return length;}
}
