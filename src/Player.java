import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends MapEvent {
	
	GameEvent animation;
	boolean idle;

	/**
	 * Player erbt von MapEvent
	 * @param posx x-Position
	 * @param posy y-Position
	 * @param dir Richtung
	 * @param charset Bilddatei
	 */
	
	public Player(int posx, int posy, int dir, Image charset) {
		super(posx, posy, dir, charset);
		
		animation = CharacterAnimation.createStandingAnimation(posx, posy, dir, charset);
		idle = true;
	}
	
	public void tryToMove( int dir ) {
		this.dir = dir;
		
		if(Game.inputs.contains("RIGHT") && !this.isBlocked(this.getX()+1, this.getY(), Game.map, Game.map.mapEvents)) move(RIGHT);
    	else if(Game.inputs.contains("LEFT") && !this.isBlocked(this.getX()-1, this.getY(), Game.map, Game.map.mapEvents)) move(LEFT);
    	else if(Game.inputs.contains("UP") && !this.isBlocked(this.getX(), this.getY()-1, Game.map, Game.map.mapEvents)) move(UP);
    	else if(Game.inputs.contains("DOWN") && !this.isBlocked(this.getX(), this.getY()+1, Game.map, Game.map.mapEvents)) move(DOWN);
	}
	
	public void move( int dir ) {
		if( dir == UP ) posy--;
		if( dir == DOWN ) posy++;
		if( dir == LEFT ) posx--;
		if( dir == RIGHT ) posx++;
		
		this.dir = dir;
		animation = CharacterAnimation.createWalkingAnimation(posx, posy, dir, charSet);
		idle = false;
	}

	public void update() {
		if( animation.isFinished() ) {
			idle = true;
			animation = CharacterAnimation.createStandingAnimation(posx , posy, dir, charSet);
		}
		
		if( !idle ) animation.update();
    	else if(Game.inputs.contains("RIGHT")) tryToMove(RIGHT);
    	else if(Game.inputs.contains("LEFT")) tryToMove(LEFT);
    	else if(Game.inputs.contains("UP")) tryToMove(UP);
    	else if(Game.inputs.contains("DOWN")) tryToMove(DOWN);
	}
	
	public void draw( GraphicsContext gc) {
		animation.draw(gc);
	}
	
	
}
