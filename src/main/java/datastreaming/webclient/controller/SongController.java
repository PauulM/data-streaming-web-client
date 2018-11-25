package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.SongConsumer;
import datastreaming.webclient.consumer.StreamingConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SongController {

    private SongConsumer songConsumer;

    private StreamingConsumer streamingConsumer;

    @Autowired
    public SongController(SongConsumer songConsumer, StreamingConsumer streamingConsumer) {
        this.songConsumer = songConsumer;
        this.streamingConsumer = streamingConsumer;
    }

    @GetMapping("/song")
    public String songDetailsGet(@RequestParam("id") Long songId, Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        model.addAttribute("song",songConsumer.getSongById(token, songId));
        model.addAttribute("songManifestUri", streamingConsumer.buildSongManifestUri(songId));
        model.addAttribute("token", token);
        return "song_details";
    }

}
