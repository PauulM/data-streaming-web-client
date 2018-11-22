package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.SongConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SongController {

    private SongConsumer songConsumer;

    @Autowired
    public SongController(SongConsumer songConsumer) {
        this.songConsumer = songConsumer;
    }

    @GetMapping("/song")
    public String songDetailsGet(@RequestParam("id") Long songId, Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        model.addAttribute("song",songConsumer.getSongById(token, songId));
        return "song_details";
    }

}
