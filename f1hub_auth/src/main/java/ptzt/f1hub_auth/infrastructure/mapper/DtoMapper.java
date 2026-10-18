package ptzt.f1hub_auth.infrastructure.mapper;

import org.mapstruct.Mapper;
import ptzt.f1hub_auth.domain.entity.Account;
import ptzt.f1hub_auth.infrastructure.adapter.dto.out.AccountOutputDto;

@Mapper
public interface DtoMapper {

    AccountOutputDto toAccountDto(Account account);
}
