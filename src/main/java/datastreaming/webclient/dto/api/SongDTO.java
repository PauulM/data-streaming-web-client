package datastreaming.webclient.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class SongDTO {

    private Long id;

    @JsonProperty("album")
    private AlbumDTO albumDTO;

    private String name;

    private String filePath;

    private Integer songNo;

    private String length;

    private String quality;

    private String genres;

    private BigDecimal size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlbumDTO getAlbumDTO() {
        return albumDTO;
    }

    public void setAlbumDTO(AlbumDTO albumDTO) {
        this.albumDTO = albumDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSongNo() {
        return songNo;
    }

    public void setSongNo(Integer songNo) {
        this.songNo = songNo;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }
}
