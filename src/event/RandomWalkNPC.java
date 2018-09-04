package event;

import game.GameUtil;

public class RandomWalkNPC extends MapEvent{

	public RandomWalkNPC(String eventID, int posx, int posy, int dir, String charSetID, boolean belowPlayer, GameEvent actionEvent, GameEvent touchEvent) {
		super(eventID, posx, posy, dir, charSetID, belowPlayer, actionEvent, touchEvent);
	}
	
	public RandomWalkNPC(String eventID, int posx, int posy, int dir, String charSetID) {
		super( eventID, posx, posy, dir, charSetID);
	}

	public void update()
	{
		super.update();
		
		if( isFinished() )
		{
			double r = Math.random();
			if( r < 0.005 ) tryToMove( GameUtil.UP );
			else if(r < 0.01 ) tryToMove( GameUtil.DOWN );
			else if(r < 0.015 ) tryToMove( GameUtil.LEFT );
			else if(r < 0.02 ) tryToMove( GameUtil.RIGHT );
		}
	}
}
