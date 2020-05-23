package invalid.bt.spring_address_book.controllers;

import invalid.bt.spring_address_book.entities.City;
import invalid.bt.spring_address_book.forms.CityForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

// Per : https://spring.io/guides/gs/handling-form-submission/
@Controller
public class CityAltController {

    @GetMapping("/city/new")
    public String showForm(Model model) {
        model.addAttribute("city", new City());
        return "thymeleaf/cities_alt/form";
    }

    // I find @ModelAttribute confusing
    // Read this later : https://stackoverflow.com/questions/8688135/modelattribute-annotation-when-to-use-it/26916920#26916920
    @PostMapping("/city")
    public String submit(@Valid @ModelAttribute City city, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "thymeleaf/cities_alt/form";
        }
        return "thymeleaf/cities_alt/success";
    }
}
