package event_system.map_entities;

import game.Game;
import render.Image;
import util.Direction;

public class Player extends MapEntity {
	public Image charsetLarge;
	public Image charsetSmall;
	
	public Player(int posx, int posy, Direction dir, String charsetID, String charsetSmallID) {
		super("player", posx, posy, dir, charsetID);
		
		charsetLarge = charset;
		this.charsetSmall = Game.charsets.get(charsetSmallID);
	}
	
	public int getScrX() {
		if( dir == Direction.RIGHT ) return posx*16-animationTimer;
		else if( dir == Direction.LEFT ) return posx*16+animationTimer;
		else return posx*16;
	}
	
	public int getScrY() {
		if( dir == Direction.DOWN ) return posy*16-animationTimer;
		else if (dir == Direction.UP ) return posy*16+animationTimer;
		else return posy*16;
	}	
}
