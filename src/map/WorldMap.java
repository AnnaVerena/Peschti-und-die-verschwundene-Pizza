package map;

import java.io.File;
import java.net.MalformedURLException;

import event.EventList;
import event.GameEvent;
import event.MapEvent;
import event.Teleport;
import game.Game;
import game.GameUtil;
import render.Image;

public class WorldMap extends game.Map{
	
	public WorldMap() throws MalformedURLException{
		super(new File("res/maps/world.txt"));
        tileset = Image.loadImage( new File("res/tilesets/world.png"));   
        
        GameEvent playerLarge = new GameEvent() {
        	public void init(){
        		Game.player.useLargeCharset();
        	}
        	
        	public boolean isFinished() {
        		return true;
        	}
        };
        
        mapEvents.add( new MapEvent(6, 5, GameUtil.DOWN, null, true, null, new EventList( playerLarge, new Teleport(1, 5, 29, GameUtil.UP))) );
	}

}