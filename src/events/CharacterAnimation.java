package events;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CharacterAnimation extends GameEvent {
	
	
	/**
	 * Wir codieren die Richtungen, in die die Figur blicken kann
	 * 
	 */
	static final int UP = 1;
	static final int DOWN = 0;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	
	public int getX() {
		return 0;
	}
	
	public int getY() {
		return 0;
	}
	
	/**
	 *
	 * @param x x-Posiiton
	 * @param y y-Position
	 * @param dir Richtung, wie gerade codiert
	 * @param charset Bilddatei für die Figur
	 * @return Game Event: Figur bewegt sich. 
	 */
	
	public static final CharacterAnimation createWalkingAnimation( int x, int y, int dir, Image charset ) {
		return new CharacterAnimation() {
			int animationTimer = 15;			
			
			public void update() {
				if( animationTimer > 0 ) animationTimer--;
			}
			
			public void draw(GraphicsContext gc) {
				if(dir == RIGHT)
		        {
		        	if( (animationTimer / 4) == 0 || (animationTimer / 4) == 3 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32-animationTimer*2, (y-1)*32, 32, 64);
		        	else if( (animationTimer / 4) == 1 || (animationTimer / 4) == 2 ) gc.drawImage(charset, ((x+y)%2 +1)*32, dir*64, 32, 64, x*32-animationTimer*2, (y-1)*32, 32, 64);
		        }
		        else if(dir == LEFT)
		        {
		        	if( (animationTimer / 4) == 0 || (animationTimer / 4) == 3 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32+animationTimer*2, (y-1)*32, 32, 64);
		        	else if( (animationTimer / 4) == 1 || (animationTimer / 4) == 2 ) gc.drawImage(charset, ((x+y)%2 +1)*32, dir*64, 32, 64, x*32+animationTimer*2, (y-1)*32, 32, 64);
		        }
		        else if(dir == DOWN)
		        {
		        	if( (animationTimer / 4) == 0 || (animationTimer / 4) == 3 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32, (y-1)*32-animationTimer*2, 32, 64);
		        	else if( (animationTimer / 4) == 1 || (animationTimer / 4) == 2 ) gc.drawImage(charset, ((x+y)%2+1)*32, dir*64, 32, 64, x*32, (y-1)*32-animationTimer*2, 32, 64);
		        }
		        else if(dir == UP)
		        {
		        	if( (animationTimer / 4) == 0 || (animationTimer / 4) == 3 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32, (y-1)*32+animationTimer*2, 32, 64);
		        	else if( (animationTimer / 4) == 1 || (animationTimer / 4) == 2 ) gc.drawImage(charset, ((x+y)%2+1)*32, dir*64, 32, 64, x*32, (y-1)*32+animationTimer*2, 32, 64);
		        }				
			}
			
			public boolean isFinished() {
				return animationTimer == 0;
			}
			
			public int getX() {
				if( dir == RIGHT ) return x*32-animationTimer*2;
				else if( dir == LEFT ) return x*32+animationTimer*2;
				else return x*32;
			}
			
			public int getY() {
				if( dir == DOWN ) return (y)*32-animationTimer*2;
				else if (dir == UP ) return (y)*32+animationTimer*2;
				else return (y)*32;
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
	
	public static final CharacterAnimation createStandingAnimation( int x, int y, int dir, Image charset ) {
		return new CharacterAnimation() {
			
			public void draw(GraphicsContext gc) {
				gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32, (y-1)*32, 32, 64);
			}
			
			public int getX() {
				return x*32;
			}
			
			public int getY() {
				return y*32;
			}

		};
	}
}
