package event_system.map_control;

import event_system.GameEvent;
import event_system.map_entities.MapEntity;
import game.Game;
import util.Direction;

public class TurnEvent extends GameEvent {
	
	MapEntity me;
	String eventID;
	boolean finished;
	Direction dir;
	
	public TurnEvent( String eventID, Direction dir ) {
		this.eventID = eventID;
		this.dir = dir;
	}
	
	public void init() {
		me = Game.map.getMapEntity(eventID);
		me.setDirection(dir);
		finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
}