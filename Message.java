package chatapp.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Message {
    private User sender;
    private User receiver;
    private String content;
    private LocalDateTime timestamp;
    private Integer replyTo;
    private boolean isRead;
    private boolean isPinned;
    private String reaction;
    public Message(User sender, User receiver, String content, Integer replyTo) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.replyTo = replyTo;
        this.isRead = false;
        this.isPinned = false;
        this.reaction = null;
    }
    public void displayMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a");
        String formattedTime = timestamp.format(formatter);
        String replyInfo = (replyTo != null) ? " (Replying to message " + replyTo + ")" : "";
        String readStatus = isRead ? " [Read]" : " [Unread]";
        String pinStatus = isPinned ? " [Pinned]" : "";
        String reactionStatus = (reaction != null) ? " [" + reaction + "]" : "";
        System.out.println(formattedTime + " [" + sender.getUsername() + " → " + receiver.getUsername() + "]: " + content + replyInfo + readStatus + pinStatus + reactionStatus);
    }
    public void updateContent(String newContent) {
        this.content = newContent;
        this.timestamp = LocalDateTime.now();
    }
    public void markAsRead() {
        this.isRead = true;
    }
    public void pinMessage() {
        this.isPinned = true;
    }
    public void unpinMessage() {
        this.isPinned = false;
    }
    public void setReaction(String reaction) {
        this.reaction = reaction;
    }
    public String getReaction() {
        return reaction;
    }
    public User getSender() {
        return sender;
    }
    public User getReceiver() {
        return receiver;
    }
    public String getContent() {
        return content;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public Integer getReplyTo() {
        return replyTo;
    }
    public boolean isRead() {
        return isRead;
    }
    public boolean isPinned() {
        return isPinned;
    }
}