package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.ArtistConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
public class ArtistController {

    private ArtistConsumer artistConsumer;

    @Autowired
    public ArtistController(ArtistConsumer artistConsumer) {
        this.artistConsumer = artistConsumer;
    }

    @GetMapping("/artist")
    public String artistDetailsGet(@RequestParam("id") Long artistId, Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        model.addAttribute("artist", artistConsumer.getArtistById(token, artistId));
        model.addAttribute("artistAlbums",
                artistConsumer.getArtistAlbums(token, artistId)
                        .stream()
                        .sorted()
                        .collect(Collectors.toList()));
        return "artist_details";
    }
}
