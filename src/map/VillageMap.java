package map;

import event.*;
import game.Game;
import game.GameUtil;

import java.io.File;

public class VillageMap extends game.Map{
	
	public VillageMap(){
		super(new File("res/maps/map_village_large.txt"));
        tileset = Game.tilesets.get("village");
        
        MapEvent doorBusiness = new MapEvent( "doorBusi", 18, 5, GameUtil.DOWN, "door");
        doorBusiness.actionEvent = new EventList( new TurnEvent("doorBusi", GameUtil.UP), new WaitEvent(10), new Teleport("shop", 9, 12, GameUtil.UP) );
        mapEvents.add(doorBusiness);
        
        MapEvent doorOutis = new MapEvent("doorOuti", 7, 8, GameUtil.DOWN, "door");
        doorOutis.actionEvent = new EventList( new TurnEvent("doorOuti", GameUtil.UP), new WaitEvent(10), new Teleport("outi_room", 9, 12, GameUtil.UP) );
        mapEvents.add(doorOutis);
        
        MapEvent doorPeschti = new MapEvent("doorPeschti", 5, 17, GameUtil.DOWN, "door");
        doorPeschti.actionEvent = new EventList( new TurnEvent("doorPeschti", GameUtil.UP), new WaitEvent(10), new Teleport("peschti_room", 9, 13, GameUtil.UP) );
        mapEvents.add(doorPeschti);
        
        mapEvents.add( new MapEvent(4, 29, GameUtil.DOWN, null, true, null, new EventList( new Teleport("world", 6, 6, GameUtil.DOWN)) ));
        mapEvents.add( new MapEvent(5, 29, GameUtil.DOWN, null, true, null, new EventList(  new Teleport("world", 6, 6, GameUtil.DOWN)) ));
        mapEvents.add( new MapEvent(6, 29, GameUtil.DOWN, null, true, null, new EventList(  new Teleport("world", 6, 6, GameUtil.DOWN)) ));
        
        MapEvent waterfall1 = new AnimatedMapEvent(30, 9, GameUtil.DOWN, "waterfall", 10);
        mapEvents.add(waterfall1);
        
        MapEvent waterfall2 = new AnimatedMapEvent(30, 11, GameUtil.UP, "waterfall", 10);
        mapEvents.add(waterfall2);
        
        MapEvent waterfall_left1 = new AnimatedMapEvent(29, 9, GameUtil.DOWN, "waterfall_left", 10);
        mapEvents.add(waterfall_left1);
        
        MapEvent waterfall_left2 = new AnimatedMapEvent(29, 11, GameUtil.UP, "waterfall_left", 10);
        mapEvents.add(waterfall_left2);
        
        MapEvent waterfall_right1 = new AnimatedMapEvent(31, 9, GameUtil.DOWN, "waterfall_right", 10);
        mapEvents.add(waterfall_right1);
        
        MapEvent waterfall_right2 = new AnimatedMapEvent(31, 11, GameUtil.UP, "waterfall_right", 10);
        mapEvents.add(waterfall_right2);
	}
	
	

}