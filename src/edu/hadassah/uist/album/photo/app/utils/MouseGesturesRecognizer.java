package edu.hadassah.uist.album.photo.app.utils;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;


/**
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public class MouseGesturesRecognizer {
	/**
	 * String representation of left movement.
	 */
	private static final String LEFT_MOVE = "L";
	/**
	 * String representation of right movement.
	 */
	private static final String RIGHT_MOVE = "R";
	/**
	 * String representation of up movement.
	 */
	private static final String UP_MOVE = "U";
	/**
	 * String representation of down movement.
	 */
	private static final String DOWN_MOVE = "D";

	private static final String UP_LEFT_MOVE = "A";
	private static final String UP_RIGHT_MOVE = "B";
	private static final String DOWN_RIGHT_MOVE = "C";
	private static final String DOWN_LEFT_MOVE = "E";

	private static final double TAN_BOUND = 2;

	private static final double LOWER_TAN_BOUND = 1/TAN_BOUND;
	private static final double UPPER_TAN_BOUND = 1*TAN_BOUND;




	/**
	 * Grid size. Default is 30.
	 */
	private int gridSize = 30;
	/**
	 * Reference to {@link MouseGestures}.
	 */
	/**
	 * Start point for current movement.
	 */
	private Point startPoint = null;
	/**
	 * String representation of gesture.
	 */
	private final StringBuffer gesture = new StringBuffer();

	/**
	 * Processes mouse event.
	 *
	 * @param mouseEvent MouseEvent
	 */
	public void processMouseEvent(MouseEvent mouseEvent) {
		if (!(mouseEvent.getSource() instanceof Component)) {
			return;
		}
		Point mouseEventPoint = mouseEvent.getPoint();
		SwingUtilities.convertPointToScreen(mouseEventPoint, (Component) mouseEvent.getSource());
		if (startPoint == null) {
			startPoint = mouseEventPoint;
			return;
		}
		int deltaX = getDeltaX(startPoint, mouseEventPoint);
		int deltaY = getDeltaY(startPoint, mouseEventPoint);
		int absDeltaX = Math.abs(deltaX);
		int absDeltaY = Math.abs(deltaY);

		if ((absDeltaX < gridSize) && (absDeltaY < gridSize)) {
			return;
		}
		double absTangent = ((double) absDeltaX) / absDeltaY;

		if (absTangent > LOWER_TAN_BOUND && absTangent < UPPER_TAN_BOUND){
			if (deltaY < 0) {
				if ( deltaX < 0){
					saveMove(UP_LEFT_MOVE);
				} else { //delataX > 0
					saveMove(UP_RIGHT_MOVE);
				}
			} else { //deltaY > 0
				if ( deltaX < 0){
					saveMove(DOWN_LEFT_MOVE);
				} else { //delataX > 0
					saveMove(DOWN_RIGHT_MOVE);
				}
			}

		} else {
			if (absTangent < 1) {
				if (deltaY < 0) {
					saveMove(UP_MOVE);
				} else {
					saveMove(DOWN_MOVE);
				}
			} else {
				if (deltaX < 0) {
					saveMove(LEFT_MOVE);
				} else {
					saveMove(RIGHT_MOVE);
				}
			}
		}

		startPoint = mouseEventPoint;
	}

	/**
	 * Returns delta x.
	 *
	 * @param a First point
	 * @param b Second point
	 * @return Delta x
	 */
	private int getDeltaX(Point a, Point b) {
		return b.x - a.x;
	}

	/**
	 * Returns delta y.
	 *
	 * @param a First point
	 * @param b Second point
	 * @return Delta y
	 */
	private int getDeltaY(Point a, Point b) {
		return b.y - a.y;
	}

	/**
	 * Adds movement to buffer.
	 *
	 * @param move String representation of recognized movement
	 */
	private void saveMove(String move) {
		// should not store two equal moves in succession
		if ((gesture.length() > 0) && (gesture.charAt(gesture.length() - 1) == move.charAt(0))) {
			return;
		}
		gesture.append(move);
	}

	/**
	 * Returns current grid size (minimum mouse movement length to be recognized).
	 *
	 * @return Grid size in pixels. Default is 30.
	 */
	public int getGridSize() {
		return gridSize;
	}

	/**
	 * Sets grid size (minimum mouse movement length to be recognized).
	 *
	 * @param gridSize New grid size in pixels
	 */
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}

	/**
	 * Returns string representation of mouse gesture.
	 *
	 * @return String representation of mouse gesture. "L" for left, "R" for right,
	 *         "U" for up, "D" for down movements. For example: "ULD".
	 */
	public String getGesture() {
		return gesture.toString();
	}

	/**
	 * Indicates whether any movements were recognized.
	 *
	 * @return <code>true</code> if there are recognized movements; <code>false</code> otherwise
	 */
	public boolean isGestureRecognized() {
		return gesture.length() > 0;
	}

	/**
	 * Clears temporary info about previous gesture.
	 */
	public void clearTemporaryInfo() {
		startPoint = null;
		gesture.delete(0, gesture.length());
	}
}
