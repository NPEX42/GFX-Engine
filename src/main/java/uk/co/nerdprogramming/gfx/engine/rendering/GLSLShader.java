package uk.co.nerdprogramming.gfx.engine.rendering;
import static org.lwjgl.opengl.GL46.*;

import java.io.*;
public class GLSLShader {
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
			System.err.println(glGetShaderInfoLog(progID));
			System.err.println("Source: \n=========\n"+vertexSource+"=========");
		}
		//TODO: Fix Potential False Negative Compile Status.
		if(glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Error Compiling Fragment Shader...");
			System.err.println(glGetShaderInfoLog(fragID));
			System.err.println("Source: \n=========\n"+fragmentSource+"=========");
		}
		
		glAttachShader(progID, vertID);
		glAttachShader(progID, fragID);
		
		glLinkProgram(progID);
		
		glValidateProgram(progID);
		if(glGetProgrami(progID, GL_LINK_STATUS) == GL_FALSE) {
			System.err.println("Error Linking Shader Pipeline...");
			System.err.println(glGetProgramInfoLog(progID));
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
	
	public void Bind() {glUseProgram(progID);}
	public void Destruct() {
		glDeleteShader(vertID);
		glDeleteShader(fragID);
		glDeleteProgram(progID);
	}
}
