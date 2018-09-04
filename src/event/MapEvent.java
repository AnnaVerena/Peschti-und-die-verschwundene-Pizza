package event;

import game.Game;
import game.GameUtil;
import render.Image;
import render.Renderer;

public class MapEvent extends GameEvent{

	protected int posx;
	protected int posy;
	protected int animationTimer;
	
	public boolean belowPlayer = false;
	public GameEvent actionEvent = null;
	public GameEvent touchEvent = null;
	public final String eventID;
	
	// Das charset besteht aus 3x4 Bildern.
	protected Image charset;
	int dir; 		// y-Position im Charset
	
	public MapEvent(int posx, int posy, int dir, String charSetID ) {
		this("", posx, posy, dir, charSetID, false, null, null);
	}
	
	public MapEvent(String eventID, int posx, int posy, int dir, String charSetID ) {
		this(eventID, posx, posy, dir, charSetID, false, null, null);
	}
	
	public MapEvent(int posx, int posy, int dir, String charSetID, boolean belowPlayer, GameEvent actionEvent, GameEvent touchEvent ) {
		this("", posx, posy, dir, charSetID, belowPlayer, actionEvent, touchEvent);
	}
	
	public MapEvent(String eventID, int posx, int posy, int dir, String charSetID, boolean belowPlayer, GameEvent actionEvent, GameEvent touchEvent ) {
		this.eventID = eventID;
		this.posx = posx;
		this.posy=posy;
		this.charset=Game.charsets.get(charSetID);
		this.dir = dir;
		this.belowPlayer = belowPlayer;
		this.actionEvent = actionEvent;
		this.touchEvent = touchEvent;
	}
	
	
	/**
	 * @param name description Bildfile, Blickrichtung, Position
	 * Wir malen die Figur
	 */
	
	public void render() {
		if(charset == null) return;
		
		int x = (animationTimer/4)%4;
		
		if(dir == GameUtil.RIGHT)
        {
        	if( x == 0 || x == 3 ) Renderer.renderSubImage(charset, 0, dir*32, 16, 32, posx*16-animationTimer, (posy-1)*16, 16, 32);
        	else if( x == 1 || x == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2 +1)*16, dir*32, 16, 32, posx*16-animationTimer, (posy-1)*16, 16, 32);
        }
        else if(dir == GameUtil.LEFT)
        {
        	if( x == 0 || x == 3 ) Renderer.renderSubImage(charset, 0, dir*32, 16, 32, posx*16+animationTimer, (posy-1)*16, 16, 32);
        	else if( x == 1 || x == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2 +1)*16, dir*32, 16, 32, posx*16+animationTimer, (posy-1)*16, 16, 32);
        }
        else if(dir == GameUtil.DOWN)
        {
        	if( x == 0 || x == 3 ) Renderer.renderSubImage(charset, 0, dir*32, 16, 32, posx*16, (posy-1)*16-animationTimer, 16, 32);
        	else if( x == 1 || x == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2+1)*16, dir*32, 16, 32, posx*16, (posy-1)*16-animationTimer, 16, 32);
        }
        else if(dir == GameUtil.UP)
        {
        	if( x == 0 || x == 3 ) Renderer.renderSubImage(charset, 0, dir*32, 16, 32, posx*16, (posy-1)*16+animationTimer, 16, 32);
        	else if( x == 1 || x == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2+1)*16, dir*32, 16, 32, posx*16, (posy-1)*16+animationTimer, 16, 32);
        }				
	}
	

	
	public void tryToMove( int dir ) {		
		int x = posx;
		if(dir == GameUtil.RIGHT) x++;
		if(dir == GameUtil.LEFT) x--;
		int y = posy;
		if(dir == GameUtil.UP) y--;
		if(dir == GameUtil.DOWN) y++;
		
		if( !Game.map.isBlocked(x, y)) move(dir);
	}
	
	public void move( int dir ) {
		
		if( dir == GameUtil.UP ) posy--;
		if( dir == GameUtil.DOWN ) posy++;
		if( dir == GameUtil.LEFT ) posx--;
		if( dir == GameUtil.RIGHT ) posx++;
		
		this.dir = dir;
		animationTimer = 16;
	}
	
	public int getX() {
		return posx;
	}
	
	void setX(int x) {
		posx=x;
	}
	
	public int getY() {
		return posy;
	}
	
	void setY(int y) {
		posy=y;
	}

	int getDirection() {
		return dir;
	}
	
	void setDirection(int d) {
		dir = d;
	}
	
	public void init()
	{
		animationTimer = 0;
	}
	
	public void update()
	{
		if( animationTimer > 0 ) animationTimer--;
	}
	
	public boolean isFinished()
	{
		return animationTimer == 0;
	}
	
}
