package invalid.bt.spring_address_book.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(
            @RequestParam(defaultValue = "John Doe") String name,
            Model model) {
        model.addAttribute("name", name);
        return "thymeleaf/index";
    }

    @GetMapping("/freemarker")
    public String index2(
            @RequestParam(defaultValue = "John Doe") String name,
            Model model) {
        model.addAttribute("name", name);
        return "freemarker/index";
    }
}
