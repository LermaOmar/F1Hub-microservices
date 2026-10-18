package ptzt.f1hub_login.application.ports.in;

import ptzt.f1hub_login.infrastructure.adapter.dto.out.AuthToken;
import ptzt.f1hub_login.infrastructure.adapter.dto.in.LoginDto;

import javax.security.auth.login.LoginException;

public interface LoginUseCase {

    AuthToken execute(LoginDto loginDto) throws LoginException;
}
