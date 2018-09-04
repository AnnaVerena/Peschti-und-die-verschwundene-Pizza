package event;

import game.Game;

public class MoveEvent extends GameEvent 
{
	private String eventID;
	private int dir;
	
	public MoveEvent( String eventID, int dir )
	{
		this.eventID = eventID;
		this.dir = dir;
	}
	
	public void init() {
		Game.map.getMapEvent(eventID).move(dir);
	}
}
