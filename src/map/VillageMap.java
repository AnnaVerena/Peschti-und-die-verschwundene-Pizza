package map;

import event_system.*;
import event_system.control_flow.EventList;
import event_system.map_control.Teleport;
import event_system.map_control.TurnEvent;
import event_system.map_entities.AnimatedMapEntity;
import event_system.map_entities.MapEntity;
import game.Game;
import game.GameUtil;

import java.io.File;

public class VillageMap extends game.Map{
	
	public VillageMap(){
		super(new File("res/maps/map_village_large.txt"));
        tileset = Game.tilesets.get("village");
        
        MapEntity doorBusiness = new MapEntity( "doorBusi", 18, 5, GameUtil.DOWN, "door");
        doorBusiness.actionEvent = new EventList( new TurnEvent("doorBusi", GameUtil.UP), new WaitEvent(10), new Teleport("shop", 9, 12, GameUtil.UP) );
        mapEntities.add(doorBusiness);
        
        MapEntity doorOutis = new MapEntity("doorOuti", 7, 8, GameUtil.DOWN, "door");
        doorOutis.actionEvent = new EventList( new TurnEvent("doorOuti", GameUtil.UP), new WaitEvent(10), new Teleport("outi_room", 9, 12, GameUtil.UP) );
        mapEntities.add(doorOutis);
        
        MapEntity doorPeschti = new MapEntity("doorPeschti", 5, 17, GameUtil.DOWN, "door");
        doorPeschti.actionEvent = new EventList( new TurnEvent("doorPeschti", GameUtil.UP), new WaitEvent(10), new Teleport("peschti_room", 9, 13, GameUtil.UP) );
        mapEntities.add(doorPeschti);
        
        mapEntities.add( new MapEntity(4, 29, GameUtil.DOWN, null, true, null, new EventList( new Teleport("world", 6, 6, GameUtil.DOWN)) ));
        mapEntities.add( new MapEntity(5, 29, GameUtil.DOWN, null, true, null, new EventList(  new Teleport("world", 6, 6, GameUtil.DOWN)) ));
        mapEntities.add( new MapEntity(6, 29, GameUtil.DOWN, null, true, null, new EventList(  new Teleport("world", 6, 6, GameUtil.DOWN)) ));
        
        MapEntity waterfall1 = new AnimatedMapEntity(30, 9, GameUtil.DOWN, "waterfall", 10);
        mapEntities.add(waterfall1);
        
        MapEntity waterfall2 = new AnimatedMapEntity(30, 11, GameUtil.UP, "waterfall", 10);
        mapEntities.add(waterfall2);
        
        MapEntity waterfall_left1 = new AnimatedMapEntity(29, 9, GameUtil.DOWN, "waterfall_left", 10);
        mapEntities.add(waterfall_left1);
        
        MapEntity waterfall_left2 = new AnimatedMapEntity(29, 11, GameUtil.UP, "waterfall_left", 10);
        mapEntities.add(waterfall_left2);
        
        MapEntity waterfall_right1 = new AnimatedMapEntity(31, 9, GameUtil.DOWN, "waterfall_right", 10);
        mapEntities.add(waterfall_right1);
        
        MapEntity waterfall_right2 = new AnimatedMapEntity(31, 11, GameUtil.UP, "waterfall_right", 10);
        mapEntities.add(waterfall_right2);
	}
	
	

}