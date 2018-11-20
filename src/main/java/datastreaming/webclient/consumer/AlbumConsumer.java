package datastreaming.webclient.consumer;

import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.stereotype.Component;

@Component
public class AlbumConsumer extends AbstractApiConsumer{

    public AlbumConsumer(ApplicationPropertiesUtil applicationPropertiesUtil) {
        super(applicationPropertiesUtil);
    }

}
