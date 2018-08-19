package events;

import javafx.scene.canvas.GraphicsContext;

public class EventList extends GameEvent{
	GameEvent[] events;
	int stage;
	int length;
	boolean finished;
	
	public EventList(GameEvent... events)
	{
		this.events = events;
		length = events.length;
	}
	public void init() {
		stage = 0;
		finished = false;
		for(GameEvent event : events)
		{
			event.init();
		}
	}
	
	public void update() {
		if( stage >= length ) finished = true;		
		if( !finished )
		{
			if( events[stage].isFinished() ) stage++;
			else events[stage].update();
		}
	}
	
	public void draw( GraphicsContext gc ){
		if( length == 0 ) return;
		else if( stage >= length ) events[length-1].draw(gc);
		else events[stage].draw(gc);
	}
	
	public boolean isFinished() {
		return finished;
	}
}