package shinjice.SpringSecurity.auto;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/autos")
public class AutoManagementController {

    private static final List<Auto> AUTOS = Arrays.asList(
            new Auto(1,"BMW 3-SERIE"),
            new Auto(2,"OPEL ASTRA"),
            new Auto(3,"FORD FIESTA")

    );

    @GetMapping
    public static List<Auto> getAUTOS() {
        System.out.println("return all autos");
        return AUTOS;
    }
    @PostMapping
    public void registerNewAuto(@RequestBody Auto auto){
        System.out.println("registerNewAuto");
        System.out.println(auto);
    }
    @DeleteMapping(path = "{autoId}")
    public void deleteAuto(@PathVariable("autoId") Integer autoID){
        System.out.println("deleteAuto");
        System.out.println(autoID);
    }
    @PutMapping (path = "{autoId}")
    public void updateAuto(@PathVariable("autoId") Integer autoId, @RequestBody Auto auto){
        System.out.println("updateAuto");
        System.out.println(String.format("%s %s", autoId, auto));
    }
}
