import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Textbox extends GameEvent{
	
	String text;
	boolean finished = false;

	public Textbox(String text) {
		this.text = text;
	}
	
	public void update() {
		if(Game.inputs.contains("ENTER"))
			finished = true;
		
	}
	
	public void draw( GraphicsContext gc ){
		gc.drawImage(Game.textboxTileset, 0, 0, 32, 32, 3*16*2, 11*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 0, 32, 32, 32, 3*16*2, 12*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 0, 32, 32, 32, 3*16*2, 13*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 0, 64, 32, 32, 3*16*2, 14*16*2, 32, 32  );
		
		for( int x = 4; x < 16; x++ ) {
			gc.drawImage(Game.textboxTileset, 32, 0, 32, 32, x*16*2, 11*16*2, 32, 32  );
			gc.drawImage(Game.textboxTileset, 32, 32, 32, 32, x*16*2, 12*16*2, 32, 32  );
			gc.drawImage(Game.textboxTileset, 32, 32, 32, 32, x*16*2, 13*16*2, 32, 32  );
			gc.drawImage(Game.textboxTileset, 32, 64, 32, 32, x*16*2, 14*16*2, 32, 32  );
		}
		
		gc.drawImage(Game.textboxTileset, 64, 0, 32, 32, 16*16*2, 11*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 64, 32, 32, 32, 16*16*2, 12*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 64, 32, 32, 32, 16*16*2, 13*16*2, 32, 32  );
		gc.drawImage(Game.textboxTileset, 64, 64, 32, 32, 16*16*2, 14*16*2, 32, 32  );
		
		Font font = Font.font( "Times New Roman", 20 );
	    gc.setFont( font );
	    gc.fillText( text, 4*32, 12*32 );
		
	}
	
	public boolean isFinished() {
		return finished;
	}

	
	
}
