package edu.hadassah.uist.album.photo.app.listeners;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.hadassah.uist.album.photo.app.utils.MessagesUtils;

/**
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public final class PhotoFileFilter extends javax.swing.filechooser.FileFilter implements FilenameFilter, FileFilter {

	private static Pattern graphFiles = Pattern.compile("" + 
			".*\\.bmp|" + 
			".*\\.png|" + 
			".*\\.jpg|" + 
			".*\\.jpeg|" + 
			".*\\.png"); 
	@Override
	public boolean accept(File dir, String name) {
		Matcher matcher = graphFiles.matcher(name.toLowerCase());
		return matcher.matches();
	}

	/**
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {
	    return file != null &&
	    (file.isDirectory() || accept(file.getParentFile(), file.getName()));
	}

	/**
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		return MessagesUtils.getString("album.ui.main.window.import.photo.filter.description"); //$NON-NLS-1$
	}
}