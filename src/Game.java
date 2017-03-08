
/**
 * Represents a video game with an id, title, developer, year and esrb.
 * 
 * @author Brian Jorgenson
 */
public class Game {
    
    private int gameId;
    private String title;
    private String developer;
    private int year;
    private String esrb;
    
    /**
     * Initialize the game parameters.
     * 
     * @param id game id
     * @param title game title
     * @param devoloper developer name
     * @param year release year
     * @param esrb esrb age rating
     * @throws IllegalArgumentException if title, developer or esrb are null or empty,
     * id < 1, year < 1958. 
     */
    public Game(final int id, final String title, final String developer, final int year,
            final String esrb) {
        setGameId(id);
        setTitle(title);
        setDeveloper(developer);
        setYear(year);
        setEsrb(esrb);
    }
    
    /**
     * Create new game.
     * 
     * @param title game title
     * @param developer game developer
     * @param year year released
     * @param esrb esrb age rating
     * @throws IllegalArgumentException if title, developer or esrb are null or empty,
     * id < 1, year < 1958. 
     */
    public Game(final String title, final String developer, final int year,
            final String esrb) {
        setTitle(title);
        setDeveloper(developer);
        setYear(year);
        setEsrb(esrb);
    }
    
    @Override
    public String toString() {
        return "Game [title=" + title + ", developer=" 
                + developer + ", year="
                + year + ", esrb=" + esrb + "]";
    }

    private void setEsrb(String esrb) {
        if (esrb == null || esrb.length() == 0) {
            throw new IllegalArgumentException("Please enter a valid esrb");
        }
        this.esrb = esrb;
    }

    public String getEsrb() {
        return esrb;
    }

    private void setYear(int year) {
        if (year < 1958) {
            throw new IllegalArgumentException("Please enter a valid year");
        }
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    private void setDeveloper(String developer) {
        if (developer == null || developer.length() == 0) {
            throw new IllegalArgumentException("Please enter a valid developer");
        }
        this.developer = developer;
    }

    public String getDeveloper() {
        return developer;
    }

    private void setTitle(String title) {
        if (title == null || title.length() == 0) {
            throw new IllegalArgumentException("Please enter a valid title");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    private void setGameId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("Please enter a valid gameId");
        }
        this.gameId = id;  
    }

    public int getGameId() {
        return gameId;
    }
}