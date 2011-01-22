package edu.hadassah.uist.album.photo.app.component;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author Sergey Persikov
 *
 */
final class PhotoComponentUI extends JPanel {
	private final InnerPhotoComponentUI photoUI;
	/**
	 * Creates new instance of {@link PhotoComponentUI}
	 * @param isDoubleBuffered
	 */
	PhotoComponentUI(PhotoModel photoModel ) {
		super(true);
		photoUI = new InnerPhotoComponentUI(photoModel, this);
	}

	/**
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponents(Graphics g) {
		photoUI.paintComponent(g);
	}
}