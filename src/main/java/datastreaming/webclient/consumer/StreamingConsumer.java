package datastreaming.webclient.consumer;

import datastreaming.webclient.misc.ApplicationPropertiesUtil;
import org.springframework.stereotype.Component;

@Component
public class StreamingConsumer extends AbstractApiConsumer {

    public StreamingConsumer(ApplicationPropertiesUtil applicationPropertiesUtil) {
        super(applicationPropertiesUtil);
    }

    public String buildSongManifestUri(Long songId){
        StringBuilder builder = new StringBuilder();
        builder.append(baseUri);
        builder.append("/api/streaming/music/").append(songId).append("/manifest.m3u8");
        return builder.toString();
    }

}
