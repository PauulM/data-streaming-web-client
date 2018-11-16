package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.TokenConsumer;
import datastreaming.webclient.dto.api.TokenDTO;
import datastreaming.webclient.dto.web.UserWebDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private TokenConsumer tokenConsumer;

    @GetMapping("/login")
    public String loginGet(Model model){
        model.addAttribute("userWebDTO", new UserWebDTO());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute UserWebDTO userWebDTO, Model model, RedirectAttributes redirectAttributes){
        try {
            TokenDTO tokenDTO = tokenConsumer.queryForToken(userWebDTO.getUserName(), userWebDTO.getPassword(), "password");
            model.addAttribute("tokenDTO", tokenDTO);
            return "success";
        }
        catch (HttpClientErrorException ex){
            redirectAttributes.addFlashAttribute("errorMessage","Login or password is incorrect");
            return "redirect:/login";
        }
    }
}