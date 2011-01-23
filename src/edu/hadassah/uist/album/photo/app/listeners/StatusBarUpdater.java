package edu.hadassah.uist.album.photo.app.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.hadassah.uist.album.photo.app.utils.MessagesUtils;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;



/**
 * Listener for updates of the status bar
 *
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public class StatusBarUpdater implements ActionListener {

	/** UI mediator for cross components interaction */
	private final IPhotoAlbumController mediator;

	/**
	 * Creates new instance of {@link StatusBarUpdater}
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
		mediator.setStatusMessage(e.getActionCommand() + MessagesUtils.getString("album.ui.main.window.status.activated")); //$NON-NLS-1$

	}
}