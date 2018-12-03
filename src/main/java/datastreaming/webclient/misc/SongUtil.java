package datastreaming.webclient.misc;

import datastreaming.webclient.dto.api.SongDTO;

import java.util.List;

public class SongUtil {

    public static void convertSecondsToMinsAndSecs(SongDTO songDTO){
        if(songDTO.getLength() == null)
            return;
        Integer totalLengthSeconds = Integer.parseInt(songDTO.getLength());
        Integer minutes = totalLengthSeconds / 60;
        Integer seconds = totalLengthSeconds % 60;
        songDTO.setLength(minutes + ":" + seconds);
    }

    public static void convertMinsAndSecsToSeconds(SongDTO songDTO){
        if(songDTO.getLength() == null)
            return;
        try{
            Integer minutes = Integer.parseInt(songDTO.getLength().split(":")[0]);
            Integer seconds = Integer.parseInt(songDTO.getLength().split(":")[1]);
            songDTO.setLength(minutes * 60 + seconds + "");
        }
        catch (NumberFormatException | IndexOutOfBoundsException ex){
            return;
        }
    }

    public static void convertSecondsToMinsAndSecs(List<SongDTO> songs){
        for (SongDTO song : songs){
            convertSecondsToMinsAndSecs(song);
        }
    }

    public static void convertMinsAndSecsToSeconds(List<SongDTO> songs){
        for (SongDTO song : songs){
            convertMinsAndSecsToSeconds(song);
        }
    }

}
