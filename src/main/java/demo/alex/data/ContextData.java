package demo.alex.data;

import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Version;
import java.time.Instant;

@Getter
public abstract class ContextData {

    @CreatedDate
    @Column(name = "CREATION_DATE")
    private Instant creationDate;

    @UpdateTimestamp
    @Column(name = "LAST_UPDATED_DATE")
    private Instant lastUpdatedDate;

    @Version
    @Column(name = "VERSION")
    private int version;
}
