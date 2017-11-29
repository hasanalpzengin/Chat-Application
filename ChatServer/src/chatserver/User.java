package chatserver;

import java.util.ArrayList;

/**
 *
 * @author hasalp
 */
public class User {
    private static String username;
    private static ArrayList<String> users = new ArrayList<>();

    public User(String username) {
        User.username = username;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static ArrayList<String> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<String> users) {
        User.users = users;
    }
    
    public static void addUser(String user){
        users.add(user);
    }
    
    public static void removeUser(String user){
        users.remove(user);
    }
}
