package datastreaming.webclient.consumer;

import datastreaming.webclient.dto.api.SongDTO;
import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SongConsumer extends AbstractApiConsumer {

    public SongConsumer(ApplicationPropertiesUtil applicationPropertiesUtil) {
        super(applicationPropertiesUtil);
    }

    public SongDTO getSongById(String token, Long id){
        HttpEntity<?> request = new HttpEntity<>("", buildBearerTokenAuthorizationHeader(token));
        ResponseEntity<SongDTO> responseEntity = restTemplate.exchange(
                baseUri + "/api/songs/" + id,
                HttpMethod.GET, request, SongDTO.class);
        return responseEntity.getBody();
    }

}
