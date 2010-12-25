import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class MyCanvas extends JPanel implements  MouseMotionListener
{
    Image img;      // Contains the image to draw on MyCanvas
	int currentX, currentY, oldX, oldY;

    public static void main(String[] args) {
        Frame f = new Frame("Have a nice day!");
        f.addWindowListener(new WindowAdapter() {
            @Override
			public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.add(new MyCanvas(), BorderLayout.CENTER);
        f.pack();
        f.show();
    }

    public MyCanvas()
    {
		File file = new File("Images"+System.getProperty("file.separator")+"cod.bmp");
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setPreferredSize(new Dimension(img.getWidth(this), img.getHeight(this)));
//        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}
		});
    }

    @Override
	public void paintComponent(Graphics g)
    {
        // Draws the image to the canvas
        g.drawImage(img, 0, 0, null);
    }


	/**
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		currentX = e.getX();
		currentY = e.getY();
		Graphics2D g = (Graphics2D)img.getGraphics();
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.drawLine(oldX, oldY, currentX, currentY);
        g.dispose();
        repaint();
		oldX = currentX;
		oldY = currentY;
	}

	/**
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
//		oldX = e.getX();
//		oldY = e.getY();
	}

}