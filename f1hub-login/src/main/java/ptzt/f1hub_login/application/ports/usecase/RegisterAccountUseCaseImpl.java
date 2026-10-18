package ptzt.f1hub_login.application.ports.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptzt.f1hub_login.application.ports.in.RegisterAccountUseCase;
import ptzt.f1hub_login.application.ports.out.AccountRepostory;
import ptzt.f1hub_login.domain.entity.Account;

@Service
@RequiredArgsConstructor
public class RegisterAccountUseCaseImpl implements RegisterAccountUseCase {

    private final AccountRepostory accountRepostory;

    @Override
    public Account execute(InputValues inputValues) {
        return accountRepostory.register(inputValues);
    }
}
