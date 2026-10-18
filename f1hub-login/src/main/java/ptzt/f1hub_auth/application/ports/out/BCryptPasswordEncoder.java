package ptzt.f1hub_auth.application.ports.out;

public interface BCryptPasswordEncoder {

    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
