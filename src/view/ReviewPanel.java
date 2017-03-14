package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Game;
import model.Query;

/**
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class ReviewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public ReviewPanel(Game g) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setGame(g);
	}
	
	public void setGame(Game g) {
		removeAll();
		for (String review : Query.getReviewsByGame(g)) {
			
		}
	}

}
