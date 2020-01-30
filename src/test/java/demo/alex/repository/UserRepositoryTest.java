package demo.alex.repository;

import demo.alex.Application;
import demo.alex.data.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSavingUserSuccessfully() {
        User user = User.createUser(createDefaultUserBuilder());
        User savedUser = userRepository.save(user);

        Assert.assertNotNull(savedUser);
        Assert.assertNotNull(savedUser.getUserId());
    }

    private User.Builder createDefaultUserBuilder() {
        User.Builder builder = User.createBuilder();
        builder.password("test").name("test").email("test");

        return builder;
    }

}
