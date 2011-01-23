/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.regex.Pattern;

import edu.hadassah.uist.album.photo.app.component.PhotoTags;
import edu.hadassah.uist.album.photo.app.utils.MessagesUtils;
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
	private int currentX, currentY, oldX, oldY;


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
				mediator.removeCurrentComponent();
				mediator.setStatusMessage(MessagesUtils.getString("album.message.gesture.recognized.delete"));
			} else if (WORK_TAG_PATTERN.matcher(gesture).matches()){
				mediator.toggleCurrentComponentTag(PhotoTags.WORK);
				mediator.setStatusMessage(MessagesUtils.getString("album.message.gesture.recognized.tag.work"));
			} else if (VACATION_TAG_PATTERN.matcher(gesture).matches()){
				mediator.toggleCurrentComponentTag(PhotoTags.VACATION);
				mediator.setStatusMessage(MessagesUtils.getString("album.message.gesture.recognized.tag.vacation"));
			} else if (SHCOOL_TAG_PATTERN.matcher(gesture).matches()){
				mediator.toggleCurrentComponentTag(PhotoTags.SHCOOL);
				mediator.setStatusMessage(MessagesUtils.getString("album.message.gesture.recognized.tag.school"));
			} else if (FAMILY_TAG_PATTERN.matcher(gesture).matches()){
				mediator.toggleCurrentComponentTag(PhotoTags.FAMILY);
				mediator.setStatusMessage(MessagesUtils.getString("album.message.gesture.recognized.tag.family"));
			} else if (enableClearAnnotations && DELETE_ANNOTATION_PATTERN.matcher(gesture).matches()){
				mediator.removeRemarks();
				mediator.setStatusMessage(MessagesUtils.getString("album.message.gesture.recognized.remark.clear"));
			} else if (NEXT_PHOTO_PATTERN.matcher(gesture).matches()){
				mediator.showNextPhoto();
				mediator.setStatusMessage(MessagesUtils.getString("album.message.gesture.recognized.photo.next"));
			} else if (PREV_PHOTO_PATTERN.matcher(gesture).matches()){
				mediator.showPreviousPhoto();
				mediator.setStatusMessage(MessagesUtils.getString("album.message.gesture.recognized.photo.prev"));
			} else {
				mediator.setStatusMessage(MessagesUtils.getString("album.message.gesture.not.recognized"));
			}
			mediator.refreshUI();
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
		oldX = e.getX();
		oldY = e.getY();
	}

	/**
	 * Pass current event to gesture recognizer
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (isGesture){
			gesturesRecognizer.processMouseEvent(e);
			Component component = e.getComponent();
			if (component != null){
				Graphics2D g2d = (Graphics2D)component.getGraphics();
				g2d.setColor(Color.RED);
				g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				currentX = e.getX();
				currentY = e.getY();
				g2d.drawLine(oldX, oldY, currentX, currentY);
				//			graphics2d.dispose();
				oldX = currentX;
				oldY = currentY;
			}

		}
	}

}
