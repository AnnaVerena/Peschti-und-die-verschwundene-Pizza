package event_system.dialog;

import event_system.GameEvent;
import game.Game;
import game.GameUtil;
import render.Color;
import render.Renderer;

public class DialogBox extends GameEvent {
	
	String text;
	boolean endOfDialog = true;
	boolean finished = false;
	
	private int textSpeed = 5;
	private int textWidth;
	private float totalTextLength;

	public DialogBox(String text) {
		this(text, false);
	}
	
	public DialogBox(String text, boolean endOfDialog) {
		this.text = text;
		this.endOfDialog = endOfDialog;
	}
	
	public void init() {
		finished = false;
		textWidth = 0;
		totalTextLength = Renderer.font.getTextLength(text);
	}
	
	public void update() {
		if( textWidth < totalTextLength ) textWidth += textSpeed;
		
		if(Game.inputs.contains("A"))
		{
			finished = true;
			Game.inputs.remove("A");
		}		
	}
	
	public void render(){
		Renderer.setView(Renderer.MAT4_IDENTITY);		
		GameUtil.renderTextbox(2, 11, 16, 4);
		
		Renderer.renderText(2*16+8, 11*16+5, text, Color.WHITE, textWidth);
		
	}
	
	public boolean isFinished() {
		return finished;
	}

	
	
}