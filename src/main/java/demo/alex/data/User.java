package demo.alex.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends ContextData {

    private Long userId;
    private String userName;
    private String email;
    private String password;

}
