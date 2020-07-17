package uk.co.nerdprogramming.gfx.engine.cameras;

import org.joml.Matrix4f;
/**
 * 
 * <h2>API Specification</h2>
 * <p>GetProjection(width, height) MUST return a joml Matrix4f(), it MUST NOT return null.</p>
 *
 */
public abstract class ACamera {
	public abstract Matrix4f GetProjection(int width, int height);
}
