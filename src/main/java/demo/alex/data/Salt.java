package demo.alex.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "SALT",
        indexes = {@Index(name = "pk_user_id", columnList = "ID", unique = true)})
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Salt extends ContextData {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Id
    @Column(name = "SALT", nullable = false, length = 4000)
    private String salt;

}
