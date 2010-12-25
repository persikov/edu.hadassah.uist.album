/**
 *
 */
package edu.hadassah.uist.album.photo.model.controller;

import java.awt.Component;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JLabel;


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

}
