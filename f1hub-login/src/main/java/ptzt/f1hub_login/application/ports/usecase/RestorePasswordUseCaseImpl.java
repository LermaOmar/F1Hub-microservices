package ptzt.f1hub_login.application.ports.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptzt.f1hub_login.application.ports.in.RestorePasswordUseCase;
import ptzt.f1hub_login.application.ports.out.AccountRepostory;

@RequiredArgsConstructor
@Service
public class RestorePasswordUseCaseImpl implements RestorePasswordUseCase {

    private final AccountRepostory accountRepostory;

    @Override
    public void execute(InputValues inputValues) {
        accountRepostory.restorePassword(inputValues);
    }
}
