package identity.TuanHuy.UI.ControllerAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/admin/v1/")
public class AdminUserController {


    @GetMapping("/user")
    public String getDashboardv3(Model model){
        model.addAttribute("content", "Admin/User/index");
        return "fragments/base";
    }


}
