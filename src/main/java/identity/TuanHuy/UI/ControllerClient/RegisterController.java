package identity.TuanHuy.UI.ControllerClient;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class RegisterController {

    @GetMapping("/register")
    public String getRegister(){
        return "Client/register/index";
    }
}
