import java.util.Random;

public class Message {

    private String messageID;
    private int numSent;
    private String recipient;
    private String messageContent;
    private String messageHash;
    private String status;

    // Constructor
    public Message(String recipient, String messageContent) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.status = "Pending";
    }

    // Check if Message ID is valid
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Validate recipient cell number
    public String checkRecipientCell() {

        if (recipient == null || recipient.isEmpty()) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        // International numbers should start with +
        if (recipient.startsWith("+") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Create message hash
    public String createMessageHash() {

        String idPart;

        if (messageID.length() >= 2) {
            idPart = messageID.substring(0, 2);
        } else {
            idPart = messageID;
        }

        String[] words = messageContent.trim().split("\\s+");

        String firstWord = "";
        String lastWord = "";

        if (words.length > 0) {
            firstWord = words[0].toUpperCase();
            lastWord = words[words.length - 1].toUpperCase();
        }

        messageHash = idPart + ":" + numSent + ":" + firstWord + lastWord;

        return messageHash;
    }

    // Handle message sending options
    public String sentMessage(int choice) {

        switch (choice) {

            case 1:
                status = "Sent";
                return "Message successfully sent.";

            case 0:
                status = "Discarded";
                return "Message discarded.";

            case 2:
                status = "Stored";
                return "Message successfully stored.";

            default:
                return "Invalid choice.";
        }
    }

    // Print message details
    public String printMessages() {

        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + messageContent + "\n"
                + "Status: " + status + "\n";
    }

    // Return total sent messages
    public int returnTotalMessages() {

        if (status.equals("Sent")) {
            return 1;
        } else {
            return 0;
        }
    }

    // Generate random message ID
    private String generateMessageID() {

        Random rand = new Random();

        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);

        return String.valueOf(id);
    }

    // Getters and Setters
    public String getMessageID() {
        return messageID;
    }

    public void setNumSent(int numSent) {
        this.numSent = numSent;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getStatus() {
        return status;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageHash() {
        return messageHash;
    }
}
