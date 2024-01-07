package event_system.map_control;

import event_system.GameEvent;
import game.Game;
import util.Direction;

public class MoveEvent extends GameEvent
{
	private String eventID;
	private Direction dir;
	
	public MoveEvent( String eventID, Direction dir )
	{
		this.eventID = eventID;
		this.dir = dir;
	}
	
	public void init() {
		Game.map.getMapEntity(eventID).move(dir);
	}
}
