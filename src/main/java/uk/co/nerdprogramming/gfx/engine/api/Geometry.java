package uk.co.nerdprogramming.gfx.engine.api;

import uk.co.nerdprogramming.gfx.engine.shaders.GLSLShader;

public class Geometry {
	private Mesh mesh;
	private GLSLShader shader;
	public Geometry(Mesh mesh, GLSLShader shader) {
		super();
		this.mesh = mesh;
		this.shader = shader;
	}
	public Mesh getMesh() {
		return mesh;
	}
	public GLSLShader getShader() {
		return shader;
	}
	
	public void Destruct() {
		mesh.Destruct();
	}
}
