package ptzt.f1hub_auth.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.*;
import ptzt.f1hub_auth.domain.enums.AccountRoles;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 20)
    private AccountRoles accountRoles;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
