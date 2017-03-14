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
public class EditGamePromptPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final JTextField title;
	private final JTextField devs;
	private final JTextField year;
	private final JTextField esrb;
	private final int gameId;
	
	public EditGamePromptPanel(Game theGame) {
		JLabel t = new JLabel("Title");
    	JLabel d = new JLabel("Developer(s)");
    	JLabel y = new JLabel("Year Released");
    	JLabel e = new JLabel("ESRB");

    	title = new JTextField();
		title.setText(theGame.getTitle());
    	devs = new JTextField();
    	devs.setText(theGame.getDeveloper());
    	year = new JTextField();
    	year.setText(String.format("%d", theGame.getYear()));
    	esrb = new JTextField();
    	esrb.setText(theGame.getEsrb());
		gameId = theGame.getGameId();
    	
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
	
	public String getTitle(){
		return title.getText();
	}
	
	public String getDevs(){
		return devs.getText();
	}
	
	public int getYear(){
		return Integer.parseInt(year.getText());
	}
	
	public String getEsrb(){
		return esrb.getText();
	}
	
	public int getGameId(){
		return gameId;
	}
	

}
