package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Game;

/**
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class NewGamePromptPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final JTextField title;
	private final JTextField devs;
	private final JTextField year;
	private final JTextField esrb;
	
	public NewGamePromptPanel() {
		JLabel t = new JLabel("Title");
    	JLabel d = new JLabel("Developer(s)");
    	JLabel y = new JLabel("Year Released");
    	JLabel e = new JLabel("ESRB");
		
		title = new JTextField(20);
    	devs = new JTextField(20);
    	year = new JTextField(20);
    	esrb = new JTextField(20);
    	
    	JPanel tw = new JPanel();
    	tw.add(t);
    	tw.add(title);
    	JPanel dw = new JPanel();
    	dw.add(d);
    	dw.add(devs);
    	JPanel yw = new JPanel();
    	yw.add(y);
    	yw.add(year);
    	JPanel ew = new JPanel();
    	ew.add(e);
    	ew.add(esrb);
    	
    	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
    	add(tw);
    	add(dw);
    	add(yw);
    	add(ew);
	}
	
	public Game getGame() {
		return new Game(title.getText(), devs.getText(), Integer.parseInt(year.getText()), esrb.getText());
	}

}
