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
 * Listener of the mouse gestures
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public class MouseGestureListener extends MouseAdapter implements
		MouseMotionListener {
	private static final Pattern DELETE_PATTERN = Pattern.compile("[RBC]+[ELD]+[RBC]+");
	private static final Pattern DELETE_ANNOTATION_PATTERN = Pattern.compile("[DE]+[L]+");
	private static final Pattern NEXT_PHOTO_PATTERN = Pattern.compile("[RBC]+");
	private static final Pattern PREV_PHOTO_PATTERN = Pattern.compile("[LAE]+");

	private static final Pattern WORK_TAG_PATTERN = Pattern.compile("[CD]+[BU]+[CD]+[RBU]+");
	private static final Pattern VACATION_TAG_PATTERN = Pattern.compile("[CD]+[BU]+");
	private static final Pattern SHCOOL_TAG_PATTERN = Pattern.compile("[LAE]+[CDR]+[LAE]+");
	private static final Pattern FAMILY_TAG_PATTERN = Pattern.compile("[U]+[B]*[R]+");

	/**	utility for  gesture recognition */
	private final MouseGesturesRecognizer gesturesRecognizer = new MouseGesturesRecognizer();

	/** application UI controller */
	private final IPhotoAlbumController mediator;
	/** indicate if curent mouse action is gestire */
	private boolean isGesture;
	/** indicate if clear annotation (remark) action should be recognized */
	private boolean enableClearAnnotations = true;


	/**
	 * Creates new instance of {@link MouseGestureListener}
	 * @param mediator
	 * @param enableClearAnnotations
	 */
	public MouseGestureListener(IPhotoAlbumController mediator,
			boolean enableClearAnnotations) {
		this(mediator);
		this.enableClearAnnotations = enableClearAnnotations;
	}
	/**
	 * Creates new instance of {@link MouseGestureListener}
	 * @param mediator
	 */
	public MouseGestureListener(IPhotoAlbumController mediator) {
		this.mediator = mediator;
	}
	/**
	 * On release event this method tries to recognize gesture using patterns,
	 * and perform corresponding operation
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
				mediator.toggleCurrentComponentTag(PhotoTags.WORK);
				System.out.println("tag work");
			} else if (VACATION_TAG_PATTERN.matcher(gesture).matches()){
				mediator.toggleCurrentComponentTag(PhotoTags.VACATION);
				System.out.println("tag vacation");
			} else if (SHCOOL_TAG_PATTERN.matcher(gesture).matches()){
				mediator.toggleCurrentComponentTag(PhotoTags.SHCOOL);
				System.out.println("tag school");
			} else if (FAMILY_TAG_PATTERN.matcher(gesture).matches()){
				mediator.toggleCurrentComponentTag(PhotoTags.FAMILY);
				System.out.println("tag family");
			} else if (enableClearAnnotations && DELETE_ANNOTATION_PATTERN.matcher(gesture).matches()){
				mediator.removeRemarks();
				System.out.println("clear annotation");
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
	 * Set state - is the curent mouse action is gesture
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		isGesture = (e.getButton() == MouseEvent.BUTTON3);
	}

	/**
	 * Pass current event to gesture recognizer
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (isGesture){
			gesturesRecognizer.processMouseEvent(e);
		}
	}

}
