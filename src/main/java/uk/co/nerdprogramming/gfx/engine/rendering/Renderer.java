package uk.co.nerdprogramming.gfx.engine.rendering;
import java.util.ArrayList;
import uk.co.nerdprogramming.gfx.engine.api.Pair;
public class Renderer {
	public static int RENDER_QUEUE_LIMIT = 256;
	private static ArrayList<Pair<GLVertexArray, Integer>> renderQueue = new ArrayList<Pair<GLVertexArray, Integer>>();
	
	public static void Submit(GLVertexArray vao, int vertexCount) {
		renderQueue.add(new Pair<GLVertexArray, Integer>(vao, vertexCount));
	}
	
	public static void Flush() {
		for(Pair<GLVertexArray, Integer> pair : renderQueue) {
			GLRenderCommand.Draw(pair.first, pair.second);
		}
	}
}
