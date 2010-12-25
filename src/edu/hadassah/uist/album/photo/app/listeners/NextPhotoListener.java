/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Sergey Persikov
 *
 */
public class NextPhotoListener implements ActionListener, Runnable {


	private final IPhotoAlbumController mediator;
	private final boolean forward;

	public NextPhotoListener(final IPhotoAlbumController mediator, boolean forward) {
		super();
		this.mediator = mediator;
		this.forward = forward;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(this);

	}

	/**
	 *  void
	 */
	@Override
	public void run() {
		if ( forward ) {
			mediator.showNextPhoto();
		} else {
			mediator.showPreviousPhoto();
		}
	}
}
