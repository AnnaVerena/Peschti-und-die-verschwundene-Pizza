package events;

import javafx.scene.canvas.GraphicsContext;

public class SetStepEvent extends GameEvent {
	
	MapEvent me;
	boolean finished;
	int step;
	
	public SetStepEvent( MapEvent me, int step ) {
		this.me = me;
		this.step = step;
	}
	
	public void init() {
		finished = false;
	}
	
	public void update() {
		me.setStep(step);
		finished = true;
	}
	
	public void draw( GraphicsContext gc ){
		
	}
	
	public boolean isFinished() {
		return finished;
	}
}