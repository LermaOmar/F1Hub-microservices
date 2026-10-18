package ptzt.f1hub_login.infrastructure.adapter.out.persistence;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ptzt.f1hub_login.application.ports.in.RegisterAccountUseCase;
import ptzt.f1hub_login.application.ports.in.RestorePasswordUseCase;
import ptzt.f1hub_login.application.ports.out.AccountRepostory;
import ptzt.f1hub_login.application.ports.out.BCryptPasswordEncoder;
import ptzt.f1hub_login.domain.entity.Account;
import ptzt.f1hub_login.infrastructure.exceptions.EntityNotFoundException;
import ptzt.f1hub_login.infrastructure.jpa.AccountEntity;
import ptzt.f1hub_login.infrastructure.mapper.AccountPostgresMapper;
import ptzt.f1hub_login.infrastructure.repository.AccountJpaRepository;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepostory {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountPostgresMapper accountPostgresMapper;
    private final BCryptPasswordEncoder BCryptPasswordEncoder;

    @Override
    @Transactional
    public Account register(RegisterAccountUseCase.InputValues inputValues) {
        return accountPostgresMapper.toDomain(
                accountJpaRepository.save(
                        accountPostgresMapper.toJpaEntity(
                                Account.createAccount(
                                        inputValues.getUsername(),
                                        BCryptPasswordEncoder.encode(
                                                inputValues.getPassword()
                                        ),
                                        inputValues.getEmail()
                                )
                        )
                )
        );
    }

    @Override
    @Transactional
    public void restorePassword(RestorePasswordUseCase.InputValues inputValues) {
        AccountEntity entity = accountJpaRepository
                .findById(inputValues.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Account not found with id %d", inputValues.getId())
                        )
                );
        Account account = accountPostgresMapper.toDomain(entity);

        account.restorePassword(inputValues.getPassword());

        accountJpaRepository.save(
                accountPostgresMapper.toJpaEntity(account)
        );
    }

    @Override
    @Transactional
    public void deactivateAccount(Long id) {
        AccountEntity entity = accountJpaRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Account not found with id %d", id)
                        )
                );
        Account account = accountPostgresMapper.toDomain(entity);

        account.deactivateAccount();

        accountJpaRepository.save(
                accountPostgresMapper.toJpaEntity(account)
        );
    }

    @Override
    public Account findByEmailOrUsername(String identifier) {
        return accountJpaRepository
                .findByUsernameOrEmail(identifier)
                .map(accountPostgresMapper::toDomain)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Account not found with identifier %s", identifier)
                        )
                );
    }

}
