package edu.hadassah.uist.album.photo.app.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/**
 * @author Itay Cohen
 * @author Sergey Persikov
 *
 */
final class PhotoCanvasUI extends ComponentUI {
	private final JComponent canvas;
	private final PhotoModel photoModel;

	/**
	 * Creates new instance of {@link PhotoCanvasUI}
	 * @param photoComponent
	 * @param canvas
	 */
	public PhotoCanvasUI(PhotoModel photoModel, JComponent canvas) {
		super();
		this.photoModel = photoModel;
		this.canvas = canvas;
	}
	/**
	 * @param photo
	 * @param graphics void
	 */
	public void paintComponent(Graphics graphics){
		Graphics2D g2 = (Graphics2D) graphics;
		paintBackground(g2, photoModel);
		if (photoModel.flipped){
			paintRemarks(g2, photoModel);
			canvas.setVisible(true);
		}
		else{
			g2.drawImage(photoModel.getImage(), 20, 20, canvas);
			canvas.setVisible(false);
		}

	}
	/**
	 * @param g void
	 */
	private void paintRemarks(Graphics2D g, PhotoModel model) {
		Point oldPoint = null;

		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		for (Remark currRemark : model.getAllRemarks()) {
			oldPoint = currRemark.getStartPoint();

			for (Point currPoint : currRemark.getPoints()) {
				if (! oldPoint.equals(currPoint) ){
					g.drawLine(oldPoint.x, oldPoint.y, currPoint.x, currPoint.y);
					oldPoint = currPoint;
				}
			}
		}
	}

	/**
	 * @param g
	 * @param photo void
	 */
	private void paintBackground(Graphics2D g2, PhotoModel model)
	{
		Dimension dimension = new Dimension(model.getImage().getWidth(canvas), model.getImage().getHeight(canvas));
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRoundRect(10, 10, dimension.width+20, dimension.height+20, 20, 20);
		g2.setColor(Color.BLACK);
		g2.drawRoundRect(10, 10, dimension.width+20, dimension.height+20, 20, 20);


	}

}