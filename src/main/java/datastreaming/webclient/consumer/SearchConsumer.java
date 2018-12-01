package datastreaming.webclient.consumer;

import datastreaming.webclient.dto.api.AlbumDTO;
import datastreaming.webclient.dto.api.ArtistDTO;
import datastreaming.webclient.dto.api.SearchDTO;
import datastreaming.webclient.dto.api.SongDTO;
import datastreaming.webclient.dto.webinput.SearchLimitOffsetDTO;
import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchConsumer extends AbstractApiConsumer {

    public SearchConsumer(ApplicationPropertiesUtil applicationPropertiesUtil) {
        super(applicationPropertiesUtil);
    }

    public List<SongDTO> searchSongs(String token, String query){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<SongDTO[]> responseEntity = restTemplate.exchange(
                baseUri + "/api/songs/search?query=" + query,
                HttpMethod.GET, request, SongDTO[].class);
        //return multiplyElementsInList(Arrays.asList(responseEntity.getBody()),20);
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }

    public List<AlbumDTO> searchAlbums(String token, String query){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<AlbumDTO[]> responseEntity = restTemplate.exchange(
                baseUri + "/api/albums/search?query=" + query,
                HttpMethod.GET, request, AlbumDTO[].class);
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }

    public List<ArtistDTO> searchArtists(String token, String query){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<ArtistDTO[]> responseEntity = restTemplate.exchange(
                baseUri + "/api/artists/search?query=" + query,
                HttpMethod.GET, request, ArtistDTO[].class);
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }

    public SearchDTO searchEverything(String token, String query, SearchLimitOffsetDTO searchLimitOffsetDTO){
        HttpHeaders httpHeaders = buildBearerTokenAuthorizationHeader(token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(searchLimitOffsetDTO, httpHeaders);
        ResponseEntity<SearchDTO> responseEntity = restTemplate.exchange(
                baseUri + "/api/search?query=" + query,
                HttpMethod.GET, request, SearchDTO.class);
        return responseEntity.getBody();
    }

    private List<SongDTO> multiplyElementsInList(List<SongDTO> songs, int times){
        List<SongDTO> newList = new ArrayList<>();
        for (SongDTO song : songs){
            for (int i=0;i<times;i++){
                newList.add(song);
            }
        }
        return newList;
    }
}
