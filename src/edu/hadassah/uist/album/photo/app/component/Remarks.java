/**
 *
 */
package edu.hadassah.uist.album.photo.app.component;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * This class tepresents collections of remarks
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public class Remarks {

	/** {@link Deque} of the {@link Remark} */
	private final Deque<Remark> remarks = new LinkedList<Remark>();

	/**
	 * Creates new {@link Remark} with given coordinates
	 * @param x
	 * @param y void
	 */
	public void startNew(int x, int y) {
		remarks.offerLast(new Remark(x, y));
	}

	/**
	 * Add new point to the remark with given coordinates
	 * @param x
	 * @param y
	 */
	public void addPoint(int x, int y) {
		remarks.peekLast().addPoint(x,y);

	}

	/**
	 * @return all {@link Remark} of the photo
	 */
	public Collection<Remark> getAllRemarks() {
		return remarks;
	}

	/**
	 *  remove all remarks from the photo
	 */
	public void clear() {
		remarks.clear();
	}
}
