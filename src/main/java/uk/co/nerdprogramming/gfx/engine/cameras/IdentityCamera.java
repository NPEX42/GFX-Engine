package uk.co.nerdprogramming.gfx.engine.cameras;

import org.joml.Matrix4f;

public class IdentityCamera extends ACamera{

	@Override
	public Matrix4f GetProjection(int width, int height) {
		return new Matrix4f().identity();
	}
}
