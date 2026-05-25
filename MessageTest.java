import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {

        Message msg = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertTrue(msg.getMessageContent().length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 251; i++) {
            sb.append("a");
        }

        String longMsg = sb.toString();

        Message msg = new Message("+27718693002", longMsg);

        assertFalse(msg.getMessageContent().length() <= 250);
    }

    @Test
    public void testRecipientSuccess() {

        Message msg = new Message("+27718693002", "Hello");

        assertEquals(
                "Cell phone number successfully captured.",
                msg.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientFailure() {

        Message msg = new Message("27718693002", "Hello");

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell()
        );
    }

    @Test
    public void testMessageHash() {

        Message msg = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        msg.setNumSent(0);

        String hash = msg.createMessageHash();

        // First 2 digits of generated ID will vary
        assertTrue(hash.contains(":0:HITONIGHT"));
    }

    @Test
    public void testSendMessageChoice() {

        Message msg = new Message("+27718693002", "Test");

        assertEquals(
                "Message successfully sent.",
                msg.sentMessage(1)
        );

        assertEquals(
                "Message discarded.",
                msg.sentMessage(0)
        );

        assertEquals(
                "Message successfully stored.",
                msg.sentMessage(2)
        );
    }
}