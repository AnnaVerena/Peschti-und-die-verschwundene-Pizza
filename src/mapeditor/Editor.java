package mapeditor;
import java.io.File;
import java.net.MalformedURLException;

import game.Map;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Editor extends Application {
	
	 Map map;
	 final int SCALE = 2;
	 int tilesetWidth;
	
	public static void main(String[] args) 
    {
        launch(args);
    }
	
	public void start(Stage theStage) throws MalformedURLException 
	{
	    theStage.setTitle( "Mapeditor (Layer 0)" );
	    map = new Map(20, 15);
	    int[] layer = {0};
	    
	         
	    Group root = new Group();
	    Scene theScene = new Scene( root, 800, 600 );
	    theStage.setScene( theScene );    
	         
	    BorderPane borderPane = new BorderPane();
	    
	    ScrollPane scrollPane = new ScrollPane();
	    ScrollPane scrollPane2 = new ScrollPane();
	    
	    Menu mFile = new Menu("File");
	    MenuItem mOpen = new MenuItem("open");
	    MenuItem mSave = new MenuItem("save");
	    MenuItem mNew = new MenuItem("new");
	    mFile.getItems().add(mNew);
	    mFile.getItems().add(mOpen);
	    mFile.getItems().add(mSave);
	    MenuBar menuBar = new MenuBar( mFile );
	    
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setInitialDirectory(new File("."));
	    
	    mNew.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		GridPane gridPane = new GridPane();
	    		
	    		Label lWidth = new Label("Width:");
	    		Label lHeight = new Label("Height:");
	    		
	    		TextField tWidth = new TextField();
	    		TextField tHeight = new TextField();
	    		
	    		tWidth.setText("20");
	    		tHeight.setText("15");
	    		
	    		Button bCreate = new Button("Create");
	    		 		
	    		
	    		gridPane.add(lWidth, 0, 0);
	    		gridPane.add(lHeight, 0, 1);
	    		gridPane.add(tWidth,  1,  0 );
	    		gridPane.add(tHeight,  1,  1);
	    		gridPane.add(bCreate,  1, 2);
	    		
                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(gridPane);
 
                Scene secondScene = new Scene(secondaryLayout, 230, 100);
                                 
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("New Map");
                newWindow.setScene(secondScene);
                
                bCreate.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent t) {
	                	int width = Integer.parseInt(tWidth.getText());
	                	int height = Integer.parseInt(tHeight.getText());
	                	map = new Map( width, height );
	                	
	                	newWindow.close();
	                }
	            }); 
 
                // Set position of second window, related to primary window.
                newWindow.setX(theStage.getX() + 200);
                newWindow.setY(theStage.getY() + 100);
 
                newWindow.show();
            }
        }); 
	    
	    mOpen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	File f = fileChooser.showOpenDialog(theStage);
            	if( f != null ) {
            		map = new Map(f);
            	}
            }
        }); 
	    
	    mSave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	File f = fileChooser.showSaveDialog(theStage);
            	if( f != null ) {
            		map.toFile(f);
            	}
            }
        });
	    
	    Image tileset = resample( new Image( new File("res/tilesets/Tileset2.png").toURI().toURL().toString()), SCALE );
	    tilesetWidth = (int) (tileset.getWidth()/(16*SCALE));
	    
	    Canvas canvas = new Canvas( map.getWidth()*16*SCALE, map.getHeight()*16*SCALE );	    
	    Canvas tCanvas = new Canvas( tilesetWidth*16*SCALE,512*SCALE );
	    
	    scrollPane.setContent( canvas );
	    scrollPane2.setContent( tCanvas );
	    scrollPane2.setHbarPolicy(ScrollBarPolicy.NEVER);
	    scrollPane2.setVbarPolicy(ScrollBarPolicy.ALWAYS);

	    
	    borderPane.setTop(menuBar);
	    borderPane.setCenter(scrollPane);
	    borderPane.setRight(scrollPane2);

	    borderPane.prefHeightProperty().bind(theScene.heightProperty());
        borderPane.prefWidthProperty().bind(theScene.widthProperty());
	    
	    root.getChildren().add( borderPane );
	         
	    GraphicsContext gc = canvas.getGraphicsContext2D();	    
	    GraphicsContext gct = tCanvas.getGraphicsContext2D();
	    
	    
	    
	    int[] tileId = new int[1];
	    tileId[0] = 8;
	    
	    theScene.setOnKeyPressed(
	            new EventHandler<KeyEvent>()
	            {
	                public void handle(KeyEvent e)
	                {
	                    if( e.getCode().equals(KeyCode.DIGIT1))
	                    {
	                    	theStage.setTitle( "Mapeditor (Layer 0)" );
	                    	layer[0] = 0;
	                    }
	                    else if( e.getCode().equals(KeyCode.DIGIT2))
	                    {
	                    	theStage.setTitle( "Mapeditor (Layer 1)" );
	                    	layer[0] = 1;
	                    }
	                    else if( e.getCode().equals(KeyCode.DIGIT3))
	                    {
	                    	theStage.setTitle( "Mapeditor (Layer 2)" );
	                    	layer[0] = 2;
	                    }
	                    else if( e.getCode().equals(KeyCode.DIGIT4))
	                    {
	                    	theStage.setTitle( "Mapeditor (View all)" );
	                    	layer[0] = 3;
	                    }
	                    else if( e.getCode().equals(KeyCode.B))
	                    {
	                    	theStage.setTitle( "Mapeditor (Blocked tiles)" );
	                    	layer[0] = 4;
	                    }
	                }
	            });
	    
	    canvas.setOnMouseDragged(
	            new EventHandler<MouseEvent>()
	            {
	                public void handle(MouseEvent e)
	                {
	                	int tx = (int)e.getX()/(16*SCALE);
	                	int ty = (int)e.getY()/(16*SCALE);
	                	if( layer[0] < 3 ) map.setTile(layer[0], tx, ty, tileId[0]);
	                }
	            });
	    
	    canvas.setOnMouseClicked(
	            new EventHandler<MouseEvent>()
	            {
	                public void handle(MouseEvent e)
	                {
	                	int tx = (int)e.getX()/(16*SCALE);
	                	int ty = (int)e.getY()/(16*SCALE);
	                	if( layer[0] < 3 ) map.setTile(layer[0], tx, ty, tileId[0]);
	                	if( layer[0] == 4 ) map.setTileBlocked(tx, ty, !map.isTileBlocked(tx, ty));
	                }
	            });
	    
	    tCanvas.setOnMouseClicked(
	            new EventHandler<MouseEvent>()
	            {
	                public void handle(MouseEvent e)
	                {
	                    tileId[0] = (int)e.getX()/(16*SCALE) + (int)e.getY()/(16*SCALE)*tilesetWidth;
	                }
	            });
	    
	    new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	canvas.setWidth(map.getWidth()*16*SCALE);
            	canvas.setHeight(map.getHeight()*16*SCALE);
            	
            	gc.setFill(Color.DARKGRAY);
        	    gc.fillRect(0,0,map.getWidth()*16*SCALE,map.getHeight()*16*SCALE);
        	    
        	    if( layer[0] < 3 )
        	    {
        	    	for( int l = 0; l < layer[0]; l++ )
            	    	for( int y = 0; y < map.getHeight(); y++ )
            	    		for( int x = 0; x < map.getWidth(); x++ )
            	    			renderTile( x, y, map.getTile(l, x, y), tileset, gc );
        	    	
        	    	gc.setFill(Color.rgb(0,0,0,0.3));
        	    	gc.fillRect(0,0,map.getWidth()*16*SCALE,map.getHeight()*16*SCALE);
        	    	
        	    	for( int y = 0; y < map.getHeight(); y++ )
        	    		for( int x = 0; x < map.getWidth(); x++ )
        	    		{
        	    			renderTile( x, y, map.getTile(layer[0], x, y), tileset, gc );        
        	    			gc.setStroke(Color.GRAY);
    	    				gc.strokeRect(x*16*SCALE + 0.5, y*16*SCALE + 0.5, 16*SCALE , 16*SCALE );
        	    		}
        	    				    	
        	    }
        	    else {
        	    	for( int l = 0; l < 3; l++ )
            	    	for( int y = 0; y < map.getHeight(); y++ )
            	    		for( int x = 0; x < map.getWidth(); x++ )
            	    			renderTile( x, y, map.getTile(l, x, y), tileset, gc );
            	    
            	    if( layer[0] == 4 )
            	    {            	    	
                    	
            	    	for( int y = 0; y < map.getHeight(); y++ )
            	    		for( int x = 0; x < map.getWidth(); x++ ) {
            	    			gc.setStroke(Color.GRAY);
        	    				gc.strokeRect(x*16*SCALE + 0.5, y*16*SCALE + 0.5, 16*SCALE , 16*SCALE );
        	    				
            	    			if(map.isTileBlocked(x, y))
            	    			{    	    				
            	    				
            	    				gc.setStroke(Color.BLACK);
            	    				gc.setFill( Color.RED);
            	    				gc.fillOval(x*16*SCALE + 4*SCALE, y*16*SCALE + 4*SCALE, 8*SCALE, 8*SCALE);
            	    				gc.strokeOval(x*16*SCALE + 4*SCALE, y*16*SCALE + 4*SCALE, 8*SCALE, 8*SCALE);
            	    			}
            	    		}
            	    			
            	    }
        	    }
        	    
        	    
        	    gct.setFill(Color.BLACK);
        	    gct.fillRect(0, 0, tilesetWidth*16*SCALE, 1024*SCALE);
        	    gct.drawImage(tileset, 0, 0);

        	    gct.setStroke(Color.RED);
        	    gct.strokeRect( tileId[0]%tilesetWidth*16*SCALE, tileId[0]/tilesetWidth*16*SCALE, 16*SCALE, 16*SCALE);
            }
        }.start();
	         
	    theStage.show();
	}
	
	private void renderTile( int x, int y, int tileId, Image tileset, GraphicsContext gc )
	{
		gc.drawImage(tileset, (tileId%tilesetWidth) * 16 * SCALE, (tileId/tilesetWidth)*16*SCALE, 16*SCALE, 16*SCALE, x*16*SCALE, y*16*SCALE, 16*SCALE, 16*SCALE );
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
