package ptzt.f1hub_login.application.ports.out;

public interface AuthEventPublisher {

    void sendMessage(String key, Object object);
}
