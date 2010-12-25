package edu.hadassah.uist.album.photo.app.component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;


public class PhotoModel {
	protected Image image = null;
	protected boolean flipped = false;

	protected Vector<ActionListener> listeners;
	private final File file;
	protected Vector<GeneralPath> remarkStrokes;

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
		image = ImageIO.read(file);
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

	public void addRemark(GeneralPath stroke)
	{
		remarkStrokes.add(stroke);
	}

	public Vector<GeneralPath> getRemarks()
	{
		return remarkStrokes;
	}
}
