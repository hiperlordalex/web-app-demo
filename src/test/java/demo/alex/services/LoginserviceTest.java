package demo.alex.services;

import demo.alex.data.Salt;
import demo.alex.data.User;
import demo.alex.exception.LoginException;
import demo.alex.repository.SaltRepository;
import demo.alex.repository.UserRepository;
import demo.alex.rest.LoginUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginserviceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SaltRepository saltRepository;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Test(expected = LoginException.class)
    public void saveNewUserFailOnExistingUser() throws LoginException {
        LoginUser loginUser = new LoginUser("test", "test", "test");
        User user = User.createUser(getUserBuilderByLoginUser(loginUser));

        when(userRepository.findByEmail(loginUser.getEmail())).thenReturn(Optional.of(user));

        loginService.saveNewUser(loginUser);
    }

    @Test
    public void saveNewUserSuccess() throws LoginException {
        LoginUser loginUser = new LoginUser("test", "test", "test");
        User mockUser = mock(User.class);
        Salt mockSalt=mock(Salt.class);

        when(userRepository.findByEmail(loginUser.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(mockUser);
        when(mockUser.getUserId()).thenReturn(1L);
        when(saltRepository.save(any(Salt.class))).thenReturn(mockSalt);

        Assert.assertTrue(loginService.saveNewUser(loginUser));
    }

    private User.Builder getUserBuilderByLoginUser(LoginUser loginUser) {
        return User.createBuilder()
                .email(loginUser.getEmail())
                .name(loginUser.getUserName())
                .password(loginUser.getPassword());
    }

}
