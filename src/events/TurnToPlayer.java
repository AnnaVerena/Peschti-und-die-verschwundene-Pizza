package events;

import game.Game;
import javafx.scene.canvas.GraphicsContext;

public class TurnToPlayer extends GameEvent {
	
	MapEvent me;
	boolean finished;
	
	public TurnToPlayer( MapEvent me ) {
		this.me = me;
	}
	
	public void init() {
		finished = false;
	}
	
	public void update() {
		if( Game.player.getDirection() == Game.UP ) me.setDirection(Game.DOWN);
		if( Game.player.getDirection() == Game.DOWN ) me.setDirection(Game.UP);
		if( Game.player.getDirection() == Game.LEFT ) me.setDirection(Game.RIGHT);
		if( Game.player.getDirection() == Game.RIGHT ) me.setDirection(Game.LEFT);
		finished = true;
	}
	
	public void draw( GraphicsContext gc ){
		
	}
	
	public boolean isFinished() {
		return finished;
	}
}
