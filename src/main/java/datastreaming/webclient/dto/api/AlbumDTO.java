package datastreaming.webclient.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumDTO implements Comparable<AlbumDTO>{

    private Long id;

    @JsonProperty("artist")
    private ArtistDTO artistDTO;

    private String name;

    private String albumYear;

    private String publisher;

    private String imageEncoded;

    @Override
    public int compareTo(AlbumDTO o) {
        return albumYear.compareTo(o.albumYear);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArtistDTO getArtistDTO() {
        return artistDTO;
    }

    public void setArtistDTO(ArtistDTO artistDTO) {
        this.artistDTO = artistDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumYear() {
        return albumYear;
    }

    public void setAlbumYear(String albumYear) {
        this.albumYear = albumYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageEncoded() {
        return imageEncoded;
    }

    public void setImageEncoded(String imageEncoded) {
        this.imageEncoded = imageEncoded;
    }
}
