package uk.co.nerdprogramming.gfx.engine.textures;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.stb.STBImage.*;
import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryUtil;
public class GLTexture {
	private int texID, width, height;
	
	public GLTexture(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public GLTexture(int texID, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.texID = texID;
	}

	public void SetTextureData(int[] data, int channels) {
		glBindTexture(GL_TEXTURE_2D, texID);
		glInvalidateTexImage(texID, 0);
		glTexImage2D(GL_TEXTURE_2D, 0, channels, width, height, 0, GL_RGBA, GL_UNSIGNED_INT_8_8_8_8, data);
	}
	
	public void SetTextureData(byte[] data, int channels) {
		ByteBuffer buffer = MemoryUtil.memAlloc(data.length);
		buffer.put(data);
		SetTextureData(buffer, channels);
	}
	
	public void SetTextureData(ByteBuffer data, int channels) {
		glBindTexture(GL_TEXTURE_2D, texID);
		glInvalidateTexImage(texID, 0);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, data);
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
	
	public void Bind(int slot) {
		glActiveTexture(GL_TEXTURE0 + slot);
		glBindTexture(GL_TEXTURE_2D, texID);
	}
	
	public static GLTexture Create(int width, int height) {
		GLTexture tex = new GLTexture(width, height);
		tex.Generate();
		tex.SetScaleParameters(NEAREST, NEAREST);
		tex.SetUVWrappingMode(CLAMP, CLAMP);
		tex.SetTextureData(new byte[width * height], 4);
		return tex;
	}
	
	public static GLTexture Load(String filePath, boolean flipY, int scaleMode, int UVMode) {
		
		int[] w = new int[1], h = new int[1];
		int[] channels = new int[1];
		stbi_set_flip_vertically_on_load(flipY);
		ByteBuffer texBuffer = stbi_load(filePath, w, h, channels, 4);
		if(texBuffer == null) return null;
		int texID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, texID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, scaleMode);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, scaleMode);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, UVMode);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, UVMode);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w[0], h[0], 0, GL_RGBA, GL_UNSIGNED_BYTE, texBuffer);
		glBindTexture(GL_TEXTURE_2D, 0);
		return new GLTexture(texID, w[0], h[0]);
	}
	
	public static final int
	NEAREST = GL_NEAREST,
	CLAMP = GL_CLAMP,
	LINEAR = GL_LINEAR,
	UNIT_0 = GL_TEXTURE0;
}
