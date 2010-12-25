package edu.hadassah.uist.album.photo.app.component;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;


public class PhotoComponent extends JPanel implements IPhotoComponent
{
	protected PhotoModel photo;
	protected DoubleClickListener dblClickListener;
//	protected DrawPad drawPad;
	int currentX, currentY, oldX, oldY;


//	protected Graphics2D graphics2d;

	protected static String pictureFrame = "Images"+System.getProperty("file.separator")+"cod.bmp";

	public PhotoComponent(File file){

		setDoubleBuffered(true);
		setOpaque(true);
		//### isOptimizedDrawingEnabled

		photo = new PhotoModel(file);


//		photo.addActionListener(dblClickListener);
//		drawPad = new DrawPad();//photo);
//		drawPad.setVisible(false);
//		this.add(drawPad, BorderLayout.CENTER);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2) {
					photo.flip();
					repaint();
				}
			}
//			@Override
//			public void mousePressed(MouseEvent e) {
//			}
		});
//		addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseDragged(MouseEvent e) {
//				System.out.println("MouseMotionAdapter:mouseDragged");
//			}
//		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				System.out.println("MouseMotionAdapter:mousePressed");
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
//				System.out.println("MouseMotionAdapter:mouseDragged");
				Graphics2D graphics2d = (Graphics2D)photo.getImage().getGraphics();
				graphics2d.setColor(Color.BLACK);
				graphics2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				currentX = e.getX();
				currentY = e.getY();
				graphics2d.drawLine(oldX, oldY, currentX, currentY);
				graphics2d.dispose();
				repaint();
				oldX = currentX;
				oldY = currentY;
			}
		});
	}
//	public class DoubleClickListener implements ActionListener
//	{
//		JComponent component;
//
//		public DoubleClickListener(JComponent component)
//		{
//			this.component = component;
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			component.repaint();
//		}
//	}

	public void loadPhoto() throws IOException{
		photo.loadPhoto();
//		drawPad.setHeight(photo.getImage().getHeight(this));
//		drawPad.setWidth(photo.getImage().getWidth(this));
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (photo.flipped)
		{

//			drawPad.setVisible(true);
			//			Graphics2D imageGraphics = (Graphics2D)photo.getImage().getGraphics();

			graphics2d.setPaint(Color.LIGHT_GRAY);
			graphics2d.fillRect(20, 20 , photo.getImage().getWidth(this), photo.getImage().getHeight(this) );

			//			paintBackground(graphics2d, Color.white);
			paintRemarks();
		}
		else
		{
//			drawPad.setVisible(false);
//			paintBackground(graphics2d, Color.orange);
//			paintFrameImage(graphics2d);
			graphics2d.drawImage(photo.getImage(), 20, 20, null);

//			drawPad.setVisible(false);
		}
	}

	private void paintRemarks() {
		// TODO Auto-generated method stub
	}

	protected void paintBackground(Graphics2D g, Color color)
	{
		g.setPaint(color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintFrameImage(Graphics2D g) {
		try
		{
			File file = new File(pictureFrame);
			Image image = ImageIO.read(file);
			g.drawImage(image, 0, 0, null);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
	    JFrame frame = new JFrame();
	    File file = new File(pictureFrame);
	    final PhotoComponent drawPad = new PhotoComponent(file);
	    frame.add(drawPad, BorderLayout.CENTER);
	    JButton clearButton = new JButton("Clear");
//	    clearButton.addActionListener(new ActionListener() {
//	      public void actionPerformed(ActionEvent e) {
//	        drawPad.paintBackground();
//	      }
//	    });
	    frame.add(clearButton, BorderLayout.SOUTH);
	    frame.setSize(700, 280);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	  }


}
