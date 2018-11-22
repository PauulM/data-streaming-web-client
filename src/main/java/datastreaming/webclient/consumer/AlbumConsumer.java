package datastreaming.webclient.consumer;

import datastreaming.webclient.dto.api.AlbumDTO;
import datastreaming.webclient.dto.api.SongDTO;
import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AlbumConsumer extends AbstractApiConsumer{

    public AlbumConsumer(ApplicationPropertiesUtil applicationPropertiesUtil) {
        super(applicationPropertiesUtil);
    }

    public AlbumDTO getAlbumById(String token, Long id){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<AlbumDTO> responseEntity = restTemplate.exchange(
                baseUri + "/api/album/" + id,
                HttpMethod.GET, request, AlbumDTO.class);
        return responseEntity.getBody();
    }

    public List<SongDTO> getAlbumSongs(String token, Long albumId){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<SongDTO[]> responseEntity = restTemplate.exchange(
                baseUri + "/api/album/" + albumId + "/songs",
                HttpMethod.GET, request, SongDTO[].class);
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }
}