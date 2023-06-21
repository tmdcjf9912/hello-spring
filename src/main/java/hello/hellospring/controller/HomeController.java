package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  //매핑된게 있을 시에 index.html 이 아니라 매핑된 페이지가 로드된다. 매핑된게 없을시엔 index.html이 로드됨
    public String home(){
        return "home";
    }
}
