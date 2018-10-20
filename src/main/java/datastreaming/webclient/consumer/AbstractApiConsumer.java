package datastreaming.webclient.consumer;

import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class AbstractApiConsumer {

    @Autowired
    private ApplicationPropertiesUtil applicationPropertiesUtil;

    protected RestTemplate restTemplate;

    protected UriBuilder uriBuilder;

    public AbstractApiConsumer() {
        restTemplate = new RestTemplate();
        uriBuilder = prepareUriBuilder();
    }

    private UriBuilder prepareUriBuilder(){
        return UriComponentsBuilder.newInstance()
                .scheme(applicationPropertiesUtil.getApiScheme())
                .host(applicationPropertiesUtil.getApiDomain())
                .port(applicationPropertiesUtil.getApiPort())
                .path(applicationPropertiesUtil.getApiPrefix());
    }
}
