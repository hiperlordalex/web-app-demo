package demo.alex.services;

import demo.alex.data.User;
import demo.alex.exception.LoginException;
import demo.alex.repository.UserRepository;
import demo.alex.rest.LoginUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl {

    private UserRepository userRepository;

    public boolean saveNewUser(LoginUser loginUser) throws LoginException {

        String salt = PasswordEncoder.generateSalt();
        Optional<String> encryptedPassword = PasswordEncoder.hashPassword(loginUser.getPassword(), salt);
        if (encryptedPassword.isPresent()) {
            User.Builder builder = User.createBuilder();
            builder.email(loginUser.getEmail())
                    .name(loginUser.getUserName())
                    .password(encryptedPassword.get());
            User user = User.createUser(builder);

            userRepository.save(user);

            return true;
        }

        return false;
    }

}
