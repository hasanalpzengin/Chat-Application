package chatclient;

import java.util.ArrayList;

/**
 *
 * @author hasalp
 */
public class User {
    private static String username;

    public User(String username) {
        User.username = username;
    }

    public static String getUsername() {
        return User.username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }
}
