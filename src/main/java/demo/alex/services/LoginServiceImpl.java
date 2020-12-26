package demo.alex.services;

import demo.alex.data.Salt;
import demo.alex.data.User;
import demo.alex.repository.SaltRepository;
import demo.alex.repository.UserRepository;
import demo.alex.rest.LoginUser;
import demo.alex.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LoginServiceImpl {

    private final UserRepository userRepository;
    private final SaltRepository saltRepository;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, SaltRepository saltRepository) {
        this.userRepository = userRepository;
        this.saltRepository = saltRepository;
    }

    public boolean saveNewUser(LoginUser loginUser) {
        String salt = PasswordEncoder.generateSalt();
        Optional<String> encryptedPassword = PasswordEncoder.hashPassword(loginUser.getPassword(), salt);
        if (encryptedPassword.isPresent()) {
            User.Builder builder = User.createBuilder();
            builder.email(loginUser.getEmail())
                    .name(loginUser.getUserName())
                    .password(encryptedPassword.get());
            User user = User.createUser(builder);

            User savedUser = userRepository.save(user);
            Salt userSalt = new Salt(savedUser.getUserId(), salt);
            saltRepository.save(userSalt);

            return true;
        }

        return false;
    }

}
