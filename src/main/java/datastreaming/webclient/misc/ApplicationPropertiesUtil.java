package datastreaming.webclient.misc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationPropertiesUtil {

    @Value("${api.domain}")
    private String apiDomain;

    @Value("${api.port}")
    private Integer apiPort;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Value("${api.scheme}")
    private String apiScheme;

    @Value("${client.name}")
    private String clientName;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${client.token-endpoint}")
    private String tokenEndpoint;

    public ApplicationPropertiesUtil() {
    }

    public String getApiDomain() {
        return apiDomain;
    }

    public Integer getApiPort() {
        return apiPort;
    }

    public String getApiPrefix() {
        return apiPrefix;
    }

    public String getApiScheme() {
        return apiScheme;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }
}
