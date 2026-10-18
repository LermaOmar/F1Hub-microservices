package ptzt.f1hub_login.infrastructure.mapper;

import org.mapstruct.Mapper;
import ptzt.f1hub_login.domain.entity.Account;
import ptzt.f1hub_login.infrastructure.jpa.AccountEntity;

@Mapper
public interface AccountPostgresMapper {

    default Account toDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        return Account.reconstitute(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getAccountRoles(),
                entity.isActive()
        );
    }
    AccountEntity toJpaEntity(Account account);
}
