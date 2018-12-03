package datastreaming.webclient.consumer;

import datastreaming.webclient.dto.api.AlbumDTO;
import datastreaming.webclient.dto.api.SongDTO;
import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import datastreaming.webclient.misc.SongUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
public class AlbumConsumer extends AbstractApiConsumer{

    public AlbumConsumer(ApplicationPropertiesUtil applicationPropertiesUtil) {
        super(applicationPropertiesUtil);
    }

    public AlbumDTO getAlbumById(String token, Long id){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<AlbumDTO> responseEntity = restTemplate.exchange(
                baseUri + "/api/albums/" + id,
                HttpMethod.GET, request, AlbumDTO.class);
        AlbumDTO albumDTO = responseEntity.getBody();
        albumDTO.setImageEncoded(getAlbumArtworkEncoded(token, id));
        return albumDTO;
    }

    public List<SongDTO> getAlbumSongs(String token, Long albumId){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<SongDTO[]> responseEntity = restTemplate.exchange(
                baseUri + "/api/albums/" + albumId + "/songs",
                HttpMethod.GET, request, SongDTO[].class);
        List<SongDTO> songs = new ArrayList<>(Arrays.asList(responseEntity.getBody()));
        SongUtil.convertSecondsToMinsAndSecs(songs);
        return songs;
    }

    public String getAlbumArtworkEncoded(String token, Long albumId){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                baseUri + "/api/albums/" + albumId + "/artwork",
                HttpMethod.GET, request, byte[].class);
        return responseEntity.getBody() != null ?
                Base64.getEncoder().encodeToString(responseEntity.getBody()) : null;
    }
}
