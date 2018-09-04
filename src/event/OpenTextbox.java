package event;

import game.GameUtil;
import render.Color;
import render.Renderer;

public class OpenTextbox extends GameEvent{
	
	boolean finished = false;
	float speed = 0.1f;
	float alpha;
	Color color;
		
	public void init() {
		finished = false;
		alpha = 0;
		
		color = new Color(1,1,1,0);
	}
	
	public void update() {
		if( alpha < 1 ) alpha += speed;
		if( alpha >= 1 ) {
			alpha = 1;
			finished = true;
		}
		color.a = alpha;
	}
	
	public void render(){
		Renderer.setView(Renderer.MAT4_IDENTITY);		
		GameUtil.renderTextbox(2, 11, 16, 4, color);		
	}
	
	public boolean isFinished() {
		return finished;
	}

	
	
}
