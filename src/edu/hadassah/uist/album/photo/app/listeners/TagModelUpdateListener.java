/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.hadassah.uist.album.photo.app.component.PhotoModel;
import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * Listener to update {@link PhotoModel} with selected tag
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public class TagModelUpdateListener implements ActionListener {

	/** UI mediator for cross components interaction */
	private final IPhotoAlbumController mediator;
	private final PhotoTags tag;

	/**
	 * Creates new instance of {@link TagModelUpdateListener}
	 * @param mediator
	 */
	public TagModelUpdateListener(IPhotoAlbumController mediator, PhotoTags tag) {
		super();
		this.mediator = mediator;
		this.tag = tag;
	}
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		mediator.toggleCurrentComponentTag(tag);
	}

}
