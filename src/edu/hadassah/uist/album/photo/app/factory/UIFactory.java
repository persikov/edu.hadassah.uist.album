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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

import edu.hadassah.uist.album.photo.app.actions.ComponentVisibilityToggleAction;
import edu.hadassah.uist.album.photo.app.listeners.ImportFolderPhotoListener;
import edu.hadassah.uist.album.photo.app.listeners.ImportPhotoListener;
import edu.hadassah.uist.album.photo.app.listeners.LigthFrameComponentListener;
import edu.hadassah.uist.album.photo.app.listeners.NextPhotoListener;
import edu.hadassah.uist.album.photo.app.listeners.StatusBarUpdater;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;
import edu.hadassah.uist.album.photo.model.factory.IUIFactory;

/**
 * UI elements factory for the application
 * @author Sergey Persikov
 *
 */
public class UIFactory implements IUIFactory {

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
		JMenu fileMenu = new JMenu("File");
		JMenuItem menuItem;

		menuItem = new JMenuItem("Import Photo");
		menuItem.addActionListener(new ImportPhotoListener(mediator));
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Import Photos from Folder");
		menuItem.addActionListener(new ImportFolderPhotoListener(mediator));
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Delete Photo");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mediator.removeCurrentComponent();

			}
		});
		fileMenu.add(menuItem);

		menuItem = new JMenuItem(new AbstractAction("Exit") {

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
		JFrame frame = new JFrame("PhotoAlbum");
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

		JFrame frame = new JFrame("Ligth tool");
		frame.setPreferredSize(new Dimension(300, 400));
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setVisible(false);
		frame.pack();


		JMenu viewMenu = new JMenu("View");
		final JMenuItem menuItem = new JMenuItem(new ComponentVisibilityToggleAction("Show Light Table", frame));
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
		return new JLabel("Status messages");
	}

	/**
	 * @see edu.hadassah.uist.album.photo.model.factory.IUIFactory#createToolPanel()
	 */
	@Override
	public JPanel createToolPanel() {
		JPanel toolsPanel = new JPanel();
		toolsPanel.setBackground(Color.GRAY);
		toolsPanel.setPreferredSize(new Dimension(180, 500));
		toolsPanel.setLayout(new BorderLayout());
		//		toolsPanel.setLayout(new GridLayout(5, 2, 2, 2));
		toolsPanel.setBorder(BorderFactory.createEtchedBorder());

		JPanel pageControlPanel = new JPanel(new GridLayout(1, 2, 2, 2));
		toolsPanel.add(pageControlPanel, BorderLayout.SOUTH);
		JButton bBack = new JButton("Back");
		bBack.addActionListener(statusUpdaterListener);
		bBack.addActionListener(new NextPhotoListener(mediator, false));
		pageControlPanel.add(bBack);

		JButton bForward = new JButton("Forward");
		bForward.addActionListener(statusUpdaterListener);
		bForward.addActionListener(new NextPhotoListener(mediator, true));
		pageControlPanel.add(bForward);

		JPanel tagControlPanel = new JPanel(new GridLayout(3, 2, 2, 2));
		toolsPanel.add(tagControlPanel, BorderLayout.NORTH);

		JToggleButton bTagVacation = new JToggleButton("Vacation");
		bTagVacation.addActionListener(statusUpdaterListener);
		tagControlPanel.add(bTagVacation);

		JToggleButton bTagFamily = new JToggleButton("Family");
		bTagFamily.addActionListener(statusUpdaterListener);
		tagControlPanel.add(bTagFamily);

		JToggleButton bTagSchool = new JToggleButton("School");
		bTagSchool.addActionListener(statusUpdaterListener);
		tagControlPanel.add(bTagSchool);

		JToggleButton bTagWork = new JToggleButton("Work");
		bTagWork.addActionListener(statusUpdaterListener);
		tagControlPanel.add(bTagWork);


		return toolsPanel;
	}

}
