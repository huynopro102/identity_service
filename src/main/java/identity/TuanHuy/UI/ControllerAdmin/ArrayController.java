package identity.TuanHuy.UI.ControllerAdmin;


import identity.TuanHuy.UI.Services.ArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class ArrayController {

    @Autowired
    ArrayService arrayService;


    @GetMapping("/array")
    public String getArray(Model model){
        model.addAttribute("Sum",arrayService.getArraySum());
        return "array/array";
    }
}
