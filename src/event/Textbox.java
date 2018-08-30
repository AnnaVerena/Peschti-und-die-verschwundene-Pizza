package event;

import game.Game;
import game.GameUtil;
import render.Color;
import render.Renderer;

public class Textbox extends GameEvent{
	
	String text;
	boolean finished = false;

	public Textbox(String text) {
		this.text = text;
	}
	
	public void init() {
		finished = false;
	}
	
	public void update() {
		if(Game.inputs.contains("A"))
		{
			finished = true;
			Game.inputs.remove("A");
		}		
	}
	
	public void render(){
		Renderer.setView(Renderer.MAT4_IDENTITY);		
		GameUtil.renderTextbox(2, 11, 16, 4);
		
		Renderer.renderText(2*16+8, 11*16+5, text, Color.BLACK);
		
	}
	
	public boolean isFinished() {
		return finished;
	}

	
	
}
