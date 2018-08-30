package event;

import javafx.scene.canvas.GraphicsContext;

public class TurnEvent extends GameEvent {
	
	MapEvent me;
	boolean finished;
	int dir;
	
	public TurnEvent( MapEvent me, int dir ) {
		this.me = me;
		this.dir = dir;
	}
	
	public void init() {
		finished = false;
	}
	
	public void update() {
		me.setDirection(dir);
		finished = true;
	}
	
	public void draw( GraphicsContext gc ){
		
	}
	
	public boolean isFinished() {
		return finished;
	}
}