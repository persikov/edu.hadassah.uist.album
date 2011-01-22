/**
 * 
 */
package edu.hadassah.uist.album.photo.model.component;

import java.util.EnumSet;

import edu.hadassah.uist.album.photo.app.component.PhotoTags;

/**
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public interface ITagable {

	public boolean addTag(PhotoTags tagToAdd);

	public boolean removeTag(PhotoTags tagToAdd);

	/**
	 * @return the tags
	 */
	public EnumSet<PhotoTags> getTags();

}