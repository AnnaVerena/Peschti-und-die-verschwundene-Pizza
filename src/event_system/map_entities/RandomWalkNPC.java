package event_system.map_entities;

import event_system.GameEvent;
import util.Direction;

public class RandomWalkNPC extends MapEntity {

	public RandomWalkNPC(String eventID, int posx, int posy, Direction dir, String charSetID, boolean belowPlayer, GameEvent actionEvent, GameEvent touchEvent) {
		super(eventID, posx, posy, dir, charSetID, belowPlayer, actionEvent, touchEvent);
	}
	
	public RandomWalkNPC(String eventID, int posx, int posy, Direction dir, String charSetID) {
		super( eventID, posx, posy, dir, charSetID);
	}

	public void update()
	{
		super.update();
		
		if( isFinished() )
		{
			double r = Math.random();
			if( r < 0.005 ) tryToMove( Direction.UP );
			else if(r < 0.01 ) tryToMove( Direction.DOWN );
			else if(r < 0.015 ) tryToMove( Direction.LEFT );
			else if(r < 0.02 ) tryToMove( Direction.RIGHT );
		}
	}
}
