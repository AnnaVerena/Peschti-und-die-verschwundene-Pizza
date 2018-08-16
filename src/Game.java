import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class Game extends Application 
{
	final int UP = 1;
	final int DOWN = 0;
	final int LEFT = 2;
	final int RIGHT = 3;
	
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    public void start(Stage theStage) throws MalformedURLException 
    {
        theStage.setTitle( "Canvas Example" );
             
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
                
        
        ArrayList<String> input = new ArrayList<String>();
        ArrayList<MapEvent> chars = new ArrayList<MapEvent>();

        
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });
 
        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });
        
        Canvas canvas = new Canvas( 640, 480 );
        root.getChildren().add( canvas );
             
        GraphicsContext gc = canvas.getGraphicsContext2D();  
        
        Map map = new Map(new File("res/map.txt"));
      
         
        chars.add( new Player( 5, 5, DOWN, resample (new Image(new File("res/Peschti.png").toURI().toURL().toString()),2)));
        chars.add( new MapEvent( 8,5, DOWN, resample (new Image(new File("res/Business.png").toURI().toURL().toString()),2)));
        Image tileset = resample (new Image(new File("res/Tileset.png").toURI().toURL().toString()),2);
        
        
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.017),                // 60 FPS
            new EventHandler<ActionEvent>()
            {
            
            	
                public void handle(ActionEvent ae)
                {
                	for( MapEvent charEvent : chars) charEvent.update(map, chars, input);
                	
                	gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, 640,480);
                    for( int x = 0; x < 20; x++ )
                    	for( int y = 0; y < 15; y++ )
                    	{
                    		gc.drawImage( tileset, (map.getTile(0, x, y)%8)*32, (map.getTile(0, x, y)/8)*32, 32, 32, x*32, y*32, 32, 32 );
                    	}
                    
                    for( int x = 0; x < 20; x++ )
                    	for( int y = 0; y < 15; y++ )
                    	{
                    		gc.drawImage( tileset, (map.getTile(1, x, y)%8)*32, (map.getTile(1, x, y)/8)*32, 32, 32, x*32, y*32, 32, 32 );
                    	}
                      
                    Collections.sort(chars, new Comparator<MapEvent>() {
                        @Override
                        public int compare(MapEvent o1, MapEvent o2) {
                            return o1.getY() - o2.getY();
                        }
                    });
                    
                    for( MapEvent charEvent : chars) charEvent.draw(gc);
                    
                    
                    for( int x = 0; x < 20; x++ )
                    	for( int y = 0; y < 15; y++ )
                    	{
                    		gc.drawImage( tileset, (map.getTile(2, x, y)%8)*32, (map.getTile(2, x, y)/8)*32, 32, 32, x*32, y*32, 32, 32 );
                    	}
                }
            });
        
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
        
        
        theStage.show();
    }
    
    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;
        
        WritableImage output = new WritableImage(
          W * S,
          H * S
        );
        
        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();
        
        for (int y = 0; y < H; y++) {
          for (int x = 0; x < W; x++) {
            final int argb = reader.getArgb(x, y);
            for (int dy = 0; dy < S; dy++) {
              for (int dx = 0; dx < S; dx++) {
                writer.setArgb(x * S + dx, y * S + dy, argb);
              }
            }
          }
        }
        
        return output;
      }
}


	
