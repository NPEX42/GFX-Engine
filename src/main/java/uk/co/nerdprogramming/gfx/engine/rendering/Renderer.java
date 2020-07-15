package uk.co.nerdprogramming.gfx.engine.rendering;
import java.util.ArrayList;

import uk.co.nerdprogramming.gfx.engine.api.Mesh;


public class Renderer {
	public static int RENDER_QUEUE_LIMIT = 256;
	private static ArrayList<Mesh> renderQueue = new ArrayList<Mesh>();
	
	public static void Submit(Mesh mesh) {
		renderQueue.add(mesh);
	}
	
	public static void Flush() {
		for(Mesh mesh : renderQueue) {
			GLRenderCommand.Draw(mesh.GetVAO(), mesh.GetVertexCount());
		}
		
		renderQueue.clear();
	}
}
