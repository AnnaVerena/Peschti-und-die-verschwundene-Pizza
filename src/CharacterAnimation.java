import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CharacterAnimation {
	
	
	/**
	 * Wir codieren die Richtungen, in die die Figur blicken kann
	 * 
	 */
	static final int UP = 1;
	static final int DOWN = 0;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	
	/**
	 *
	 * @param x x-Posiiton
	 * @param y y-Position
	 * @param dir Richtung, wie gerade codiert
	 * @param charset Bilddatei für die Figur
	 * @return Game Event: Figur bewegt sich. 
	 */
	
	public static final GameEvent createWalkingAnimation( int x, int y, int dir, Image charset ) {
		return new GameEvent() {
			int animationTimer = 16;			
			
			public void update() {
				if( animationTimer > 0 ) animationTimer--;
			}
			
			public void draw(GraphicsContext gc) {
				if(dir == RIGHT)
		        {
		        	if( (animationTimer / 4)%2 == 0 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32-animationTimer*2, (y-1)*32, 32, 64);
		        	else if( (animationTimer / 4)%4 == 3 ) gc.drawImage(charset, 1*32, dir*64, 32, 64, x*32-animationTimer*2, (y-1)*32, 32, 64);
		        	else if( (animationTimer / 4)%4 == 1 ) gc.drawImage(charset, 2*32, dir*64, 32, 64, x*32-animationTimer*2, (y-1)*32, 32, 64);
		        }
		        else if(dir == LEFT)
		        {
		        	if( (animationTimer / 4)%2 == 0 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32+animationTimer*2, (y-1)*32, 32, 64);
		        	else if( (animationTimer / 4)%4 == 3 ) gc.drawImage(charset, 1*32, dir*64, 32, 64, x*32+animationTimer*2, (y-1)*32, 32, 64);
		        	else if( (animationTimer / 4)%4 == 1 ) gc.drawImage(charset, 2*32, dir*64, 32, 64, x*32+animationTimer*2, (y-1)*32, 32, 64);
		        }
		        else if(dir == DOWN)
		        {
		        	if( (animationTimer / 4)%2 == 0 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32, (y-1)*32-animationTimer*2, 32, 64);
		        	else if( (animationTimer / 4)%4 == 3 ) gc.drawImage(charset, 1*32, dir*64, 32, 64, x*32, (y-1)*32-animationTimer*2, 32, 64);
		        	else if( (animationTimer / 4)%4 == 1 ) gc.drawImage(charset, 2*32, dir*64, 32, 64, x*32, (y-1)*32-animationTimer*2, 32, 64);
		        }
		        else if(dir == UP)
		        {
		        	if( (animationTimer / 4)%2 == 0 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32, (y-1)*32+animationTimer*2, 32, 64);
		        	else if( (animationTimer / 4)%4 == 3 ) gc.drawImage(charset, 1*32, dir*64, 32, 64, x*32, (y-1)*32+animationTimer*2, 32, 64);
		        	else if( (animationTimer / 4)%4 == 1 ) gc.drawImage(charset, 2*32, dir*64, 32, 64, x*32, (y-1)*32+animationTimer*2, 32, 64);
		        }				
			}
			
			public boolean isFinished() {
				return animationTimer == 0;
			}
		};
	}
	
	/**
	 * 
	 * 
	 * @param x wie oben
	 * @param y wie oben
	 * @param dir wie oben
	 * @param charset wie oben
	 * @return GameEvent: Figur steht
	 */
	
	public static final GameEvent createStandingAnimation( int x, int y, int dir, Image charset ) {
		return new GameEvent() {
			
			public void draw(GraphicsContext gc) {
				gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32, (y-1)*32, 32, 64);
			}
		};
	}
}
