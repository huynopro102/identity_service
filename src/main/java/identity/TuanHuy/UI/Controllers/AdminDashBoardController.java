package identity.TuanHuy.UI.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/admin/v1/")
public class AdminDashBoardController {


    @GetMapping("/dashboard/index3")
    public String getDashboardv3(Model model){
        // dashboard là tên thư mực .html ở trong folder fragments
        // dashboard3 là fragments trong file dashboard
        // dashboard_name là tên biến được truyền vào file html
        model.addAttribute("content", "/admin/music");
        return "fragments/base";
    }

}
