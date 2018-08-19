import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class MapEvent extends GameEvent{

	int posx;
	int posy;
	
	Image charSet;
	int dir;
	
	final int UP = 1;
	final int DOWN = 0;
	final int LEFT = 2;
	final int RIGHT = 3;
	
	public MapEvent(int posx, int posy, int dir, Image charSet ) {
		this.posx = posx;
		this.posy=posy;
		this.charSet=charSet;
		this.dir = dir;
	}
	
	
	/**
	 * @param name description Bildfile, Blickrichtung, Position
	 * Wir malen die Figur
	 */
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(charSet, 0*32, dir*64, 32, 64, posx*32, (posy-1)*32, 32, 64);	
	}
	
	
	int getX() {
		return posx;
	}
	
	void setX(int x) {
		posx=x;
	}
	
	int getY() {
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
			if(x == me.getX() && y == me.getY()) return true;
		}
		return false;
	}
}
