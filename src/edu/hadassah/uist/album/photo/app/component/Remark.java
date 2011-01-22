/**
 *
 */
package edu.hadassah.uist.album.photo.app.component;

import java.awt.Point;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * This class represents single remark
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public class Remark {

	/** {@link Deque} of the {@link Point} that represent the remark */
	private final Deque<Point> points = new LinkedList<Point>();

	/**
	 * Creates new instance of {@link Remark}
	 * @param x
	 * @param y
	 */
	public Remark(int x, int y) {
		points.offerFirst(new Point(x, y));
	}

	/**
	 * @return start {@link Point} of the remark
	 */
	public Point getStartPoint() {
		return points.peekFirst();
	}

	/**
	 * @return all {@link Point} of the remark
	 */
	public Collection<Point> getPoints() {
		return points;
	}

	/**
	 * Add new point to the remark with given coordinates
	 * @param x
	 * @param y void
	 */
	public void addPoint(int x, int y) {
		points.offerLast(new Point(x, y));
	}

}
