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


/**
 * @author persikov
 *
 */
public interface IPhotoAlbumController {

	/**
	 */
	public void showNextPhoto();

	/**
	 */
	public void showPreviousPhoto();

	/**
	 * update application status bar
	 */
	public void setStatusMessage(String message);

	/**
	 * set status bar component to the controller
	 * @param statusBar
	 */
	public void setStatusBar(JLabel statusBar);

	/**
	 * @param contentPanel void
	 */
	public void setContentPanel(JComponent contentPanel);

	public Component getMainComponent();

	/**
	 * @param file void
	 */
	public void addPhotoComponent(File file);

	public void addPhotoComponents(File[] files);

	public void removeCurrentComponent();

	/**
	 * @param tag void
	 */
	public void toggleCurrentComponentTag(PhotoTags tag);

	/**
	 * @param tags void
	 */
	public void setSelectedTags(EnumSet<PhotoTags> tags);

	public abstract void removeActionListener(ActionListener listener);

	public abstract void addActionListener(ActionListener listener);

}
