package event;
import game.Game;

public class Teleport extends GameEvent{

	boolean finished = false;
	String mapID;
	int x, y, dir;
	
	public Teleport(String mapID, int x, int y, int dir) {
		this.mapID = mapID;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void init() 
	{		
		Game.player.setX(x);
		Game.player.setY(y);
		Game.player.setDirection(dir);

		Game.eventStack.clear();
		
		Game.map = Game.maps.get(mapID);
		Game.startEvent( new MapMode());
		Game.startEvent(new WaitEvent(5));
		
		finished = true;
	}
	
	public void update() {
		
	}

	public boolean isFinished() {
		return finished;
	}
}
