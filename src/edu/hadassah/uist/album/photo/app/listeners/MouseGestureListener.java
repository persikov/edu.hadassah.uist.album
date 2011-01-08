/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.regex.Pattern;

import edu.hadassah.uist.album.photo.app.utils.MouseGesturesRecognizer;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Sergey Persikov
 *
 */
public class MouseGestureListener extends MouseAdapter implements
		MouseMotionListener {
	private static final Pattern pattern = Pattern.compile("[RNB]+[ELD]+[RNB]+");

	private final MouseGesturesRecognizer gesturesRecognizer = new MouseGesturesRecognizer();
	private boolean isGesture;

	private final IPhotoAlbumController mediator;


	/**
	 * Creates new instance of {@link MouseGestureListener}
	 * @param mediator
	 */
	public MouseGestureListener(IPhotoAlbumController mediator) {
		this.mediator = mediator;
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3){
			String gesture = gesturesRecognizer.getGesture();
			System.out.println(gesture);
			if (pattern.matcher(gesture).matches()){
				System.out.println("delete");
			}
			gesturesRecognizer.clearTemporaryInfo();
		}
	}
	/**
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		isGesture = (e.getButton() == MouseEvent.BUTTON3);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (isGesture){
			gesturesRecognizer.processMouseEvent(e);
		}
	}

}
