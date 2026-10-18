package ptzt.f1hub_login.application.ports.in;

import lombok.Builder;
import lombok.Getter;

public interface RestorePasswordUseCase {

    void execute(InputValues inputValues);

    @Builder
    @Getter
    class InputValues{
        private String password;
        private Long id;
    }
}
