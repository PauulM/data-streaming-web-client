package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.ArtistConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private ArtistConsumer artistConsumer;

    @Autowired
    public HomeController(ArtistConsumer artistConsumer) {
        this.artistConsumer = artistConsumer;
    }

    @GetMapping("/home")
    public String homePageGet(Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        model.addAttribute("artists", artistConsumer.getArtists(token));
        return "home";
    }

}
