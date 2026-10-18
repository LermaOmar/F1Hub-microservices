package ptzt.f1hub_auth.application.ports.out;

public interface AuthEventPublisher {

    void sendMessage(String key, Object object);
}
