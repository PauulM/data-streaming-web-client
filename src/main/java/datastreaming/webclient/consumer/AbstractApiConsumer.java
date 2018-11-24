package datastreaming.webclient.consumer;

import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public abstract class AbstractApiConsumer {

    protected final ApplicationPropertiesUtil applicationPropertiesUtil;

    protected final RestTemplate restTemplate;

    protected final String baseUri;

    @Autowired
    public AbstractApiConsumer(ApplicationPropertiesUtil applicationPropertiesUtil) {
        this.applicationPropertiesUtil = applicationPropertiesUtil;
        restTemplate = new RestTemplate();
        baseUri = prepareUriBuilder();
    }

    private String prepareUriBuilder(){
        return UriComponentsBuilder.newInstance()
                .scheme(applicationPropertiesUtil.getApiScheme())
                .host(applicationPropertiesUtil.getApiDomain())
                .port(applicationPropertiesUtil.getApiPort())
                .path(applicationPropertiesUtil.getApiPrefix())
                .build()
                .toUriString();
    }

    protected HttpHeaders buildBearerTokenAuthorizationHeader(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }

}
