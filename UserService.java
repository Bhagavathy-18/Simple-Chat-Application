package chatapp.services;
import chatapp.model.User;
import java.util.ArrayList;
import java.util.List;
public class UserService {
    private List<User> users = new ArrayList<>();
    private int userIdCounter = 1;
    public User registerUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("***************");
            System.out.println("Error: Username cannot be empty.");
            System.out.println("***************");
            return null;
        }
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username.trim())) {
                System.out.println("***************");
                System.out.println("Error: Username '" + username + "' is already taken.");
                System.out.println("***************");
                return null;
            }
        }
        User user = new User(username, userIdCounter++);
        users.add(user);
        System.out.println("***************");
        System.out.println("User registered: " + username);
        System.out.println("***************");
        return user;
    }
    public void listUsers() {
        System.out.println("***************");
        if (users.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            System.out.println("Registered Users:");
            for (User user : users) {
                String status = user.isOnline() ? "Online" : "Offline";
                System.out.println("ID: " + user.getUserId() + ", Username: " + user.getUsername() + " (" + status + ", Status: " + user.getStatusMessage() + ")");
            }
        }
        System.out.println("***************");
    }
    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
    public boolean toggleUserStatus(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            user.setOnline(!user.isOnline());
            System.out.println("***************");
            System.out.println("User " + user.getUsername() + " is now " + (user.isOnline() ? "Online" : "Offline"));
            System.out.println("***************");
            return true;
        } else {
            System.out.println("***************");
            System.out.println("User not found.");
            System.out.println("***************");
            return false;
        }
    }
    public boolean blockUser(User user, User userToBlock) {
        if (user == null || userToBlock == null) {
            System.out.println("***************");
            System.out.println("Error: Invalid user.");
            System.out.println("***************");
            return false;
        }
        if (user.getUserId() == userToBlock.getUserId()) {
            System.out.println("***************");
            System.out.println("Error: Cannot block yourself.");
            System.out.println("***************");
            return false;
        }
        if (user.isBlocked(userToBlock)) {
            System.out.println("***************");
            System.out.println("Error: " + userToBlock.getUsername() + " is already blocked.");
            System.out.println("***************");
            return false;
        }
        user.blockUser(userToBlock);
        System.out.println("***************");
        System.out.println(userToBlock.getUsername() + " blocked successfully!");
        System.out.println("***************");
        return true;
    }
    public boolean unblockUser(User user, User userToUnblock) {
        if (user == null || userToUnblock == null) {
            System.out.println("***************");
            System.out.println("Error: Invalid user.");
            System.out.println("***************");
            return false;
        }
        if (!user.isBlocked(userToUnblock)) {
            System.out.println("***************");
            System.out.println("Error: " + userToUnblock.getUsername() + " is not blocked.");
            System.out.println("***************");
            return false;
        }
        user.unblockUser(userToUnblock);
        System.out.println("***************");
        System.out.println(userToUnblock.getUsername() + " unblocked successfully!");
        System.out.println("***************");
        return true;
    }
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}