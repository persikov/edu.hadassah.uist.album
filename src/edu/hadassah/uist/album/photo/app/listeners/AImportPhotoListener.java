/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import edu.hadassah.uist.album.photo.app.utils.MessagesUtils;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * Base listener class for import operations
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public abstract class AImportPhotoListener implements ActionListener, Runnable {
	/** controller of the application UI */
	protected final IPhotoAlbumController mediator;
	/** file chooser dialog */
	protected static final JFileChooser FC = new JFileChooser();
	/** filter of the photo files supported by application */
	protected static final PhotoFileFilter PHOTO_FILE_FILTER = new PhotoFileFilter();

	/**
	 * Creates new instance of {@link AImportPhotoListener}
	 */
	public AImportPhotoListener(IPhotoAlbumController mediator) {
		this.mediator = mediator;
	}

	/**
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal = FC.showDialog(mediator.getContentPanel(), getDialogTitle());
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	SwingUtilities.invokeLater(this);
	    } else {
	        mediator.setStatusMessage(MessagesUtils.getString("album.ui.main.window.import.status.canceled")); //$NON-NLS-1$
	    }

	}

	/**
	 * Load selected image
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		File file = FC.getSelectedFile();
		mediator.addPhotoComponent(file);
		//This is where a real application would open the file.
		mediator.setStatusMessage(MessagesUtils.getString("album.ui.main.window.import.status.successful.1") + file.getAbsolutePath() + MessagesUtils.getString("album.ui.main.window.import.status.successful.2")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @return titlo of the import dialog
	 */
	protected abstract String getDialogTitle();

}