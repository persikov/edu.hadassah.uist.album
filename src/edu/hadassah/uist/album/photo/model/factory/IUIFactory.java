/**
 *
 */
package edu.hadassah.uist.album.photo.model.factory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


/**
 * Factory of album's UI components
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public interface IUIFactory {

	/**
	 * Creates file menu
	 * @return file menu
	 */
	public JMenu createFileMenu();

	/**
	 * Creates tool panel
	 * @return tool panel
	 */
	public JPanel createToolPanel();

	/**
	 * Creates menu bar
	 * @return application menu bar
	 */
	public JMenuBar createMenuBar();

	/**
	 * Creates application main frame
	 * @return application main frame
	 */
	public JFrame createMainFrame();

	/**
	 * Creates application status bar
	 * @return application status bar
	 */
	public JLabel createStatusBar();

	/**
	 * Creates application main panel
	 * @return application main panel
	 */
	public JPanel createMainPanel();

	/**
	 * Creates content panel for Photo Album
	 * @return Photo Album content panel
	 */
	public JPanel createContentPanel();

}
