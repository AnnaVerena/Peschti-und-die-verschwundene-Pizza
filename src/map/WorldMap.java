package map;

import event_system.control_flow.EventList;
import event_system.map_entities.MapEntity;
import event_system.map_control.Teleport;
import game.Game;
import game.GameUtil;

import java.io.File;

public class WorldMap extends game.Map{
	
	public WorldMap(){
		super(new File("res/maps/world.txt"));
        tileset = Game.tilesets.get("world");
        
        
        
        mapEntities.add( new MapEntity(6, 5, GameUtil.DOWN, null, true, null, new EventList( new Teleport("village", 5, 29, GameUtil.UP))) );
	}
	
	public boolean isWorldMap() {
		return true;
	}
}