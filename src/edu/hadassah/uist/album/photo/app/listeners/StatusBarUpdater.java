package edu.hadassah.uist.album.photo.app.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;



/**
 *
 * @author Sergey Persikov
 *
 */
public class StatusBarUpdater implements ActionListener {

	/** UI mediator for cross components interaction */
	private final IPhotoAlbumController mediator;

	/**
	 * Creates new instance of {@link StatusBarUpdater}
	 * @param name
	 * @param mediator
	 */
	public StatusBarUpdater(final IPhotoAlbumController mediator) {
		this.mediator = mediator;
	}

	/**
	 * update application status bar
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		mediator.setStatusMessage(e.getActionCommand() + " control were activated");

	}
}