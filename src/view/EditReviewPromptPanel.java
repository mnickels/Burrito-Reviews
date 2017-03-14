package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Zach Ouellette | arzalam@uw.edu
 */
public class EditReviewPromptPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final JTextArea content;
	JScrollPane scrollPane;
	
	public EditReviewPromptPanel(String oldReview) {
		
		JLabel t = new JLabel("Edit Your Review");
		
		content = new JTextArea(oldReview);
		scrollPane = new JScrollPane(content);
		setPreferredSize(new Dimension(500, 300));
    	
    	JPanel cw = new JPanel();
    	cw.add(t);
    	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
    	add(cw);
    	add(scrollPane, BorderLayout.CENTER);
	}
	
	public String getContent() {
		return new String(content.getText().toString());
	}

}