/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.hadassah.uist.album.photo.app.component.ITagable;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Sergey Persikov
 *
 */
public class TagUIUpdaterListener implements ActionListener, ChangeListener {

	private final IPhotoAlbumController mediator;

	/**
	 * Creates new instance of {@link TagUIUpdaterListener}
	 * @param mediator
	 */
	public TagUIUpdaterListener(IPhotoAlbumController mediator) {
		this.mediator = mediator;
	}

	/**
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof ITagable){
			ITagable photoModel = (ITagable)source;
//			mediator.setSelectedTags(photoModel.getTags());
		}

	}

}
