package uk.co.nerdprogramming.gfx.engine.components;
import org.joml.*;
public class TransformComponent extends Component {
	
	private Vector3f pos, rot, scale;
	
	public static Vector3f 
	UP = new Vector3f(0,1,0), 
	RIGHT = new Vector3f(1,0,0), 
	FORWARD = new Vector3f(0,0,1),
	X = new Vector3f(1,0,0),
	Y = new Vector3f(0,1,0),
	Z = new Vector3f(0,0,1);
	
	public TransformComponent() {
		componentTypeID = "TRANSFORM".hashCode();
		pos = new Vector3f(0,0,0);
		rot = new Vector3f(0,0,0);
		scale = new Vector3f(1, 1, 1);
	}
	
	public Matrix4f GetTransform() {
		Matrix4f mat = new Matrix4f().identity();
		mat.translate(pos);
		mat.rotate(rot.x, RIGHT);
		mat.rotate(rot.y, UP);
		mat.rotate(rot.z, FORWARD);
		mat.scale(scale);
		return mat;
	}
	
	public void Scale(Vector3f xyz) {
		scale.add(xyz);
	}
	
	public void Translate(Vector3f xyz) {
		pos.add(xyz);
	}
	
	public void Rotate(Vector3f xyz) {
		rot.add(xyz);
	}
	
	public void SetScale(Vector3f xyz) {
		scale = xyz;
	}
	
	public void SetTranslate(Vector3f xyz) {
		pos = xyz;
	}
	
	public void SetRotate(Vector3f xyz) {
		rot = xyz;
	}
}
