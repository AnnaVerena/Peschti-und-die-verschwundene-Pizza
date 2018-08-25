package events;

import game.Game;
import game.GameUtil;
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
		if( Game.player.getDirection() == GameUtil.UP ) me.setDirection(GameUtil.DOWN);
		if( Game.player.getDirection() == GameUtil.DOWN ) me.setDirection(GameUtil.UP);
		if( Game.player.getDirection() == GameUtil.LEFT ) me.setDirection(GameUtil.RIGHT);
		if( Game.player.getDirection() == GameUtil.RIGHT ) me.setDirection(GameUtil.LEFT);
		finished = true;
	}
	
	public void draw( GraphicsContext gc ){
		
	}
	
	public boolean isFinished() {
		return finished;
	}
}
