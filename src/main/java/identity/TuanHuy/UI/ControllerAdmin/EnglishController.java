package identity.TuanHuy.UI.ControllerAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class EnglishController {

    @GetMapping("/english")
    public String getEnglish(){
        return "english/index";
    }

}
