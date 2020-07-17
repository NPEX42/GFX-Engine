#type vertexShader
#version 330 core
in vec4 a_Position;
in vec2 a_UVS;
out vec4 v_Position;
out vec2 v_UVS; 
uniform mat4 u_Proj;
uniform mat4 u_Transform;
void main() {
	gl_Position = u_Proj * u_Transform * a_Position;
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