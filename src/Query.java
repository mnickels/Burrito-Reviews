import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Queries to the database.
 * 
 * @author Brian Jorgenson
 */
public class Query {
    
    private static String db = "445_database_system_design"; //Change to yours
    private static String userName = "Brian";
    private static String password = "localhost";
    private static String serverName = "localhost"; //cssgate.insttech.washington.edu
    private static Connection conn;
    
    /**
     * Creates a sql connection to MySQL using the properties for
     * userid, password and server information.
     * @throws SQLException
     */
    public static void createConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
                + serverName + "/", connectionProps);
    }
    
    /**
     * Check if user exists in the database.
     * 
     * @param name user name
     * @param password user password
     * @return true if user exists otherwise false
     * @throws SQLException
     */
    public static boolean isValidUser(String name, String password) throws SQLException {
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
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return valid;
    }
    
    /**
     * Adds a user to the database.
     * 
     * @param user user to add
     * @return true if successful otherwise false
     * @throws SQLException
     */
    public static boolean addUser(User user) throws SQLException {
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
     * @throws SQLException
     */
    public static User getUserById(int id) throws SQLException {
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
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return user;
    }
    
    /**
     * Gets all users from the user table.
     * 
     * @return list of users
     * @throws SQLException
     */
    public static List<User> getUsers() throws SQLException {
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
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return users;
    }
    
    /**
     * Edit a users name.
     * 
     * @param userId user to edit
     * @param name new name
     * @return true on success otherwise false
     * @throws SQLException
     */
    public static boolean editUserName(int userId, String name) throws SQLException {
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
     * @throws SQLException
     */
    public static boolean editUserType(int userId, UserType type) throws SQLException {
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
     * @throws SQLException
     */
    public static boolean editUserPassword(int userId, String password) throws SQLException {
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
     * Adds a new rating to a game.
     * If user has already rated the game their rating is updated.
     * 
     * @param user the user that is rating
     * @param game the game that is being rated
     * @param rating the rating
     * @throws SQLException
     */
    public static void rateGame(User user, Game game, int rating) throws SQLException {
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
     * Gets the average rating for the game.
     * 
     * @param game game to get rating for
     * @return average of all user ratings
     * @throws SQLException
     */
    public static double getGameAvgRating(Game game) throws SQLException {
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
            
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return average;
    }
    
    /**
     * Gets all games from game table.
     * 
     * @return list of games
     * @throws SQLException
     */
    public static List<Game> getGames() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "SELECT * FROM "+db+".Game";
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
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return games;
    }
    
    /**
     * Gets all reviews for the game by game.
     * 
     * @param game the game
     * @return List<String> of reviews
     * @throws SQLException
     */
    public static List<String> getReviewsByGame(Game game)  throws SQLException {
        return getReviewsByGameTitle(game.getTitle());
    }
    
    /**
     * Gets all reviews for the game by title.
     * 
     * @param title game title
     * @return List<String> of reviews
     * @throws SQLException
     */
    public static List<String> getReviewsByGameTitle(String title)  throws SQLException {
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
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
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
     * @throws SQLException
     */
    public static boolean editReview(Game game, User user, String reviewText) throws SQLException {
        if (conn == null) {
            createConnection();
        }
        String sql = "UPDATE "+db+".GameReview "
                + "SET  reviewText = \""+reviewText+"\" "
                + "WHERE gameId = "+game.getGameId()+" "
                + "AND userId = "+user.getUserId()+";";
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
     * Edit a games title.
     * 
     * @param gameId game to edit
     * @param title new title
     * @return true on success otherwise false
     * @throws SQLException
     */
    public static boolean editGameTitle(int gameId, String title) throws SQLException {
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
     * @throws SQLException
     */
    public static boolean editGameDeveloper(int gameId, String developer) throws SQLException {
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
     * @throws SQLException
     */
    public static boolean editGameYear(int gameId, int year) throws SQLException {
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
     * @throws SQLException
     */
    public static boolean editGameESRB(int gameId, String esrb) throws SQLException {
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