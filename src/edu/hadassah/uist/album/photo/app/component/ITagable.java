/**
 * 
 */
package edu.hadassah.uist.album.photo.app.component;

import java.util.EnumSet;

/**
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