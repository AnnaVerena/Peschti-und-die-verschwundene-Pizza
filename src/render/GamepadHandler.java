package render;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.glfw.GLFW;
import game.Game;

public class GamepadHandler
{
	// Constants for XBox gamepad
	private static int BUTTON_A = 0;
	private static int BUTTON_B = 1;
	private static int BUTTON_X = 2;
	private static int BUTTON_Y = 3;
	private static int BUTTON_L = 4;
	private static int BUTTON_R = 5;
	private static int BUTTON_BACK = 6;
	private static int BUTTON_START = 7;	
	
	private static int BUTTON_UP = 10;
	private static int BUTTON_RIGHT = 11;
	private static int BUTTON_DOWN = 12;
	private static int BUTTON_LEFT = 13;
	
	private static int AXIS_LH = 0;
	private static int AXIS_LV = 1;
	
	private float t = 0.5f;
		
	private boolean[] isPressed = new boolean[14];
	private int[] axisDir = new int[6];
	
	private int activeJoystick = GLFW.GLFW_JOYSTICK_1;
	
	public void handleInputs()
	{
		if( GLFW.glfwJoystickPresent( activeJoystick ) )
		{
			FloatBuffer axes = GLFW.glfwGetJoystickAxes( activeJoystick );
			ByteBuffer buttons = GLFW.glfwGetJoystickButtons( activeJoystick );
			
			handleButton( BUTTON_A, "A", buttons );
			handleButton( BUTTON_UP, "UP", buttons );
			handleButton( BUTTON_DOWN, "DOWN", buttons );
			handleButton( BUTTON_LEFT, "LEFT", buttons );
			handleButton( BUTTON_RIGHT, "RIGHT", buttons );
			
			handleAxis( AXIS_LH, "LEFT", "RIGHT", axes );
			handleAxis( AXIS_LV, "UP", "DOWN", axes );
		}
		else
		{
			
		}
	}
	
	private void handleButton( int button, String code, ByteBuffer buttons )
	{
		if( buttons.get(button) == 1 && !isPressed[button] )
		{
			isPressed[button] = true;
			if( !Game.inputs.contains(code) ) Game.inputs.add(code);
		}
		else if( buttons.get(button) == 0 && isPressed[button] )
		{
			isPressed[button] = false;
			Game.inputs.remove(code);
		}
	}
	
	private void handleAxis( int axis, String negCode, String posCode, FloatBuffer axes )
	{
		float f = axes.get(axis);
		
		if( axisDir[axis] == -1 && f>-t )
		{
			Game.inputs.remove( negCode );
		}
		if( axisDir[axis] == 1 && f<t )
		{
			Game.inputs.remove( posCode );
		}
		
		if( axisDir[axis] != -1 && f<=-t )
		{
			if( !Game.inputs.contains(negCode) ) Game.inputs.add(negCode);
		}
		if( axisDir[axis] != 1 && f>=t )
		{
			if( !Game.inputs.contains(posCode) ) Game.inputs.add(posCode);
		}
		
		if( f <= -t ) axisDir[axis] = -1;
		else if ( f >= t ) axisDir[axis] = 1;
		else axisDir[axis] = 0;		
	}
}
