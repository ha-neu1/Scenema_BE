package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovieController {

    @GetMapping("/movie")
    public String index() {
        return "movielist";
    }
}