package ptzt.f1hub_login.application.ports.out;

public interface BCryptPasswordEncoder {

    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
