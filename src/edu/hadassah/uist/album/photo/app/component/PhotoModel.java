package edu.hadassah.uist.album.photo.app.component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import javax.imageio.ImageIO;

import edu.hadassah.uist.album.photo.model.component.ITagable;


/**
 * Model of the {@link PhotoComponent} class
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public class PhotoModel implements ITagable {

	private static final int DEFAULT_EVENT_ID = 0;

	/** image of the photo model*/
	private Image image = null;
	/** image file of the photo model*/
	private final File file;
	/** state of the photo model*/
	private boolean flipped = false;
	/** remarks (annotations) set of the photo*/
	private final Remarks remarks = new Remarks();
	/** tags set of the photo model*/
	private final EnumSet<PhotoTags> tags = EnumSet.noneOf(PhotoTags.class);
	/** listeners of the photo model*/
	private final List<ActionListener> listeners;

	/**
	 * Creates new instance of {@link PhotoModel}
	 * @param file
	 */
	public PhotoModel(File file)
	{
		this.file = file;
		listeners = new ArrayList<ActionListener>();
	}

	/**
	 * Load image from file. Called on show PhotoComponent action, to support lazy loading.
	 * @throws IOException
	 */
	public void loadPhoto() throws IOException {
		if(file != null){
			image = ImageIO.read(file);
			raiseActionEvent("image loaded");
		}
		raiseActionEvent("shown");
	}


	/**
	 *  flip the photo - change model state
	 */
	public void flip(){
		this.flipped ^= true;
		raiseActionEvent("photo flipped");
	}

	/**
	 * @return {@link Image}
	 */
	public Image getImage(){
		return image;
	}

	/**
	 * add listener to the photo model
	 * @param listener {@link ActionListener}
	 */
	public void addActionListener(ActionListener listener){
		if (listeners.contains(listener)) {
			return;
		}
		listeners.add(listener);
	}

	/**
	 * remove listener from photo model
	 * @param listener {@link ActionListener}
	 */
	public void removeActionListener(ActionListener listener){
		if (!listeners.contains(listener)) {
			return;
		}
		listeners.remove(listener);
	}

	/**
	 * fire action event
	 * @param command
	 */
	private final void raiseActionEvent(String command){
		raiseActionEvent(command, DEFAULT_EVENT_ID);
	}

	/**
	 * fire avtion event
	 * @param command
	 * @param eventId
	 */
	private final void raiseActionEvent(String command, int eventId){
		ActionEvent actionEvent = new ActionEvent(this, eventId, command);
		for (ActionListener l : listeners) {
			l.actionPerformed(actionEvent);
		}
	}


	/**
	 * @return {@link Remarks} remarks
	 */
	public Remarks getRemarks(){
		return remarks;
	}

	/**
	 * Start new remark at given coordinates
	 * @param x
	 * @param y
	 */
	public void startNewRemark(int x, int y) {
		remarks.startNew(x, y);
	}

	/**
	 * add remark with given coordinates
	 * @param x
	 * @param y
	 */
	public void addRemarkPoint(int x, int y) {
		remarks.addPoint(x, y);
	}

	/**
	 * @return all remarks
	 */
	public Collection<Remark> getAllRemarks() {
		return remarks.getAllRemarks();
	}

	/**
	 * Returns photo state
	 * @return true if photo is flipped, false otherwise
	 */
	public boolean isFlipped() {
		return flipped;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.component.ITagable#addTag(edu.hadassah.uist.album.photo.app.component.PhotoTags)
	 */
	@Override
	public boolean addTag(PhotoTags tagToAdd){
		boolean added = tags.add(tagToAdd);
		raiseActionEvent("add tag");
		return added;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.component.ITagable#removeTag(edu.hadassah.uist.album.photo.app.component.PhotoTags)
	 */
	@Override
	public boolean removeTag(PhotoTags tagToAdd){
		boolean removed = tags.remove(tagToAdd);
		raiseActionEvent("remove tag");
		return removed;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.component.ITagable#getTags()
	 */
	@Override
	public EnumSet<PhotoTags> getTags() {
		return tags;
	}

	/**
	 *  Removes all remarks
	 */
	public void removeAllRemarks() {
		remarks.clear();
	}

	/**
	 * @returns the image File
	 */
	public File getFile() {
		return file;
	}
}
