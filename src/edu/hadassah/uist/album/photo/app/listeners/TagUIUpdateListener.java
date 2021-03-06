/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;

/**
 * Update UI with selected tags of the {@link IPhotoComponent}
 *
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public class TagUIUpdateListener implements ActionListener {


	private final AbstractButton button;
	private final PhotoTags tag;

	/**
	 * Creates new instance of {@link TagUIUpdateListener}
	 * @param tag
	 * @param button
	 */
	public TagUIUpdateListener(AbstractButton button, PhotoTags tag) {
		this.button = button;
		this.tag = tag;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getID() == tag.ordinal()) {
			button.setSelected(Boolean.valueOf(e.getActionCommand()));
		}
	}

}
