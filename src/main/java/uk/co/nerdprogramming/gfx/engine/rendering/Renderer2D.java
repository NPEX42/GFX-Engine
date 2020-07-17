package uk.co.nerdprogramming.gfx.engine.rendering;

import java.awt.Color;

import org.joml.Vector3f;

import uk.co.nerdprogramming.gfx.engine.api.GFX;
import uk.co.nerdprogramming.gfx.engine.api.Geometry;
import uk.co.nerdprogramming.gfx.engine.api.Mesh;
import uk.co.nerdprogramming.gfx.engine.cameras.OrthoCamera;
import uk.co.nerdprogramming.gfx.engine.components.TransformComponent;
import uk.co.nerdprogramming.gfx.engine.shaders.GLSLShader;
import uk.co.nerdprogramming.gfx.engine.textures.GLTexture;

public class Renderer2D {
	private static OrthoCamera camera = new OrthoCamera();
	private static TransformComponent transform = new TransformComponent();
	private static GLSLShader shader = GLSLShader.LoadShaderJAR("uk/co/nerdprogramming/gfx/res/2D.glsl");
	private static Geometry quad = new Geometry(
			Mesh.CreateUnitQuad(0, 0, 1, 1),
			shader
	);
	
	static {
		shader.SetupAttribs("a_Positions","a_UVS");
	}
	
	public static void DrawQuad(int x, int y, int w, int h, GLTexture albedo, Color color) {
		transform.SetTranslate(new Vector3f(x, y, 0));
		transform.SetScale(new Vector3f(w, h, 0));
		shader.Bind();
		shader.UploadColor("u_Tint", color);
		shader.UploadTexture("u_Albedo", albedo, 0);
		shader.UploadMat4("u_Proj", camera.GetProjection(GFX.GetWidth(), GFX.GetWidth()));
		shader.UploadMat4("u_Transform", transform.GetTransform());
		Renderer.Submit(quad);
	}
	
	public static void DrawQuad(int x, int y, int w, int h, Color color) {
		DrawQuad(x, y, w, h, GLTexture.white, color);
	}
	
	public static void DrawImage(int x, int y, GLTexture albedo) {
		DrawQuad(x, y, albedo.getWidth(), albedo.getHeight(), albedo, Color.white);
	}

	public static void DrawQuad(double x, double y, double w, double h, Color color) {
		DrawQuad((int)x, (int)y, (int)w, (int)h, color);
	}
	
	
}
