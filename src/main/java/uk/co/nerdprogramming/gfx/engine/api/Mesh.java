package uk.co.nerdprogramming.gfx.engine.api;

import java.util.Arrays;

import uk.co.nerdprogramming.gfx.engine.rendering.GLIndexBuffer;
import uk.co.nerdprogramming.gfx.engine.rendering.GLVertexArray;
import uk.co.nerdprogramming.gfx.engine.rendering.GLVertexBuffer;

public class Mesh {
	private GLVertexArray vao;
	private GLVertexBuffer pos, uvs, normals, color, texID;
	private GLIndexBuffer tris;
	
	public Mesh(float[] positions, int[] triangles) {
		vao = GLVertexArray.Create();
		pos = GLVertexBuffer.Create(0, positions, 3, 0, 0);
		tris = GLIndexBuffer.Create(triangles);
	}
	
	public Mesh(float[] positions, float[] texCoords, int[] triangles) {
		vao = GLVertexArray.Create();
		pos = GLVertexBuffer.Create(0, positions, 3, 0, 0);
		uvs = GLVertexBuffer.Create(1, texCoords, 2, 0, 0);
		tris = GLIndexBuffer.Create(triangles);
	}
	
	public int GetVertexCount() {return tris.GetVertexCount();}
	public GLVertexArray GetVAO() {return vao;}
	
	public void Destruct() {
		vao.Delete();
		if(pos != null) pos.Delete();
		if(uvs != null) uvs.Delete();
		if(normals != null) normals.Delete();
		if(color != null) color.Delete();
		if(texID != null) texID.Delete();
		if(tris != null) tris.Delete();
	}
	
	//Factory Methods
	public static Mesh CreateUnitQuad(float x, float y, float w, float h) {
		float[] pos = {
				x+w/2, y-h/2, 0,
				x-w/2, y-h/2, 0,
				x-w/2, y+h/2, 0,
				x+w/2, y+h/2, 0
		};
		
		float[] uvs = {
				0.0f, 1.0f,
				1.0f, 1.0f,
				1.0f, 0.0f,
				0.0f, 0.0f,
		};
		
		int[] tris = {0,1,2,2,3,0};
		
		System.err.println(Arrays.toString(pos));
		
		return new Mesh(pos, uvs, tris);
	}
}
