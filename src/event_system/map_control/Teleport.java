package event_system.map_control;
import event_system.GameEvent;
import event_system.MapMode;
import event_system.WaitEvent;
import game.Game;

public class Teleport extends GameEvent {

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

        try {
            Game.map = Game.loadMap(mapID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Game.startEvent( new MapMode());
		Game.startEvent(new WaitEvent(5));
		
		finished = true;
	}

	public boolean isFinished() {
		return finished;
	}
}
