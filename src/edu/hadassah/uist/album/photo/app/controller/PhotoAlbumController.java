/**
 *
 */
package edu.hadassah.uist.album.photo.app.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.app.factory.PhotoComponentFactory;
import edu.hadassah.uist.album.photo.app.utils.MessagesUtils;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;
import edu.hadassah.uist.album.photo.model.data.IPhotoAlbumModel;

/**
 * Controller of the Photo Album application
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public class PhotoAlbumController implements IPhotoAlbumController {

	private static final String NOTHING_TO_SHOW = "album.ui.main.window.controller.nothing.to.show";

	/** album data model */
	private final IPhotoAlbumModel albumModel;
	/** album's UI components factory*/
	private final PhotoComponentFactory photoComponentFactory = new PhotoComponentFactory();
	/** listeners of the controller */
	protected List<ActionListener> listeners = new ArrayList<ActionListener>();
	/** application status bar */
	private JLabel statusBar;
	/** album's content panel*/
	private JComponent contentPanel;

	/**
	 * Creates new instance of {@link PhotoAlbumController}
	 * @param albumModel
	 */
	public PhotoAlbumController(IPhotoAlbumModel albumModel) {
		this.albumModel = albumModel;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#setStatusMessage(java.lang.String)
	 */
	@Override
	public void setStatusMessage(final String message) {
		if ( statusBar == null) {
			// TODO use log here
			System.err.println("Failed to update status. Status bar was not set to controller"); //$NON-NLS-1$
		} else {
			statusBar.setText(message);
		}

	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#setStatusBar(javax.swing.JLabel)
	 */
	@Override
	public void setStatusBar(final JLabel statusBar) {
		this.statusBar = statusBar;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#showNextPhoto()
	 */
	@Override
	public void showNextPhoto() {
		IPhotoComponent nextPhotoComponents = albumModel.getNextPhotoComponents();
		showPhoto(nextPhotoComponents);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#showPreviousPhoto()
	 */
	@Override
	public void showPreviousPhoto() {
		IPhotoComponent previousPhotoComponents = albumModel.getPreviousPhotoComponents();
		showPhoto(previousPhotoComponents);
	}

	/**
	 * Shows given {@link IPhotoComponent} in content panel
	 * @param photoComponents
	 */
	private void showPhoto(IPhotoComponent photoComponents) {
		if ( photoComponents == null){
			setStatusMessage(MessagesUtils.getString(NOTHING_TO_SHOW));
			contentPanel.removeAll();
			contentPanel.repaint();
		} else {
			contentPanel.removeAll();
			contentPanel.add((JComponent)photoComponents);
			contentPanel.invalidate();
			contentPanel.revalidate();
			try {
				photoComponents.getPhotoModel().loadPhoto();
			} catch (IOException e) {
				setStatusMessage("Fails to load photoModel from " + photoComponents.getPhotoModel().getFile().getAbsolutePath());
			}
			contentPanel.repaint();
		}
	}


	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#setContentPanel(javax.swing.JComponent)
	 */
	@Override
	public void setContentPanel(JComponent contentPanel) {
		this.contentPanel = contentPanel;

	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#getContentPanel()
	 */
	@Override
	public Component getContentPanel() {
		return contentPanel;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#addPhotoComponent(java.io.File)
	 */
	@Override
	public void addPhotoComponent(File file) {
		IPhotoComponent photoComponent;
		photoComponent = photoComponentFactory.createPhotoComponent(file, this);
		albumModel.addPhotoComponent(photoComponent);
		showPhoto(photoComponent);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#removeCurrentComponent()
	 */
	@Override
	public void removeCurrentComponent() {
		Component comp = albumModel.removeCurrentComponent();
		if (comp != null){
			contentPanel.remove(comp);
		}
		showNextPhoto();
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#addPhotoComponents(java.io.File[])
	 */
	@Override
	public void addPhotoComponents(File[] files) {
		IPhotoComponent photoComponent = null;
		for (File currFile : files) {
			photoComponent = photoComponentFactory.createPhotoComponent(currFile, this);
			albumModel.addPhotoComponent(photoComponent);
		}
		showPhoto(photoComponent);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#toggleCurrentComponentTag(edu.hadassah.uist.album.photo.app.component.PhotoTags)
	 */
	@Override
	public void toggleCurrentComponentTag(PhotoTags tag) {
		albumModel.toggleCurrentComponentTag(tag);
	}

	/**
	 * Raise event to the listeners that tag was selected
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#updateUISelectedTags(java.util.EnumSet)
	 */
	@Override
	public void updateUISelectedTags(EnumSet<PhotoTags> tags) {
		for (PhotoTags currTag : PhotoTags.values()) {
			if (tags.contains(currTag) ){
				raiseActionEvent("true", currTag.ordinal());
			} else {
				raiseActionEvent("false", currTag.ordinal());
			}
		}
	}

	/**
	 * Fire event to all registered listeners
	 * @param command
	 * @param eventId
	 */
	protected void raiseActionEvent(String command, int eventId){
		ActionEvent event = new ActionEvent(this, eventId, command);
		for (ActionListener l : listeners) {
			l.actionPerformed(event);
		}
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#addActionListener(java.awt.event.ActionListener)
	 */
	@Override
	public void addActionListener(ActionListener listener){
		if (listeners.contains(listener)) {
			return;
		}
		listeners.add(listener);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#removeActionListener(java.awt.event.ActionListener)
	 */
	@Override
	public void removeActionListener(ActionListener listener){
		if (!listeners.contains(listener)) {
			return;
		}
		listeners.remove(listener);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#removeRemarks()
	 */
	@Override
	public void removeRemarks() {
		albumModel.removeCurrentAnnotations();
	}

}
