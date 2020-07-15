package uk.co.nerdprogramming.gfx.engine.api;

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
		uvs = GLVertexBuffer.Create(1, new float[2], 2, 0, 0);
		normals = GLVertexBuffer.Create(2, new float[3], 3, 0, 0);
		color = GLVertexBuffer.Create(3, new float[3], 3, 0, 0);
		texID = GLVertexBuffer.Create(1, new float[1], 1, 0, 0);
		tris = GLIndexBuffer.Create(triangles);
	}
	
	public Mesh(float[] positions, float[] texCoords, int[] triangles) {
		vao = GLVertexArray.Create();
		pos = GLVertexBuffer.Create(0, positions, 3, 0, 0);
		uvs = GLVertexBuffer.Create(1, texCoords, 2, 0, 0);
		normals = GLVertexBuffer.Create(2, new float[3], 3, 0, 0);
		color = GLVertexBuffer.Create(3, new float[3], 3, 0, 0);
		texID = GLVertexBuffer.Create(1, new float[1], 1, 0, 0);
		tris = GLIndexBuffer.Create(triangles);
	}
	
	public int GetVertexCount() {return tris.GetVertexCount();}
	public GLVertexArray GetVAO() {return vao;}
	
	public void Destruct() {
		vao.Delete();
		pos.Delete();
		uvs.Delete();
		normals.Delete();
		color.Delete();
		texID.Delete();
		tris.Delete();
	}
}
