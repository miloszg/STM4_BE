package pl.milosz.demo.web.health;

public class HealthCheckService {
    private static final String VERSION = "Server version: v1";
    private static final String HEALTH = "Server running.";

    public String getVersion() {
        return VERSION;
    }

    public String getHealth() {
        return HEALTH;
    }
}