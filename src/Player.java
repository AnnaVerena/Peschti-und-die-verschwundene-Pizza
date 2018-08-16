import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends MapEvent {
	
	GameEvent animation;
	boolean idle;

	public Player(int posx, int posy, int dir, Image charset) {
		super(posx, posy, dir, charset);
		
		animation = CharacterAnimation.createStandingAnimation(posx, posy, dir, charset);
		idle = true;
	}
	
	public void tryToMove( int dir, Map map, ArrayList<MapEvent> chars, ArrayList<String> input ) {
		this.dir = dir;
		
		if(input.contains("RIGHT") && !this.isBlocked(this.getX()+1, this.getY(), map, chars)) move(RIGHT);
    	else if(input.contains("LEFT") && !this.isBlocked(this.getX()-1, this.getY(), map, chars)) move(LEFT);
    	else if(input.contains("UP") && !this.isBlocked(this.getX(), this.getY()-1, map, chars)) move(UP);
    	else if(input.contains("DOWN") && !this.isBlocked(this.getX(), this.getY()+1, map, chars)) move(DOWN);
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

	public void update(Map map, ArrayList<MapEvent> chars, ArrayList<String> input) {
		if( animation.isFinished() ) {
			idle = true;
			animation = CharacterAnimation.createStandingAnimation(posx , posy, dir, charSet);
		}
		
		if( !idle ) animation.update();
    	else if(input.contains("RIGHT")) tryToMove(RIGHT, map, chars, input);
    	else if(input.contains("LEFT")) tryToMove(LEFT, map, chars, input);
    	else if(input.contains("UP")) tryToMove(UP, map, chars, input);
    	else if(input.contains("DOWN")) tryToMove(DOWN, map, chars, input);
	}
	
	public void draw( GraphicsContext gc) {
		animation.draw(gc);
	}
	
	
}
