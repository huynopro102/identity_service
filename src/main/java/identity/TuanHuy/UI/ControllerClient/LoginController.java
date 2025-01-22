package identity.TuanHuy.UI.ControllerClient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class LoginController {

    @GetMapping("/login")
    public String getlogin(){
        return "Client/login/index";
    }
}
