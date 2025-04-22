package chatapp.model;
import java.util.ArrayList;
import java.util.List;
public class User {
    private String username;
    private int userId;
    private boolean isOnline;
    private String statusMessage;
    private List<User> blockedUsers;
    public User(String username, int userId) {
        this.username = username;
        this.userId = userId;
        this.isOnline = true;
        this.statusMessage = "Available";
        this.blockedUsers = new ArrayList<>();
    }
    public String getUsername() {
        return username;
    }
    public int getUserId() {
        return userId;
    }
    public boolean isOnline() {
        return isOnline;
    }
    public void setOnline(boolean online) {
        this.isOnline = online;
    }
    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    public boolean isBlocked(User user) {
        return blockedUsers.contains(user);
    }
    public void blockUser(User user) {
        blockedUsers.add(user);
    }
    public void unblockUser(User user) {
        blockedUsers.remove(user);
    }
}