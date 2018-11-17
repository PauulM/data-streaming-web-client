package datastreaming.webclient.consumer;

import datastreaming.webclient.dto.api.SongDTO;
import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
                baseUri + "/api/song/search?query=" + query,
                HttpMethod.GET, request, SongDTO[].class);
        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }
}
