/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;

import edu.hadassah.uist.album.photo.app.utils.MessagesUtils;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Itay Cohen
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
		FC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		super.actionPerformed(e);
	}

	/**
	 * @see edu.hadassah.uist.album.photo.app.listeners.AImportPhotoListener#getDialogTitle()
	 */
	@Override
	protected String getDialogTitle() {
		return MessagesUtils.getString("album.ui.main.window.dilaog.import.folder.title"); //$NON-NLS-1$
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
        File file = FC.getSelectedFile();
        FilenameFilter filter = AImportPhotoListener.PHOTO_FILE_FILTER;
		File[] listFiles = file.listFiles(filter);
        mediator.addPhotoComponents(listFiles);
        //This is where a real application would open the file.
        mediator.setStatusMessage(MessagesUtils.getString("album.ui.main.window.import.status.successful.1") + file.getAbsolutePath() + MessagesUtils.getString("album.ui.main.window.import.status.successful.2"));
	}


}
