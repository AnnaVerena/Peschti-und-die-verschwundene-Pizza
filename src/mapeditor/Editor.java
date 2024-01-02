package mapeditor;

import game.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Editor {
	
	 Map map;
	 final int SCALE = 2;
	 int tilesetWidth;
	
	public static void main(String[] args) throws IOException {
        new Editor().start();
    }
	
	public void start() throws IOException
	{
		JFrame editorFrame = new JFrame();

		editorFrame.setTitle("Mapeditor (Layer 0)");
		editorFrame.setSize(800, 600);

	    map = new Map(20, 15);
	    int[] layer = {0};

	    JMenuBar menuBar = new JMenuBar();
		JMenu mFile = new JMenu("File");
		JMenuItem mOpen = new JMenuItem("open");
		JMenuItem mSave = new JMenuItem("save");
		JMenuItem mNew = new JMenuItem("new");

		menuBar.add(mFile);
		mFile.add(mOpen);
		mFile.add(mSave);
		mFile.add(mNew);

		editorFrame.setJMenuBar(menuBar);
	    
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setCurrentDirectory(new File("."));

		mOpen.addActionListener(t -> {
            int result = fileChooser.showOpenDialog(editorFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File f = fileChooser.getSelectedFile();
                map = new Map(f);
            }
        });

		mSave.addActionListener(t -> {
			int result = fileChooser.showOpenDialog(editorFrame);
			if (result == JFileChooser.APPROVE_OPTION) {
				File f = fileChooser.getSelectedFile();
				map.toFile(f);
			}
		});

	    mNew.addActionListener(event -> {
            JFrame nFrame = new JFrame();
            GridLayout gridLayout = new GridLayout(0, 2);

            nFrame.setSize(230, 100);
            nFrame.setTitle("New Map");
            nFrame.setLayout(gridLayout);

            JLabel lWidth = new JLabel("Width:");
            JLabel lHeight = new JLabel("Height:");

            JTextField tWidth = new JTextField();
            JTextField tHeight = new JTextField();

            tWidth.setText("20");
            tHeight.setText("15");

            JButton bCreate = new JButton("Create");

            nFrame.add(lWidth);
            nFrame.add(lHeight);
            nFrame.add(tWidth);
            nFrame.add(tHeight);
            nFrame.add(bCreate);

            bCreate.addActionListener(t -> {
                int width = Integer.parseInt(tWidth.getText());
                int height = Integer.parseInt(tHeight.getText());
                map = new Map(width, height);

                nFrame.setVisible(false);
            });

            nFrame.setVisible(true);
        });

	    BufferedImage tileset = resample(ImageIO.read( new File("res/tilesets/Tileset2.png")), SCALE );
	    tilesetWidth = tileset.getWidth()/(16*SCALE);

		int[] tileId = new int[1];
		tileId[0] = 8;

	    JPanel canvas = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);

				this.setSize(map.getWidth()*16*SCALE, map.getHeight()*16*SCALE);

				g.setColor(Color.DARK_GRAY);
				g.fillRect(0,0,map.getWidth()*16*SCALE,map.getHeight()*16*SCALE);

				if( layer[0] < 3 )
				{
					for( int l = 0; l < layer[0]; l++ )
						for( int y = 0; y < map.getHeight(); y++ )
							for( int x = 0; x < map.getWidth(); x++ )
								renderTile( x, y, map.getTile(l, x, y), tileset, g );

					g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
					g.fillRect(0,0,map.getWidth()*16*SCALE,map.getHeight()*16*SCALE);

					for( int y = 0; y < map.getHeight(); y++ )
						for( int x = 0; x < map.getWidth(); x++ )
						{
							renderTile( x, y, map.getTile(layer[0], x, y), tileset, g );
							g.setColor(Color.GRAY);
							g.drawRect(x*16*SCALE, y*16*SCALE, 16*SCALE , 16*SCALE );
						}

				}
				else {
					for( int l = 0; l < 3; l++ )
						for( int y = 0; y < map.getHeight(); y++ )
							for( int x = 0; x < map.getWidth(); x++ )
								renderTile( x, y, map.getTile(l, x, y), tileset, g );

					if( layer[0] == 4 )
					{

						for( int y = 0; y < map.getHeight(); y++ )
							for( int x = 0; x < map.getWidth(); x++ ) {
								g.setColor(Color.GRAY);
								g.drawRect(x*16*SCALE , y*16*SCALE, 16*SCALE , 16*SCALE );

								if(map.isTileBlocked(x, y))
								{
									g.setColor( Color.RED);
									g.fillOval(x*16*SCALE + 4*SCALE, y*16*SCALE + 4*SCALE, 8*SCALE, 8*SCALE);
									g.setColor(Color.BLACK);
									g.drawOval(x*16*SCALE + 4*SCALE, y*16*SCALE + 4*SCALE, 8*SCALE, 8*SCALE);
								}
							}

					}
				}
			}

			public Dimension getPreferredSize(){
				return new Dimension(map.getWidth()*16*SCALE, map.getHeight()*16*SCALE);
			}
		};
		canvas.setSize(map.getWidth()*16*SCALE, map.getHeight()*16*SCALE);


	    JPanel tCanvas = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				this.setSize(tilesetWidth*16*SCALE,512*SCALE);

				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tilesetWidth*16*SCALE, 1024*SCALE);
				g.drawImage(tileset, 0, 0, null);

				g.setColor(Color.RED);
				g.drawRect( tileId[0]%tilesetWidth*16*SCALE, tileId[0]/tilesetWidth*16*SCALE, 16*SCALE, 16*SCALE);
			}

			public Dimension getPreferredSize(){
				return new Dimension(tilesetWidth*16*SCALE,512*SCALE);
			}
		};
		tCanvas.setSize(tilesetWidth*16*SCALE,512*SCALE );

		JScrollPane scrollPane = new JScrollPane(canvas, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		JScrollPane scrollPane2 = new JScrollPane(tCanvas, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		editorFrame.add(scrollPane, BorderLayout.CENTER);
		editorFrame.add(scrollPane2, BorderLayout.EAST);

		canvas.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tx = e.getX() /(16*SCALE);
				int ty = e.getY() /(16*SCALE);
				if( layer[0] < 3 ) map.setTile(layer[0], tx, ty, tileId[0]);
				if( layer[0] == 4 ) map.setTileBlocked(tx, ty, !map.isTileBlocked(tx, ty));
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		canvas.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int tx = e.getX() /(16*SCALE);
				int ty = e.getY() /(16*SCALE);
				if( layer[0] < 3 ) map.setTile(layer[0], tx, ty, tileId[0]);
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

		tCanvas.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tileId[0] = e.getX() /(16*SCALE) + e.getY() /(16*SCALE)*tilesetWidth;
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		editorFrame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if( e.getKeyChar() == '1')
				{
					editorFrame.setTitle( "Mapeditor (Layer 0)" );
					layer[0] = 0;
				}
				else if( e.getKeyChar() == '2')
				{
					editorFrame.setTitle( "Mapeditor (Layer 1)" );
					layer[0] = 1;
				}
				else if( e.getKeyChar() == '3' )
				{
					editorFrame.setTitle( "Mapeditor (Layer 2)" );
					layer[0] = 2;
				}
				else if( e.getKeyChar() == '4' )
				{
					editorFrame.setTitle( "Mapeditor (View all)" );
					layer[0] = 3;
				}
				else if( e.getKeyChar() == 'b')
				{
					editorFrame.setTitle( "Mapeditor (Blocked tiles)" );
					layer[0] = 4;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		//editorFrame.pack();
		editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    editorFrame.setVisible(true);
	}
	
	private void renderTile( int x, int y, int tileId, BufferedImage tileset, Graphics g )
	{
		BufferedImage tileImage = tileset.getSubimage((tileId%tilesetWidth) * 16 * SCALE,  (tileId/tilesetWidth)*16*SCALE, 16*SCALE,16*SCALE);
		g.drawImage(tileImage, x*16*SCALE, y*16*SCALE, null );
	}

	private BufferedImage resample(BufferedImage input, int scaleFactor) {
		final int W = input.getWidth();
		final int H = input.getHeight();

		BufferedImage output = new BufferedImage(
				W * scaleFactor,
				H * scaleFactor,
				BufferedImage.TYPE_INT_ARGB
		);

		AffineTransform at = new AffineTransform();
		at.scale(scaleFactor, scaleFactor);
		AffineTransformOp scaleOp =
				new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		output = scaleOp.filter(input, output);

		return output;
	}
}
