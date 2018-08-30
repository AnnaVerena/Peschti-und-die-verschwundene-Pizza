package event;

import game.Game;
import game.GameUtil;
import render.Color;
import render.Renderer;

public class Choicebox extends GameEvent {


	String text;
	boolean finished = false;
	GameEvent yes;
	GameEvent no;
	boolean choice = true;
	boolean choosen = false;

	public Choicebox(String text, GameEvent yes, GameEvent no) {
		this.text = text;
		this.yes = yes;
		this.no = no;
	}
	
	public void init() {
		finished = false;
		choice = true;
		choosen = false;
	}
	
	public void update() {
		if( choosen )
		{
			if( choice == true ) {
				yes.update();
				if( yes.isFinished() ) finished = true;
			}
			else {
				no.update();
				if( no.isFinished() ) finished = true;
			}
		}
		else
		{
			if(Game.inputs.contains("A"))
			{
				choosen = true;
				yes.init();
				no.init();
				Game.inputs.remove("A");
			}			
			if(Game.inputs.contains("DOWN"))
			{
				choice = false;
				Game.inputs.remove("DOWN");
			}
			
			if(Game.inputs.contains("UP"))
			{
				choice = true;
				Game.inputs.remove("UP");
			}
		}
		
	}
	
	public void render( ){
		
		Renderer.setView(Renderer.MAT4_IDENTITY);
		
		if( choosen )
		{
			if( choice == true ) yes.render();
			else no.render();
		}
		else {
			GameUtil.renderTextbox( 2, 11, 16, 4);			
			GameUtil.renderTextbox(15, 11, 3, 4);
			
			Renderer.renderText(2*16+8, 11*16+5, text, Color.WHITE);
			Color color = Color.BLACK;
			
			if(choice == true)
		    	Renderer.renderText(15*16+8, 11*16+5, "\nJa", color);
		    else Renderer.renderText(15*16+8, 11*16+5, "\n\nNein", color);
		    
			color = Color.GRAY;
			if(choice == false)
		    	Renderer.renderText(15*16+8, 11*16+5, "\nJa", color);
		    else Renderer.renderText(15*16+8, 11*16+5, "\n\nNein", color);
		}		
	}
	
	public boolean isFinished() {
		return finished;
	}

}
