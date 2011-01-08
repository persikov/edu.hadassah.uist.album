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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.hadassah.uist.album.photo.app.listeners.MouseGestureListener;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;


public class PhotoComponent extends JPanel implements IPhotoComponent
{
	final class PhotoFlippListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount() == 2) {
				photoModel.flip();
				repaint();
			}
		}
	}

	protected PhotoModel photoModel;
	protected DoubleClickListener dblClickListener;
	protected final JPanel canvas;

//	protected Graphics2D graphics2d;

	protected static String pictureFrame = "Images"+System.getProperty("file.separator")+"cod.bmp";

	public PhotoComponent(File file, IPhotoAlbumController mediator){

		setDoubleBuffered(true);
		setOpaque(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		setOpaque(true);
		//### isOptimizedDrawingEnabled

		photoModel = new PhotoModel(file);
		canvas = new JPanel();
		this.add(canvas);
		canvas.setVisible(false);
		canvas.setBackground(Color.GREEN);
		canvas.setDoubleBuffered(true);

		PhotoFlippListener doubleClickListener = new PhotoFlippListener();
		MouseGestureListener gesturesRecognizer = new MouseGestureListener(mediator);
		addMouseListener(doubleClickListener);
		addMouseListener(gesturesRecognizer);
		addMouseMotionListener(gesturesRecognizer);

		canvas.addMouseListener(doubleClickListener);
		canvas.addMouseListener(gesturesRecognizer);
		canvas.addMouseMotionListener(gesturesRecognizer);
		RemarkDrawListener remarkDrawListener = new RemarkDrawListener(this, canvas, photoModel);
		canvas.addMouseListener(remarkDrawListener);
		canvas.addMouseMotionListener(remarkDrawListener);
	}

	public Graphics getCanvasGraphics(){
		return canvas.getGraphics();
	}

	public void loadPhoto() throws IOException{
		photoModel.loadPhoto();
		canvas.setPreferredSize(new Dimension(photoModel.getImage().getWidth(this), photoModel.getImage().getHeight(this)));
	}

	/**
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (photoModel.flipped){
			Graphics2D graphics2d = (Graphics2D) canvas.getGraphics();
			paintRemarks(graphics2d);
			canvas.setVisible(true);
		}
		else{
			canvas.setVisible(false);
			Graphics2D graphics2d = (Graphics2D) g;
			graphics2d.drawImage(photoModel.getImage(), 20, 20, this);
		}
	}

	private void paintRemarks(Graphics2D g) {
		Point oldPoint = null;

		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		for (Remark currRemark : photoModel.getAllRemarks()) {
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
