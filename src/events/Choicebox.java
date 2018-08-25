package events;
import game.Game;
import game.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;

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
			if(Game.inputs.contains("ENTER"))
			{
				choosen = true;
				yes.init();
				no.init();
				Game.inputs.remove("ENTER");
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
	
	public void draw( GraphicsContext gc ){
		gc.setTransform(new Affine() );
		if( choosen )
		{
			if( choice == true ) yes.draw(gc);
			else no.draw( gc );
		}
		else {
			GameUtil.drawTextbox(gc, 2, 11, 16, 4);			
			GameUtil.drawTextbox(gc, 15, 11, 3, 4);
			
			Font font = Font.loadFont("file:res/OxygenMono-Regular.otf", 20 );
		    gc.setFont( font );
		    gc.setFill(Color.BLACK);
		    gc.fillText( text , 2*32+16 , 12*32  );
		    if(choice == true)
		    	gc.setFill(Color.BLACK);
		    else gc.setFill(Color.GREY);
		    
		    gc.fillText( "\nJa" , 15*32+16 +4 , 12*32  );
		    
		    if(choice == false)
		    	gc.setFill(Color.BLACK);
		    else gc.setFill(Color.GREY);
		    gc.fillText( "\n\nNein" , 15*32+16 +4 , 12*32  );
		}		
	}
	
	public boolean isFinished() {
		return finished;
	}

}
