package identity.TuanHuy.UI.ControllerClient;


import identity.TuanHuy.UI.Services.ArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class ArrayController {

    @Autowired
    ArrayService arrayService;


    @Value("${api.base-url}")
    private String base_url;


    @GetMapping("/array")
    public String getArray(){

        return "Client/array/array";
    }
}
