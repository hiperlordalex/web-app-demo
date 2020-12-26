package demo.alex.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "SALT",
        indexes = {@Index(name = "pk_salt_user_id", columnList = "USER_ID", unique = true)})
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Salt extends ContextData implements Serializable {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "SALT", nullable = false, length = 4000)
    private String salt;

}
