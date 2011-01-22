/**
 *
 */
package edu.hadassah.uist.album.photo.app.factory;

import java.io.File;
import java.io.IOException;

import edu.hadassah.uist.album.photo.app.component.PhotoComponent;
import edu.hadassah.uist.album.photo.app.listeners.TagUIUpdaterListener;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public class PhotoComponentFactory {

	/**
	 * @param file
	 * @return Object
	 * @throws IOException
	 */
	public IPhotoComponent createPhotoComponent(File file, IPhotoAlbumController mediator) throws IOException {
		PhotoComponent photoComponent = new PhotoComponent(file, mediator);
		photoComponent.loadPhoto();
		photoComponent.getModel().addActionListener(new TagUIUpdaterListener(mediator));
		return photoComponent;
	}

}
