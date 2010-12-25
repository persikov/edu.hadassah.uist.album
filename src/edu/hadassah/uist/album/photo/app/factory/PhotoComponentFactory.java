/**
 *
 */
package edu.hadassah.uist.album.photo.app.factory;

import java.io.File;
import java.io.IOException;

import edu.hadassah.uist.album.photo.app.component.PhotoComponent;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;

/**
 * @author Sergey Persikov
 *
 */
public class PhotoComponentFactory {

	/**
	 * @param file
	 * @return Object
	 * @throws IOException
	 */
	public IPhotoComponent createPhotoComponent(File file) throws IOException {
		PhotoComponent photoComponent = new PhotoComponent(file);
		photoComponent.loadPhoto();
		return photoComponent;
	}

}
