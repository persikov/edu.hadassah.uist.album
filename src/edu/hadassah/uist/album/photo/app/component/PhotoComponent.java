package edu.hadassah.uist.album.photo.app.component;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;


public class PhotoComponent extends JPanel implements IPhotoComponent
{
	/**
	 * @author Sergey Persikov
	 *
	 */
	private final class MouseAdapterExtension extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount() == 2) {
				photo.flip();
				repaint();
			}
		}
	}

	protected PhotoModel photo;
	protected DoubleClickListener dblClickListener;
	protected int currentX, currentY, oldX, oldY;
	protected final JPanel canvas;
	private Color currColor = Color.BLACK;


//	protected Graphics2D graphics2d;

	protected static String pictureFrame = "Images"+System.getProperty("file.separator")+"cod.bmp";

	public PhotoComponent(File file){

		setDoubleBuffered(true);
		setOpaque(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		setOpaque(true);
		//### isOptimizedDrawingEnabled

		photo = new PhotoModel(file);
		canvas = new JPanel();
		this.add(canvas);
		canvas.setVisible(false);
		canvas.setBackground(Color.LIGHT_GRAY);
		canvas.setDoubleBuffered(false);

		MouseAdapterExtension doubleClickListener = new MouseAdapterExtension();
		addMouseListener(doubleClickListener);
		canvas.addMouseListener(doubleClickListener);
		canvas.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
//				System.out.println("MouseMotionAdapter:mousePressed");
				oldX = e.getX();
				oldY = e.getY();
				Graphics2D graphics2d = (Graphics2D)canvas.getGraphics();
				graphics2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				if (e.getButton() == MouseEvent.BUTTON3){
					currColor=Color.RED;
				} else {
					photo.startNewRemark(oldX, oldY);
					currColor=Color.BLACK;
				}
			}
		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
//				System.out.println("MouseMotionAdapter:mouseDragged");
				Graphics2D graphics2d = (Graphics2D)canvas.getGraphics();
				graphics2d.setColor(currColor);
				graphics2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				currentX = e.getX();
				currentY = e.getY();
				graphics2d.drawLine(oldX, oldY, currentX, currentY);
//				graphics2d.dispose();
				oldX = currentX;
				oldY = currentY;
				if (e.getButton() == MouseEvent.BUTTON1){
					photo.addRemarkPoint(currentX, currentY);
				}
			}
		});
	}

	public void loadPhoto() throws IOException{
		photo.loadPhoto();
		canvas.setPreferredSize(new Dimension(photo.getImage().getWidth(this), photo.getImage().getHeight(this)));
	}

	/**
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (photo.flipped){
			Graphics2D graphics2d = (Graphics2D) canvas.getGraphics();
			paintRemarks(graphics2d);
			canvas.setVisible(true);
		}
		else{
			canvas.setVisible(false);
			Graphics2D graphics2d = (Graphics2D) g;
			graphics2d.drawImage(photo.getImage(), 20, 20, this);
		}
	}

	private void paintRemarks(Graphics2D g) {
		Point oldPoint = null;

		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		for (Remark currRemark : photo.getAllRemarks()) {
			oldPoint = currRemark.getStartPoint();

			for (Point currPoint : currRemark.getPoints()) {
				if (! oldPoint.equals(currPoint) ){
					g.drawLine(oldPoint.x, oldPoint.y, currPoint.x, currPoint.y);
					oldPoint = currPoint;
				}
			}
		}
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

}
