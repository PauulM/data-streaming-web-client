package datastreaming.webclient.consumer;

import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.stereotype.Component;

@Component
public class SongConsumer extends AbstractApiConsumer {

    public SongConsumer(ApplicationPropertiesUtil applicationPropertiesUtil) {
        super(applicationPropertiesUtil);
    }

}
