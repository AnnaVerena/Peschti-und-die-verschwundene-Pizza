package game;

import org.joml.Matrix4f;

public class Camera {
	
	public int camX, camY;
	public Matrix4f view;
	
	public Camera( int camX, int camY )
	{
		this.camX = camX;
		this.camY = camY;
		
		view = new Matrix4f().translate( camX, camY, 0 );
	}

	public void setPos( int x, int y ) {
		camX = x;
		camY = y;
		
		view.identity().translate( camX, camY, 0 );
	}
}
