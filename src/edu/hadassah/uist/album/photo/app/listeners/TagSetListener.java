/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Sergey Persikov
 *
 */
public class TagSetListener implements ActionListener {

	/** UI mediator for cross components interaction */
	private final IPhotoAlbumController mediator;
	private final PhotoTags tag;

	/**
	 * Creates new instance of {@link TagSetListener}
	 * @param mediator
	 */
	public TagSetListener(IPhotoAlbumController mediator, PhotoTags tag) {
		super();
		this.mediator = mediator;
		this.tag = tag;
	}
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		mediator.tagCurrentComponent(tag);
	}

}
