package event_system.map_control;

import event_system.GameEvent;
import event_system.map_entities.MapEntity;
import game.Game;
import game.GameUtil;

public class TurnToPlayer extends GameEvent {
	
	MapEntity me;
	String eventID;
	boolean finished;
	
	public TurnToPlayer( String eventID ) {
		this.eventID = eventID;
	}
	
	public void init() {
		me = Game.map.getMapEntity(eventID);
		finished = true;
		
		int dx = me.getX() - Game.player.getX();
		int dy = me.getY() - Game.player.getY();
		
		if( Math.abs(dy) > Math.abs(dx) )
		{
			if( dy < 0 ) me.setDirection(GameUtil.DOWN);
			else me.setDirection(GameUtil.UP);
		}
		else
		{
			if( dx < 0 ) me.setDirection(GameUtil.RIGHT);
			else me.setDirection(GameUtil.LEFT);
		}
	}
	
	public boolean isFinished() {
		return finished;
	}
}
