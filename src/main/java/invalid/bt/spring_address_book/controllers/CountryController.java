package invalid.bt.spring_address_book.controllers;

import invalid.bt.spring_address_book.entities.Country;
import invalid.bt.spring_address_book.forms.CountryEditForm;
import invalid.bt.spring_address_book.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CountryController {

    private final CountryRepository countryRepository;

    // To read: Macros
    //   https://blog.trifork.com/2013/05/27/bootstrap-spring-mvc-form-input-freemarker-macros/

    @Qualifier("defaultValidator")
    @Autowired
    Validator validator;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // CREATE
    @GetMapping("/countries/new")
    public String newCountry(Model model) {
        model.addAttribute("country", new Country());
        model.addAttribute("action", "/countries");
        return "freemarker/countries/create-form";
    }

    // CREATE
    @PostMapping("/countries")
    public String createCountry(@Valid Country country, BindingResult result) {
        if (result.hasErrors()) {
            return "freemarker/countries/create-form";
        }
        countryRepository.save(country);
        return "redirect:/countries";
    }

    // READ
    @GetMapping("/countries")
    public String listCountries(Model model) {
        model.addAttribute("INDEX_URL", "/countries");
        model.addAttribute("NEW_URL", "/countries" + "/new");
        model.addAttribute("countries", countryRepository.findAll());
        return "freemarker/countries/index";
    }

    // READ
    @GetMapping("/countries/{id}")
    public String showCountry(@PathVariable long id, Model model) {
        final Country country = countryRepository.findById(id).orElseThrow();
        model.addAttribute("country", country);
        return "freemarker/countries/show";
    }

    // UPDATE
    @GetMapping("/countries/{id}/edit")
    public String editCountry(@PathVariable long id, Model model) {
        final Country country = countryRepository.findById(id).orElseThrow();
        model.addAttribute("country", country);
        return "freemarker/countries/edit-form";
    }

    @PostMapping("/countries/{id}/edit")
    public String updateCountry(@PathVariable("id") long id, Country country, BindingResult result, Model model) {
        final Country country2 = countryRepository.findById(id).orElseThrow();
        country2.setName(country.getName());

        validator.validate(country2, result);
        if (result.hasErrors()) {
            model.addAttribute("country", country2);
            model.addAttribute(result.MODEL_KEY_PREFIX + "country", result); // --> result.MODEL_KEY_PREFIX = org.springframework.validation.BindingResult.

            return "freemarker/countries/edit-form";
        }

        countryRepository.save(country2);

        return "redirect:/countries";
    }

    // UPDATE
    @GetMapping("/countries/{id}/edit2")
    public String editCountry2(@PathVariable long id, Model model) {
        final Country country = countryRepository.findById(id).orElseThrow();
        final CountryEditForm form = new CountryEditForm();
        form.setName(country.getName());
        model.addAttribute(form); // referenced as `countryFormEdit` in the view
        return "freemarker/countries/edit-form2";
    }

    @PostMapping("/countries/{id}/edit2")
    public String updateCountry2(@PathVariable("id") long id, @Valid CountryEditForm form, BindingResult result, Model model) {
        // ModelAttribute is required if I want to use another name than the class name with lower first char
        // For instance: @ModelAttribute("form"), then use `form.name` instead of `countryFormEdit.name` within the view
        if (result.hasErrors()) {
            model.addAttribute(form); // referenced as `countryFormEdit` in the view
            return "freemarker/countries/edit-form2";
        }

        final Country country = countryRepository.findById(id).orElseThrow();
        country.setName(form.getName());
        countryRepository.save(country);
        return "redirect:/countries";
    }

    // DELETE
    @DeleteMapping("/countries/{id}")
    @ResponseBody // otherwise I get template not found error
    public void deleteCountry(@PathVariable long id) throws Exception {
        if (id == 1 || id == 2) {
            throw new Exception("Not authorized!");
        }

        countryRepository.deleteById(id);
    }
}
