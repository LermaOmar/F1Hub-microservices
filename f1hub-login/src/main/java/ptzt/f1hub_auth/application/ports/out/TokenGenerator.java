package ptzt.f1hub_auth.application.ports.out;

import ptzt.f1hub_auth.infrastructure.adapter.dto.out.AuthToken;
import ptzt.f1hub_auth.domain.entity.Account;

public interface TokenGenerator {

    AuthToken generate(Account account);
}
