package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.AlbumConsumer;
import datastreaming.webclient.dto.api.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
public class AlbumController {

    private AlbumConsumer albumConsumer;

    @Autowired
    public AlbumController(AlbumConsumer albumConsumer) {
        this.albumConsumer = albumConsumer;
    }

    @GetMapping("/album")
    public String albumDetailsGet(@RequestParam("id") Long albumId, Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        model.addAttribute("album", albumConsumer.getAlbumById(token, albumId));
        model.addAttribute("albumSongs", albumConsumer.getAlbumSongs(token, albumId)
            .stream()
            .sorted(Comparator.comparing(SongDTO::getSongNo))
            .collect(Collectors.toList()));
        return "album_details";
    }
}
