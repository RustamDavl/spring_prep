package dance.brain.scbtspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@BatchSize(size = 5)
public class Locale {

    @Column(length = 2)
    private String lang;
    private String description;
}
