package ptzt.f1hub_login.infrastructure.adapter.dto.out;

import java.time.LocalDateTime;

public record AuthToken(String value, LocalDateTime expiresAt) {
}
