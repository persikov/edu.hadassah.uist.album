package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Sergey Persikov
 *
 */
public class ImportPhotoListener extends AImportPhotoListener  {
	/**
	 * Creates new instance of {@link ImportPhotoListener}
	 * @param uiFactory
	 */
	public ImportPhotoListener(IPhotoAlbumController mediator) {
		super(mediator);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(AImportPhotoListener.photoFileFilter);
		super.actionPerformed(e);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.app.listeners.AImportPhotoListener#getDialogTitle()
	 */
	@Override
	protected String getDialogTitle() {
		return "Import Photo";
	}

}