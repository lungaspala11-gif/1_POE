import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {
    Password password = new Password();

    @Test
    void testCheckUserName_CorrectFormat() {
        assertTrue(password.checkUserName("ben_1"));
    }

    @Test
    void testCheckUserName_IncorrectFormat() {
        assertFalse(password.checkUserName("beNz!!!!!!!"));
    }

    @Test
    void testCheckPasswordComplexity_MeetsRequirements() {
        assertTrue(password.checkPasswordComplexity("Java#Rocks8"));
    }

    @Test
    void testCheckPasswordComplexity_DoesNotMeetRequirements() {
        assertFalse(password.checkPasswordComplexity("password"));
    }

    @Test
    void testCheckCellPhoneNumber_CorrectFormat() {
        assertTrue(password.checkCellPhoneNumber("+27790220563"));
    }

    @Test
    void testCheckCellPhoneNumber_IncorrectFormat() {
        assertFalse(password.checkCellPhoneNumber("090220563"));
    }
}