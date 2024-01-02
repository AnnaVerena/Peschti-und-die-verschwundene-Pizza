package map;

import java.io.File;
import java.net.MalformedURLException;

import event.EventList;
import event.MapEvent;
import event.Teleport;
import game.Game;
import game.GameUtil;

public class WorldMap extends game.Map{
	
	public WorldMap(){
		super(new File("res/maps/world.txt"));
        tileset = Game.tilesets.get("world");
        
        
        
        mapEvents.add( new MapEvent(6, 5, GameUtil.DOWN, null, true, null, new EventList( new Teleport("village", 5, 29, GameUtil.UP))) );
	}
	
	public boolean isWorldMap() {
		return true;
	}
}