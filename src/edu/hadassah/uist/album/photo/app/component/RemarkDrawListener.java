/**
 *
 */
package edu.hadassah.uist.album.photo.app.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public final class RemarkDrawListener extends MouseAdapter implements MouseMotionListener {

	private int currentX, currentY, oldX, oldY;
	private Color currColor = Color.BLACK;
	private final Component component;
	private final PhotoModel photoModel;
	private final Component canvas;

	/**
	 * Creates new instance of {@link RemarkDrawListener}
	 * @param photoComponent
	 */
	public RemarkDrawListener(Component parent, Component canvas, PhotoModel photoModel) {
		this.component = parent;
		this.canvas = canvas;
		this.photoModel = photoModel;
	}

	/**
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//				System.out.println("MouseMotionAdapter:mousePressed");
		oldX = e.getX();
		oldY = e.getY();
		Graphics2D g2d = (Graphics2D)canvas.getGraphics();
		g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		if (e.getButton() == MouseEvent.BUTTON3){
			currColor=Color.RED;
		} else {
			photoModel.startNewRemark(oldX, oldY);
			currColor=Color.BLACK;
		}
	}

	/**
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		component.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//			System.out.println("MouseMotionAdapter:mouseDragged");
		Graphics2D g2d = (Graphics2D)canvas.getGraphics();
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