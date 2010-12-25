/**
 *
 */
package edu.hadassah.uist.album.photo.app.data;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

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
		return (Component)photoComponents.remove(currPhotoIndex);
	}

	private static int mod(int x, int y)
	{
	    int result = x % y;
	    if (result < 0)
	    {
	        result += y;
	    }
	    return result;
	}
}
