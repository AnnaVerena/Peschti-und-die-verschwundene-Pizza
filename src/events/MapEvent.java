package events;

import render.Image;
import render.Renderer;

public class MapEvent extends GameEvent{

	protected int posx;
	protected int posy;
	
	public boolean belowPlayer = false;
	public GameEvent actionEvent = null;
	public GameEvent touchEvent = null;
	
	// Das charset besteht aus 3x4 Bildern.
	// dir und frame geben das Bild an.
	protected Image charset;
	int dir; 		// y-Position im Charset
	int frame = 0; 	// x-Position im Charset
	
	final int UP = 1;
	final int DOWN = 0;
	final int LEFT = 2;
	final int RIGHT = 3;
	
	public MapEvent(int posx, int posy, int dir, Image charSet ) {
		this.posx = posx;
		this.posy=posy;
		this.charset=charSet;
		this.dir = dir;
	}
	
	public MapEvent(int posx, int posy, int dir, Image charSet, boolean belowPlayer, GameEvent actionEvent, GameEvent touchEvent ) {
		this.posx = posx;
		this.posy=posy;
		this.charset=charSet;
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
		if( charset != null ) {
			Renderer.renderSubImage(charset, frame*16, dir*32, 16, 32, posx*16, (posy-1)*16, 16, 32);	
		}
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
	void setFrame(int f) {
		frame = f;
	}
	
	public void init()
	{
		frame = 0;
	}
	
	public void update()
	{
		
	}
	
	
}
