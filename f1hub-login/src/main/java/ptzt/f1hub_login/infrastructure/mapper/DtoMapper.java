package ptzt.f1hub_login.infrastructure.mapper;

import org.mapstruct.Mapper;
import ptzt.f1hub_login.domain.entity.Account;
import ptzt.f1hub_login.infrastructure.adapter.dto.out.AccountOutputDto;

@Mapper
public interface DtoMapper {

    AccountOutputDto toAccountDto(Account account);
}
