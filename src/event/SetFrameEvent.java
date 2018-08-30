package event;

import javafx.scene.canvas.GraphicsContext;

public class SetFrameEvent extends GameEvent {
	
	MapEvent me;
	boolean finished;
	int frame;
	
	public SetFrameEvent( MapEvent me, int frame ) {
		this.me = me;
		this.frame = frame;
	}
	
	public void init() {
		finished = false;
	}
	
	public void update() {
		me.setFrame(frame);
		finished = true;
	}
	
	public void draw( GraphicsContext gc ){
		
	}
	
	public boolean isFinished() {
		return finished;
	}
}