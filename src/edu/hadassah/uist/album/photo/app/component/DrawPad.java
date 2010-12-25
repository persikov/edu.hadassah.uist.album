package edu.hadassah.uist.album.photo.app.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;


public class DrawPad extends JComponent {
//	Image image;
//	public Graphics2D graphics2D;
	int currentX, currentY, oldX, oldY;
	private int height;
	private int width;


	public DrawPad(/*final Graphics2D graphics2D, Dimension photoDim*/)
	{
//		this.graphics2D = graphics2D;
//		graphics2D.setColor(Color.BLACK);
//		graphics2D.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("MouseMotionAdapter:mousePressed");
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("MouseMotionAdapter:mouseDragged");
				Graphics2D graphics2D = (Graphics2D)getGraphics();
				graphics2D.setColor(Color.BLACK);
				graphics2D.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				currentX = e.getX();
				currentY = e.getY();
				graphics2D.drawLine(oldX, oldY, currentX, currentY);
				graphics2D.dispose();
				repaint();
				oldX = currentX;
				oldY = currentY;
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {

		((Graphics2D)g).setPaint(Color.LIGHT_GRAY);
		g.fillRect(20, 20 , getWidth(), getHeight() );

//		if (image == null) {
//			//image = createImage(getSize().width, getSize().height);
//			//graphics2D = (Graphics2D) image.getGraphics();
//			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//					RenderingHints.VALUE_ANTIALIAS_ON);
//			//clear();
//		}
//		g.drawImage(image, 0, 0, null);
	}

	public void clear() {
//		graphics2D.setPaint(Color.white);
//		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
//		graphics2D.setPaint(Color.black);
//		repaint();
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getWidth() {
		return width;
	}
}


//public class DrawPad extends JComponent {
//	Graphics2D graphics2D;
//	int currentX, currentY, oldX, oldY;
//
//	GeneralPath stroke;
//	PhotoModel model;
//
//	public DrawPad(final PhotoModel model)
//	{
//		this.model = model;
//
//		//this.setDoubleBuffered(false);
//		this.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e)
//			{
//				stroke = new GeneralPath();
//				stroke.moveTo(e.getX(), e.getY());
//			}
//
//			public void mouseReleased(MouseEvent e)
//			{
//				model.addRemark(stroke);
//			}
//		});
//
//		this.addMouseMotionListener(new MouseMotionAdapter() {
//			public void mouseDragged(MouseEvent e) {
//				currentX = e.getX();
//				currentY = e.getY();
//				if (graphics2D != null)
//					graphics2D.drawLine(oldX, oldY, currentX, currentY);
//				repaint();
//				oldX = currentX;
//				oldY = currentY;
//
//				stroke.lineTo(e.getX(), e.getY());
//			}
//		});
//	}
//}
