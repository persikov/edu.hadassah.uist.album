/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.regex.Pattern;

import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.app.utils.MouseGesturesRecognizer;
import edu.hadassah.uist.album.photo.model.controller.IPhotoAlbumController;

/**
 * @author Sergey Persikov
 *
 */
public class MouseGestureListener extends MouseAdapter implements
		MouseMotionListener {
	private static final Pattern DELETE_PATTERN = Pattern.compile("[RBC]+[ELD]+[RBC]+");
	private static final Pattern NEXT_PHOTO_PATTERN = Pattern.compile("[RBC]+");
	private static final Pattern PREV_PHOTO_PATTERN = Pattern.compile("[LAE]+");

	private static final Pattern WORK_TAG_PATTERN = Pattern.compile("[CD]+[BU]+[CD]+[BU]+");
	private static final Pattern VACATION_TAG_PATTERN = Pattern.compile("[CD]+[BU]+");
	private static final Pattern SHCOOL_TAG_PATTERN = Pattern.compile("[LAE]+[CDR]+[LAE]+");
	private static final Pattern FAMILY_TAG_PATTERN = Pattern.compile("[UR]+");

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
			if (DELETE_PATTERN.matcher(gesture).matches()){
				System.out.println("delete");
				mediator.removeCurrentComponent();
			} else if (WORK_TAG_PATTERN.matcher(gesture).matches()){
				mediator.tagCurrentComponent(PhotoTags.WORK);
				System.out.println("tag work");
			} else if (VACATION_TAG_PATTERN.matcher(gesture).matches()){
				mediator.tagCurrentComponent(PhotoTags.VACATION);
				System.out.println("tag vacation");
			} else if (SHCOOL_TAG_PATTERN.matcher(gesture).matches()){
				mediator.tagCurrentComponent(PhotoTags.SHCOOL);
				System.out.println("tag school");
			} else if (FAMILY_TAG_PATTERN.matcher(gesture).matches()){
				mediator.tagCurrentComponent(PhotoTags.FAMILY);
				System.out.println("tag family");
			} else if (NEXT_PHOTO_PATTERN.matcher(gesture).matches()){
				mediator.showNextPhoto();
				System.out.println("next");
			} else if (PREV_PHOTO_PATTERN.matcher(gesture).matches()){
				mediator.showPreviousPhoto();
				System.out.println("prev");
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
