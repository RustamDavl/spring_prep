package dance.brain.scbtspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @ElementCollection
    @CollectionTable(name = "company_locales",
            joinColumns = @JoinColumn(name = "company_id"))
    private List<Locale> locales = new ArrayList<>();

    public void addLocale(Locale locale) {
        this.locales.add(locale);
    }
}
