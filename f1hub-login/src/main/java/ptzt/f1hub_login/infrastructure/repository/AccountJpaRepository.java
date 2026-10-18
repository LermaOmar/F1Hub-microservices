package ptzt.f1hub_login.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ptzt.f1hub_login.infrastructure.jpa.AccountEntity;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findById(Long id);

    @Query("""
            SELECT a FROM AccountEntity a
            WHERE a.username = :identifier
               OR a.email = :identifier
            """)
    Optional<AccountEntity> findByUsernameOrEmail(@Param("identifier") String identifier);
}
