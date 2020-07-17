package uk.co.nerdprogramming.gfx.engine.cameras;

import org.joml.Matrix4f;

public class PerspectiveCamera extends ACamera {
	public float FOV = 90.0f;
	public PerspectiveCamera(float fOV) {
		super();
		FOV = fOV;
	}
	@Override
	public Matrix4f GetProjection(int width, int height) {
		return new Matrix4f().perspective(FOV, width / height, 0.3f, 1000);
	}

}
