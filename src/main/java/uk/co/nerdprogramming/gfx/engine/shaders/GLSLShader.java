package uk.co.nerdprogramming.gfx.engine.shaders;
import static org.lwjgl.opengl.GL46.*;
import java.io.*;
import org.joml.*;
import java.awt.Color;
import uk.co.nerdprogramming.gfx.engine.api.SimpleDB;
import uk.co.nerdprogramming.gfx.engine.textures.GLTexture;
public class GLSLShader {
	private static SimpleDB db = new SimpleDB();
	private int progID, vertID, fragID;
	
	private GLSLShader(int progID, int vertID, int fragID) {
		super();
		this.progID = progID;
		this.vertID = vertID;
		this.fragID = fragID;
	}

	public static GLSLShader Compile(String vertexSource, String fragmentSource) {
		int progID, vertID, fragID;
		progID = glCreateProgram();
		vertID = glCreateShader(GL_VERTEX_SHADER);
		fragID = glCreateShader(GL_FRAGMENT_SHADER);
		
		glShaderSource(vertID, vertexSource);
		glShaderSource(fragID, fragmentSource);
		
		glCompileShader(vertID);
		if(glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Error Compiling Vertex Shader...");
			System.err.println(glGetShaderInfoLog(progID, 1024));
			System.err.println("Source: \n=========\n"+vertexSource+"=========");
		}
		//TODO: Fix Potential False Negative Compile Status.
		if(glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Error Compiling Fragment Shader...");
			System.err.println(glGetShaderInfoLog(fragID, 1024));
			System.err.println("Source: \n=========\n"+fragmentSource+"=========");
		}
		
		glAttachShader(progID, vertID);
		glAttachShader(progID, fragID);
		
		glLinkProgram(progID);
		
		glValidateProgram(progID);
		if(glGetProgrami(progID, GL_LINK_STATUS) == GL_FALSE) {
			System.err.println("Error Linking Shader Pipeline...");
			System.err.println(glGetProgramInfoLog(progID, 1024));
		}
		
		return new GLSLShader(progID, vertID, fragID);
	}
	
	public static GLSLShader LoadShader(String shaderFilePath) {
		try(BufferedReader reader = new BufferedReader(new FileReader(shaderFilePath))) {
			String line = "";
			StringBuffer buffer = new StringBuffer();
			while((line = reader.readLine()) != null) {
				buffer.append(line+"\n");
			}
			
			String[] source = buffer.toString().split("\n");
			
			StringBuffer
			vertBuffer = new StringBuffer(),
			fragBuffer = new StringBuffer();
			int TYPE = 0;
			for(String item : source) {
				switch(item) {
				case "#type vertexShader": TYPE = GL_VERTEX_SHADER; break;
				
				case "#type pixelShader":
				case "#type fragementShader": TYPE = GL_FRAGMENT_SHADER; break;
				
				default:
					switch(TYPE) {
					case GL_VERTEX_SHADER: vertBuffer.append(item + "\n"); break;
					case GL_FRAGMENT_SHADER: fragBuffer.append(item + "\n"); break;
					}
				}
			}
			
			return Compile(vertBuffer.toString(), fragBuffer.toString());
			
		} catch (FileNotFoundException e) {
			System.err.println("[GLSLShader] Unable To Locate File '"+shaderFilePath+"'");
			return null;
		} catch (IOException e) {
			System.err.println("[GLSLShader] An IOException Occurred: ");
			e.printStackTrace();
			return null;
		}
	}
	
	public static GLSLShader LoadShader(InputStream stream) {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			String line = "";
			StringBuffer buffer = new StringBuffer();
			while((line = reader.readLine()) != null) {
				buffer.append(line+"\n");
			}
			
			String[] source = buffer.toString().split("\n");
			
			StringBuffer
			vertBuffer = new StringBuffer(),
			fragBuffer = new StringBuffer();
			int TYPE = 0;
			for(String item : source) {
				switch(item) {
				case "#type vertexShader": TYPE = GL_VERTEX_SHADER; break;
				
				case "#type pixelShader":
				case "#type fragementShader": TYPE = GL_FRAGMENT_SHADER; break;
				
				default:
					switch(TYPE) {
					case GL_VERTEX_SHADER: vertBuffer.append(item + "\n"); break;
					case GL_FRAGMENT_SHADER: fragBuffer.append(item + "\n"); break;
					}
				}
			}
			
			return Compile(vertBuffer.toString(), fragBuffer.toString());
			
		} catch (FileNotFoundException e) {
			System.err.println("[GLSLShader] Unable To Locate File '");
			return null;
		} catch (IOException e) {
			System.err.println("[GLSLShader] An IOException Occurred: ");
			e.printStackTrace();
			return null;
		}
	}
	
	public static GLSLShader LoadShaderJAR(String shaderFilePath) {
		InputStream stream = GLSLShader.class.getClassLoader().getResourceAsStream(shaderFilePath);
		if(stream == null) return null;
		return LoadShader(stream);
	}
	
	public void Bind() {glUseProgram(progID);}
	public void Destruct() {
		glDeleteShader(vertID);
		glDeleteShader(fragID);
		glDeleteProgram(progID);
	}
	
	public void SetupAttribs(String... attribs) {
		for(int i = 0; i < attribs.length; i++) {
			glBindAttribLocation(progID, i, attribs[i]);
		}
	}
	
	private int GetUniform(String name) {
		if(db.ContainsEntry(progID+"."+name)) {
			return db.GetInt(progID+"."+name);
		} else {
			int loc = glGetUniformLocation(progID, name);
			//if(loc == -1) System.err.println("Couldn't Load Uniform '"+name+"'");
			if(loc > -1) db.SetInt(progID+"."+name, loc);
			return loc;
		}
	}
	
	public void UploadFloat1(String name, float x) {
		Bind();
		glUniform1f(GetUniform(name), x);
	}
	
	public void UploadFloat2(String name, Vector2f xy) {
		Bind();
		glUniform2f(GetUniform(name), xy.x, xy.y);
	}
	
	public void UploadFloat3(String name, Vector3f xyz) {
		Bind();
		glUniform3f(GetUniform(name), xyz.x, xyz.y, xyz.z);
	}
	
	public void UploadFloat4(String name, Vector4f xyzw) {
		Bind();
		glUniform4f(GetUniform(name), xyzw.x, xyzw.y, xyzw.z, xyzw.w);
	}
	
	public void UploadMat2(String name, Matrix2f mat) {
		Bind();
		glUniformMatrix2fv(GetUniform(name), false, mat.get(new float[4]));
	}
	
	public void UploadMat3(String name, Matrix3f mat) {
		Bind();
		glUniformMatrix3fv(GetUniform(name), false, mat.get(new float[9]));
	}
	
	public void UploadMat4(String name, Matrix4f mat) {
		Bind();
		glUniformMatrix4fv(GetUniform(name), false, mat.get(new float[16]));
	}
	
	public void UploadBool(String name, boolean b) {
		Bind();
		glUniform1i(GetUniform(name), (b) ? 1 : 0);
	}
	
	public void UploadInt(String name, int i) {
		Bind();
		glUniform1i(GetUniform(name), i);
	}
	
	public void UploadInts(String name, int[] i) {
		Bind();
		glUniform1iv(GetUniform(name), i);
	}
	
	public void UploadFloats(String name, float[] i) {
		Bind();
		glUniform1fv(GetUniform(name), i);
	}
	
	public void UploadColor(String name, Color c) {
		UploadFloat4(name, new Vector4f(
				c.getRed() / 255f,
				c.getGreen() / 255f,
				c.getBlue() / 255f,
				c.getAlpha() / 255f
				));
	}
	
	public void UploadTexture(String name, GLTexture tex, int slot) {
		tex.Bind(slot);
		UploadInt(name, slot);
	}
}
