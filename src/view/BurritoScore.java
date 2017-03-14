package view;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class BurritoScore extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final JPanel burritoWrapper;
	private BurritoScoreMouseListener bm;
	
	public BurritoScore(int score, boolean clickable) {
		burritoWrapper = new JPanel(new FlowLayout());
        final JLabel gameScore = new JLabel("Game Score: ");
		add(gameScore);
        setScore(score);
        if (clickable) {
            bm = new BurritoScoreMouseListener(this.getWidth());
        	addMouseListener(bm);
        }
        add(burritoWrapper);
	}
	
	public int getBurritoScore() {
		return bm.getBurritos();
	}
	
	public void setScore(int burritos) {
		burritoWrapper.removeAll();
		for (int i = 0; i < burritos; i++) {
        	burritoWrapper.add(new JLabel(new ImageIcon(GUI.logo)));
        }
		redraw();
	}
	
	public void redraw() {
		revalidate();
		repaint();
	}
	
	class BurritoScoreMouseListener implements MouseListener {
		
		private int width;
		private int burritos;
		
		public BurritoScoreMouseListener(int pixelWidthOfContainer) {
			width = pixelWidthOfContainer;
			burritos = 0;
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {
			int burritos = (int) Math.round(arg0.getX() / (width / 10));
			if (burritos < 1) burritos = 1;
			else if (burritos > 10) burritos = 10;
			redraw();
		}
		
		public int getBurritos() {
			return burritos;
		}
		
	}

}
