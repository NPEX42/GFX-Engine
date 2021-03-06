package uk.co.nerdprogramming.gfx.engine.api;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
public class GFX {
	private static long windowID;
	private static int width, height;
	public static boolean Init(int width, int height, String title) {
		if(!glfwInit()) return false;
		
		GFX.width = width;
		GFX.height = height;
		
		windowID = glfwCreateWindow(width, height, title, NULL, NULL);
		if(windowID == NULL) return false;
		glfwMakeContextCurrent(windowID);
		createCapabilities();
		glfwSwapInterval(1);
		
		glfwSetWindowSizeCallback(windowID, (long win, int w, int h) -> {
			GFX.width = w;
			GFX.height = h;
		});
		
		return true;
	}
	
	public static boolean Update() {
		glfwSwapBuffers(windowID);
		glfwPollEvents();
		glViewport(0,0,width,height);
		return !glfwWindowShouldClose(windowID);
	}
	
	public static void Destruct() {
		glfwDestroyWindow(windowID);
		glfwTerminate();
	}
	
	
	
	public static long GetWindowID() {
		return windowID;
	}

	public static int GetWidth() {
		return width;
	}

	public static int GetHeight() {
		return height;
	}


	public static final int
	NULL = 0;

}
