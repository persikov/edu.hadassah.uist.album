package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import edu.hadassah.uist.album.photo.app.utils.MessagesUtils;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * Listener class for import single file
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public class ImportPhotoListener extends AImportPhotoListener  {
	/**
	 * Creates new instance of {@link ImportPhotoListener}
	 * @param mediator
	 */
	public ImportPhotoListener(IPhotoAlbumController mediator) {
		super(mediator);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.app.listeners.AImportPhotoListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FC.setFileFilter(AImportPhotoListener.PHOTO_FILE_FILTER);
		super.actionPerformed(e);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.app.listeners.AImportPhotoListener#getDialogTitle()
	 */
	@Override
	protected String getDialogTitle() {
		return MessagesUtils.getString("album.ui.main.window.import.dialog.title0"); //$NON-NLS-1$
	}

}