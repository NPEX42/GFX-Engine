![Java CI with Maven](https://github.com/NPEX42/GFX-Engine/workflows/Java%20CI%20with%20Maven/badge.svg)
![Nightly Build](https://github.com/NPEX42/GFX-Engine/workflows/Nightly%20Build/badge.svg?event=schedule)

# GFX-Engine

-----

## Installation Instructions (Requires Maven & Git)

### Windows 

```CMD
git clone --recursive https://github.com/NPEX42/GFX-Engine.git
cd GFX-Engine
mvn install
```
### Linux:

```Bash
git clone --recursive https://github.com/NPEX42/GFX-Engine.git
cd GFX-Engine
mvn install
```

----

## Example App

```Java
import java.awt.Color;
import uk.co.nerdprogramming.gfx.engine.api.GFX;
import uk.co.nerdprogramming.gfx.engine.api.Geometry;
import uk.co.nerdprogramming.gfx.engine.api.Mesh;
import uk.co.nerdprogramming.gfx.engine.cameras.*;
import uk.co.nerdprogramming.gfx.engine.rendering.GLRenderCommand;
import uk.co.nerdprogramming.gfx.engine.rendering.Renderer;
import uk.co.nerdprogramming.gfx.engine.shaders.GLSLShader;
import uk.co.nerdprogramming.gfx.engine.textures.GLTexture;

public class SandboxMain {

	public static void main(String[] args) {
		
		if(!GFX.Init(1080, 720, "Sandbox")) System.exit(-1);
		OrthoCamera camera = new OrthoCamera();
		Mesh mesh = Mesh.CreateUnitQuad(GFX.GetWidth() / 2, GFX.GetHeight() / 2, 128, 128);
		GLSLShader shader = GLSLShader.LoadShader("test.glsl");
		//Create A Texture In RGBA Formatting
		GLTexture tex = GLTexture.Load(new int[] {0xFFFFFFFF}, 1, 1, GLTexture.NEAREST, GLTexture.CLAMP);
		Geometry geom = new Geometry(mesh, shader);
		shader.UploadTexture("t_Albedo", tex, 0);
		shader.SetupAttribs("a_Position", "a_UVS");
		shader.UploadColor("u_Tint", Color.white);
		//shader.UploadMat4("u_Proj", camera.GetProjection(GFX.GetWidth(), GFX.GetHeight()));
		while(GFX.Update()) {
			shader.UploadMat4("u_Proj", camera.GetProjection(GFX.GetWidth(), GFX.GetHeight()));
			GLRenderCommand.ClearColor(1, 0, 0, 1);
			Renderer.Submit(geom);
			Renderer.Flush();
		}
		shader.Destruct();
		geom.Destruct();
		GFX.Destruct();
	}

}
```

## test.glsl

```glsl
#type vertexShader
#version 330 core
in vec4 a_Position;
in vec2 a_UVS;
out vec4 v_Position;
out vec2 v_UVS; 
uniform mat4 u_Proj;
void main() {
	gl_Position = u_Proj * a_Position;
	v_Position = a_Position;
	v_UVS = a_UVS;
}
#type pixelShader
#version 330 core
in vec4 v_Position;
in vec2 v_UVS;
out vec4 v_PixelColor;
uniform sampler2D t_Albedo;
uniform vec4 u_Tint;
void main() {
	vec4 texel = texture(t_Albedo,v_UVS);
	v_PixelColor = texel * u_Tint;
}
```

