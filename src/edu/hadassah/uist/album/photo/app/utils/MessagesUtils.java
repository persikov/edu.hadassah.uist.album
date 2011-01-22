/**
 *
 */
package edu.hadassah.uist.album.photo.app.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility to load UI labels from resource file
 * TODO support i18n
 *
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public final class MessagesUtils {
	private static final String BUNDLE_NAME = "edu.hadassah.uist.album.photo.app.resources.album_ui_resources"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	/**
	 * Creates new instance of {@link MessagesUtils}
	 */
	private MessagesUtils() {}

	/**
	 * @param key
	 * @return resolved string
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
