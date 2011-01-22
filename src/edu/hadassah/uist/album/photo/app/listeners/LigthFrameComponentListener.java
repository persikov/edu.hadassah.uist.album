package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JMenuItem;

import edu.hadassah.uist.album.photo.app.utils.MessagesUtils;

/**
 * Listener for changing menu item label according to the component status
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public class LigthFrameComponentListener implements
ComponentListener {
	private final JMenuItem menuItem;

	/**
	 * Creates new instance of {@link LigthFrameComponentListener}
	 * @param menuItem
	 */
	public LigthFrameComponentListener(final JMenuItem menuItem) {
		this.menuItem = menuItem;
	}

	/**
	 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentShown(final ComponentEvent e) {
		menuItem.setText(MessagesUtils.getString("album.ui.main.window.menu.title.tool.frame.hide")); //$NON-NLS-1$
	}

	/**
	 * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentHidden(final ComponentEvent e) {
		menuItem.setText(MessagesUtils.getString("album.ui.main.window.menu.title.tool.frame.show")); //$NON-NLS-1$
	}
	/**
	 * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentResized(final ComponentEvent e) {}

	/**
	 * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentMoved(final ComponentEvent e) {}
}