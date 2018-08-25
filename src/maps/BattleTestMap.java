package maps;

import java.io.File;
import java.net.MalformedURLException;

import events.EventList;
import events.GameEvent;
import events.MapEvent;
import events.SetStepEvent;
import events.Teleport;
import events.Textbox;
import events.TurnToPlayer;
import events.WaitEvent;
import game.Game;
import game.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;

public class BattleTestMap extends game.Map{
	
	public BattleTestMap() throws MalformedURLException{
		super(new File("res/battlescreen.txt"));
        tileset = GameUtil.resample (new Image(new File("res/Tileset2.png").toURI().toURL().toString()),2);
        
        MapEvent doorBusiness = new MapEvent(0, 5, GameUtil.DOWN, GameUtil.resample (new Image(new File("res/door.png").toURI().toURL().toString()),2));
        GameEvent teleportBusiness = new EventList( new SetStepEvent(doorBusiness, 1), new WaitEvent(10), new Teleport(0, 9, 12, GameUtil.UP) );
        doorBusiness.actionEvent = teleportBusiness;
        mapEvents.add(doorBusiness);
        
        MapEvent outi = new MapEvent( 1,5, GameUtil.DOWN, GameUtil.resample (new Image(new File("res/Business.png").toURI().toURL().toString()),2));
        outi.actionEvent = new Textbox("") {
        	public void draw( GraphicsContext gc) {
        		GameUtil.drawTextbox(gc, 0, 10, 20, 5);
        		Font font = Font.loadFont("file:res/OxygenMono-Regular.otf", 16 );
        	    gc.setFont( font );
        	    gc.setFill(Color.BLACK);
        	    gc.fillText( "Peschti\n"
        	    		+ "WP: 91/243  AP: 9/12\n"
        	    		+ "Outis\n"
        	    		+ "WP: 91/243  AP: 9/12\n"
        	    		+ "Mäh\n"
        	    		+ "WP: 91/243  AP: 9/12", 15*32 , 11*32  );
        	    GameUtil.drawTextbox(gc, 5, 10, 10, 5);
        	    gc.fillText("Was soll Peschti tun?", 5*32+16, 11*32);
        	    gc.fillText("Fähigkeit", 8*32+16, 12*32+8);
        	    gc.fillText("Gegenstand", 8*32+16, 13*32);
        	    gc.fillText("Flucht", 8*32+16, 14*32-8);
        	}
        };
        mapEvents.add( outi );
        
        MapEvent char1 = new MapEvent( 18,9, GameUtil.LEFT, GameUtil.resample (new Image(new File("res/Business.png").toURI().toURL().toString()),2));
        mapEvents.add(char1);
        
        MapEvent char2 = new MapEvent( 18,7, GameUtil.LEFT, GameUtil.resample (new Image(new File("res/Peschti.png").toURI().toURL().toString()),2));
        mapEvents.add(char2);
        
        MapEvent char3 = new MapEvent( 18,5, GameUtil.LEFT, GameUtil.resample (new Image(new File("res/Business.png").toURI().toURL().toString()),2));
        mapEvents.add(char3);
	}
	
	

}