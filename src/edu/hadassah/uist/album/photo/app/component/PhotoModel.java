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


public class PhotoModel implements ITagable {
	/**
	 *
	 */
	private static final int DEFAULT_EVENT_ID = 0;
	protected Image image = null;
	protected boolean flipped = false;

	protected List<ActionListener> listeners;
	private final File file;
	private final Remarks remarks = new Remarks();
	private final EnumSet<PhotoTags> tags = EnumSet.noneOf(PhotoTags.class);

	public PhotoModel(File file)
	{
		this.file = file;
		listeners = new ArrayList<ActionListener>();
	}

	/**
	 * @param file
	 * @throws IOException void
	 */
	public void loadPhoto() throws IOException {
		if(file != null){
			image = ImageIO.read(file);
			raiseActionEvent("image loaded");
		}
	}

	public PhotoModel(File file, List<ActionListener> listeners) {
		this(file);
		this.listeners =  new ArrayList<ActionListener>(listeners);
		raiseActionEvent("model created");
	}

	public void flip()
	{
		this.flipped = !this.flipped;
		raiseActionEvent("photo flipped");
	}

	public Image getImage(){
		return image;
	}

	public void addActionListener(ActionListener listener){
		if (listeners.contains(listener)) {
			return;
		}
		listeners.add(listener);
	}

	public void removeActionListener(ActionListener listener){
		if (!listeners.contains(listener)) {
			return;
		}
		listeners.remove(listener);
	}

	public void raiseActionEvent(String command){
		raiseActionEvent(command, DEFAULT_EVENT_ID);
	}

	protected void raiseActionEvent(String command, int eventId){
		for (ActionListener l : listeners) {
			l.actionPerformed(new ActionEvent(this, eventId, command));
		}
	}


	public Remarks getRemarks(){
		return remarks;
	}

	public void startNewRemark(int x, int y) {
		remarks.startNew(x, y);
	}

	public void addRemarkPoint(int x, int y) {
		remarks.addPoint(x, y);
	}

	public Collection<Remark> getAllRemarks() {
		return remarks.getAllRemarks();
	}

	/**
	 * @return boolean
	 */
	public boolean isFlipped() {
		return flipped;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.app.component.ITagable#addTag(edu.hadassah.uist.album.photo.app.component.PhotoTags)
	 */
	@Override
	public boolean addTag(PhotoTags tagToAdd){
		boolean added = tags.add(tagToAdd);
		raiseActionEvent("add tag");
		return added;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.app.component.ITagable#removeTag(edu.hadassah.uist.album.photo.app.component.PhotoTags)
	 */
	@Override
	public boolean removeTag(PhotoTags tagToAdd){
		boolean removed = tags.remove(tagToAdd);
		raiseActionEvent("remove tag");
		return removed;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.app.component.ITagable#getTags()
	 */
	@Override
	public EnumSet<PhotoTags> getTags() {
		return tags;
	}

	/**
	 *  void
	 */
	public void removeAllRemarks() {
		remarks.clear();

	}
}
