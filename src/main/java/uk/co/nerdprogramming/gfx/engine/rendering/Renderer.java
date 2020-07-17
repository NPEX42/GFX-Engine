package uk.co.nerdprogramming.gfx.engine.rendering;
import java.util.ArrayList;

import uk.co.nerdprogramming.gfx.engine.api.Geometry;


public class Renderer {
	public static int RENDER_QUEUE_LIMIT = 256;
	private static ArrayList<Geometry> renderQueue = new ArrayList<Geometry>();
	public static void Submit(Geometry mesh) {
		renderQueue.add(mesh);
		Flush();
	}
	public static void Flush() {
		for(Geometry mesh : renderQueue) {
			mesh.getShader().Bind();
			GLRenderCommand.Draw(mesh.getMesh().GetVAO(), mesh.getMesh().GetVertexCount());
		}
		renderQueue.clear();
	}
}
