package render;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import game.Game;

public class KeyboardHandler extends GLFWKeyCallback
{
	 @Override
	    public void invoke(long window, int key, int scancode, int action, int mods) {
	        if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
	            glfwSetWindowShouldClose(window, true);
	        }
	        else if (key == GLFW.GLFW_KEY_RIGHT && action == GLFW_PRESS) {
	            if(!Game.inputs.contains("RIGHT")) Game.inputs.add("RIGHT");
	        }
	        else if (key == GLFW.GLFW_KEY_RIGHT && action == GLFW.GLFW_RELEASE) {
	            Game.inputs.remove("RIGHT");
	        }
	        else if (key == GLFW.GLFW_KEY_LEFT && action == GLFW_PRESS) {
	            if(!Game.inputs.contains("LEFT")) Game.inputs.add("LEFT");
	        }
	        else if (key == GLFW.GLFW_KEY_LEFT && action == GLFW.GLFW_RELEASE) {
	            Game.inputs.remove("LEFT");
	        }
	        else if (key == GLFW.GLFW_KEY_UP && action == GLFW_PRESS) {
	            if(!Game.inputs.contains("UP")) Game.inputs.add("UP");
	        }
	        else if (key == GLFW.GLFW_KEY_UP && action == GLFW.GLFW_RELEASE) {
	            Game.inputs.remove("UP");
	        }
	        else if (key == GLFW.GLFW_KEY_DOWN && action == GLFW_PRESS) {
	            if(!Game.inputs.contains("DOWN")) Game.inputs.add("DOWN");
	        }
	        else if (key == GLFW.GLFW_KEY_DOWN && action == GLFW.GLFW_RELEASE) {
	            Game.inputs.remove("DOWN");
	        }
	        else if (key == GLFW.GLFW_KEY_ENTER && action == GLFW_PRESS) {
	            if(!Game.inputs.contains("A")) Game.inputs.add("A");
	        }
	        else if (key == GLFW.GLFW_KEY_ENTER && action == GLFW.GLFW_RELEASE) {
	            Game.inputs.remove("A");
	        }
	        else if (key == GLFW.GLFW_KEY_SPACE && action == GLFW_PRESS) {
	            if(!Game.inputs.contains("A")) Game.inputs.add("A");
	        }
	        else if (key == GLFW.GLFW_KEY_SPACE && action == GLFW.GLFW_RELEASE) {
	            Game.inputs.remove("A");
	        }
	    }
}
