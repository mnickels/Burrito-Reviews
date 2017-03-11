package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Queries to the database.
 * 
 * @author Brian Jorgenson
 */
public class Query {
    
    private static String db; //Change to yours
    private static String userName;
    private static String password;
    private static String serverName; //cssgate.insttech.washington.edu
    private static Connection conn;
    
    static {
    	Scanner s = null;
    	try {
			s = new Scanner(new File("dbinfo.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	if (s != null) {
    		db = s.nextLine();
    		userName = s.nextLine();
    		password = s.nextLine();
    		serverName = s.nextLine();
    		s.close();
    	}
    }
    
    /**
     * Creates a sql connection to MySQL using the properties for
     * userid, password and server information.
     * @throws SQLException
     */
    public static void createConnection() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        try {
            conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
                    + serverName + "/", connectionProps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Check if user exists in the database.
     * 
     * @param name user name
     * @param password user password
     * @return true if user exists otherwise false
     */
    public static boolean isValidUser(String name, String password) {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "SELECT EXISTS("
                     + "SELECT * FROM "+db+".`User` "
                     + "WHERE `name` = \""+name+"\" "
                     + "AND `password` = \""+password+"\") "
                     + "AS \"valid\";";
        boolean valid = false;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            valid = rs.getBoolean("valid");
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return valid;
    }
    
    /**
     * Adds a user to the database.
     * 
     * @param user user to add
     * @return true if successful otherwise false
     */
    public static boolean addUser(User user) {
        if (conn == null) {
            createConnection();
        }
        String query = "INSERT INTO "+db+".`User` "
                     + "(`name`, `password`, userType) "
                     + "VALUES (?, ?, ?);";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getType().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
    /**
     * Gets a user by their id.
     * 
     * @param id user id
     * @return the user
     */
    public static User getUserById(int id) {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "SELECT `name`, userType "
                     + "FROM "+db+".User "
                     + "WHERE userId = " + id;
        User user = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            String name = rs.getString("name");
            UserType type = UserType.valueOf(rs.getString("userType"));
            user = new User(id, name, type);
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    /**
     * Gets a user based on correct name and password.
     * 
     * @param name user name
     * @param password user password
     * @return the user
     */
    public static User getUserByNameAndPassword(String name, String password) {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "SELECT userId, userType "
                     + "FROM "+db+".User "
                     + "WHERE `name` = \""+name+"\" "
                     + "AND `password` = \""+password+"\";";
        User user = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int id = rs.getInt("userId");
            UserType type = UserType.valueOf(rs.getString("userType"));
            user = new User(id, name, type);
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    /**
     * Gets all users from the user table.
     * 
     * @return list of users
     */
    public static List<User> getUsers() {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "SELECT userId, `name`, userType "
                     + "FROM "+db+".User"; 
        List<User> users = new ArrayList<User>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("userId");
                String name = rs.getString("name");
                UserType type = UserType.valueOf(rs.getString("userType"));
                User user = new User(id, name, type);
                users.add(user);
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * Edit a users name.
     * 
     * @param userId user to edit
     * @param name new name
     * @return true on success otherwise false
     */
    public static boolean editUserName(int userId, String name) {
        if (conn == null) {
            createConnection();
        }
        String sql = "UPDATE "+db+".`User` "
                + "SET  `name` = \""+name+"\" "
                + "WHERE userId = "+userId+";";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
    /**
     * Edit a users type.
     * 
     * @param userId user to edit
     * @param type new type
     * @return true on success otherwise false
     */
    public static boolean editUserType(int userId, UserType type) {
        if (conn == null) {
            createConnection();
        }
        String sql = "UPDATE "+db+".`User` "
                + "SET  userType = \""+type.toString()+"\" "
                + "WHERE userId = "+userId+";";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
    /**
     * Edit a users password.
     * 
     * @param userId user to edit
     * @param password new password
     * @return true on success otherwise false
     */
    public static boolean editUserPassword(int userId, String password) {
        if (conn == null) {
            createConnection();
        }
        String sql = "UPDATE "+db+".`User` "
                + "SET  `password` = \""+password+"\" "
                + "WHERE userId = "+userId+";";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
    /**
     * Add a game to the database.
     * 
     * @param game game to add
     * @return true on successful add otherwise false
     */
    public static boolean addGame(Game game) {
        if (conn == null) {
            createConnection();
        }
        String query = "INSERT INTO "+db+".Game "
                     + "(title, developer, `year`, esrb) "
                     + "VALUES (?, ?, ?, ?);";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, game.getTitle());
            pstmt.setString(2, game.getDeveloper());
            pstmt.setInt(3, game.getYear());
            pstmt.setString(4, game.getEsrb());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
    /**
     * Adds a new rating to a game.
     * If user has already rated the game their rating is updated.
     * 
     * @param user the user that is rating
     * @param game the game that is being rated
     * @param rating the rating
     */
    public static void rateGame(User user, Game game, int rating) {
        if (conn == null) {
            createConnection();
        }
        String sql = "INSERT INTO "+db+".UserRating "
                   + "VALUES (?, ?, ?)"
                   + "ON DUPLICATE KEY "
                   + "UPDATE rating = " + rating + ";";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserId());
            pstmt.setInt(2, game.getGameId());
            pstmt.setInt(3, rating);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    
    /**
     * Gets the rating for a game by a user.
     * 
     * @param user the user
     * @param game the game
     * @return the rating the user gave the game
     */
    public static int getGameRatingByUser(User user, Game game) {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "SELECT rating "
                     + "FROM "+db+".UserRating "
                     + "WHERE fk_gameId = " + game.getGameId() + " "
                     + "AND fk_userId = " +user.getUserId()+ ";";
        int rating = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            rating = rs.getInt("rating");
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rating;
    }
    
    /**
     * Gets the average rating for the game.
     * 
     * @param game game to get rating for
     * @return average of all user ratings
     */
    public static double getGameAvgRating(Game game) {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "SELECT AVG(rating) AS average "
                     + "FROM "+db+".UserRating "
                     + "WHERE fk_gameId = " + game.getGameId() + ";";
        double average = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            average = rs.getDouble("average");
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return average;
    }
    
    /**
     * Gets all games from game table.
     * 
     * @return list of games
     */
    public static List<Game> getGames() {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "SELECT * FROM "+db+".Game "
                     + "ORDER BY title ASC";
        List<Game> games = new ArrayList<Game>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("gameId");
                String title = rs.getString("title");
                String developer = rs.getString("developer");
                int year = rs.getInt("year");
                String esrb = rs.getString("esrb");
                Game game = new Game(id, title, developer, year, esrb);
                games.add(game);
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }
    
    /**
     * @author Mike Nickels
     */
    public static Game getGameByName(String name) {
    	for (Game g : getGames()) {
    		if (g.getTitle().equalsIgnoreCase(name)) {
    			return g;
    		}
    	}
    	return null;
    }
    
    /**
     * Gets all reviews for the game by game.
     * 
     * @param game the game
     * @return List<String> of reviews
     */
    public static List<String> getReviewsByGame(Game game) {
        return getReviewsByGameTitle(game.getTitle());
    }
    
    /**
     * Gets all reviews for the game by title.
     * 
     * @param title game title
     * @return List<String> of reviews
     */
    public static List<String> getReviewsByGameTitle(String title) {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "SELECT reviewText "
                     + "FROM "+db+".GameReview , "+db+".Game "
                     + "WHERE title = \"" + title + "\"";
        List<String> reviews = new ArrayList<String>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String review = rs.getString("reviewText");
                reviews.add(review);
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
    
    /**
     * Edit a review.
     * 
     * @param game game linked to the review
     * @param user user linked to the review
     * @param reviewText new review text
     * @return true if successful otherwise false
     */
    public static boolean editReview(Game game, User user, String reviewText) {
        if (conn == null) {
            createConnection();
        }
        String sql = "UPDATE "+db+".GameReview "
                + "SET  reviewText = \""+reviewText+"\" "
                + "WHERE fk_gameId = "+game.getGameId()+" "
                + "AND fk_userId = "+user.getUserId()+";";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
    public static String getReview(Game game, User user) {
    	if (conn == null) {
            createConnection();
        }
        String sql = "SELECT reviwText FROM "+db+".GameReview "
                + "WHERE fk_gameId = "+game.getGameId()+" "
                + "AND fk_reviewerId = "+user.getUserId()+";";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return pstmt.toString();
    }
    
    /**
     * Edit a games title.
     * 
     * @param gameId game to edit
     * @param title new title
     * @return true on success otherwise false
     */
    public static boolean editGameTitle(int gameId, String title) {
        if (conn == null) {
            createConnection();
        }
        String sql = "UPDATE "+db+".Game "
                + "SET  title = \""+title+"\" "
                + "WHERE gameId = "+gameId+";";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
    /**
     * Edit a games developer.
     * 
     * @param gameId game to edit
     * @param developer new developer
     * @return true on success otherwise false
     */
    public static boolean editGameDeveloper(int gameId, String developer) {
        if (conn == null) {
            createConnection();
        }
        String sql = "UPDATE "+db+".Game "
                + "SET  developer = \""+developer+"\" "
                + "WHERE gameId = "+gameId+";";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
    /**
     * Edit a games year.
     * 
     * @param gameId game to edit
     * @param year new year
     * @return true on success otherwise false
     */
    public static boolean editGameYear(int gameId, int year) {
        if (conn == null) {
            createConnection();
        }
        String sql = "UPDATE "+db+".Game "
                + "SET  year = \""+year+"\" "
                + "WHERE gameId = "+gameId+";";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
    /**
     * Edit a games esrb.
     * 
     * @param gameId game to edit
     * @param esrb new esrb
     * @return true on success otherwise false
     */
    public static boolean editGameESRB(int gameId, String esrb) {
        if (conn == null) {
            createConnection();
        }
        String sql = "UPDATE "+db+".Game "
                + "SET  esrb = \""+esrb+"\" "
                + "WHERE gameId = "+gameId+";";
        PreparedStatement pstmt = null;
        boolean successful = true;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
    
}