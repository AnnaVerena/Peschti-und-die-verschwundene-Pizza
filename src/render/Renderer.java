package render;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import game.Game;
import game.Map;

// Zum erstellen des Bildschirms und initialisieren von OpenGL. Startet die Gameloop und stellt Methoden zum Rendern bereit.
// Speichert Tasteneingaben in Game.inputs.
public class Renderer {

	
	 // Identitätsmatrix. Wird genutzt um in Bildkoordinaten zu Arbeiten.
	public static Matrix4f MAT4_IDENTITY = new Matrix4f().identity();
	public static Font font;
	
    private static int vbo;
    private static int vao;
    private static int texture;
    
    private static int colorAttrib, posAttrib, texcoordAttrib;

    private static ShaderProgram shaderProgram;
    
    private static GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);
    
    private static GLFWKeyCallback keyCallback = new KeyboardHandler();
    private static GamepadHandler gph = new GamepadHandler();

	// Löscht den Inhalt des Bildes
	public static void clearScreen() {
    	glClear(GL_COLOR_BUFFER_BIT);
    }
    
    public static void renderSubImage( Image img, float sourceX, float sourceY, float sourceWidth, float sourceHeight, float x, float y, float width, float height) {
    	renderSubImage( img, sourceX, sourceY, sourceWidth, sourceHeight, x, y ,width, height, Color.WHITE );
    }
    
    
    // Rendert ein Teilbild eines Bildes.
    public static void renderSubImage( Image img, float sourceX, float sourceY, float sourceWidth, float sourceHeight, float x, float y, float width, float height, Color color ) {
    	
    	Renderer.bindImage(img);
    	
        FloatBuffer verticesBuffer = null;
        try (MemoryStack stack = MemoryStack.stackPush()){
        	verticesBuffer = stack.mallocFloat(8*6);
        	
        	addQuad(x, y, width, height, ((float)sourceX)/ img.width, ((float) sourceY)/ img.height, ((float)sourceWidth)/ img.width, ((float) sourceHeight)/ img.height, color, verticesBuffer );
        	verticesBuffer.flip();
        	
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STREAM_DRAW);
        }
        
        glDrawArrays(GL_TRIANGLES, 0, 6);
    }
    
    private static void bindImage( Image img ) {
    	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, img.width, img.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, img.buffer);
    }
    
    // Rendert eine Ebene der Karte.
    public static void renderMapLayer( Map map, int layer ) {
    	
    	Renderer.bindImage(map.tileset);
    	
    	FloatBuffer verticesBuffer = MemoryUtil.memAllocFloat(8*6*map.getHeight()*map.getWidth());
        	
        int sourceX, sourceY;
        int sourceWidth = 16, sourceHeight = 16;
        int height = 16, width = 16;
        Image img = map.tileset;
        int vertexCount = 0;
        
        int tilesetWidth = img.width / 16;
        
        for( int x = 0; x < map.getWidth(); x++)
        	for( int y = 0; y < map.getHeight(); y++ )
        	{
        		if( map.getTile(layer, x, y) != 0)
        		{
        			vertexCount++;
        			
        			sourceX = (map.getTile(layer, x, y)%tilesetWidth)*16;
           			sourceY = (map.getTile(layer, x, y)/tilesetWidth)*16;
           			
           			addQuad(x*16, y*16, width, height, ((float)sourceX)/ img.width, ((float) sourceY)/ img.height, ((float)sourceWidth)/ img.width, ((float) sourceHeight)/ img.height, verticesBuffer );
          
        		}
        		
        	}        	
        	
        verticesBuffer.flip();
        	
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STREAM_DRAW);
        MemoryUtil.memFree(verticesBuffer);
        
        glDrawArrays(GL_TRIANGLES, 0, 6*vertexCount);
    }
    
    // Schreibt Text.
    public static void renderText( float x, float y, String text, Color color )
    {
    	renderText( x, y, text, color, Integer.MAX_VALUE );
    }
    
    public static void renderText( float x, float y, String text, Color color, int textWidth ) {
    	float drawX = x;
    	float drawY = y;
    	
    	float tmp = 0;
    	
    	Renderer.bindImage(font.buffer);
    	
    	FloatBuffer verticesBuffer = MemoryUtil.memAllocFloat(8*6*text.length());
    	int vertexCount = 0;
    	
    	for (int i = 0; i < text.length(); i++) {
    	    char ch = text.charAt(i);
    	    if (ch == '\n') {
    	        /* Line feed, set x and y to draw at the next line */
    	        drawY += (float)font.fontHeight/font.scale;
    	        drawX = x;
    	        continue;
    	    }
    	    if (ch == '\r') {
    	        /* Carriage return, just skip it */
    	        continue;
    	    }
    	    Glyph g = font.glyphs.get(ch);
    	    vertexCount++;
    	    addQuad(  drawX, drawY, (float)g.width/font.scale, (float)g.height/font.scale, (float)g.x/font.buffer.width, (float)g.y/font.buffer.height, (float)g.width/font.buffer.width, (float)g.height/font.buffer.height, color, verticesBuffer);
    	    drawX += (float)g.width/font.scale;
    	    
    	    tmp += (float)g.width/font.scale;
    	    if( tmp > textWidth ) break;
    	}
    	
    	verticesBuffer.flip();
    	
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STREAM_DRAW);
        MemoryUtil.memFree(verticesBuffer);
        
        glDrawArrays(GL_TRIANGLES, 0, 6*vertexCount);
    }
    
    private static void addQuad( float x, float y, float width, float height, float texX, float texY, float texWidth, float texHeight, FloatBuffer verticesBuffer ) {
    	addQuad(x,y,width,height,texX,texY,texWidth,texHeight,Color.WHITE,verticesBuffer);
    }
    
    private static void addQuad( float x, float y, float width, float height, float texX, float texY, float texWidth, float texHeight, Color color, FloatBuffer verticesBuffer ) {
    	verticesBuffer.put(x).put(y).put(color.r).put(color.g).put(color.b).put(color.a).put(texX).put(texY);
    	verticesBuffer.put( x+width).put( 	y).put(color.r).put(color.g).put(color.b).put(color.a).put(	texX+texWidth ).put( texY );
    	verticesBuffer.put( x+width ).put(	y+height ).put(color.r).put(color.g).put(color.b).put(color.a).put(	texX+texWidth ).put( texY+texHeight );
    	verticesBuffer.put(x ).put(	y ).put(color.r).put(color.g).put(color.b).put(color.a).put( texX ).put(	texY );
    	verticesBuffer.put( x ).put(	y+height ).put(color.r).put(color.g).put(color.b).put(color.a).put(	texX ).put( texY+texHeight );
    	verticesBuffer.put( x+width ).put( 	y+height ).put(color.r).put(color.g).put(color.b).put(color.a).put(	texX+texWidth ).put( texY+texHeight );
    }
    
    // Erstellt das Fenster, initialisiert OpenGL und startet die Gameloop.
    // Muss ganz am Anfang einmal aufgerufen werden.
    public static void run(){
    	glfwSetErrorCallback( errorCallback );
    	if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
						
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		long window = glfwCreateWindow(960, 720, "Peschti-RPG", NULL, NULL);
		
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");
		
		glfwSetKeyCallback(window, keyCallback);
				
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glfwSwapInterval(1); //v-Sync
		glfwShowWindow(window);
		
		try {
			shaderProgram = new ShaderProgram("res/shader/vertex_shader.txt", "res/shader/fragment_shader.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        
        posAttrib = glGetAttribLocation(shaderProgram.shaderProgram, "position");
    	glVertexAttribPointer(posAttrib, 2, GL_FLOAT, false, 8*Float.BYTES, 0);
    	colorAttrib = glGetAttribLocation(shaderProgram.shaderProgram, "color");
    	glVertexAttribPointer(colorAttrib, 4, GL_FLOAT, false, 8*Float.BYTES, 2*Float.BYTES);
    	texcoordAttrib = glGetAttribLocation(shaderProgram.shaderProgram, "texcoord");
    	glVertexAttribPointer(texcoordAttrib, 2, GL_FLOAT, false, 8*Float.BYTES, 6*Float.BYTES);
                  
        texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        
        shaderProgram.setUniformMatrix("model",  new Matrix4f());
		shaderProgram.setUniformMatrix("view", new Matrix4f());
		
		Matrix4f projection = new Matrix4f().ortho( 0f, 320f, 240f, 0f, -1f, 1f);
		
		shaderProgram.setUniformMatrix("projection",  projection);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glEnableVertexAttribArray(posAttrib);
        glEnableVertexAttribArray(colorAttrib);
        glEnableVertexAttribArray(texcoordAttrib);
		
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
		glClearColor( 0.5f, 0.5f, 0.5f, 0.0f );
		
		font = new Font(new File("res/fonts/OxygenMono-Regular.ttf"));
		
		while ( !glfwWindowShouldClose(window) ) {
			glfwSwapBuffers(window);

			Game.loop();
			
			glfwPollEvents();
			gph.handleInputs();
		}
		
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		
		glfwTerminate();
    }
    
    // Setzt die view-Matrix.
    // Wird benutzt um zwischen Welt- und Bildkoordinaten zu wechseln.
    public static void setView( Matrix4f view ) {
    	shaderProgram.setUniformMatrix("view", view );
    }
}