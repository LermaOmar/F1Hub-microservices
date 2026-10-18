package ptzt.f1hub_login.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import ptzt.f1hub_login.domain.enums.AccountRoles;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Account  {

    private Long id;
    private String username;
    private String password;
    private String email;
    @Builder.Default
    private AccountRoles accountRoles = AccountRoles.USER;
    @Builder.Default
    private boolean isActive = false;

    public static Account createAccount(String username, String password, String email){
        return Account.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }

    public void restorePassword(String password){
        this.password = password;
    }

    public void activateAccount(){
        this.isActive = true;
    }

    public void deactivateAccount(){
        this.isActive = false;
    }

    public static Account reconstitute(
            Long id,
            String username,
            String password,
            String email,
            AccountRoles accountRoles,
            boolean isActive
    ) {
        return Account.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .accountRoles(accountRoles)
                .isActive(isActive)
                .build();
    }
}
