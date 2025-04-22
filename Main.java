package chatapp;
import chatapp.model.User;
import chatapp.services.ChatService;
import chatapp.services.UserService;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        ChatService chatService = new ChatService();
        System.out.println("Welcome to Simple Chat App!");
        System.out.println("***************");
        System.out.println("Menu:");
        System.out.println("1. Register a new user");
        System.out.println("2. List all users");
        System.out.println("3. Send a private message");
        System.out.println("4. Send a group message");
        System.out.println("5. Reply to a message");
        System.out.println("6. Forward a message");
        System.out.println("7. Add reaction to a message");
        System.out.println("8. Search messages");
        System.out.println("9. Pin a message");
        System.out.println("10. Unpin a message");
        System.out.println("11. Block a user");
        System.out.println("12. Unblock a user");
        System.out.println("13. View full chat history");
        System.out.println("14. View chat history for a user");
        System.out.println("15. Delete a message");
        System.out.println("16. Edit a message");
        System.out.println("17. Mark message as read");
        System.out.println("18. Update profile status or Toggle user status (Online/Offline)");
        System.out.println("***************");
        while (true) {
            System.out.print("Enter option (1-18) or 'exit': ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("***************");
                System.out.println("Thank you for using Simple Chat App! Goodbye.");
                System.out.println("***************");
                scanner.close();
                return;
            }
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("***************");
                System.out.println("Invalid input. Please enter a number (1-18) or 'exit'.");
                System.out.println("***************");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    userService.registerUser(username);
                    break;
                case 2:
                    userService.listUsers();
                    break;
                case 3:
                    userService.listUsers();
                    System.out.print("Enter sender's user ID: ");
                    int senderId;
                    try {
                        senderId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User sender = userService.getUserById(senderId);
                    if (sender == null) {
                        System.out.println("***************");
                        System.out.println("Sender not found.");
                        System.out.println("***************");
                        continue;
                    }
                    System.out.print("Enter receiver's user ID: ");
                    int receiverId;
                    try {
                        receiverId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User receiver = userService.getUserById(receiverId);
                    if (receiver == null) {
                        System.out.println("***************");
                        System.out.println("Receiver not found.");
                        System.out.println("***************");
                        continue;
                    }
                    System.out.print("Enter message: ");
                    String message = scanner.nextLine();
                    chatService.sendMessage(sender, receiver, message, null);
                    break;
                case 4:
                    userService.listUsers();
                    System.out.print("Enter sender's user ID: ");
                    int groupSenderId;
                    try {
                        groupSenderId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User groupSender = userService.getUserById(groupSenderId);
                    if (groupSender == null) {
                        System.out.println("***************");
                        System.out.println("Sender not found.");
                        System.out.println("***************");
                        continue;
                    }
                    System.out.print("Enter group message: ");
                    String groupMessage = scanner.nextLine();
                    chatService.sendGroupMessage(groupSender, userService.getAllUsers(), groupMessage, null);
                    break;
                case 5:
                    chatService.displayChatHistory();
                    if (chatService.isChatHistoryEmpty()) {
                        continue;
                    }
                    System.out.print("Enter the index of the message to reply to: ");
                    int replyIndex;
                    try {
                        replyIndex = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid index. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    userService.listUsers();
                    System.out.print("Enter sender's user ID: ");
                    int replySenderId;
                    try {
                        replySenderId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User replySender = userService.getUserById(replySenderId);
                    if (replySender == null) {
                        System.out.println("***************");
                        System.out.println("Sender not found.");
                        System.out.println("***************");
                        continue;
                    }
                    System.out.print("Enter receiver's user ID: ");
                    int replyReceiverId;
                    try {
                        replyReceiverId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User replyReceiver = userService.getUserById(replyReceiverId);
                    if (replyReceiver == null) {
                        System.out.println("***************");
                        System.out.println("Receiver not found.");
                        System.out.println("***************");
                        continue;
                    }
                    System.out.print("Enter reply message: ");
                    String replyMessage = scanner.nextLine();
                    chatService.sendMessage(replySender, replyReceiver, replyMessage, replyIndex);
                    break;
                case 6:
                    chatService.displayChatHistory();
                    if (chatService.isChatHistoryEmpty()) {
                        continue;
                    }
                    System.out.print("Enter the index of the message to forward: ");
                    int forwardIndex;
                    try {
                        forwardIndex = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid index. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    userService.listUsers();
                    System.out.print("Enter sender's user ID: ");
                    int forwardSenderId;
                    try {
                        forwardSenderId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User forwardSender = userService.getUserById(forwardSenderId);
                    if (forwardSender == null) {
                        System.out.println("***************");
                        System.out.println("Sender not found.");
                        System.out.println("***************");
                        continue;
                    }
                    System.out.print("Forward to group? (y/n): ");
                    boolean toGroup = scanner.nextLine().trim().equalsIgnoreCase("y");
                    User forwardReceiver = null;
                    if (!toGroup) {
                        System.out.print("Enter receiver's user ID: ");
                        int forwardReceiverId;
                        try {
                            forwardReceiverId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("***************");
                            System.out.println("Invalid ID. Please enter a number.");
                            System.out.println("***************");
                            continue;
                        }
                        forwardReceiver = userService.getUserById(forwardReceiverId);
                        if (forwardReceiver == null) {
                            System.out.println("***************");
                            System.out.println("Receiver not found.");
                            System.out.println("***************");
                            continue;
                        }
                    }
                    chatService.forwardMessage(forwardIndex, forwardSender, forwardReceiver, userService.getAllUsers(), toGroup);
                    break;
                case 7:
                    chatService.displayChatHistory();
                    if (!chatService.isChatHistoryEmpty()) {
                        System.out.print("Enter the index of the message to add reaction to: ");
                        int reactionIndex;
                        try {
                            reactionIndex = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("***************");
                            System.out.println("Invalid index. Please enter a number.");
                            System.out.println("***************");
                            continue;
                        }
                        chatService.addReaction(reactionIndex);
                    }
                    break;
                case 8:
                    System.out.print("Enter keyword to search messages: ");
                    String keyword = scanner.nextLine();
                    chatService.searchMessages(keyword);
                    break;
                case 9:
                    chatService.displayChatHistory();
                    if (!chatService.isChatHistoryEmpty()) {
                        System.out.print("Enter the index of the message to pin: ");
                        int pinIndex;
                        try {
                            pinIndex = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("***************");
                            System.out.println("Invalid index. Please enter a number.");
                            System.out.println("***************");
                            continue;
                        }
                        chatService.pinMessage(pinIndex);
                    }
                    break;
                case 10:
                    chatService.displayChatHistory();
                    if (!chatService.isChatHistoryEmpty()) {
                        System.out.print("Enter the index of the message to unpin: ");
                        int unpinIndex;
                        try {
                            unpinIndex = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("***************");
                            System.out.println("Invalid index. Please enter a number.");
                            System.out.println("***************");
                            continue;
                        }
                        chatService.unpinMessage(unpinIndex);
                    }
                    break;
                case 11:
                    userService.listUsers();
                    System.out.print("Enter your user ID: ");
                    int blockerId;
                    try {
                        blockerId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User blocker = userService.getUserById(blockerId);
                    if (blocker == null) {
                        System.out.println("***************");
                        System.out.println("User not found.");
                        System.out.println("***************");
                        continue;
                    }
                    System.out.print("Enter user ID to block: ");
                    int blockId;
                    try {
                        blockId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User userToBlock = userService.getUserById(blockId);
                    if (userToBlock == null) {
                        System.out.println("***************");
                        System.out.println("User not found.");
                        System.out.println("***************");
                        continue;
                    }
                    userService.blockUser(blocker, userToBlock);
                    break;
                case 12:
                    userService.listUsers();
                    System.out.print("Enter your user ID: ");
                    int unblockerId;
                    try {
                        unblockerId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User unblocker = userService.getUserById(unblockerId);
                    if (unblocker == null) {
                        System.out.println("***************");
                        System.out.println("User not found.");
                        System.out.println("***************");
                        continue;
                    }
                    System.out.print("Enter user ID to unblock: ");
                    int unblockId;
                    try {
                        unblockId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User userToUnblock = userService.getUserById(unblockId);
                    if (userToUnblock == null) {
                        System.out.println("***************");
                        System.out.println("User not found.");
                        System.out.println("***************");
                        continue;
                    }
                    userService.unblockUser(unblocker, userToUnblock);
                    break;
                case 13:
                    chatService.displayChatHistory();
                    break;
                case 14:
                    userService.listUsers();
                    System.out.print("Enter user ID to view their messages: ");
                    int userId;
                    try {
                        userId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User user = userService.getUserById(userId);
                    if (user == null) {
                        System.out.println("***************");
                        System.out.println("User not found.");
                        System.out.println("***************");
                        continue;
                    }
                    chatService.displayChatHistoryByUser(user);
                    break;
                case 15:
                    chatService.displayChatHistory();
                    if (!chatService.isChatHistoryEmpty()) {
                        System.out.print("Enter the index of the message to delete: ");
                        int deleteIndex;
                        try {
                            deleteIndex = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("***************");
                            System.out.println("Invalid index. Please enter a number.");
                            System.out.println("***************");
                            continue;
                        }
                        chatService.deleteMessage(deleteIndex);
                    }
                    break;
                case 16:
                    chatService.displayChatHistory();
                    if (!chatService.isChatHistoryEmpty()) {
                        System.out.print("Enter the index of the message to edit: ");
                        int editIndex;
                        try {
                            editIndex = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("***************");
                            System.out.println("Invalid index. Please enter a number.");
                            System.out.println("***************");
                            continue;
                        }
                        System.out.print("Enter new message content: ");
                        String newContent = scanner.nextLine();
                        chatService.editMessage(editIndex, newContent);
                    }
                    break;
                case 17:
                    chatService.displayChatHistory();
                    if (!chatService.isChatHistoryEmpty()) {
                        System.out.print("Enter the index of the message to mark as read: ");
                        int readIndex;
                        try {
                            readIndex = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("***************");
                            System.out.println("Invalid index. Please enter a number.");
                            System.out.println("***************");
                            continue;
                        }
                        chatService.markMessageAsRead(readIndex);
                    }
                    break;
                case 18:
                    userService.listUsers();
                    System.out.print("Enter your user ID: ");
                    int statusUserId;
                    try {
                        statusUserId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid ID. Please enter a number.");
                        System.out.println("***************");
                        continue;
                    }
                    User statusUser = userService.getUserById(statusUserId);
                    if (statusUser == null) {
                        System.out.println("***************");
                        System.out.println("User not found.");
                        System.out.println("***************");
                        continue;
                    }
                    System.out.print("Update status message (1) or Toggle online/offline (2)? ");
                    int statusChoice;
                    try {
                        statusChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("***************");
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                        System.out.println("***************");
                        continue;
                    }
                    if (statusChoice == 1) {
                        System.out.print("Enter new status message (e.g., Available, Busy): ");
                        String statusMessage = scanner.nextLine();
                        if (statusMessage.trim().isEmpty()) {
                            System.out.println("***************");
                            System.out.println("Error: Status message cannot be empty.");
                            System.out.println("***************");
                            continue;
                        }
                        statusUser.setStatusMessage(statusMessage);
                        System.out.println("***************");
                        System.out.println("Status updated successfully!");
                        System.out.println("***************");
                    } else if (statusChoice == 2) {
                        userService.toggleUserStatus(statusUserId);
                    } else {
                        System.out.println("***************");
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                        System.out.println("***************");
                    }
                    break;
                default:
                    System.out.println("***************");
                    System.out.println("Invalid option. Please choose 1-18 or 'exit'.");
                    System.out.println("***************");
            }
        }
    }
}