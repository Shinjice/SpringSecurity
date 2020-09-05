package shinjice.SpringSecurity.auto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/autos")
public class AutoController {

    private static final List<Auto> AUTOS = Arrays.asList(
            new Auto(1,"BMW 3-SERIE"),
            new Auto(2,"OPEL ASTRA"),
            new Auto(3,"FORD FIESTA")

    );

    @GetMapping(path = "/{autoId}")
    public Auto getAuto(@PathVariable("autoId") Integer autoId){
    return AUTOS.stream()
            .filter(auto -> autoId.equals(auto.getAutoId()))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("Auto " + autoId + " bestaat niet"));
    }
}
