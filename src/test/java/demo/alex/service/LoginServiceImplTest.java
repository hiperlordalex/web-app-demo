package demo.alex.service;

import demo.alex.Application;
import demo.alex.data.User;
import demo.alex.repository.SaltRepository;
import demo.alex.repository.UserRepository;
import demo.alex.rest.LoginUser;
import demo.alex.services.LoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static demo.alex.utility.UserTestUtils.createDefaultLoginUser;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class LoginServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SaltRepository saltRepository;

    private LoginServiceImpl service;

    @Before
    public void init() {
        this.service = new LoginServiceImpl(userRepository, saltRepository);
    }

    @Test
    public void userLoginPerformedSuccessfully() {
        LoginUser loginUser = createDefaultLoginUser();
        service.saveNewUser(loginUser);

        User user = userRepository.findByEmail(loginUser.getEmail());
        assertNotNull(user);
    }

}
