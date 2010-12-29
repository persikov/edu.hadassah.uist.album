/**
 *
 */
package edu.hadassah.uist.album.photo.app.component;

import java.awt.Point;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Sergey Persikov
 *
 */
public class Remark {

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
	 * @return Point
	 */
	public Point getStartPoint() {
		return points.peekFirst();
	}

	/**
	 * @return Object
	 */
	public Collection<Point> getPoints() {
		return points;
	}

	/**
	 * @param x
	 * @param y void
	 */
	public void addPoint(int x, int y) {
		points.offerLast(new Point(x, y));
	}

}
