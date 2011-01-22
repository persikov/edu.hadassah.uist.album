/**
 *
 */
package edu.hadassah.uist.album.photo.model.controller;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.EnumSet;

import javax.swing.JComponent;
import javax.swing.JLabel;

import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;


/**
 * Interface application UI mediator (controller) for cross component actions
 *
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public interface IPhotoAlbumController {

	/**
	 * show next photo
	 */
	public void showNextPhoto();

	/**
	 * shows previous photo
	 */
	public void showPreviousPhoto();

	/**
	 * updates application status bar
	 */
	public void setStatusMessage(String message);

	/**
	 * set status bar component to the controller
	 * @param statusBar
	 */
	public void setStatusBar(JLabel statusBar);

	/**
	 * Set application content panel
	 * @param contentPanel
	 */
	public void setContentPanel(JComponent contentPanel);

	/**
	 * @return pplication content panel
	 */
	public Component getContentPanel();

	/**
	 * Creates and add new {@link IPhotoComponent} to the application and UI
	 * @param file image file
	 */
	public void addPhotoComponent(File file);

	/**
	 * Creates and add new {@link IPhotoComponent} to the application and UI
	 * @param files image files
	 */
	public void addPhotoComponents(File[] files);

	/**
	 *  remove current {@link IPhotoComponent} from the application model and IU
	 */
	public void removeCurrentComponent();

	/**
	 * Add/remove component's tag to the component model
	 * @param tag
	 */
	public void toggleCurrentComponentTag(PhotoTags tag);

	/**
	 * update UI with set of tags
	 * @param tags
	 */
	public void updateUISelectedTags(EnumSet<PhotoTags> tags);

	/**
	 * remove listener from the mediator
	 * @param listener
	 */
	public abstract void removeActionListener(ActionListener listener);

	/**
	 * add listener to the mediator
	 * @param listener
	 */
	public abstract void addActionListener(ActionListener listener);

	/**
	 *  remove annotations (remarks) from the currentlu shown component
	 */
	public void removeRemarks();

}
