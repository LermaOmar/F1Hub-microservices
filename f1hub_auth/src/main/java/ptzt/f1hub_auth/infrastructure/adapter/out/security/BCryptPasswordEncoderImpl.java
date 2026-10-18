package ptzt.f1hub_auth.infrastructure.adapter.out.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ptzt.f1hub_auth.application.ports.out.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class BCryptPasswordEncoderImpl implements BCryptPasswordEncoder {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
