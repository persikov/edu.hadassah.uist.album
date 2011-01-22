/**
 *
 */
package edu.hadassah.uist.album.photo.app.data;

import java.awt.Component;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import edu.hadassah.uist.album.photo.app.component.PhotoModel;
import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.model.component.IPhotoComponent;
import edu.hadassah.uist.album.photo.model.data.IPhotoAlbumModel;

/**
 * @author Sergey Persikov
 *
 */
public class PhotoAlbumModel implements IPhotoAlbumModel {

	private final List<IPhotoComponent> photoComponents = new ArrayList<IPhotoComponent>();

	private int currPhotoIndex = 0;


	/**
	 * @see edu.hadassah.uist.album.photo.model.data.IPhotoAlbumModel#getNextPhotoComponents()
	 */
	@Override
	public IPhotoComponent getNextPhotoComponents() {
		IPhotoComponent next = null;
		if ( photoComponents.isEmpty()){
			return null;//TODO return null object
		}
		currPhotoIndex = mod(currPhotoIndex + 1, photoComponents.size());
		next = photoComponents.get(currPhotoIndex);
		return next;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.data.IPhotoAlbumModel#getPreviousPhotoComponents()
	 */
	@Override
	public IPhotoComponent getPreviousPhotoComponents() {
		IPhotoComponent prev = null;
		if ( photoComponents.isEmpty()){
			return null;//TODO return null object
		}
		currPhotoIndex = mod(currPhotoIndex - 1, photoComponents.size());
		prev = photoComponents.get(currPhotoIndex);
		return prev;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.data.IPhotoAlbumModel#addPhotoComponent(edu.hadassah.uist.album.photo.model.component.IPhotoComponent)
	 */
	@Override
	public boolean addPhotoComponent(IPhotoComponent photoComponent) {
		boolean added = photoComponents.add(photoComponent);
		if ( added ){
			currPhotoIndex = photoComponents.indexOf(photoComponent);
		}
		return added;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.data.IPhotoAlbumModel#removeCurrentComponent()
	 */
	@Override
	public Component removeCurrentComponent() {
		if ( photoComponents.isEmpty()){
			return null;//TODO return null object
		}
		Component removed = (Component)photoComponents.remove(currPhotoIndex);
		currPhotoIndex = mod(currPhotoIndex - 1, photoComponents.size());
		return removed;
	}

	private static int mod(int x, int y)
	{
		if (y == 0){
			return 0;
		}

	    int result = x % y;
	    if (result < 0)
	    {
	        result += y;
	    }
	    return result;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.data.IPhotoAlbumModel#toggleCurrentComponentTag(edu.hadassah.uist.album.photo.app.component.PhotoTags)
	 */
	@Override
	public void toggleCurrentComponentTag(PhotoTags tag) {
		if ( photoComponents.isEmpty()){
			return;
		}
		PhotoModel model = photoComponents.get(currPhotoIndex).getModel();
		EnumSet<PhotoTags> selectedTags = model.getTags();
		if ( selectedTags.contains(tag)){
			model.removeTag(tag);
		} else {
			model.addTag(tag);
		}
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.data.IPhotoAlbumModel#removeCurrentAnnotations()
	 */
	@Override
	public void removeCurrentAnnotations() {
		if ( photoComponents.isEmpty()){
			return;
		}
		PhotoModel model = photoComponents.get(currPhotoIndex).getModel();
		model.removeAllRemarks();
	}

}
