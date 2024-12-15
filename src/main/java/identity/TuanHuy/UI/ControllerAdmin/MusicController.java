package identity.TuanHuy.UI.ControllerAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class MusicController {

    @GetMapping("/music")
    public String getDashboard(){
        return "music/index";
    }

}
