package ptzt.f1hub_login.infrastructure.adapter.dto.out;

import java.time.LocalDateTime;

public record GenericOutputDto(String message, int httpStatus, LocalDateTime timeStamp) {
}
