package identity.TuanHuy.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class helloController extends baseApiController{

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
    @GetMapping("/mienphi")
    public String mienphi() {
        return "hello world mien phi";
    }


}
