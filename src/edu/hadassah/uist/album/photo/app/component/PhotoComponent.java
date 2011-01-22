package edu.hadassah.uist.album.photo.app.component;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.ComponentUI;

import edu.hadassah.uist.album.photo.app.listeners.MouseGestureListener;
import edu.hadassah.uist.album.photo.app.listeners.RemarkDrawListener;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;


public class PhotoComponent extends JComponent implements IPhotoComponent
{
	/**
	 * @author Sergey Persikov
	 *
	 */
	private final class ComponentUIExtension extends ComponentUI {
		/**
		 * @param photo
		 * @param graphics void
		 */
		public void paintComponent(PhotoComponent photo, Graphics graphics){
			PhotoModel model = photo.getModel();
			Graphics2D g2 = (Graphics2D) graphics;
			paintBackground(g2, photo);
			if (model.flipped){
				paintRemarks(g2, model);
				canvas.setVisible(true);
			}
			else{
				g2.drawImage(model.getImage(), 20, 20, photo);
				canvas.setVisible(false);
			}

		}
		/**
		 * @param g void
		 */
		private void paintRemarks(Graphics2D g, PhotoModel model) {
			Point oldPoint = null;

			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			for (Remark currRemark : model.getAllRemarks()) {
				oldPoint = currRemark.getStartPoint();

				for (Point currPoint : currRemark.getPoints()) {
					if (! oldPoint.equals(currPoint) ){
						g.drawLine(oldPoint.x, oldPoint.y, currPoint.x, currPoint.y);
						oldPoint = currPoint;
					}
				}
			}
		}

		/**
		 * @param g
		 * @param photo void
		 */
		private void paintBackground(Graphics2D g2, PhotoComponent photo)
		{
			PhotoModel model = photo.getModel();
			Dimension dimension = new Dimension(model.getImage().getWidth(photo), model.getImage().getHeight(photo));
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRoundRect(10, 10, dimension.width+20, dimension.height+20, 20, 20);
			g2.setColor(Color.BLACK);
			g2.drawRoundRect(10, 10, dimension.width+20, dimension.height+20, 20, 20);


		}

	}


	final class PhotoFlippListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount() == 2) {
				getModel().flip();
				repaint();
			}
		}
	}

	private final PhotoModel photoModel;
	private final ComponentUIExtension photoUI;
	private final JComponent canvas;

	public PhotoComponent(File file, IPhotoAlbumController mediator){

		setDoubleBuffered(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		setOpaque(false);
		//### isOptimizedDrawingEnabled

		photoModel = new PhotoModel(file);
		photoUI = new ComponentUIExtension();
		canvas = new JPanel(true){

			/**
			 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
			 */
			@Override
			public void paintComponents(Graphics g) {
				photoUI.paintComponent(PhotoComponent.this, g);
			}

		};
		this.add(canvas);
		canvas.setVisible(false);
		canvas.setBackground(Color.GREEN);
		canvas.setOpaque(false);


		PhotoFlippListener doubleClickListener = new PhotoFlippListener();
		MouseGestureListener photoGesturesRecognizer = new MouseGestureListener(mediator, false);
		addMouseListener(doubleClickListener);
		addMouseListener(photoGesturesRecognizer);
		addMouseMotionListener(photoGesturesRecognizer);

		MouseGestureListener flippedGesturesRecognizer = new MouseGestureListener(mediator, true);
		canvas.addMouseListener(doubleClickListener);
		canvas.addMouseListener(flippedGesturesRecognizer);
		canvas.addMouseMotionListener(flippedGesturesRecognizer);
		RemarkDrawListener remarkDrawListener = new RemarkDrawListener(this, canvas, photoModel);
		canvas.addMouseListener(remarkDrawListener);
		canvas.addMouseMotionListener(remarkDrawListener);
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
		canvas.paintComponents(g);
//		super.paintComponent(g);
//		if (photoModel.flipped){
//			Graphics2D graphics2d = (Graphics2D) canvas.getGraphics();
//			paintRemarks(graphics2d);
//			canvas.setVisible(true);
//		}
//		else{
//			canvas.setVisible(false);
//			Graphics2D graphics2d = (Graphics2D) g;
//			graphics2d.drawImage(photoModel.getImage(), 20, 20, this);
//		}
	}



	/**
	 * @return the photoModel
	 */
	@Override
	public PhotoModel getModel() {
		return photoModel;
	}


}
