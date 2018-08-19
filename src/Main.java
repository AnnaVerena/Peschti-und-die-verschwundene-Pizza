import java.net.MalformedURLException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class Main extends Application 
{
	
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
        
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !Game.inputs.contains(code) )
                        Game.inputs.add( code );
                }
            });
 
        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    Game.inputs.remove( code );
                }
            });
        
        Canvas canvas = new Canvas( 640, 480 );
        root.getChildren().add( canvas );
             
        GraphicsContext gc = canvas.getGraphicsContext2D();  
        
        Game.init();
        
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.017),                // 60 FPS
            new EventHandler<ActionEvent>()
            { 
            	public void handle(ActionEvent ae)
                {
                	Game.update();
                	Game.draw(gc);
                }
            });
        
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();        
        
        theStage.show();
    } 
}


	
