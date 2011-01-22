package edu.hadassah.uist.album.photo.app.component;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JComponent;

import edu.hadassah.uist.album.photo.app.listeners.MouseGestureListener;
import edu.hadassah.uist.album.photo.app.listeners.RemarkDrawListener;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;


/**
 * Photo component controller and UI
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public class PhotoComponent extends JComponent implements IPhotoComponent, ActionListener
{
	/** Model implementation of photo */
	private final PhotoModel photoModel;

	/** pane used to define listeners on the filled photo*/
	private final JComponent flippedPane;

	/**
	 * Creates new instance of {@link PhotoComponent}
	 * @param file
	 * @param mediator
	 */
	public PhotoComponent(File file, IPhotoAlbumController mediator){
		super();
		setDoubleBuffered(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		setOpaque(false);

		photoModel = new PhotoModel(file);

		flippedPane = new JComponent(){};
		flippedPane.setVisible(false);
		this.add(flippedPane);


		PhotoFlippListener doubleClickListener = new PhotoFlippListener();
		MouseGestureListener photoGesturesRecognizer = new MouseGestureListener(mediator, false);
		addMouseListener(doubleClickListener);
		addMouseListener(photoGesturesRecognizer);
		addMouseMotionListener(photoGesturesRecognizer);

		MouseGestureListener flippedGesturesRecognizer = new MouseGestureListener(mediator, true);
		flippedPane.addMouseListener(doubleClickListener);
		flippedPane.addMouseListener(flippedGesturesRecognizer);
		flippedPane.addMouseMotionListener(flippedGesturesRecognizer);
		RemarkDrawListener remarkDrawListener = new RemarkDrawListener(this, flippedPane, photoModel);
		flippedPane.addMouseListener(remarkDrawListener);
		flippedPane.addMouseMotionListener(remarkDrawListener);
	}


	/**
	 * @see edu.hadassah.uist.album.photo.model.component.IPhotoComponent#getPhotoModel()
	 */
	@Override
	public PhotoModel getPhotoModel() {
		return photoModel;
	}

	/**
	 * Paints photo, or its "flipped" side with remarks
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics graphics){
		Graphics2D g2d = (Graphics2D) graphics;

		paintBackground(g2d, photoModel);
		if (photoModel.isFlipped()){
			paintRemarks(g2d, photoModel);
			flippedPane.setVisible(true);
		}
		else{
			g2d.drawImage(photoModel.getImage(), 20, 20, flippedPane);
			flippedPane.setVisible(false);
		}

	}
	/**
	 * Paints background of the photo
	 * @param g2d {@link Graphics2D}
	 * @param model {@link PhotoModel}
	 */
	private void paintBackground(Graphics2D g2d, PhotoModel model){
		Dimension dimension = new Dimension(model.getImage().getWidth(flippedPane), model.getImage().getHeight(flippedPane));
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRoundRect(10, 10, dimension.width+20, dimension.height+20, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(10, 10, dimension.width+20, dimension.height+20, 20, 20);


	}
	/**
	 * Paints remarks (annotations) of the photo
	 * @param g2d {@link Graphics2D}
	 */
	private void paintRemarks(Graphics2D g2d, PhotoModel model) {
		Point oldPoint = null;

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		for (Remark currRemark : model.getAllRemarks()) {
			oldPoint = currRemark.getStartPoint();

			for (Point currPoint : currRemark.getPoints()) {
				if (! oldPoint.equals(currPoint) ){
					g2d.drawLine(oldPoint.x, oldPoint.y, currPoint.x, currPoint.y);
					oldPoint = currPoint;
				}
			}
		}
	}

	/**
	 * inner class that listen to double click
	 * @author Sergey Persikov
	 */
	final class PhotoFlippListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount() == 2) {
				getPhotoModel().flip();
				repaint();
			}
		}
	}

	/**
	 * Listener metod that trigerred by file load
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("image loaded".equals(e.getActionCommand()) ){
			flippedPane.setPreferredSize(new Dimension(photoModel.getImage().getWidth(this), photoModel.getImage().getHeight(this)));
		}
	}
}
