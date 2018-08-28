package render;

public class Color {
	
	public static final Color WHITE = new Color();
	public static final Color BLACK = new Color(0,0,0);
	public static final Color RED = new Color(1,0,0);
	public static final Color GREEN = new Color(0,1,0);
	public static final Color BLUE = new Color(0,0,1);
	public static final Color GRAY = new Color(0.5f,0.5f,0.5f);
	
	public float r;
	public float g;
	public float b;
	public float a;
	
	public Color( float r, float g, float b, float a) {
		this.r = r;
		this.g= g;
		this.b = b;
		this.a = a;
	}
	
	public Color( float r, float g, float b) {
		this(r,g,b,1f);
	}
	
	public Color() {
		this(1f,1f,1f,1f);
	}
}
