package pl.milosz.demo.web.parameter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.milosz.demo.Point;
import pl.milosz.demo.web.parameter.ParameterService;

import java.io.IOException;
import java.net.URISyntaxException;

@RequestMapping
@RestController
public class ParameterController {

    private ParameterService service = new ParameterService();

    @GetMapping(value= "/text", produces = "text/plain")
    public String getMapText(
            @RequestParam("xLU") double xLU,
            @RequestParam("yLU") double yLU,
            @RequestParam("xRL") double xRL,
            @RequestParam("yRL") double yRL
    ) {
        return service.getMapText(
                new Point("pLeftUpper", xLU, yLU),
                new Point("pRightLower", xRL, yRL)
        );
    }

    @GetMapping(value= "/img", produces = "image/jpg")
    public ResponseEntity getMapFull() throws IOException, URISyntaxException {
        byte[] image = service.getMapFull();
        return ResponseEntity.ok(image);
    }

    @GetMapping(value= "/imgc", produces = "image/jpg")
    public ResponseEntity getMapCropped(
            @RequestParam("xLU") double xLU,
            @RequestParam("yLU") double yLU,
            @RequestParam("xRL") double xRL,
            @RequestParam("yRL") double yRL
    ) throws IOException {

        byte[] image = service.getMapCropped(
                new Point("pLeftUpper", xLU, yLU),
                new Point("pRightLower", xRL, yRL)
        );

        return ResponseEntity.ok(image);
    }
}
