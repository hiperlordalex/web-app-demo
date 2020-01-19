package demo.alex.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "USER",
        indexes = {@Index(name = "pk_user_id", columnList = "ID", unique = true)})
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends ContextData {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static User createUser(Builder builder){
        return new User(builder);
    }

    public static Builder createBuilder(){
        return new Builder();
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public static class Builder {

        private String name;
        private String email;
        private String password;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

    }

}
