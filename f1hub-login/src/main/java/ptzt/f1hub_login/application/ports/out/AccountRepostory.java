package ptzt.f1hub_login.application.ports.out;

import ptzt.f1hub_login.application.ports.in.RegisterAccountUseCase;
import ptzt.f1hub_login.application.ports.in.RestorePasswordUseCase;
import ptzt.f1hub_login.domain.entity.Account;

public interface AccountRepostory {

    Account register(RegisterAccountUseCase.InputValues inputValues);

    void restorePassword(RestorePasswordUseCase.InputValues inputValues);

    void deactivateAccount(Long id);

    Account findByEmailOrUsername(String identifier);

}
