package render;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.stb.STBImage;

public class Image 
{
	int width;
	int height;
	ByteBuffer buffer;
	
	public Image(int width, int height, ByteBuffer buffer) {
		this.width = width;
		this.height = height;
		this.buffer = buffer;
	}
	
	
	// Lädt ein Bild aus einer Datei.
	public static Image loadImage( File f ) {
		int width, height;
		ByteBuffer buffer;
		
		try( MemoryStack stack = MemoryStack.stackPush() ){
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer comp = stack.mallocInt(1);
			
			//STBImage.stbi_set_flip_vertically_on_load(true);
			buffer = STBImage.stbi_load( f.getPath(), w, h, comp, 4);
			if (buffer == null) {
			    throw new RuntimeException("Failed to load a texture file!"
			            + System.lineSeparator() + STBImage.stbi_failure_reason());
			}

			width = w.get();
			height = h.get();
		}
		
		return new Image( width, height, buffer );
	}
	
	// Konvertiert ein BufferedImage in ein Bild vom Typ Image.
	public static Image convertImage( BufferedImage image ) {
		int width = image.getWidth();
		int height = image.getHeight();

		int[] pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		
		ByteBuffer buffer = MemoryUtil.memAlloc(width * height * 4);

		for (int y = 0; y < height; y++) {
		    for (int x = 0; x < width; x++) {
		        int pixel = pixels[y * width + x];

		        /* Red component 0xAARRGGBB >> (4 * 4) = 0x0000AARR */
		        buffer.put((byte) ((pixel >> 16) & 0xFF));

		        /* Green component 0xAARRGGBB >> (2 * 4) = 0x00AARRGG */
		        buffer.put((byte) ((pixel >> 8) & 0xFF));

		        /* Blue component 0xAARRGGBB >> 0 = 0xAARRGGBB */
		        buffer.put((byte) (pixel & 0xFF));

		        /* Alpha component 0xAARRGGBB >> (6 * 4) = 0x000000AA */
		        buffer.put((byte) ((pixel >> 24) & 0xFF));
		    }
		}
		buffer.flip();
		
		return new Image( width, height, buffer );
	}
}
