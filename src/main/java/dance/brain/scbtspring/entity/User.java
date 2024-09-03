package dance.brain.scbtspring.entity;

import dance.brain.scbtspring.dto.UserReadDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(name = "User.company",
        attributeNodes = @NamedAttributeNode("company"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class User extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @Builder.Default
    @NotAudited
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @Builder.Default
    @NotAudited
    private List<UsersChat> usersChats = new ArrayList<>();
}
