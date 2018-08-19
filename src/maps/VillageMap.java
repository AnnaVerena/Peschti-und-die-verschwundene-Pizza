package maps;

import java.io.File;
import java.net.MalformedURLException;

import events.MapEvent;
import events.Teleport;
import game.Game;
import javafx.scene.image.Image;

public class VillageMap extends game.Map{
	
	public VillageMap() throws MalformedURLException{
		super(new File("res/map_village.txt"));
        tileset = Game.resample (new Image(new File("res/Tileset2.png").toURI().toURL().toString()),2);
        
        mapEvents.add(new MapEvent(13, 5, Game.DOWN, null, false, new Teleport(0, 9, 12, Game.UP), null));
	}

}