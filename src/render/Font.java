package render;

import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Font {
    private java.awt.Font font = null;
    int fontHeight;
    private boolean antiAlias = false;
    
    public final int scale = 3;
    
    Image buffer;
    
    Map<Character, Glyph> glyphs = new HashMap<>();
    
    public Font( File f ) {
    	try {
			font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, f).deriveFont(java.awt.Font.PLAIN, 10*scale);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
    	
    	int imageWidth = 0;
    	int imageHeight = 0;

    	for (int i = 32; i < 256; i++) {
    	    if (i == 127) {
    	        continue;
    	    }
    	    char c = (char) i;
    	    BufferedImage ch = createCharImage(font, c, antiAlias);

    	    imageWidth += ch.getWidth();
    	    imageHeight = Math.max(imageHeight, ch.getHeight());
    	}

    	fontHeight = imageHeight;
    	
    	BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = image.createGraphics();
    	
    	int x = 0;
    	
    	for (int i = 32; i < 256; i++) {
    	    if (i == 127) {
    	        continue;
    	    }
    	    char c = (char) i;
    	    BufferedImage charImage = createCharImage(font, c, antiAlias);

    	    int charWidth = charImage.getWidth();
    	    int charHeight = charImage.getHeight();
    	    	    
    	    
    	    Glyph ch = new Glyph(charWidth, charHeight, x, image.getHeight() - charHeight);
    	    g.drawImage(charImage, x, 0, null);
    	    x += ch.width;
    	    glyphs.put(c, ch);
    	}
    	
    	buffer = Image.convertImage(image);
    }
    
    private BufferedImage createCharImage( java.awt.Font font, char c, boolean antiAlias ) {
    	BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = image.createGraphics();
    	if (antiAlias) {
    	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	}
    	g.setFont(font);
    	FontMetrics metrics = g.getFontMetrics();
    	g.dispose();
    	
    	int charWidth = metrics.charWidth(c);
    	int charHeight = metrics.getHeight();
    	
    	image = new BufferedImage(charWidth, charHeight, BufferedImage.TYPE_INT_ARGB);
    	g = image.createGraphics();
    	if (antiAlias) {
    	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	}
    	g.setFont(font);
    	g.setPaint(java.awt.Color.WHITE);
    	g.drawString(String.valueOf(c), 0, metrics.getAscent());
    	g.dispose();
    	return image;
    }
}
