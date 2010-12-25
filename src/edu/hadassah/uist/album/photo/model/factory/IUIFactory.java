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
 * @author Sergey Persikov
 *
 */
public interface IUIFactory {

	/**
	 * Creates file menu
	 * @return
	 */
	public JMenu createFileMenu();

	/**
	 * Creates tool panel
	 * @return
	 */
	public JPanel createToolPanel();

	/**
	 * Creates menu bar
	 * @return
	 */
	public JMenuBar createMenuBar();

	/**
	 * Creates application main frame
	 * @return
	 */
	public JFrame createMainFrame();

	/**
	 * Creates application status bar
	 * @return
	 */
	public JLabel createStatusBar();

	/**
	 * Creates application main panel
	 * @return
	 */
	public JPanel createMainPanel();

	/**
	 * Creates content panel for Photo Album
	 * @return Photo Album content panel
	 */
	public JPanel createContentPanel();

}
