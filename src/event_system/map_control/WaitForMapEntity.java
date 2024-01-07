package event_system.map_control;

import event_system.GameEvent;
import game.Game;

public class WaitForMapEntity extends GameEvent
{
	private GameEvent event;
	private String eventID;

	public WaitForMapEntity(String eventID )
	{
		this.eventID = eventID;
	}
	
	public void init()
	{
		event = Game.map.getMapEntity(eventID);
	}
	
	public void update()
	{
	}
	
	public boolean isFinished()
	{
		return event.isFinished();
	}
}
