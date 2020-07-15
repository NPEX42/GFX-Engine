package uk.co.nerdprogramming.gfx.engine.api;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
public class GFX {
	private static long windowID;
	public static boolean Init(int width, int height, String title) {
		if(!glfwInit()) return false;
		windowID = glfwCreateWindow(width, height, title, NULL, NULL);
		if(windowID == NULL) return false;
		glfwMakeContextCurrent(windowID);
		createCapabilities();
		glfwSwapInterval(1);
		return true;
	}
	
	public static boolean Update() {
		glfwSwapBuffers(windowID);
		glfwPollEvents();
		return !glfwWindowShouldClose(windowID);
	}
	
	public static void Destruct() {
		glfwDestroyWindow(windowID);
		glfwTerminate();
	}
	
	public static final int
	NULL = 0;

}
