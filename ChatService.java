package chatapp.services;

import chatapp.model.Message;
import chatapp.model.User;
import java.util.ArrayList;
import java.util.List;
public class ChatService {
    private List<Message> chatHistory = new ArrayList<>();
    private static final int MAX_MESSAGE_LENGTH = 200;
    public boolean sendMessage(User sender, User receiver, String content, Integer replyToIndex) {
        if (content == null || content.trim().isEmpty()) {
            System.out.println("***************");
            System.out.println("Error: Message cannot be empty.");
            System.out.println("***************");
            return false;
        }
        if (content.length() > MAX_MESSAGE_LENGTH) {
            System.out.println("***************");
            System.out.println("Error: Message exceeds " + MAX_MESSAGE_LENGTH + " characters.");
            System.out.println("***************");
            return false;
        }
        if (replyToIndex != null && (replyToIndex < 0 || replyToIndex >= chatHistory.size())) {
            System.out.println("***************");
            System.out.println("Error: Invalid reply-to message index.");
            System.out.println("***************");
            return false;
        }
        if (receiver.isBlocked(sender)) {
            System.out.println("***************");
            System.out.println("Error: You are blocked by " + receiver.getUsername() + ".");
            System.out.println("***************");
            return false;
        }

        System.out.println("***************");
        System.out.println(sender.getUsername() + " is typing...");
        System.out.println("***************");
        try {
            Thread.sleep(1000); // Simulate typing delay
        } catch (InterruptedException e) {
            // Ignore interruption
        }
        Message msg = new Message(sender, receiver, content, replyToIndex);
        chatHistory.add(msg);
        System.out.println("***************");
        System.out.println("Message sent successfully!");
        System.out.println("***************");
        return true;
    }
    public boolean sendGroupMessage(User sender, List<User> allUsers, String content, Integer replyToIndex) {
        if (content == null || content.trim().isEmpty()) {
            System.out.println("***************");
            System.out.println("Error: Message cannot be empty.");
            System.out.println("***************");
            return false;
        }
        if (content.length() > MAX_MESSAGE_LENGTH) {
            System.out.println("***************");
            System.out.println("Error: Message exceeds " + MAX_MESSAGE_LENGTH + " characters.");
            System.out.println("***************");
            return false;
        }
        if (replyToIndex != null && (replyToIndex < 0 || replyToIndex >= chatHistory.size())) {
            System.out.println("***************");
            System.out.println("Error: Invalid reply-to message index.");
            System.out.println("***************");
            return false;
        }

        System.out.println("***************");
        System.out.println(sender.getUsername() + " is typing...");
        System.out.println("***************");
        try {
            Thread.sleep(1000); // Simulate typing delay
        } catch (InterruptedException e) {
            // Ignore interruption
        }

        boolean success = false;
        for (User receiver : allUsers) {
            if (receiver.getUserId() != sender.getUserId() && !receiver.isBlocked(sender)) {
                Message msg = new Message(sender, receiver, content, replyToIndex);
                chatHistory.add(msg);
                success = true;
            }
        }
        if (success) {
            System.out.println("***************");
            System.out.println("Group message sent successfully!");
            System.out.println("***************");
            return true;
        } else {
            System.out.println("***************");
            System.out.println("Error: No valid recipients (all blocked or only sender).");
            System.out.println("***************");
            return false;
        }
    }
    public boolean forwardMessage(int messageIndex, User sender, User receiver, List<User> allUsers, boolean toGroup) {
        if (messageIndex < 0 || messageIndex >= chatHistory.size()) {
            System.out.println("***************");
            System.out.println("Error: Invalid message index.");
            System.out.println("***************");
            return false;
        }
        Message original = chatHistory.get(messageIndex);
        String forwardedContent = "FWD: " + original.getContent();
        if (toGroup) {
            return sendGroupMessage(sender, allUsers, forwardedContent, null);
        } else {
            return sendMessage(sender, receiver, forwardedContent, null);
        }
    }
    public boolean addReaction(int messageIndex) {
        if (messageIndex < 0 || messageIndex >= chatHistory.size()) {
            System.out.println("***************");
            System.out.println("Error: Invalid message index.");
            System.out.println("***************");
            return false;
        }
        Message msg = chatHistory.get(messageIndex);
        if (msg.getReaction() != null) {
            System.out.println("***************");
            System.out.println("Error: Message already has a reaction.");
            System.out.println("***************");
            return false;
        }
        msg.setReaction("+1");
        System.out.println("***************");
        System.out.println("Reaction added successfully!");
        System.out.println("***************");
        return true;
    }
    public void displayChatHistory() {
        System.out.println("***************");
        if (chatHistory.isEmpty()) {
            System.out.println("No messages in chat history.");
        } else {
            System.out.println("Chat History:");
            for (int i = 0; i < chatHistory.size(); i++) {
                System.out.print("[" + i + "] ");
                chatHistory.get(i).displayMessage();
            }
        }
        System.out.println("***************");
    }
    public void displayChatHistoryByUser(User user) {
        System.out.println("***************");
        if (chatHistory.isEmpty()) {
            System.out.println("No messages in chat history.");
            System.out.println("***************");
            return;
        }

        boolean found = false;
        System.out.println("Messages for " + user.getUsername() + ":");
        for (int i = 0; i < chatHistory.size(); i++) {
            Message msg = chatHistory.get(i);
            if (msg.getSender().getUserId() == user.getUserId() || msg.getReceiver().getUserId() == user.getUserId()) {
                System.out.print("[" + i + "] ");
                msg.displayMessage();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for " + user.getUsername() + ".");
        }
        System.out.println("***************");
    }
    public void searchMessages(String keyword) {
        System.out.println("***************");
        if (chatHistory.isEmpty()) {
            System.out.println("No messages in chat history.");
            System.out.println("***************");
            return;
        }
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Error: Search keyword cannot be empty.");
            System.out.println("***************");
            return;
        }

        boolean found = false;
        System.out.println("Messages containing '" + keyword + "':");
        for (int i = 0; i < chatHistory.size(); i++) {
            Message msg = chatHistory.get(i);
            if (msg.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.print("[" + i + "] ");
                msg.displayMessage();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found containing '" + keyword + "'.");
        }
        System.out.println("***************");
    }
    public boolean deleteMessage(int index) {
        if (index >= 0 && index < chatHistory.size()) {
            chatHistory.remove(index);
            System.out.println("***************");
            System.out.println("Message deleted successfully!");
            System.out.println("***************");
            return true;
        } else {
            System.out.println("***************");
            System.out.println("Invalid message index.");
            System.out.println("***************");
            return false;
        }
    }
    public boolean editMessage(int index, String newContent) {
        if (index >= 0 && index < chatHistory.size()) {
            if (newContent == null || newContent.trim().isEmpty()) {
                System.out.println("***************");
                System.out.println("Error: New message cannot be empty.");
                System.out.println("***************");
                return false;
            }
            if (newContent.length() > MAX_MESSAGE_LENGTH) {
                System.out.println("***************");
                System.out.println("Error: New message exceeds " + MAX_MESSAGE_LENGTH + " characters.");
                System.out.println("***************");
                return false;
            }

            chatHistory.get(index).updateContent(newContent);
            System.out.println("***************");
            System.out.println("Message edited successfully!");
            System.out.println("***************");
            return true;
        } else {
            System.out.println("***************");
            System.out.println("Invalid message index.");
            System.out.println("***************");
            return false;
        }
    }
    public boolean markMessageAsRead(int index) {
        if (index >= 0 && index < chatHistory.size()) {
            chatHistory.get(index).markAsRead();
            System.out.println("***************");
            System.out.println("Message marked as read!");
            System.out.println("***************");
            return true;
        } else {
            System.out.println("***************");
            System.out.println("Invalid message index.");
            System.out.println("***************");
            return false;
        }
    }
    public boolean pinMessage(int index) {
        if (index >= 0 && index < chatHistory.size()) {
            chatHistory.get(index).pinMessage();
            System.out.println("***************");
            System.out.println("Message pinned successfully!");
            System.out.println("***************");
            return true;
        } else {
            System.out.println("***************");
            System.out.println("Invalid message index.");
            System.out.println("***************");
            return false;
        }
    }
    public boolean unpinMessage(int index) {
        if (index >= 0 && index < chatHistory.size()) {
            chatHistory.get(index).unpinMessage();
            System.out.println("***************");
            System.out.println("Message unpinned successfully!");
            System.out.println("***************");
            return true;
        } else {
            System.out.println("***************");
            System.out.println("Invalid message index.");
            System.out.println("***************");
            return false;
        }
    }
    public boolean isChatHistoryEmpty() {
        return chatHistory.isEmpty();
    }
}