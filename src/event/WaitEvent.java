package event;

import javafx.scene.canvas.GraphicsContext;

public class WaitEvent extends GameEvent{
	int duration;
	int timer;
	
	public WaitEvent(int duration)
	{
		this.duration = duration;
	}
	
	public void init() {
		timer = duration;
	}
	
	public void update() {
		timer--;
	}
	
	public void draw( GraphicsContext gc ){
		
	}
	
	public boolean isFinished() {
		return timer <= 0;
	}
}