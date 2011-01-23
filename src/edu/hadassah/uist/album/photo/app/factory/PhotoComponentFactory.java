/**
 *
 */
package edu.hadassah.uist.album.photo.app.factory;

import java.io.File;

import edu.hadassah.uist.album.photo.app.component.PhotoComponent;
import edu.hadassah.uist.album.photo.app.component.PhotoModel;
import edu.hadassah.uist.album.photo.app.listeners.TagMediatorUpdaterListener;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * Factory of the {@link IPhotoComponent}for Photo Album application
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public class PhotoComponentFactory {

	/**
	 * Creates new {@link IPhotoComponent}
	 * @param file image file
	 * @param mediator application UI controller
	 * @return new {@link IPhotoComponent}
	 */
	public IPhotoComponent createPhotoComponent(File file, IPhotoAlbumController mediator){
		PhotoComponent photoComponent = new PhotoComponent(file, mediator);
		PhotoModel photoModel = photoComponent.getPhotoModel();
		photoModel.addActionListener(new TagMediatorUpdaterListener(mediator));
		photoModel.addActionListener(photoComponent);
		return photoComponent;
	}

}
