package ptzt.f1hub_auth.application.ports.out;

import ptzt.f1hub_auth.application.ports.in.RegisterAccountUseCase;
import ptzt.f1hub_auth.application.ports.in.RestorePasswordUseCase;
import ptzt.f1hub_auth.domain.entity.Account;

public interface AccountRepostory {

    Account register(RegisterAccountUseCase.InputValues inputValues);

    void restorePassword(RestorePasswordUseCase.InputValues inputValues);

    void deactivateAccount(Long id);

    Account findByEmailOrUsername(String identifier);

}
