package event_system;

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

	public boolean isFinished() {
		return timer <= 0;
	}
}