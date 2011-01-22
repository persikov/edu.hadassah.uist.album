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

	/**
	 *
	 */
	private static final String NOTHING_TO_SHOW = "album.ui.main.window.controller.nothing.to.show";
	/** application status bar */
	private JLabel statusBar;
	private final IPhotoAlbumModel albumModel;
	private final PhotoComponentFactory photoComponentFactory = new PhotoComponentFactory();
	private JComponent contentPanel;
	protected List<ActionListener> listeners = new ArrayList<ActionListener>();

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
	 * @param photoComponents void
	 */
	private void showPhoto(IPhotoComponent photoComponents) {
		if ( photoComponents == null){
			setStatusMessage(MessagesUtils.getString(NOTHING_TO_SHOW));
		} else {
			contentPanel.removeAll();
			contentPanel.add((JComponent)photoComponents);
			contentPanel.invalidate();
			contentPanel.revalidate();
			photoComponents.getModel().raiseActionEvent("shown");
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
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#getMainComponent()
	 */
	@Override
	public Component getMainComponent() {
		return contentPanel;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#addPhotoComponent(java.io.File)
	 */
	@Override
	public void addPhotoComponent(File file) {
		IPhotoComponent photoComponent;
		try {
			photoComponent = photoComponentFactory.createPhotoComponent(file, this);
			albumModel.addPhotoComponent(photoComponent);
			contentPanel.removeAll();
			contentPanel.add((JComponent)photoComponent);
		} catch (IOException e) {
			setStatusMessage("Fails to load photoModel from " + file.getAbsolutePath()); //$NON-NLS-1$
		}

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
			try {
				photoComponent = photoComponentFactory.createPhotoComponent(currFile, this);
				albumModel.addPhotoComponent(photoComponent);
			} catch (IOException e) {
				setStatusMessage("Fails to load photoModel from " + currFile.getAbsolutePath()); //$NON-NLS-1$
			}
		}
		if ( photoComponent != null){
			contentPanel.removeAll();
			contentPanel.add((JComponent)photoComponent);
		}

	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#toggleCurrentComponentTag(edu.hadassah.uist.album.photo.app.component.PhotoTags)
	 */
	@Override
	public void toggleCurrentComponentTag(PhotoTags tag) {
		albumModel.toggleCurrentComponentTag(tag);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#setSelectedTags(java.util.EnumSet)
	 */
	@Override
	public void setSelectedTags(EnumSet<PhotoTags> tags) {
		for (PhotoTags currTag : PhotoTags.values()) {
			if (tags.contains(currTag) ){
				raiseActionEvent("true", currTag.ordinal());
			} else {
				raiseActionEvent("false", currTag.ordinal());
			}
		}
	}

	protected void raiseActionEvent(String command, int eventId){
		for (ActionListener l : listeners) {
			l.actionPerformed(new ActionEvent(this, eventId, command));
		}
	}

	@Override
	public void addActionListener(ActionListener listener){
		if (listeners.contains(listener)) {
			return;
		}
		listeners.add(listener);
	}

	@Override
	public void removeActionListener(ActionListener listener){
		if (!listeners.contains(listener)) {
			return;
		}
		listeners.remove(listener);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController#removeCurrentAnnotations()
	 */
	@Override
	public void removeCurrentAnnotations() {
		albumModel.removeCurrentAnnotations();
	}

}
