import java.util.ArrayList;

public class ChatStorage {

    private ArrayList<String> sentMessages = new ArrayList<>();
    private ArrayList<String> disregardedMessages = new ArrayList<>();
    private ArrayList<Message> storedMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();

    public void addMessage(Message msg) {

        messageHashes.add(msg.getMessageHash());
        messageIDs.add(msg.getMessageID());

        String flag = msg.getStatus() == null ? "" : msg.getStatus().toLowerCase();

        switch (flag) {
            case "sent":
                sentMessages.add(msg.getMessageContent());
                break;

            case "stored":
                storedMessages.add(msg);
                break;

            case "disregard":
            case "discarded":
                disregardedMessages.add(msg.getMessageContent());
                break;
        }
    }

    public String displayAllStoredSenderRecipient() {

        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }

        StringBuilder sb = new StringBuilder();

        for (Message msg : storedMessages) {
            sb.append("Recipient: ")
                    .append(msg.getRecipient())
                    .append("\nMessage: ")
                    .append(msg.getMessageContent())
                    .append("\n\n");
        }

        return sb.toString();
    }

    public String displayLongestStoredMessage() {

        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }

        String longest = "";

        for (Message msg : storedMessages) {

            String content = msg.getMessageContent();

            if (content.length() > longest.length()) {
                longest = content;
            }
        }

        return "Longest stored message: " + longest;
    }

    // Getters

    public ArrayList<String> getSentMessages() {
        return sentMessages;
    }

    public ArrayList<String> getDisregardedMessages() {
        return disregardedMessages;
    }

    public ArrayList<Message> getStoredMessages() {
        return storedMessages;
    }

    public ArrayList<String> getMessageHashes() {
        return messageHashes;
    }

    public ArrayList<String> getMessageIDs() {
        return messageIDs;
    }
}