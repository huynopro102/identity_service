package identity.TuanHuy.UI.ControllerClient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class SqlController {

    @GetMapping("/sql")
    public String sql(Model model){
        model.addAttribute("content","Client/sql/index");
        return "fragments/baseClient";
    }

}
