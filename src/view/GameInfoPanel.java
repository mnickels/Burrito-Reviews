package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

/**
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class GameInfoPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param display is to be displayed if true, otherwise make fields editable
	 */
	public GameInfoPanel(Game toDisplay) {
		JLabel t = new JLabel("Title: " + toDisplay.getTitle());
    	JLabel d = new JLabel("Developer(s): " + toDisplay.getDeveloper());
    	JLabel y = new JLabel("Year Released: " + toDisplay.getYear());
    	JLabel e = new JLabel("ESRB: " + toDisplay.getEsrb());
    	
    	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	add(t);
    	add(d);
    	add(y);
    	add(e);
	}

}
