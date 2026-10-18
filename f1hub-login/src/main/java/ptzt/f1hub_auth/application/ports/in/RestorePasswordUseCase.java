package ptzt.f1hub_auth.application.ports.in;

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
