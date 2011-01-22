package edu.hadassah.uist.album.photo.app.component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;

import edu.hadassah.uist.album.photo.app.listeners.MouseGestureListener;
import edu.hadassah.uist.album.photo.app.listeners.RemarkDrawListener;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;


public class PhotoComponent extends JComponent implements IPhotoComponent
{
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
	private final JComponent canvas;

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
		canvas = new PhotoComponentUI(photoModel);
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
	protected void paintComponent(Graphics g){
		canvas.paintComponents(g);
	}

	/**
	 * @return the photoModel
	 */
	@Override
	public PhotoModel getModel() {
		return photoModel;
	}
}
