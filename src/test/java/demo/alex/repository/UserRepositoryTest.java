package demo.alex.repository;

import demo.alex.Application;
import demo.alex.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static demo.alex.utility.UserTestUtils.createDefaultUserBuilder;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSavingUserSuccessfully() {
        User user = User.createUser(createDefaultUserBuilder());
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getUserId());
    }

    @Test
    public void testSavingUserFailOnDuplicatedEmail() {
        User.Builder builder = createDefaultUserBuilder();
        builder.name("asd");
        User userA = User.createUser(builder);
        User savedUser = userRepository.save(userA);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getUserId());

        User userB = User.createUser(builder);
        assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()->userRepository.save(userB));
    }

}
