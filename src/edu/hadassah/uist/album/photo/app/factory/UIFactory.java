/**
 *
 */
package edu.hadassah.uist.album.photo.app.factory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import edu.hadassah.uist.album.photo.app.actions.ComponentVisibilityToggleAction;
import edu.hadassah.uist.album.photo.app.listeners.ImportFolderPhotoListener;
import edu.hadassah.uist.album.photo.app.listeners.ImportPhotoListener;
import edu.hadassah.uist.album.photo.app.listeners.LigthFrameComponentListener;
import edu.hadassah.uist.album.photo.app.listeners.NextPhotoListener;
import edu.hadassah.uist.album.photo.app.listeners.StatusBarUpdater;
import edu.hadassah.uist.album.photo.app.utils.MessagesUtils;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;
import edu.hadassah.uist.album.photo.model.factory.IUIFactory;

/**
 * UI elements factory for the application
 * @author Sergey Persikov
 *
 */
public class UIFactory implements IUIFactory {

	private static final String TAG_WORK = "album.ui.main.window.tag.work";
	private static final String TAG_SCHOOL = "album.ui.main.window.tag.school";
	private static final String TAG_FAMILY = "album.ui.main.window.tag.family";
	private static final String TAG_VACATION = "album.ui.main.window.tag.vacation";
	private static final String NAVIGATION_BTN_FORWARD = "album.ui.main.window.navigation.btn.forward";
	private static final String NAVIGATION_BTN_BACK = "album.ui.main.window.navigation.btn.back";
	private static final String STATUS_BAR_LABEL = "album.ui.main.window.status.bar.label";
	private static final String TOOL_FRAME_TITLE = "album.ui.main.window.tool.frame.title";
	private static final String MAIN_FRAME_TITLE = "album.ui.main.window.main.frame.title";
	private static final String MENU_TITLE_EXIT = "album.ui.main.window.menu.title.exit";
	private static final String MENU_TITLE_DELETE_PHOTO = "album.ui.main.window.menu.title.delete.photo";
	private static final String MENU_TITLE_IMPORT_PHOTO_FOLDER = "album.ui.main.window.menu.title.import.photo.folder";
	private static final String TITLE_IMPORT_PHOTO = "album.ui.main.window.menu.title.import.photo";
	private static final String MENU_TITLE_FILE = "album.ui.main.window.menu.title.file";
	private static final String MENU_TITLE_VIEW = "album.ui.main.window.menu.title.view";
	private static final String MENU_TITLE_TOOL_FRAME_SHOW = "album.ui.main.window.menu.title.tool.frame.show";


	final IPhotoAlbumController mediator;
	private StatusBarUpdater statusUpdaterListener = null;


	/**
	 * Creates new instance of {@link UIFactory}
	 * @param mediator
	 * @param statusUpdaterListener
	 */
	public UIFactory(final IPhotoAlbumController mediator) {
		this.mediator = mediator;
		statusUpdaterListener = new StatusBarUpdater(this.mediator);
	}


	/**
	 * @see edu.hadassah.uist.album.photo.model.factory.IUIFactory#createContentPanel()
	 */
	@Override
	public JPanel createContentPanel() {
		JPanel cPanel = new JPanel();
		cPanel.setBackground(Color.CYAN);
		cPanel.setLayout(new BorderLayout());
		//cPanel.setBorder(BorderFactory.createCompoundBorder());
		cPanel.setBorder(BorderFactory.createEtchedBorder());
		return cPanel;
	}
	/**
	 * @see edu.hadassah.uist.album.photo.model.factory.IUIFactory#createFileMenu()
	 */
	@Override
	public JMenu createFileMenu() {
		JMenu fileMenu = new JMenu(MessagesUtils.getString(MENU_TITLE_FILE));
		JMenuItem menuItem;

		menuItem = new JMenuItem(MessagesUtils.getString(TITLE_IMPORT_PHOTO));
		menuItem.addActionListener(new ImportPhotoListener(mediator));
		fileMenu.add(menuItem);

		menuItem = new JMenuItem(MessagesUtils.getString(MENU_TITLE_IMPORT_PHOTO_FOLDER));
		menuItem.addActionListener(new ImportFolderPhotoListener(mediator));
		fileMenu.add(menuItem);

		menuItem = new JMenuItem(MessagesUtils.getString(MENU_TITLE_DELETE_PHOTO));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mediator.removeCurrentComponent();

			}
		});
		fileMenu.add(menuItem);

		menuItem = new JMenuItem(new AbstractAction(MessagesUtils.getString(MENU_TITLE_EXIT)) {

			@Override
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(menuItem);
		menuItem.addActionListener(statusUpdaterListener);

		return fileMenu;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.factory.IUIFactory#createMainFrame()
	 */
	@Override
	public JFrame createMainFrame() {
		JFrame frame = new JFrame(MessagesUtils.getString(MAIN_FRAME_TITLE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		return frame;
	}
	/**
	 * @see edu.hadassah.uist.album.photo.model.factory.IUIFactory#createMainPanel()
	 */
	@Override
	public JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		return mainPanel;
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.factory.IUIFactory#createMenuBar()
	 */
	@Override
	public JMenuBar createMenuBar() {
		//Create the menu bar.  Make it have a green background.
		JMenuBar greenMenuBar = new JMenuBar();
		greenMenuBar.setOpaque(true);
		greenMenuBar.setBackground(new Color(154, 165, 127));
		greenMenuBar.setPreferredSize(new Dimension(200, 20));


		greenMenuBar.add(createFileMenu());

		JFrame frame = new JFrame(MessagesUtils.getString(TOOL_FRAME_TITLE));
		frame.setPreferredSize(new Dimension(300, 400));
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setVisible(false);
		frame.pack();


		JMenu viewMenu = new JMenu(MessagesUtils.getString(MENU_TITLE_VIEW));
		final JMenuItem menuItem = new JMenuItem(new ComponentVisibilityToggleAction(MessagesUtils.getString(MENU_TITLE_TOOL_FRAME_SHOW), frame));
		viewMenu.add(menuItem);
		greenMenuBar.add(viewMenu);

		frame.addComponentListener(new LigthFrameComponentListener(menuItem));
		return greenMenuBar;
	}
	/**
	 * @see edu.hadassah.uist.album.photo.model.factory.IUIFactory#createStatusBar()
	 */
	@Override
	public JLabel createStatusBar() {
		return new JLabel(MessagesUtils.getString(STATUS_BAR_LABEL));
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.factory.IUIFactory#createToolPanel()
	 */
	@Override
	public JPanel createToolPanel() {
		JPanel toolsPanel = new JPanel();
		//toolsPanel.setBackground(Color.GRAY);
		toolsPanel.setPreferredSize(new Dimension(180, 500));
		toolsPanel.setLayout(new BorderLayout());
		//		toolsPanel.setLayout(new GridLayout(5, 2, 2, 2));
		toolsPanel.setBorder(BorderFactory.createEtchedBorder());

		JPanel pageControlPanel = new JPanel(new GridLayout(1, 2, 2, 2));
		toolsPanel.add(pageControlPanel, BorderLayout.SOUTH);
		JButton bBack = new JButton(MessagesUtils.getString(NAVIGATION_BTN_BACK));
		bBack.addActionListener(statusUpdaterListener);
		bBack.addActionListener(new NextPhotoListener(mediator, false));
		pageControlPanel.add(bBack);

		JButton bForward = new JButton(MessagesUtils.getString(NAVIGATION_BTN_FORWARD));
		bForward.addActionListener(statusUpdaterListener);
		bForward.addActionListener(new NextPhotoListener(mediator, true));
		pageControlPanel.add(bForward);

		JPanel tagControlPanel = new JPanel(new GridLayout(3, 2, 2, 2));
		toolsPanel.add(tagControlPanel, BorderLayout.NORTH);

		JCheckBox bTagVacation = new JCheckBox(MessagesUtils.getString(TAG_VACATION));
		bTagVacation.addActionListener(statusUpdaterListener);
		tagControlPanel.add(bTagVacation);

		JCheckBox bTagFamily = new JCheckBox(MessagesUtils.getString(TAG_FAMILY));
		bTagFamily.addActionListener(statusUpdaterListener);
		tagControlPanel.add(bTagFamily);

		JCheckBox bTagSchool = new JCheckBox(MessagesUtils.getString(TAG_SCHOOL));
		bTagSchool.addActionListener(statusUpdaterListener);
		tagControlPanel.add(bTagSchool);

		JCheckBox bTagWork = new JCheckBox(MessagesUtils.getString(TAG_WORK));
		bTagWork.addActionListener(statusUpdaterListener);
		tagControlPanel.add(bTagWork);


		return toolsPanel;
	}

}
