/**
 *
 */
package edu.hadassah.uist.album.photo.app.listeners;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import edu.hadassah.uist.album.photo.app.component.PhotoModel;

/**
 * Listener of the remarks (annotations)
 *
 * @author Itay Cohen
 * @author Sergey Persikov
 */
public final class RemarkDrawListener extends MouseAdapter implements MouseMotionListener {

	private int currentX, currentY, oldX, oldY;
	private Color currColor = Color.BLACK;
	private final JComponent parent;
	private final PhotoModel photoModel;
	private final JComponent flippedPane;

	/**
	 * Creates new instance of {@link RemarkDrawListener}
	 * @param photoComponent
	 */
	public RemarkDrawListener(JComponent parent, JComponent canvas, PhotoModel photoModel) {
		this.parent = parent;
		this.flippedPane = canvas;
		this.photoModel = photoModel;
	}

	/**
	 * Starts new remark, if LMB was used
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		oldX = e.getX();
		oldY = e.getY();
		Graphics2D g2d = getGraphics2D();
		g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		if (e.getButton() == MouseEvent.BUTTON3){
			currColor=Color.RED;
		} else {
			photoModel.startNewRemark(oldX, oldY);
			currColor=Color.BLACK;
		}
	}

	/**
	 * get graphics from the photo component
	 * @return Graphics2D
	 */
	private Graphics2D getGraphics2D() {
		Graphics2D g2d;
		if ( photoModel.isFlipped()){
			g2d = (Graphics2D)flippedPane.getGraphics();
		} else {
			g2d = (Graphics2D)photoModel.getImage().getGraphics();
		}
		return g2d;
	}

	/**
	 * For gesture, we need to remove it from the photo - repaint for it
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (currColor == Color.RED){
			parent.repaint();
		}
	}

	/**
	 * Paint he remark/gesture and in case it is remark mode - add new remar point
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Graphics2D g2d = getGraphics2D();
		g2d.setColor(currColor);
		g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		currentX = e.getX();
		currentY = e.getY();
		g2d.drawLine(oldX, oldY, currentX, currentY);
		//			graphics2d.dispose();
		oldX = currentX;
		oldY = currentY;
		if (currColor == Color.BLACK){
			photoModel.addRemarkPoint(currentX, currentY);
		}
	}

}