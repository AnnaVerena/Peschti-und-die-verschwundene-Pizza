package event;
import game.Game;
import game.GameUtil;
import render.Image;

public class Player extends MapEvent {
	Image charsetLarge;
	Image charsetSmall;
	
	public Player(int posx, int posy, int dir, String charsetID, String charsetSmallID) {
		super("player", posx, posy, dir, charsetID);
		
		charsetLarge = charset;
		this.charsetSmall = Game.charsets.get(charsetSmallID);
	}
	
	public int getScrX() {
		if( dir == GameUtil.RIGHT ) return posx*16-animationTimer;
		else if( dir == GameUtil.LEFT ) return posx*16+animationTimer;
		else return posx*16;
	}
	
	public int getScrY() {
		if( dir == GameUtil.DOWN ) return posy*16-animationTimer;
		else if (dir == GameUtil.UP ) return posy*16+animationTimer;
		else return posy*16;
	}	
}
