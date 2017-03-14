package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Zach Ouellette | arzalam@uw.edu
 */
public class RateGamePromptPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	static final int BUR_MIN = 0;
	static final int BUR_MAX = 10;
	static final int BUR_INIT = 5;
	
	private int rating = 0;
	
	public RateGamePromptPanel() {
		
		JLabel t = new JLabel("How many Burritos should this game get?");
		JSlider burritoSlider = new JSlider(JSlider.HORIZONTAL,
                BUR_MIN, BUR_MAX, BUR_INIT);
		burritoSlider.addChangeListener(new SliderListener());
		burritoSlider.setMajorTickSpacing(5);
		burritoSlider.setMinorTickSpacing(1);
		burritoSlider.setPaintTicks(true);
		burritoSlider.setPaintLabels(true);
    	JPanel cw = new JPanel();
    	cw.add(t);
    	cw.add(burritoSlider);
    	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
    	add(cw);
	}
	
	public int getRating() {
		return rating;
	}
	
	
	class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting()) {
				int burritos = (int)source.getValue();
				rating = burritos;
			}
		}
	}
	
	

}