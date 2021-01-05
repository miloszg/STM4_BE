package pl.milosz.demo.web.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.milosz.demo.web.health.HealthCheckService;

@RequestMapping
@RestController
public class HealthCheckController {
    private HealthCheckService service = new HealthCheckService();

    @GetMapping(value= "/health/version", produces = "text/plain")
    public String getVersion() {
        return service.getVersion();
    }

    @GetMapping(value= "/health", produces = "text/plain")
    public String health() {
        return service.getHealth();
    }
}
