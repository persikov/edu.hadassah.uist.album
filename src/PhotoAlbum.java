import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.hadassah.uist.album.photo.app.controller.PhotoAlbumController;
import edu.hadassah.uist.album.photo.app.data.PhotoAlbumModel;
import edu.hadassah.uist.album.photo.app.factory.UIFactory;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;
import edu.hadassah.uist.album.photo.model.data.IPhotoAlbumModel;
import edu.hadassah.uist.album.photo.model.factory.IUIFactory;

/**
 * Application main class
 * @author Sergey Persikov
 */
public class PhotoAlbum implements Runnable {


	/** UI components factory */
	private final IUIFactory uiFactory;
	/** UI mediator for cross components interaction */
	private final IPhotoAlbumController mediator;
	private final IPhotoAlbumModel albumModel;


	/**
	 * Creates new instance of {@link PhotoAlbum}
	 */
	public PhotoAlbum() {
		albumModel = new PhotoAlbumModel();
		mediator = new PhotoAlbumController(albumModel);
		uiFactory = new UIFactory(mediator);
	}


	/**
	 *
	 * @param args void
	 */
	public static void main(final String[] args) {
		javax.swing.SwingUtilities.invokeLater(new PhotoAlbum());
	}


	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createAndShowGUI();
	}

	/**
	 * create application UI
	 */
	private void createAndShowGUI() {

		JLabel statusBar = uiFactory.createStatusBar();
		mediator.setStatusBar(statusBar);

		JFrame frame = uiFactory.createMainFrame();

		JPanel mainPanel = uiFactory.createMainPanel();
		mainPanel.add(statusBar, BorderLayout.SOUTH);

		JComponent contentPanel = uiFactory.createContentPanel();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(uiFactory.createToolPanel(), BorderLayout.WEST);
		mediator.setContentPanel(contentPanel);

		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.setJMenuBar(uiFactory.createMenuBar());
		frame.pack();
		frame.setVisible(true);
	}

}
