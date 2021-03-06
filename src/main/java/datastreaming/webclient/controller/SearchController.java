package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.AlbumConsumer;
import datastreaming.webclient.consumer.SearchConsumer;
import datastreaming.webclient.dto.api.AlbumDTO;
import datastreaming.webclient.dto.api.SearchDTO;
import datastreaming.webclient.dto.api.SongDTO;
import datastreaming.webclient.dto.webinput.SearchLimitOffsetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchConsumer searchConsumer;

    @Autowired
    private AlbumConsumer albumConsumer;

    @GetMapping("/search")
    public String searchEverythingGet(@RequestParam(value = "query") String query, Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        SearchLimitOffsetDTO searchLimitOffsetDTO = new SearchLimitOffsetDTO();
        SearchDTO searchDTO = searchConsumer.searchEverything(token, query, searchLimitOffsetDTO);
        model.addAttribute("artists", searchDTO.getArtists());
        model.addAttribute("albums", getAlbumsWithArtworks(token, searchDTO.getAlbums()));
        model.addAttribute("songs", getSongsWithAlbumArtworks(token, searchDTO.getSongs()));
        model.addAttribute("searchLimitOffset", searchLimitOffsetDTO);
        return "search_results";
    }

    private List<AlbumDTO> getAlbumsWithArtworks(String token, List<AlbumDTO> albums) {
        for(AlbumDTO album : albums){
            album.setImageEncoded(albumConsumer.getAlbumArtworkEncoded(token, album.getId()));
        }
        return albums;
    }

    private List<SongDTO> getSongsWithAlbumArtworks(String token, List<SongDTO> songs) {
        for(SongDTO song : songs){
            song.getAlbumDTO().setImageEncoded(albumConsumer.getAlbumArtworkEncoded(token, song.getAlbumDTO().getId()));
        }
        return songs;
    }
}
