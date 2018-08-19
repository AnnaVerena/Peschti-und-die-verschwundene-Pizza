package events;
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
	
	public void init() {
		finished = false;
	}
	
	public void update() {
		Game.map = Game.maps.get(mapID);
		Game.player.setX(x);
		Game.player.setY(y);;
		Game.player.setDirection(dir);
		
		Game.player.animation = CharacterAnimation.createWaitAnimation(x, y, dir, Game.player.charSet);
		Game.player.idle = false;
		
		finished = true;
	}
	
	public void draw( GraphicsContext gc ){
		
	}
	
	public boolean isFinished() {
		return finished;
	}
}
