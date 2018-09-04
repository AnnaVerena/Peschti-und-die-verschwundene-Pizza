package event;

import game.Game;

public class TurnEvent extends GameEvent {
	
	MapEvent me;
	String eventID;
	boolean finished;
	int dir;
	
	public TurnEvent( String eventID, int dir ) {
		this.eventID = eventID;
		this.dir = dir;
	}
	
	public void init() {
		me = Game.map.getMapEvent(eventID);
		me.setDirection(dir);
		finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
}