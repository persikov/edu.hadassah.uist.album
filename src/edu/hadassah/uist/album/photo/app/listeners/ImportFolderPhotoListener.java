/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;

import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Sergey Persikov
 *
 */
public class ImportFolderPhotoListener extends AImportPhotoListener {

	/**
	 * Creates new instance of {@link ImportFolderPhotoListener}
	 * @param mediator
	 */
	public ImportFolderPhotoListener(IPhotoAlbumController mediator) {
		super(mediator);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		super.actionPerformed(e);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.app.listeners.AImportPhotoListener#getDialogTitle()
	 */
	@Override
	protected String getDialogTitle() {
		return "Import Photo from Folder";
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
        File file = fc.getSelectedFile();
        FilenameFilter filter = AImportPhotoListener.photoFileFilter;
		File[] listFiles = file.listFiles(filter);
        mediator.addPhotoComponents(listFiles);
        //This is where a real application would open the file.
        mediator.setStatusMessage("Photo from file " + file.getAbsolutePath() + " were loaded.");
	}


}
