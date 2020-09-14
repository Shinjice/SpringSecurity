package shinjice.SpringSecurity.auto;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_VERKOPER', 'ROLE_ADMINISTRATIE')")
    public static List<Auto> getAUTOS() {
        System.out.println("return all autos");
        return AUTOS;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('klant:write')")
    public void registerNewAuto(@RequestBody Auto auto){
        System.out.println("registerNewAuto");
        System.out.println(auto);
    }
    @DeleteMapping(path = "{autoId}")
    @PreAuthorize("hasAuthority('klant:write')")
    public void deleteAuto(@PathVariable("autoId") Integer autoID){
        System.out.println("deleteAuto");
        System.out.println(autoID);
    }
    @PutMapping (path = "{autoId}")
    @PreAuthorize("hasAuthority('klant:write')")
    public void updateAuto(@PathVariable("autoId") Integer autoId, @RequestBody Auto auto){
        System.out.println("updateAuto");
        System.out.println(String.format("%s %s", autoId, auto));
    }
}
