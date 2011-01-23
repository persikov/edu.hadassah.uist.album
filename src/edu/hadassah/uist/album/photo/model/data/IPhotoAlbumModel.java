/**
 *
 */
package edu.hadassah.uist.album.photo.model.data;

import java.awt.Component;

import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;

/**
 * Interface of the album model class
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public interface IPhotoAlbumModel {

	/**
	 * @return next {@link IPhotoComponent}
	 */
	public IPhotoComponent getNextPhotoComponents();

	/**
	 * @return previous {@link IPhotoComponent}
	 */
	public IPhotoComponent getPreviousPhotoComponents();

	/**
	 * Add new component to the nodel
	 * @param photoComponent component to add
	 * @return <code>true</code> in case the component were successfully added
	 * <code>false</code> otherwise
	 */
	public boolean addPhotoComponent(IPhotoComponent photoComponent);

	/**
	 * Remove current component and returns it
	 * @return removed component
	 */
	public Component removeCurrentComponent();

	/**
	 * Add/remove component's tag to the component model
	 * @param tag tag to toggle
	 */
	public void toggleCurrentComponentTag(PhotoTags tag);

	/**
	 * remove all remarks of the current component
	 */
	public void removeComponentRemarks();

}
