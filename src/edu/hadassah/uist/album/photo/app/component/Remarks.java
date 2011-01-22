/**
 *
 */
package edu.hadassah.uist.album.photo.app.component;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
public class Remarks {

	private final Deque<Remark> remarks = new LinkedList<Remark>();
	/**
	 * @param oldX
	 * @param oldY void
	 */
	public void startNew(int x, int y) {
		remarks.offerLast(new Remark(x, y));

	}

	/**
	 * @param currentX
	 * @param currentY void
	 */
	public void addPoint(int x, int y) {
		remarks.peekLast().addPoint(x,y);

	}

	/**
	 * @return Object
	 */
	public Collection<Remark> getAllRemarks() {
		return remarks;
	}

	/**
	 *  void
	 */
	public void clear() {
		remarks.clear();

	}

}
