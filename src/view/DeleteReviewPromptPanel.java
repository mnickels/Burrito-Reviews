package view;


import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Zach Ouellette | arzalam@uw.edu
 */
public class DeleteReviewPromptPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	
	public DeleteReviewPromptPanel() {
		
		JLabel t = new JLabel("Are you sure you want to delete your old review?");
    	
    	JPanel cw = new JPanel();
    	cw.add(t);
    	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
    	add(cw);
	}

}