package ptzt.f1hub_login.application.ports.out;

import ptzt.f1hub_login.infrastructure.adapter.dto.out.AuthToken;
import ptzt.f1hub_login.domain.entity.Account;

public interface TokenGenerator {

    AuthToken generate(Account account);
}
