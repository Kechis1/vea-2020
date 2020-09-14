package danielbill.vea.vea2020.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyFirstController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
