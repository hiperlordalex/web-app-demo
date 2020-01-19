package demo.alex.services;

import demo.alex.exception.LoginException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordEncoder {

    private static final int ITERATIONS = 64;
    private static final int KEY_LENGTH = 512;
    private static final int SALT_SEED = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static Optional<String> hashPassword(String password, String salt) throws LoginException {

        if (password == null || password.length() == 0) {
            log.error("Invalid password, password can not be null or empty, {}", password);
            throw new LoginException();
        }

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec keySpec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = secretKeyFactory.generateSecret(keySpec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Exception encountered while hashing password", e);
            return Optional.empty();

        } finally {
            keySpec.clearPassword();
        }
    }

    public static String generateSalt() {
        byte[] salt = new byte[SALT_SEED];
        RANDOM.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

}
