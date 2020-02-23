package demo.alex.services;

import demo.alex.data.Salt;
import demo.alex.data.User;
import demo.alex.exception.LoginException;
import demo.alex.repository.SaltRepository;
import demo.alex.repository.UserRepository;
import demo.alex.rest.LoginUser;
import demo.alex.utils.PasswordEncoder;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log
@Service
@Transactional
public class LoginServiceImpl {

    private UserRepository userRepository;
    private SaltRepository saltRepository;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, SaltRepository saltRepository) {
        this.userRepository = userRepository;
        this.saltRepository = saltRepository;
    }

    public boolean loginExistingUser(LoginUser loginUser) throws LoginException {
        log.info("Login existing user ...");

        User user = userRepository.findByEmail(loginUser.getEmail())
                .orElseThrow(() -> new LoginException("User with email address is not exist!"));
        Salt salt = saltRepository.findById(user.getUserId())
                .orElseThrow(() -> new LoginException("User password error!"));

        Optional<String> encryptedPassword = PasswordEncoder.hashPassword(loginUser.getPassword(), salt.getSalt());

        if (user.getPassword().equals(encryptedPassword.get())){
            log.info("Login existing user completed");

            return true;
        }else {
            log.info("Login existing user failed, password is invalid!");
        }
        log.info("Login existing user failed");

        return false;
    }

    public boolean saveNewUser(LoginUser loginUser) throws LoginException {
        log.info("Saving new user ...");

        if (isUserExist(loginUser)) {
            throw new LoginException("User with email address is already exist!");
        }

        String salt = PasswordEncoder.generateSalt();
        Optional<String> encryptedPassword = PasswordEncoder.hashPassword(loginUser.getPassword(), salt);

        if (encryptedPassword.isPresent()) {
            User user = createUser(loginUser, encryptedPassword.get());
            User savedUser = userRepository.save(user);
            Salt userSalt = new Salt(savedUser.getUserId(), salt);
            saltRepository.save(userSalt);
            log.info("Saving new user completed");

            return true;
        }
        log.warning("Saving new user failed, encrypted password is invalid.");

        return false;
    }

    private boolean isUserExist(LoginUser loginUser) {
        return userRepository.findByEmail(loginUser.getEmail()).isPresent();
    }

    private User createUser(LoginUser loginUser, String encryptedPassword) {
        User.Builder builder = User.createBuilder()
                .email(loginUser.getEmail())
                .name(loginUser.getUserName())
                .password(encryptedPassword);
        return User.createUser(builder);
    }

}
