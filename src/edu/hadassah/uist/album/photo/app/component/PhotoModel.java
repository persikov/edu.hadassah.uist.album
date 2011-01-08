package edu.hadassah.uist.album.photo.app.component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;

import javax.imageio.ImageIO;


public class PhotoModel {
	protected Image image = null;
	protected boolean flipped = false;

	protected Vector<ActionListener> listeners;
	private final File file;
	private final Remarks remarks = new Remarks();

	public PhotoModel(File file)
	{
		this.file = file;
		listeners = new Vector<ActionListener>();
	}

	/**
	 * @param file
	 * @throws IOException void
	 */
	public void loadPhoto() throws IOException {
		if(file != null){
			image = ImageIO.read(file);
		}
	}

	public PhotoModel(File file, Vector<ActionListener> listeners) throws IOException
	{
		this(file);
		this.listeners = listeners;
		raiseActionEvent();
	}

	public void flip()
	{
		this.flipped = !this.flipped;
		raiseActionEvent();
	}

	public Image getImage()
	{
		return image;
	}

	public void addActionListener(ActionListener listener)
	{
		if (listeners.contains(listener)) {
			return;
		}
		listeners.add(listener);
	}

	public void removeActionListener(ActionListener listener)
	{
		if (!listeners.contains(listener)) {
			return;
		}
		listeners.remove(listener);
	}

	protected void raiseActionEvent()
	{
		for (ActionListener l : listeners) {
			l.actionPerformed(null);
		}
	}


	public Remarks getRemarks()
	{
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
}
