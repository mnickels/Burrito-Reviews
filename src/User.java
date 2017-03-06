
/**
 * Represents a user with a userId, name, and userType.
 * 
 * @author Brian
 */
public class User {

    private int userId;
    private String name;
    private UserType type;
    private String password;

    public User(final int userId, final String name, final UserType type) {
        setUserId(userId);
        setName(name);
        setType(type);
    }
    
    @Override
    public String toString() {
        return "User [name=" + name + ", type=" + type + "]";
    }

    private void setType(UserType type) {
        if (type == null){
            throw new IllegalArgumentException("Please enter a valid userType");
        }
        this.type = type;
    }

    public UserType getType() {
        return type;
    }

    private void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Please enter a valid name");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setUserId(int userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("Please enter a valid userId");
        }
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("Please enter a valid password");
        }
        this.password = password;
    }
}