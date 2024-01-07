package event_system.map_entities;

import event_system.GameEvent;
import game.Game;
import render.Image;
import render.Renderer;
import util.Direction;

public class MapEntity extends GameEvent {

	protected int posx;
	protected int posy;
	protected int animationTimer;
	
	public boolean belowPlayer = false;
	public GameEvent actionEvent = null;
	public GameEvent touchEvent = null;
	public final String eventID;
	
	// Das charset besteht aus 3x4 Bildern.
	public Image charset;
	Direction dir;
	
	public MapEntity(int posx, int posy, Direction dir, String charSetID ) {
		this("", posx, posy, dir, charSetID, false, null, null);
	}
	
	public MapEntity(String eventID, int posx, int posy, Direction dir, String charSetID ) {
		this(eventID, posx, posy, dir, charSetID, false, null, null);
	}
	
	public MapEntity(int posx, int posy, Direction dir, String charSetID, boolean belowPlayer, GameEvent actionEvent, GameEvent touchEvent ) {
		this("", posx, posy, dir, charSetID, belowPlayer, actionEvent, touchEvent);
	}
	
	public MapEntity(String eventID, int posx, int posy, Direction dir, String charSetID, boolean belowPlayer, GameEvent actionEvent, GameEvent touchEvent ) {
		this.eventID = eventID;
		this.posx = posx;
		this.posy=posy;
		this.charset=Game.charsets.get(charSetID);
		this.dir = dir;
		this.belowPlayer = belowPlayer;
		this.actionEvent = actionEvent;
		this.touchEvent = touchEvent;
	}

	public void render() {
		if(charset == null) return;
		
		if( animationTimer > 0 ) animationTimer--;		
		int x = (animationTimer/4)%4;
		
		int h = charset.height / 4;
		
		if(dir == Direction.RIGHT)
        {
        	if( x == 0 || x == 3 ) Renderer.renderSubImage(charset, 0, dir.getDirectionId()*h, 16, h, posx*16-animationTimer, (posy+1)*16-h, 16, h);
        	else if( x == 1 || x == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2 +1)*16, dir.getDirectionId()*h, 16, h, posx*16-animationTimer, (posy+1)*16-h, 16, h);
        }
        else if(dir == Direction.LEFT)
        {
        	if( x == 0 || x == 3 ) Renderer.renderSubImage(charset, 0, dir.getDirectionId()*h, 16, h, posx*16+animationTimer, (posy+1)*16-h, 16, h);
        	else if( x == 1 || x == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2 +1)*16, dir.getDirectionId()*h, 16, h, posx*16+animationTimer, (posy+1)*16-h, 16, h);
        }
        else if(dir == Direction.DOWN)
        {
        	if( x == 0 || x == 3 ) Renderer.renderSubImage(charset, 0, dir.getDirectionId()*h, 16, h, posx*16, (posy+1)*16-h-animationTimer, 16, h);
        	else if( x == 1 || x == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2+1)*16, dir.getDirectionId()*h, 16, h, posx*16, (posy+1)*16-h-animationTimer, 16, h);
        }
        else if(dir == Direction.UP)
        {
        	if( x == 0 || x == 3 ) Renderer.renderSubImage(charset, 0, dir.getDirectionId()*h, 16, h, posx*16, (posy+1)*16-h+animationTimer, 16, h);
        	else if( x == 1 || x == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2+1)*16, dir.getDirectionId()*h, 16, h, posx*16, (posy+1)*16-h+animationTimer, 16, h);
        }				
	}
	

	
	public void tryToMove( Direction dir ) {
		int x = posx;
		if(dir == Direction.RIGHT) x++;
		if(dir == Direction.LEFT) x--;
		int y = posy;
		if(dir == Direction.UP) y--;
		if(dir == Direction.DOWN) y++;
		
		if( !Game.map.isBlocked(x, y)) move(dir);
	}
	
	public void move( Direction dir ) {
		
		if( dir == Direction.UP ) posy--;
		if( dir == Direction.DOWN ) posy++;
		if( dir == Direction.LEFT ) posx--;
		if( dir == Direction.RIGHT ) posx++;
		
		this.dir = dir;
		animationTimer = 16;
	}
	
	public int getX() {
		return posx;
	}
	
	public void setX(int x) {
		posx=x;
	}
	
	public int getY() {
		return posy;
	}
	
	public void setY(int y) {
		posy=y;
	}

	public Direction getDirection() {
		return dir;
	}
	
	public void setDirection(Direction d) {
		dir = d;
	}
	
	public void init()
	{
		animationTimer = 0;
	}

	public boolean isFinished()
	{
		return animationTimer == 0;
	}
	
}
