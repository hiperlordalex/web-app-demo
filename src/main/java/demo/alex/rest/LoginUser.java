package demo.alex.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class LoginUser {

    @JsonCreator
    public LoginUser(@NonNull String userName,@NonNull String password,@NonNull String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    private String userName;
    private String password;
    private String email;

}
