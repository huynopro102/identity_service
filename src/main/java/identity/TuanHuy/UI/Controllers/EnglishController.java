package identity.TuanHuy.UI.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/ui")
public class EnglishController {

    @GetMapping("/english")
    public String getEnglish(){
        return "english/index";
    }

}
