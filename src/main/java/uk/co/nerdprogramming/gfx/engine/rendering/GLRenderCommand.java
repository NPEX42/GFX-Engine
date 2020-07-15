package uk.co.nerdprogramming.gfx.engine.rendering;
import static org.lwjgl.opengl.GL11.*;
public class GLRenderCommand {
	public static void ClearColor(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);
		glClear(GL_COLOR_BUFFER_BIT);
	} 
	
	public static void Draw(GLVertexArray meshID, int vertexCount) {
		meshID.Bind();
		glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
	}
}
