package edu.hadassah.uist.album.photo.app.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Toggle component visibility action
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public final class ComponentVisibilityToggleAction extends AbstractAction {

	private static final long serialVersionUID = 270116091412893415L;
	private final Component component;

	/**
	 * Creates new instance of {@link ComponentVisibilityToggleAction}
	 * @param name
	 * @param component
	 */
	public ComponentVisibilityToggleAction(final String name, final Component component) {
		super(name);
		this.component = component;
	}

	/**
	 * Toggle component visibility action
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		component.setVisible(!component.isVisible());
	}
}