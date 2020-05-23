package invalid.bt.spring_address_book.controllers;

import invalid.bt.spring_address_book.forms.CityForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CityController {

    @GetMapping("/success")
    public String results() {
        return "thymeleaf/cities/success";
    }

    @GetMapping("/cities/new")
    public String showForm(CityForm cityForm) {
        return "thymeleaf/cities/form";
    }

    @PostMapping("/cities")
    public String checkCity(@Valid CityForm cityForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(">>> cityForm = " + cityForm + ", bindingResult = " + bindingResult);
            return "thymeleaf/cities/form";
        }

        // Save stuff here, I'll do that later

        return "redirect:/success";
    }
}
