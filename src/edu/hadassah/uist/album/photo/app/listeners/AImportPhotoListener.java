/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Sergey Persikov
 *
 */
public abstract class AImportPhotoListener implements ActionListener, Runnable {

	protected final IPhotoAlbumController mediator;
	protected static final JFileChooser fc = new JFileChooser();
	protected static final PhotoFileFilter photoFileFilter = new PhotoFileFilter();

	/**
	 * Creates new instance of {@link AImportPhotoListener}
	 */
	public AImportPhotoListener(IPhotoAlbumController mediator) {
		this.mediator = mediator;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal = fc.showDialog(mediator.getMainComponent(), getDialogTitle());
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	SwingUtilities.invokeLater(this);
	    } else {
	        mediator.setStatusMessage("Open command cancelled by user.");
	    }

	}

	/**
	 *  void
	 */
	@Override
	public void run() {
		File file = fc.getSelectedFile();
		mediator.addPhotoComponent(file);
		//This is where a real application would open the file.
		mediator.setStatusMessage("Photo from file " + file.getAbsolutePath() + " were loaded.");
	}

	/**
	 * @return String
	 */
	protected abstract String getDialogTitle();

}