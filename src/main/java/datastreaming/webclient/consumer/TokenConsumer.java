package datastreaming.webclient.consumer;

import datastreaming.webclient.dto.api.TokenDTO;
import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;

@Component
public class TokenConsumer {

    @Autowired
    private ApplicationPropertiesUtil applicationPropertiesUtil;

    public TokenDTO queryForToken(String userName, String password, String grantType){
        HttpEntity<?> request = new HttpEntity<Object>(buildBodyParametersMap(userName,password,grantType),
                buildBasicAuthorizationHeaders());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TokenDTO> responseEntity = restTemplate.exchange(buildTokenUriString(), HttpMethod.POST, request, TokenDTO.class);
        TokenDTO token = responseEntity.getBody();
        token.calculateExpirationDate();
        return token;
    }

    private MultiValueMap<String, String> buildBodyParametersMap(String userName, String password, String grantType){
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grantType);
        requestBody.add("username", userName);
        requestBody.add("password", password);
        requestBody.add("client_id", applicationPropertiesUtil.getClientName());
        return requestBody;
    }

    private String encodeClientNameAndSecret(String clientName, String clientSecret){
        return Base64.getEncoder().encodeToString((clientName + ":" + clientSecret).getBytes());
    }

    private HttpHeaders buildBasicAuthorizationHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Authorization", "Basic " + encodeClientNameAndSecret(
                applicationPropertiesUtil.getClientName(), applicationPropertiesUtil.getClientSecret()));
        return headers;
    }

    private String buildTokenUriString(){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(applicationPropertiesUtil.getApiScheme())
                .host(applicationPropertiesUtil.getApiDomain())
                .port(applicationPropertiesUtil.getApiPort())
                .path(applicationPropertiesUtil.getApiPrefix())
                .path(applicationPropertiesUtil.getTokenEndpoint())
                .build();
        return uriComponents.toUriString();
    }
}
