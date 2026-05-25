import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class QuickChat {

    private static ArrayList<Message> messages = new ArrayList<>();
    private static int totalSent = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to QuickChat.");

        System.out.print("Enter username to login: ");
        String user = scanner.nextLine();

        if (user.isEmpty()) {
            System.out.println("Login failed. Exiting.");
            return;
        }

        System.out.print("How many messages do you want to send? ");

        int numMessages;

        try {
            numMessages = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
            return;
        }

        for (int i = 0; i < numMessages; i++) {
            sendMessageFlow(i + 1);
        }

        System.out.println("\nTotal messages sent: " + totalSent);

        showMenu();
    }

    private static void sendMessageFlow(int msgNumber) {

        System.out.println("\n--- Message " + msgNumber + " ---");

        System.out.print("Enter recipient number (must start with +): ");
        String recipient = scanner.nextLine();

        System.out.print("Enter message (max 250 chars): ");
        String content = scanner.nextLine();

        // Validate message length
        if (content.length() > 250) {
            System.out.println("Please enter a message of less than 250 characters.");
            return;
        } else {
            System.out.println("Message ready to send.");
        }

        Message msg = new Message(recipient, content);

        msg.setNumSent(msgNumber);

        // Validate recipient
        String recipientCheck = msg.checkRecipientCell();

        System.out.println(recipientCheck);

        if (!recipientCheck.contains("successfully")) {
            return;
        }

        // Generate hash
        System.out.println("Message Hash: " + msg.createMessageHash());

        // Menu options
        System.out.println("\nChoose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Store Message to send later");
        System.out.println("0. Disregard Message");
        System.out.print("Enter choice: ");

        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid option selected.");
            return;
        }

        String result = msg.sentMessage(choice);

        System.out.println(result);

        // Process choice
        if (choice == 1) {

            totalSent++;

            messages.add(msg);

            System.out.println("\n--- Message Details ---");

            System.out.println(msg.printMessages());

        } else if (choice == 2) {

            messages.add(msg);

            storeMessage(msg);

        } else if (choice == 0) {

            System.out.println("Message discarded.");

        } else {

            System.out.println("Invalid choice.");

        }
    }

    private static void showMenu() {

        while (true) {

            System.out.println("\n--- Menu ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");

            System.out.print("Choose option: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid menu option.");
                continue;
            }

            switch (choice) {

                case 1:

                    System.out.print("How many messages? ");

                    int n;

                    try {
                        n = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                        break;
                    }

                    for (int i = 0; i < n; i++) {
                        sendMessageFlow(messages.size() + 1);
                    }

                    break;

                case 2:

                    if (messages.isEmpty()) {

                        System.out.println("No messages available.");

                    } else {

                        System.out.println("\n--- Sent Messages ---");

                        for (Message msg : messages) {
                            System.out.println(msg.printMessages());
                        }
                    }

                    break;

                case 3:

                    System.out.println("Exiting QuickChat. Goodbye!");

                    return;

                default:

                    System.out.println("Invalid option.");
            }
        }
    }

    // Store message in JSON file
    public static void storeMessage(Message msg) {



        try (FileWriter writer = new FileWriter("messages.json", true)) {


            writer.write(System.lineSeparator());

            System.out.println("Message stored in messages.json");

        } catch (IOException e) {

            System.out.println("Error storing message: " + e.getMessage());
        }
    }
}