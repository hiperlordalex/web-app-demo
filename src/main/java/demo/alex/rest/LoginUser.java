package demo.alex.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class LoginUser {

    @JsonCreator
    public LoginUser(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    private String userName;
    private String password;
    private String email;

}
