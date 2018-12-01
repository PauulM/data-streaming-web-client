package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.AlbumConsumer;
import datastreaming.webclient.consumer.SongConsumer;
import datastreaming.webclient.consumer.StreamingConsumer;
import datastreaming.webclient.dto.api.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SongController {

    private SongConsumer songConsumer;

    private AlbumConsumer albumConsumer;

    private StreamingConsumer streamingConsumer;

    @Autowired
    public SongController(SongConsumer songConsumer, AlbumConsumer albumConsumer, StreamingConsumer streamingConsumer) {
        this.songConsumer = songConsumer;
        this.albumConsumer = albumConsumer;
        this.streamingConsumer = streamingConsumer;
    }

    @GetMapping("/song")
    public String songDetailsGet(@RequestParam("id") Long songId, Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        model.addAttribute("song", getSongWithAlbumArtwork(token, songId));
        model.addAttribute("songManifestUri", streamingConsumer.buildSongManifestUri(songId));
        model.addAttribute("token", token);
        return "song_details";
    }

    private SongDTO getSongWithAlbumArtwork(String token, Long songId){
        SongDTO song = songConsumer.getSongById(token, songId);
        song.getAlbumDTO().setImageEncoded(albumConsumer.getAlbumArtworkEncoded(token, song.getAlbumDTO().getId()));
        return song;
    }

}
