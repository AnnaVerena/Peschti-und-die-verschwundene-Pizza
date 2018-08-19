package game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
		gc.drawImage(Game.textboxTileset, 0, 0, 32, 32, 2*16*2, 11*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 0, 32, 32, 32, 2*16*2, 12*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 0, 32, 32, 32, 2*16*2, 13*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 0, 64, 32, 32, 2*16*2, 14*16*2, 32, 32  );
		
		for( int x = 3; x < 17; x++ ) {
			gc.drawImage(Game.textboxTileset, 32, 0, 32, 32, x*16*2, 11*16*2, 32, 32  );
			gc.drawImage(Game.textboxTileset, 32, 32, 32, 32, x*16*2, 12*16*2, 32, 32  );
			gc.drawImage(Game.textboxTileset, 32, 32, 32, 32, x*16*2, 13*16*2, 32, 32  );
			gc.drawImage(Game.textboxTileset, 32, 64, 32, 32, x*16*2, 14*16*2, 32, 32  );
		}
		
		gc.drawImage(Game.textboxTileset, 64, 0, 32, 32, 17*16*2, 11*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 64, 32, 32, 32, 17*16*2, 12*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 64, 32, 32, 32, 17*16*2, 13*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 64, 64, 32, 32, 17*16*2, 14*16*2, 32, 32  );
		
		Font font = Font.loadFont("file:res/OxygenMono-Regular.otf", 20 );
	    gc.setFont( font );
	    gc.setFill(Color.BLACK);
	    gc.fillText( text, 2*32+16 , 12*32  );
		
	}
	
	public boolean isFinished() {
		return finished;
	}

	
	
}
