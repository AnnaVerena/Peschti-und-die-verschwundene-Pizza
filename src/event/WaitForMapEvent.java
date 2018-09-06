package event;

import game.Game;

public class WaitForMapEvent extends GameEvent
{
	private GameEvent event;
	private String eventID;

	public WaitForMapEvent( String eventID )
	{
		this.eventID = eventID;
	}
	
	public void init()
	{
		event = Game.map.getMapEvent(eventID);
	}
	
	public void update()
	{
	}
	
	public boolean isFinished()
	{
		return event.isFinished();
	}
}
