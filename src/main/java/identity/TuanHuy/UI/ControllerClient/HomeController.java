    package identity.TuanHuy.UI.ControllerClient;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/ui")
    public class HomeController {

        @GetMapping("/home")
        public String home(Model model) {
            model.addAttribute("sinhvie");
            model.addAttribute("age",17);
            model.addAttribute("type","radio");
            model.addAttribute("message","đây là message");
            return "Client/home/index";
        }

    }
