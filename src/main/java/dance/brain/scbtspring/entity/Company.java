package dance.brain.scbtspring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Company.findByIdIn",
        query = """
                select c from Company c
                where c.id in (:id1, :id2)
                """)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "company")
    private List<User> users = new ArrayList<>();

    @Builder.Default
    @BatchSize(size = 5)
    @ElementCollection
    @CollectionTable(name = "company_locales",
            joinColumns = @JoinColumn(name = "company_id"))
    private List<Locale> locales = new ArrayList<>();

    public void addLocale(Locale locale) {
        this.locales.add(locale);
    }
}
