package ptzt.f1hub_login.application.ports.in;

import lombok.Builder;
import lombok.Getter;
import ptzt.f1hub_login.domain.entity.Account;

public interface RegisterAccountUseCase {
    Account execute(InputValues inputValues);

    @Builder
    @Getter
    class InputValues{
        private String username;
        private String password;
        private String email;
    }
}
