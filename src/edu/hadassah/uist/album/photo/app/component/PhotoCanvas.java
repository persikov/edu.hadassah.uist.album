package edu.hadassah.uist.album.photo.app.component;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author Sergey Persikov
 *
 */
final class PhotoCanvas extends JPanel {
	private final PhotoCanvasUI photoUI;
	/**
	 * Creates new instance of {@link PhotoCanvas}
	 * @param isDoubleBuffered
	 */
	PhotoCanvas(PhotoModel photoModel ) {
		super(true);
		photoUI = new PhotoCanvasUI(photoModel, this);
	}

	/**
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponents(Graphics g) {
		photoUI.paintComponent(g);
	}
}