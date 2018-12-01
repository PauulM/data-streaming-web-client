package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.AlbumConsumer;
import datastreaming.webclient.consumer.ArtistConsumer;
import datastreaming.webclient.dto.api.AlbumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArtistController {

    private ArtistConsumer artistConsumer;

    private AlbumConsumer albumConsumer;

    @Autowired
    public ArtistController(ArtistConsumer artistConsumer, AlbumConsumer albumConsumer) {
        this.artistConsumer = artistConsumer;
        this.albumConsumer = albumConsumer;
    }

    @GetMapping("/artist")
    public String artistDetailsGet(@RequestParam("id") Long artistId, Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        model.addAttribute("artist", artistConsumer.getArtistById(token, artistId));
        model.addAttribute("artistAlbums",getArtistAlbumsWithArtworks(token, artistId));
        return "artist_details";
    }

    private List<AlbumDTO> getArtistAlbumsWithArtworks(String token, Long artistId){
        List<AlbumDTO> artistAlbums = artistConsumer.getArtistAlbums(token, artistId)
                .stream()
                .sorted()
                .collect(Collectors.toList());
        for(AlbumDTO album : artistAlbums){
            album.setImageEncoded(albumConsumer.getAlbumArtworkEncoded(token, album.getId()));
        }
        return artistAlbums;
    }

}
