package ptzt.f1hub_auth.infrastructure.adapter.in.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ptzt.f1hub_auth.application.ports.in.DeactivateAccountUseCase;
import ptzt.f1hub_auth.application.ports.in.LoginUseCase;
import ptzt.f1hub_auth.application.ports.in.RegisterAccountUseCase;
import ptzt.f1hub_auth.application.ports.in.RestorePasswordUseCase;
import ptzt.f1hub_auth.application.ports.out.AuthEventPublisher;
import ptzt.f1hub_auth.domain.entity.Account;
import ptzt.f1hub_auth.infrastructure.adapter.dto.in.ActivationAccountDto;
import ptzt.f1hub_auth.infrastructure.adapter.dto.in.LoginDto;
import ptzt.f1hub_auth.infrastructure.adapter.config.RabbitMQConfig;
import ptzt.f1hub_auth.infrastructure.adapter.dto.out.GenericOutputDto;
import ptzt.f1hub_auth.infrastructure.mapper.DtoMapper;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RabbitListener {
    private final LoginUseCase loginUseCase;
    private final DeactivateAccountUseCase deactivateAccountUseCase;
    private final RegisterAccountUseCase registerAccountUseCase;
    private final RestorePasswordUseCase restorePasswordUseCase;
    private final AuthEventPublisher publisher;
    private final DtoMapper dtoMapper;

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = RabbitMQConfig.LOGIN_QUEUE)
    void onLogin(@Payload LoginDto message,
                 @Header(name = AmqpHeaders.CORRELATION_ID, required = false) String correlationId) throws LoginException {
        publisher.sendMessage("core.login", loginUseCase.execute(message));
    }

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = RabbitMQConfig.REGISTER_QUEUE)
    void onRegister(@Payload RegisterAccountUseCase.InputValues message,
                 @Header(name = AmqpHeaders.CORRELATION_ID, required = false) String correlationId) throws LoginException {
        Account account = registerAccountUseCase.execute(message);
        publisher.sendMessage("core.register", new GenericOutputDto(
                "Account registered correctly",
                200, LocalDateTime.now()
                )
        );
        publisher.sendMessage("notification.activate", dtoMapper.toAccountDto(account));
    }

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = RabbitMQConfig.DEACTIVATE_QUEUE)
    void onDeactivate(@Payload ActivationAccountDto activationAccountDto,
                    @Header(name = AmqpHeaders.CORRELATION_ID, required = false) String correlationId) throws LoginException {
        deactivateAccountUseCase.execute(activationAccountDto.id());
        publisher.sendMessage("core.deactivate", new GenericOutputDto(
                "Account deactivated correctly",
                200, LocalDateTime.now()
                )
        );
    }

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = RabbitMQConfig.RESTORE_PASSWORD_QUEUE)
    void onRestorePassword(@Payload RestorePasswordUseCase.InputValues inputValues,
                      @Header(name = AmqpHeaders.CORRELATION_ID, required = false) String correlationId) throws LoginException {
        restorePasswordUseCase.execute(inputValues);
        publisher.sendMessage("core.restore_password", new GenericOutputDto(
                        "Password restored correctly",
                        200, LocalDateTime.now()
                )
        );
    }
}
