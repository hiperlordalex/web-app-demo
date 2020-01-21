package demo.alex.utils;

import demo.alex.exception.LoginException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class PasswordEncoderTest {

    @Test
    public void testSaltGenerationNotNull() {
        String salt = PasswordEncoder.generateSalt();
        Assert.assertNotNull(salt);
    }

    @Test
    public void testSaltGenerationNotEmpty() {
        String salt = PasswordEncoder.generateSalt();
        Assert.assertNotEquals("", salt);
    }

    @Test(expected = LoginException.class)
    public void testHashPasswordExceptionOnNull() throws LoginException {
        PasswordEncoder.hashPassword(null, "x");
    }

    @Test(expected = LoginException.class)
    public void testHashPasswordExceptionOnEmpty() throws LoginException {
        PasswordEncoder.hashPassword("", "x");
    }

    public void testHashPasswordNotEmpty() throws LoginException {
        Optional<String> password = PasswordEncoder.hashPassword("x", "x");
        Assert.assertTrue(password.isPresent());
    }

    @Test
    public void testVerifyHashPassword() throws LoginException {
        String password = "pswd";
        String salt = "salt";
        Optional<String> encryptedPasswordCase_A = PasswordEncoder.hashPassword(password, salt);
        Optional<String> encryptedPasswordCase_B = PasswordEncoder.hashPassword(password, salt);
        Assert.assertEquals(encryptedPasswordCase_A.get(), encryptedPasswordCase_B.get());
    }

}
