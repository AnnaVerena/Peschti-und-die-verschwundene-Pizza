package events;
import java.util.ArrayList;

import game.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MapEvent extends GameEvent{

	int posx;
	int posy;
	
	boolean belowPlayer = false;
	public GameEvent actionEvent = null;
	public GameEvent touchEvent = null;
	
	// Das charset besteht aus 3x4 Bildern.
	// dir und step geben das Bild an.
	Image charset;
	int dir; 		// y-Position im Charset
	int step = 0; 	// x-Position im Charset
	
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
	
	public void draw(GraphicsContext gc) {
		if( charset != null ) gc.drawImage(charset, step*32, dir*64, 32, 64, posx*32, (posy-1)*32, 32, 64);	
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
	void setStep(int s) {
		step = s;
	}
	
	public void init()
	{
		step = 0;
	}
	
	public void update()
	{
		
	}
	
	/**
	 * 
	 *
	 * @param x x-Position 
	 * @param y y-Position
	 * @param map Karte
	 * @param chars Liste der Map-Events
	 * @return Wir erfahren, ob eine Position auf der Karte durch einen Gegenstand oder eine Figur geblockt ist
	 */

	boolean isBlocked(int x, int y, Map map, ArrayList<MapEvent> chars) {
		if(map.isBlocked(x, y)) return true;
		for (MapEvent me : chars) {
			if(x == me.getX() && y == me.getY() && !me.belowPlayer) return true;
		}
		return false;
	}
}
