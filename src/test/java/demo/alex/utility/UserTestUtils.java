package demo.alex.utility;

import demo.alex.data.User;
import demo.alex.rest.LoginUser;

public class UserTestUtils {

    public static User.Builder createDefaultUserBuilder() {
        User.Builder builder = User.createBuilder();
        builder.password("test").name("test").email("test");

        return builder;
    }

    public static LoginUser createDefaultLoginUser(){
        return new LoginUser("test", "test", "test");
    }
}
