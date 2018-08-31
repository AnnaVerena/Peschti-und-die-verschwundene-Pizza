package event;
import game.Game;
import javafx.scene.canvas.GraphicsContext;

public class Teleport extends GameEvent{

	boolean finished = false;
	int mapID, x, y, dir;
	
	public Teleport(int mapID, int x, int y, int dir) {
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
		
		GameEvent tmp = Game.eventStack.pop();
		Game.eventStack.clear();
		
		Game.startEvent( new MapMode( Game.maps.get( mapID ) ));
		
		Game.eventStack.push(tmp);
		
		finished = true;
	}
	
	public void update() {
		
	}
	
	public void draw( GraphicsContext gc ){
		
	}
	
	public boolean isFinished() {
		return finished;
	}
}
