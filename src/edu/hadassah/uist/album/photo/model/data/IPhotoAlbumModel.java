/**
 *
 */
package edu.hadassah.uist.album.photo.model.data;

import java.awt.Component;

import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;

/**
 * @author Sergey Persikov
 *
 */
public interface IPhotoAlbumModel {

	/**
	 * @return IPhotoComponent
	 */
	public IPhotoComponent getNextPhotoComponents();

	/**
	 * @return IPhotoComponent
	 */
	public IPhotoComponent getPreviousPhotoComponents();

	public boolean addPhotoComponent(IPhotoComponent photoComponent);

	public Component removeCurrentComponent();

	/**
	 * @param tag void
	 */
	public void tagCurrentComponent(PhotoTags tag);

}
