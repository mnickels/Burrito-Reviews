/*
 * TCSS 445 B - Group Project
 */
package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import model.Game;
import model.Query;
import model.User;
import model.UserType;



/**
 * This is the GUI class that will create the front end of the program.
 * 
 * @author Thomas Schmidt
 * @author Mike Nickels
 * @version 1.0
 */
public class GUI extends JFrame {

    /** This is the generated Serialization number. */
    private static final long serialVersionUID = -2055958502864278259L;

    //Constants
    /** This is the default starting size. */
    private static final Dimension DRAWING_PANEL_PREFERRED_SIZE = new Dimension(600, 500);
    /** This is the default starting size. */
    private static final Dimension TEXT_BOX_SIZE = new Dimension(10, 40);
    final static String LOGINPANEL = "Login Screen";
    final static String HOMEPANEL = "Home Screen";
    final static String REVIEWERPANEL = "Reviewer Home Screen";
    final static String REGISTRATIONPANEL = "Registration Screen";
    final static String ADMINPANEL = "Admin Screen";
    
    public final static String APP_NAME = "Burrito Reviews";
    
    //Fields    
    /** This is the JFrame that will pop up when the program runs. */
    private final JFrame myFrame;
    
    /** This is the south login panel. */
    private final JPanel myCardsWest;

    private final JPanel myCardsSouth;

    private final JPanel myCardsNorth;
    
    private final JPanel myCardsCenter;
    
    private final JPanel myCardsEast;
    
    private User currentUser;
    
    public static Image logo;
    
    /**
     *  Constructor for the GUI.
     */
    public GUI() {
        super();

        myFrame = new JFrame("Burrito Reviews");
        myCardsWest = new JPanel(new CardLayout());
        myCardsSouth = new JPanel(new CardLayout());
        myCardsNorth = new JPanel(new CardLayout());
        myCardsCenter = new JPanel(new CardLayout());
        myCardsEast = new JPanel(new CardLayout());
        
        logo = null;
        try {
			logo = ImageIO.read(new File("burrito.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        if (logo != null) {
        	myFrame.setIconImage(logo);
        }
    }

    /**
     *  This method starts the GUI. 
     */
    public void start() {
        //Instances of the menu and tool bars

    	loginScreen();
    	RegistrationScreen();
    	UserScreen();
    	reviewerScreen();
    	adminScreen();
    	
        //Constructing the JFrame
        myFrame.setPreferredSize(DRAWING_PANEL_PREFERRED_SIZE);
        myFrame.setBackground(Color.WHITE);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(myCardsSouth, BorderLayout.SOUTH);
        myFrame.add(myCardsNorth, BorderLayout.NORTH);
        myFrame.add(myCardsCenter, BorderLayout.CENTER);
        myFrame.add(myCardsEast, BorderLayout.EAST);
        myFrame.add(myCardsWest, BorderLayout.WEST);
        myFrame.pack();
        
        //Setting visibility and center of screen
        final Toolkit tk = Toolkit.getDefaultToolkit();       
        myFrame.setLocation(
            (int) (tk.getScreenSize().getWidth() / 2 - myFrame.getWidth() / 2),
            (int) (tk.getScreenSize().getHeight() / 2 
                            - myFrame.getHeight() / 2));               
        myFrame.setVisible(true);
               
    }
    
    /**
     * This method builds the Bottom options buttons.
     */
    private void loginScreen() {
    	
    	// card creation
    	JPanel northCard = new JPanel();
    	JPanel southCard = new JPanel();
    	JPanel centerCard = new JPanel();
    	JPanel eastCard = new JPanel();
    	JPanel westCard = new JPanel();
    	centerCard.setLayout(new BoxLayout(centerCard, BoxLayout.Y_AXIS));
    	
    	// button field and label creation
        final JButton open = new JButton("Login");
        final JButton save = new JButton("Register");
        final JLabel un = new JLabel("UserName");
        final JLabel grt = new JLabel(APP_NAME);
		grt.setForeground(new Color(72, 64, 188));
		Font titleFont = new Font("Garamond", Font.BOLD, 72);
		grt.setFont(titleFont);
		add(grt);
		JLabel img = null;
		img = new JLabel(new ImageIcon(logo.getScaledInstance(64, 64, BufferedImage.SCALE_FAST)));
        un.setSize(TEXT_BOX_SIZE);
        final JLabel pw = new JLabel("Password");
        pw.setSize(TEXT_BOX_SIZE);
        final JTextField userName = new JTextField();
        userName.setPreferredSize(TEXT_BOX_SIZE);
        final JPasswordField password = new JPasswordField();
        password.setPreferredSize(TEXT_BOX_SIZE);
        
        // padding to make login screen look correct
        final JLabel padding1 = new JLabel(" ");
        final JLabel padding2 = new JLabel(" ");
        final JLabel padding3 = new JLabel(" ");
        final JLabel padding4 = new JLabel(" ");
        final JLabel padding5 = new JLabel(" ");
        final JLabel padding6 = new JLabel(" ");
        final JLabel padding7 = new JLabel(" ");
        final JLabel padding8 = new JLabel(" ");
        final JLabel padding9 = new JLabel(" ");
        final JLabel padding10 = new JLabel(" ");
        final JLabel padding11 = new JLabel(" ");
        final JLabel padding12 = new JLabel(" ");
        final JLabel padding13 = new JLabel(" ");
        final JLabel padding14 = new JLabel(" ");
        final JLabel padding15 = new JLabel(" ");
        final JLabel padding16 = new JLabel(" ");
        final JLabel padding17 = new JLabel(" ");
        final JLabel padding18 = new JLabel(" ");
        final JLabel padding19 = new JLabel("                                         ");
        final JLabel padding20 = new JLabel("                                         ");
        
        // adding to cards
        eastCard.add(padding19);
        westCard.add(padding20);
        northCard.add(grt);
        northCard.add(img);
        southCard.add(open);
        southCard.add(save);
        centerCard.add(padding3);
        centerCard.add(padding4);
        centerCard.add(padding5);
        centerCard.add(padding6);
        centerCard.add(padding7);
        centerCard.add(padding8);
        centerCard.add(padding9);
        centerCard.add(un);
        centerCard.add(userName);
        centerCard.add(pw);
        centerCard.add(password);
        centerCard.add(padding10);
        centerCard.add(padding11);
        centerCard.add(padding12);
        centerCard.add(padding13);
        centerCard.add(padding14);
        centerCard.add(padding15);
        centerCard.add(padding16);
        centerCard.add(padding17);
        centerCard.add(padding18);
        
        myCardsNorth.add(northCard, LOGINPANEL);
        myCardsSouth.add(southCard, LOGINPANEL);
        myCardsCenter.add(centerCard, LOGINPANEL);
        myCardsEast.add(eastCard, LOGINPANEL);
        myCardsWest.add(westCard, LOGINPANEL);

        pack();
        
        /**
         *  This class switches to the Homepage when the Login button is pressed.
         */
        class LoginButtonActionListener implements ActionListener {
            
            /**
             * This method opens myJFC OpenDialog box.
             * @param theButtonClick when the button action event takes place
             * 
             * 
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
                System.out.println("Login");
                
                StringBuilder sb = new StringBuilder();
                for (char c : password.getPassword()) {
                	sb.append(c);
                }
                currentUser = Query.getUserByNameAndPassword(userName.getText(), sb.toString());
                System.out.println(currentUser);
                if (currentUser != null) {
                	switch (currentUser.getType()) {
                	case User:
                		pageManagement(HOMEPANEL);
                		break;
                	case Reviewer:
                    	pageManagement(REVIEWERPANEL);
                		break;
                	case Admin:
                		pageManagement(ADMINPANEL);
                		break;
                	}
                }
            }
        }
        open.addActionListener(new LoginButtonActionListener());
        
        /**
         *  This class calls showSaveDialog() when the Save button is pressed.
         */
        class SaveButtonActionListener implements ActionListener {
            
            /**
             * This method opens myJFC saveDialog box.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("Register!");
            	pageManagement(REGISTRATIONPANEL);
            }
        }
        save.addActionListener(new SaveButtonActionListener());
    }
    
    /**
     * This method builds the User Screen.
     */
    private void adminScreen() {
    	
    	//Card creation 
    	JPanel northCard = new JPanel();
    	JPanel southCard = new JPanel();
    	JPanel centerCard = new JPanel();
    	JPanel eastCard = new JPanel();
    	JPanel westCard = new JPanel();
    	centerCard.setLayout(new BoxLayout(centerCard, BoxLayout.Y_AXIS));
    	
    	// setting up the drop down list of games
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        
        List<Game> games = Query.getGames();
        String comboBoxItems[] = new String[games.size()];
        for (int i = 0; i < games.size(); i++) {
        	comboBoxItems[i] = games.get(i).getTitle();
        }
        
        JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
        cb.setEditable(false);
        comboBoxPane.add(cb);
    	
        // constructing the button and text fields
        final JButton close = new JButton("Logout");
        final JButton editGame = new JButton("Edit Game");
        final JButton addGame = new JButton("Add Game");
        final JLabel gameTitle = new JLabel("Game Title");

        // burrito rating
        BurritoScore bs = new BurritoScore((int) Math.round(Query.getGameAvgRating(Query.getGameByName((String) cb.getSelectedItem()))), false);
        
        final JLabel review = new JLabel("Reviews:");
        
        // padding
        final JLabel padding1 = new JLabel(" ");
        final JLabel padding2 = new JLabel(" ");
        
        final JButton delGame = new JButton("Delete");
        
        // needs some code to pull from database and display all reviews
        
        // adding components to cards
        northCard.add(gameTitle);
        northCard.add(comboBoxPane);
        northCard.add(bs);
        southCard.add(addGame);
        southCard.add(editGame);
        southCard.add(delGame);
        southCard.add(close); 
        centerCard.add(review);
        eastCard.add(padding1);
        westCard.add(padding2);

        // adding cards to pack of cards
        myCardsNorth.add(northCard, ADMINPANEL);
        myCardsSouth.add(southCard, ADMINPANEL);
        myCardsCenter.add(centerCard, ADMINPANEL);
        myCardsEast.add(eastCard, ADMINPANEL);
        myCardsWest.add(westCard, ADMINPANEL);
        
        class ComboBoxActionListener implements ActionListener {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String gameTitle = (String) cb.getSelectedItem();
				final int burritos = (int) Math.round(Query.getGameAvgRating(Query.getGameByName(gameTitle)));
		        bs.setScore(burritos);
		        revalidate();
		        repaint();
        	}
        }
        cb.addActionListener(new ComboBoxActionListener());
        
        /**
         *  This class logs the user out when the button is pressed.
         */
        class CloseButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("Logout!");
            	pageManagement(LOGINPANEL);
            }
        }
        close.addActionListener(new CloseButtonActionListener());
        
        /**
         *  This class opens a new review text box when the button is pressed.
         */
        class NewGameButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("NewGame!");
            	NewGamePromptPanel p = new NewGamePromptPanel();
            	
            	int button = JOptionPane.showConfirmDialog(null, p, "Add New Game", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            	switch (button) {
            	case JOptionPane.OK_OPTION:
                	boolean success = Query.addGame(p.getGame());
                	if (!success) {
                		JOptionPane.showMessageDialog(null, "Failed to add Game due to improper inputs.");
                	}
            		break;
            	case JOptionPane.CANCEL_OPTION:
            		break;
            	default:
            		break;
            	}
            	invalidate();
            	repaint();
            	pageManagement(ADMINPANEL);
            }
        }
        addGame.addActionListener(new NewGameButtonActionListener());
        
        class DeleteGameButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	Query.removeGame(Query.getGameByName((String) cb.getSelectedItem()));
            	invalidate();
            	repaint();
            	pageManagement(ADMINPANEL);
            }
        }
        delGame.addActionListener(new DeleteGameButtonActionListener());
		
		class EditGameButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("EditingGame!");
            	EditGamePromptPanel p = new EditGamePromptPanel(Query.getGameByName((String) cb.getSelectedItem()));
            	
            	int button = JOptionPane.showConfirmDialog(null, p, "Edit Existing Game", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            	switch (button) {
            	case JOptionPane.OK_OPTION:
                	boolean success = Query.editGameTitle(p.getGameId(), p.getTitle());
					success &= Query.editGameYear(p.getGameId(), p.getYear());
					success &= Query.editGameESRB(p.getGameId(), p.getEsrb());
					success &= Query.editGameDeveloper(p.getGameId(), p.getDevs());
                	if (!success) {
                		JOptionPane.showMessageDialog(null, "Failed to edit game due to improper inputs.");
                	}
            		break;
            	case JOptionPane.CANCEL_OPTION:
            		break;
            	default:
            		break;
            	}
            	pageManagement(ADMINPANEL);
            }
        }
        editGame.addActionListener(new EditGameButtonActionListener());
        
        /**
         *  This class submits the review when the button is pressed.
         */
        class SubmitReviewButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("Add New Game!");
            	// needs to send the review to the database

            }
        }
        addGame.addActionListener(new SubmitReviewButtonActionListener());
    }
    
    /**
     * This method builds the User Screen.
     */
    private void reviewerScreen() {
    	
    	//Card creation 
    	JPanel northCard = new JPanel();
    	JPanel southCard = new JPanel();
    	JPanel centerCard = new JPanel();
    	JPanel eastCard = new JPanel();
    	JPanel westCard = new JPanel();
    	centerCard.setLayout(new BoxLayout(centerCard, BoxLayout.Y_AXIS));
    	
    	// setting up the drop down list of games
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        
        List<Game> games = Query.getGames();
        String comboBoxItems[] = new String[games.size()];
        for (int i = 0; i < games.size(); i++) {
        	comboBoxItems[i] = games.get(i).getTitle();
        }
        JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
        
        cb.setEditable(false);
        comboBoxPane.add(cb);
    	
        // constructing the button and text fields
        final JButton close = new JButton("Logout");
        final JButton newReview = new JButton("New Review");
        final JButton submitReview = new JButton("Submit Review");
        final JLabel gameTitle = new JLabel("Game Title");

        // burrito rating
        BurritoScore bs = new BurritoScore((int) Math.round(Query.getGameAvgRating(Query.getGameByName((String) cb.getSelectedItem()))), false);
        
        final JLabel review = new JLabel("New Review:");
        
        // padding
        final JLabel padding1 = new JLabel(" ");
        final JLabel padding2 = new JLabel(" ");
        
        // own review
        final JLabel myReview = new JLabel("My Review:");
        final JTextArea reviewbox = new JTextArea();
        reviewbox.setEditable(false);
        
        // needs some code to pull from database and display all reviews
        
        // adding components to cards
        northCard.add(gameTitle);
        northCard.add(comboBoxPane);
        northCard.add(bs);
        southCard.add(newReview);
        southCard.add(submitReview);
        southCard.add(close); 
        centerCard.add(review);
        eastCard.add(padding1);
        westCard.add(padding2);

        // adding cards to pack of cards
        myCardsNorth.add(northCard, REVIEWERPANEL);
        myCardsSouth.add(southCard, REVIEWERPANEL);
        myCardsCenter.add(centerCard, REVIEWERPANEL);
        myCardsEast.add(eastCard, REVIEWERPANEL);
        myCardsWest.add(westCard, REVIEWERPANEL);
        
        class ComboBoxActionListener implements ActionListener {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String gameTitle = (String) cb.getSelectedItem();
				final int burritos = (int) Math.round(Query.getGameAvgRating(Query.getGameByName(gameTitle)));
		        bs.setScore(burritos);
		        revalidate();
		        repaint();
        	}
        }
        cb.addActionListener(new ComboBoxActionListener());
        
        /**
         *  This class logs the user out when the button is pressed.
         */
        class CloseButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("Logout!");
            	pageManagement(LOGINPANEL);
            }
        }
        close.addActionListener(new CloseButtonActionListener());
        
        class NewReviewButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("NewGame!");
            	NewGamePromptPanel p = new NewGamePromptPanel();
            	
            	int button = JOptionPane.showConfirmDialog(null, p, "Add New Game", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            	switch (button) {
            	case JOptionPane.OK_OPTION:
                	boolean success = Query.addGame(p.getGame());
                	if (!success) {
                		JOptionPane.showMessageDialog(null, "Failed to add Game due to improper inputs.");
                	}
            		break;
            	case JOptionPane.CANCEL_OPTION:
            		break;
            	default:
            		break;
            	}
            	pageManagement(ADMINPANEL);
            }
        }
        //addGame.addActionListener(new NewReviewButtonActionListener());
		
		class EditReviewButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("EditingGame!");
            	EditGamePromptPanel p = new EditGamePromptPanel(Query.getGameByName(gameTitle.getText()));
            	
            	int button = JOptionPane.showConfirmDialog(null, p, "Edit Existing Game", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            	switch (button) {
            	case JOptionPane.OK_OPTION:
                	boolean success = Query.editGameTitle(p.getGameId(), p.getTitle());
					success &= Query.editGameYear(p.getGameId(), p.getYear());
					success &= Query.editGameESRB(p.getGameId(), p.getEsrb());
					success &= Query.editGameDeveloper(p.getGameId(), p.getDevs());
                	if (!success) {
                		JOptionPane.showMessageDialog(null, "Failed to edit game due to improper inputs.");
                	}
            		break;
            	case JOptionPane.CANCEL_OPTION:
            		break;
            	default:
            		break;
            	}
            	pageManagement(ADMINPANEL);
            }
        }
        //editGame.addActionListener(new EditReviewButtonActionListener());
    }
    
    /**
     * This method builds the User Screen.
     */
    private void UserScreen() {
    	
    	//Card creation 
    	JPanel northCard = new JPanel();
    	JPanel southCard = new JPanel();
    	JPanel centerCard = new JPanel();
    	JPanel eastCard = new JPanel();
    	JPanel westCard = new JPanel();
    	centerCard.setLayout(new BoxLayout(centerCard, BoxLayout.Y_AXIS));
    	
    	
    	
    	// setting up the drop down list of games
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        
        List<Game> games = Query.getGames();
        
        String comboBoxItems[] = new String[games.size()];
        // add to combobox
        for (int i = 0; i < games.size(); i++) {
        	comboBoxItems[i] = games.get(i).getTitle();
        }
        JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
        
        cb.setEditable(false);
        comboBoxPane.add(cb);
    	
        // setting up the scroll pane
        JPanel jpAcc = new JPanel();
        jpAcc.setLayout(new BorderLayout());
        String labels[] = {"Test 1"};
     
        JList checkBoxesJList = new JList<String>(labels);

        checkBoxesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(checkBoxesJList);
        jpAcc.add(scrollPane);

        getContentPane().add(jpAcc);
        pack();
        
        // constructing the button and text fields
        final JButton close = new JButton("Logout");
        final JLabel gameTitle = new JLabel("Game Title");

        // burrito rating
        BurritoScore bs = new BurritoScore((int) Math.round(Query.getGameAvgRating(Query.getGameByName((String) cb.getSelectedItem()))), false);
        
        final JLabel review = new JLabel("Reviews:");
        
        // padding
        final JLabel padding1 = new JLabel(" ");
        final JLabel padding2 = new JLabel(" ");
        
        // needs some code to pull from database and display all reviews
        
        // adding components to cards
        northCard.add(gameTitle);
        northCard.add(comboBoxPane);
//        northCard.add(gameScore);
        northCard.add(bs);
        southCard.add(close);        
        centerCard.add(review);
        centerCard.add(jpAcc);
        eastCard.add(padding1);
        westCard.add(padding2);

        // adding cards to pack of cards
        myCardsNorth.add(northCard, HOMEPANEL);
        myCardsSouth.add(southCard, HOMEPANEL);
        myCardsCenter.add(centerCard, HOMEPANEL);
        myCardsEast.add(eastCard, HOMEPANEL);
        myCardsWest.add(westCard, HOMEPANEL);
        
        class ComboBoxActionListener implements ActionListener {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String gameTitle = (String) cb.getSelectedItem();
				final int burritos = (int) Math.round(Query.getGameAvgRating(Query.getGameByName(gameTitle)));
		        bs.setScore(burritos);
		        revalidate();
		        repaint();
        	}
        }
        cb.addActionListener(new ComboBoxActionListener());
        
        /**
         *  This class logs the user out when the button is pressed.
         */
        class CloseButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("Logout!");
            	pageManagement(LOGINPANEL);
            }
        }
        close.addActionListener(new CloseButtonActionListener());
    }
    
    /**
     * This method builds the new user and adds it to the database.
     */
    private void RegistrationScreen() {
    	
    	//Card creation
    	JPanel northCard = new JPanel();
    	JPanel southCard = new JPanel();
    	JPanel centerCard = new JPanel();
    	JPanel eastCard = new JPanel();
    	centerCard.setLayout(new BoxLayout(centerCard, BoxLayout.Y_AXIS));
    	
    	// making the buttons / fields
    	final JLabel registration = new JLabel("Registration");
        final JLabel name = new JLabel("Full Name");
        final JLabel email = new JLabel("Email");
        final JLabel dun = new JLabel("Desired UserName");
        final JLabel dpw = new JLabel("Desired Password");
        final JButton save = new JButton("Submit");
        final JButton cancel = new JButton("Cancel");
        final JTextField userName = new JTextField();
        userName.setPreferredSize(TEXT_BOX_SIZE);
        final JPasswordField password = new JPasswordField();
        password.setPreferredSize(TEXT_BOX_SIZE);
        final JTextField fullName = new JTextField();
        fullName.setPreferredSize(TEXT_BOX_SIZE);
        final JTextField userEmail = new JTextField();
        userEmail.setPreferredSize(TEXT_BOX_SIZE);
        
        // padding to make login screen look correct

        final JLabel padding2 = new JLabel(" ");
        final JLabel padding3 = new JLabel(" ");
        final JLabel padding4 = new JLabel(" ");
        final JLabel padding5 = new JLabel(" ");
        final JLabel padding6 = new JLabel(" ");
        final JLabel padding7 = new JLabel(" ");
        final JLabel padding8 = new JLabel(" ");
        final JLabel padding9 = new JLabel(" ");
        final JLabel padding10 = new JLabel(" ");
        final JLabel padding11 = new JLabel(" ");
        final JLabel padding12 = new JLabel(" ");
        final JLabel padding13 = new JLabel(" ");
        final JLabel padding14 = new JLabel(" ");
        final JLabel padding15 = new JLabel(" ");

        
        // adding components to cards
        
        
        northCard.add(registration);
        

        centerCard.add(padding2);
        centerCard.add(padding3);
        centerCard.add(padding4);
        centerCard.add(padding5);
        centerCard.add(padding6);
        centerCard.add(padding7);
        centerCard.add(padding8);
        
        centerCard.add(name);
        centerCard.add(fullName);
        centerCard.add(email);
        centerCard.add(userEmail);
        centerCard.add(dun);
        centerCard.add(userName);
        centerCard.add(dpw);
        centerCard.add(password);
        
        centerCard.add(padding9);
        centerCard.add(padding10);
        centerCard.add(padding11);
        centerCard.add(padding12);
        centerCard.add(padding13);
        centerCard.add(padding14);
        centerCard.add(padding15);

        
        southCard.add(save);
        southCard.add(cancel);
        
        // adding cards to pack of cards
        myCardsNorth.add(northCard, REGISTRATIONPANEL);
        myCardsSouth.add(southCard, REGISTRATIONPANEL);
        myCardsCenter.add(centerCard, REGISTRATIONPANEL);
        myCardsEast.add(eastCard, REGISTRATIONPANEL);
        
        
        
        /**
         *  This class calls showSaveDialog() when the Save button is pressed.
         */
        class SaveButtonActionListener implements ActionListener {
            
            /**
             * This method submits the data to the database
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("Submit!");
            	StringBuilder sb = new StringBuilder();
            	for (char c : password.getPassword()) {
            		sb.append(c);
            	}
            	Query.addUser(new User(userName.getText(), UserType.User, sb.toString()));
            	pageManagement(LOGINPANEL);
            }
        }
        save.addActionListener(new SaveButtonActionListener());
        cancel.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		pageManagement(LOGINPANEL);
        	}
        });
    }
    
    /**
     * This method manages what page is visible at what time
     */
    private void pageManagement(String theName) {
    	myCardsSouth.invalidate();
    	myCardsSouth.repaint();
    	myCardsNorth.invalidate();
    	myCardsNorth.repaint();
    	myCardsCenter.invalidate();
    	myCardsCenter.repaint();
    	myCardsWest.invalidate();
    	myCardsWest.repaint();
    	myCardsEast.invalidate();
    	myCardsEast.repaint();
        CardLayout cl1 = (CardLayout)(myCardsSouth.getLayout());
        cl1.show(myCardsSouth, theName);
        CardLayout cl2 = (CardLayout)(myCardsCenter.getLayout());
        cl2.show(myCardsCenter, theName);
        CardLayout cl3 = (CardLayout)(myCardsNorth.getLayout());
        cl3.show(myCardsNorth, theName);
        CardLayout cl4 = (CardLayout)(myCardsEast.getLayout());
        cl4.show(myCardsEast, theName);
        CardLayout cl5 = (CardLayout)(myCardsWest.getLayout());
        cl5.show(myCardsWest, theName);
    }
}
