package uk.co.nerdprogramming.gfx.engine.textures;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.stb.STBImage.*;
import java.nio.ByteBuffer;
public class GLTexture {
	private int texID, width, height;
	
	public GLTexture(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	public GLTexture(int texID, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.texID = texID;
	}

	public void SetTextureData(int[] data) {
		glBindTexture(GL_TEXTURE_2D, texID);
		glInvalidateTexImage(texID, 0);
		glTexImage2D(GL_TEXTURE_2D, 0, 4, width, height, 0, GL_RGBA, GL_UNSIGNED_INT_8_8_8_8, data);
	}
	
	public void SetTextureData(ByteBuffer data) {
		glBindTexture(GL_TEXTURE_2D, texID);
		glInvalidateTexImage(texID, 0);
		glTexImage2D(GL_TEXTURE_2D, 0, 4, width, height, 0, GL_RGBA, GL_BYTE, data);
	}
	
	public void SetScaleParameters(int min, int mag) {
		glBindTexture(GL_TEXTURE_2D, texID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, mag);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, min);
	}
	
	public void SetUVWrappingMode(int u, int v) {
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, u);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, v);
	}
	
	public void Generate() {
		texID = glGenTextures();
	}
	
	public void Bind(int unit) {
		glActiveTexture(unit);
		glBindTexture(GL_TEXTURE_2D, texID);
	}
	
	public static GLTexture Create(int width, int height) {
		GLTexture tex = new GLTexture(width, height);
		tex.Generate();
		tex.SetScaleParameters(NEAREST, NEAREST);
		tex.SetUVWrappingMode(CLAMP, CLAMP);
		tex.SetTextureData(new int[width * height]);
		return tex;
	}
	
	public static GLTexture Load(String filePath) {
		
		int[] w = new int[1], h = new int[1];
		int[] channels = new int[1];
		ByteBuffer texBuffer = stbi_load(filePath, w, h, channels, 4);
		if(texBuffer == null) return null;
		GLTexture tex = new GLTexture(w[0], w[1]);
		tex.Generate();
		tex.SetScaleParameters(NEAREST, NEAREST);
		tex.SetUVWrappingMode(CLAMP, CLAMP);
		tex.SetTextureData(texBuffer);
		return tex;
	}
	
	public static final int
	NEAREST = GL_NEAREST,
	CLAMP = GL_CLAMP_TO_EDGE;
}
