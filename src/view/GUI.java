/*
 * TCSS 445 B - Group Project
 */
package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Game;
import model.Query;
import model.User;



/**
 * This is the GUI class that will create the front end of the program.
 * 
 * @author Thomas Schmidt
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
    	
    	// temp buttons
        final JButton admin = new JButton("Admin");
        final JButton reviewer = new JButton("Reviewer");
    	
    	// button field and label creation
        final JButton open = new JButton("Login");
        final JButton save = new JButton("Register");
        final JLabel un = new JLabel("UserName");
        final JLabel grt = new JLabel("GAME REVIEW TITLE");
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
        southCard.add(open);
        southCard.add(save); 
        southCard.add(admin);
        southCard.add(reviewer);
        centerCard.add(padding1);
        centerCard.add(padding2);
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

        
        
        /**
         *  This class switches to the Homepage when the Login button is pressed.
         */
        class LoginButtonActionListener implements ActionListener {
            
            /**
             * This method opens myJFC OpenDialog box.
             * @param theButtonClick when the button action event takes place
             * 
             * @author Mike Nickels
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
                System.out.println("Login");
                
                StringBuilder sb = new StringBuilder();
                for (char c : password.getPassword()) {
                	sb.append(c);
                }
                currentUser = Query.getUserByNameAndPassword(userName.getText(), sb.toString());
                if (currentUser != null) {
                	switch (currentUser.getType()) {
                	case USER:
                		UserScreen();
                		break;
                	case REVIEWER:
                		reviewerScreen();
                		break;
                	case ADMIN:
                		adminScreen();
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
        
        /**
         *  This class calls showSaveDialog() when the Save button is pressed.
         */
        class ReviewerButtonActionListener implements ActionListener {
            
            /**
             * This method opens myJFC saveDialog box.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("Reviewer!");
            	pageManagement(REVIEWERPANEL);
            }
        }
        reviewer.addActionListener(new ReviewerButtonActionListener());
        
        /**
         *  This class calls showSaveDialog() when the Save button is pressed.
         */
        class AdminButtonActionListener implements ActionListener {
            
            /**
             * This method opens myJFC saveDialog box.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
                	pageManagement(ADMINPANEL);

            	
            }
        }
        admin.addActionListener(new AdminButtonActionListener());
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
        final JLabel gameScore = new JLabel("Game Score: ");
        final JLabel gameRating = new JLabel("****/*****"); // needs to pull data from database to display rating based off each game
        final JLabel review = new JLabel("New Review:");
        
        // padding
        final JLabel padding1 = new JLabel(" ");
        final JLabel padding2 = new JLabel(" ");
        
        // needs some code to pull from database and display all reviews
        
        // adding components to cards
        northCard.add(gameTitle);
        northCard.add(comboBoxPane);
        northCard.add(gameScore);
        northCard.add(gameRating);
        southCard.add(addGame);
        southCard.add(editGame);
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
        class NewReviewButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("NewGame!");
            	// needs to open up a review text box
            }
        }
        editGame.addActionListener(new NewReviewButtonActionListener());
        
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
        String comboBoxItems[] = {"Dark Souls","Dark Souls 2","Darkest Dungeon" }; // this needs to pull from the database
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        comboBoxPane.add(cb);
    	
        // constructing the button and text fields
        final JButton close = new JButton("Logout");
        final JButton newReview = new JButton("New Review");
        final JButton submitReview = new JButton("Submit Review");
        final JLabel gameTitle = new JLabel("Game Title");
        final JLabel gameScore = new JLabel("Game Score: ");
        final JLabel gameRating = new JLabel("****/*****"); // needs to pull data from database to display rating based off each game
        final JLabel review = new JLabel("New Review:");
        
        // padding
        final JLabel padding1 = new JLabel(" ");
        final JLabel padding2 = new JLabel(" ");
        
        // needs some code to pull from database and display all reviews
        
        // adding components to cards
        northCard.add(gameTitle);
        northCard.add(comboBoxPane);
        northCard.add(gameScore);
        northCard.add(gameRating);
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
        class NewReviewButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("NewReview!");
            	// needs to open up a review text box
            	pageManagement(REVIEWERPANEL);
            }
        }
        newReview.addActionListener(new NewReviewButtonActionListener());
        
        /**
         *  This class submits the review when the button is pressed.
         */
        class SubmitReviewButtonActionListener implements ActionListener {
            
            /**
             * This method logs the user out.
             * @param theButtonClick when the button action event takes place
             */
            public void actionPerformed(final ActionEvent theButtonClick) {
            	System.out.println("Submitted Review!");
            	// needs to send the review to the database

            }
        }
        submitReview.addActionListener(new SubmitReviewButtonActionListener());
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
        String comboBoxItems[] = {"Dark Souls","Dark Souls 2","Darkest Dungeon" }; // this needs to pull from the database
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        comboBoxPane.add(cb);
    	
        // constructing the button and text fields
        final JButton close = new JButton("Logout");
        final JLabel gameTitle = new JLabel("Game Title");
        final JLabel gameScore = new JLabel("Game Score: ");
        final JLabel gameRating = new JLabel("****/*****"); // needs to pull data from database to display rating based off each game
        final JLabel review = new JLabel("Reviews:");
        
        // padding
        final JLabel padding1 = new JLabel(" ");
        final JLabel padding2 = new JLabel(" ");
        
        // needs some code to pull from database and display all reviews
        
        // adding components to cards
        northCard.add(gameTitle);
        northCard.add(comboBoxPane);
        northCard.add(gameScore);
        northCard.add(gameRating);
        southCard.add(close);        
        centerCard.add(review);
        eastCard.add(padding1);
        westCard.add(padding2);

        // adding cards to pack of cards
        myCardsNorth.add(northCard, HOMEPANEL);
        myCardsSouth.add(southCard, HOMEPANEL);
        myCardsCenter.add(centerCard, HOMEPANEL);
        myCardsEast.add(eastCard, HOMEPANEL);
        myCardsWest.add(westCard, HOMEPANEL);
        
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
        final JTextField userName = new JTextField();
        userName.setPreferredSize(TEXT_BOX_SIZE);
        final JTextField password = new JTextField();
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
            	pageManagement(LOGINPANEL);
            	// needs to send the data to the database

            }
        }
        save.addActionListener(new SaveButtonActionListener());
    }
    
    /**
     * This method manages what page is visible at what time
     */
    private void pageManagement(String theName) {
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
