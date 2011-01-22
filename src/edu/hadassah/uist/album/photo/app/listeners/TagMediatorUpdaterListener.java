/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;
import edu.hadassah.uist.album.photo.model.component.ITagable;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * Update mediator with selected tags of the {@link IPhotoComponent}
 *
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public class TagMediatorUpdaterListener implements ActionListener{

	private final IPhotoAlbumController mediator;

	/**
	 * Creates new instance of {@link TagMediatorUpdaterListener}
	 * @param mediator
	 */
	public TagMediatorUpdaterListener(IPhotoAlbumController mediator) {
		this.mediator = mediator;
	}


	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof ITagable){
			ITagable photoModel = (ITagable)source;
			mediator.updateUISelectedTags(photoModel.getTags());
		}

	}

}
