package datastreaming.webclient.consumer;

import datastreaming.webclient.dto.api.AlbumDTO;
import datastreaming.webclient.dto.api.ArtistDTO;
import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ArtistConsumer extends AbstractApiConsumer {

    public ArtistConsumer(ApplicationPropertiesUtil applicationPropertiesUtil) {
        super(applicationPropertiesUtil);
    }

    public ArtistDTO getArtistById(String token, Long id){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<ArtistDTO> responseEntity = restTemplate.exchange(
                baseUri + "/api/artists/" + id,
                HttpMethod.GET, request, ArtistDTO.class);
        return responseEntity.getBody();
    }

    public List<AlbumDTO> getArtistAlbums(String token, Long artistId){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<AlbumDTO[]> responseEntity = restTemplate.exchange(
                baseUri + "/api/artists/" + artistId + "/albums",
                HttpMethod.GET, request, AlbumDTO[].class);
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
        //return multiplyElementsInList(Arrays.asList(responseEntity.getBody()),20);
    }

    public List<ArtistDTO> getArtists(String token){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<ArtistDTO[]> responseEntity = restTemplate.exchange(
                baseUri + "/api/artists/",
                HttpMethod.GET, request, ArtistDTO[].class);
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }

    private List<AlbumDTO> multiplyElementsInList(List<AlbumDTO> list, int times){
        List<AlbumDTO> newList = new ArrayList<>();
        for (AlbumDTO el : list){
            for (int i=0;i<times;i++){
                newList.add(el);
            }
        }
        return newList;
    }
}
