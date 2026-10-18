package ptzt.f1hub_login.application.ports.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptzt.f1hub_login.application.ports.in.DeactivateAccountUseCase;
import ptzt.f1hub_login.application.ports.out.AccountRepostory;

@Service
@RequiredArgsConstructor
public class DeactivateAccountUseCaseImpl implements DeactivateAccountUseCase {

    private final AccountRepostory accountRepostory;

    @Override
    public void execute(Long id) {
        accountRepostory.deactivateAccount(id);
    }
}
