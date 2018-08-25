package events;
import game.Game;
import game.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;

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
		if(Game.inputs.contains("ENTER"))
		{
			finished = true;
			Game.inputs.remove("ENTER");
		}		
	}
	
	public void draw( GraphicsContext gc ){
		gc.setTransform(new Affine() );
		
		GameUtil.drawTextbox(gc, 2, 11, 16, 4);
		
		Font font = Font.loadFont("file:res/OxygenMono-Regular.otf", 20 );
	    gc.setFont( font );
	    gc.setFill(Color.BLACK);
	    gc.fillText( text, 2*32+16 , 12*32  );
		
	}
	
	public boolean isFinished() {
		return finished;
	}

	
	
}
