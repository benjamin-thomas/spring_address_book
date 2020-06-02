package invalid.bt.spring_address_book.controllers;

import invalid.bt.spring_address_book.entities.City;
import invalid.bt.spring_address_book.forms.CityForm;
import invalid.bt.spring_address_book.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

// Per: https://spring.io/guides/gs/validating-form-input/
@Controller
public class CityController {

    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

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

    @GetMapping("/freemarker/city/new")
    public String showForm2(CityForm cityForm, Model model) {
        model.addAttribute("city", new CityForm());
        return "freemarker/cities/form";
    }


    @PostMapping("/freemarker/cityForm")
    public String createCity(@Valid CityForm cityForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(">>> cityForm = " + cityForm + ", bindingResult = " + bindingResult);
            model.addAttribute("cityForm", cityForm);
            return "freemarker/cities/form";
        }

        final City city = new City(-1, cityForm.getName(), cityForm.getZipCode());
        // Save stuff here, I'll do that later
        cityRepository.save(city);
//        return "redirect:/city/" + city.getId();
        return "redirect:/success";
    }
}
