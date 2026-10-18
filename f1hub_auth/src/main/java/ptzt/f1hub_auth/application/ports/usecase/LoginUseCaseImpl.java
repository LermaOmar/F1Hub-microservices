package ptzt.f1hub_auth.application.ports.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptzt.f1hub_auth.application.ports.in.LoginUseCase;
import ptzt.f1hub_auth.application.ports.out.AccountRepostory;
import ptzt.f1hub_auth.application.ports.out.BCryptPasswordEncoder;
import ptzt.f1hub_auth.application.ports.out.TokenGenerator;
import ptzt.f1hub_auth.infrastructure.adapter.dto.out.AuthToken;
import ptzt.f1hub_auth.infrastructure.adapter.dto.in.LoginDto;
import ptzt.f1hub_auth.domain.entity.Account;
import ptzt.f1hub_auth.infrastructure.exceptions.AccountNotActiveException;

import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final AccountRepostory accountRepostory;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenGenerator tokenGenerator;

    @Override
    public AuthToken execute(LoginDto loginDto) throws LoginException {
        Account account = accountRepostory.findByEmailOrUsername(loginDto.identifier());

        if (!bCryptPasswordEncoder.matches(loginDto.password(), account.getPassword()))
            throw new LoginException("Invalid credentials");

        if (!account.isActive())
            throw new AccountNotActiveException("Account not active, please active your account if you want to login");

        return tokenGenerator.generate(account);
    }
}
