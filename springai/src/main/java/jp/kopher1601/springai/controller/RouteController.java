package jp.kopher1601.springai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    @GetMapping("/hotel")
    public String hotel()  {
        return "hotel";
    }
}
