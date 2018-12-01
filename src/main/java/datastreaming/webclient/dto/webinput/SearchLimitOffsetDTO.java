package datastreaming.webclient.dto.webinput;

public class SearchLimitOffsetDTO {

    private Integer artistLimit;

    private Integer artistOffset;

    private Integer albumLimit;

    private Integer albumOffset;

    private Integer songLimit;

    private Integer songOffset;

    public SearchLimitOffsetDTO() {
        artistLimit = 20;
        artistOffset = 0;
        albumLimit = 20;
        albumOffset = 0;
        songLimit = 20;
        songOffset = 0;
    }

    public Integer setArtistsNextPageValues(){
        artistOffset += artistLimit;
        return artistOffset;
    }

    public Integer setArtistsPreviousPageValues(){
        artistOffset -= artistLimit;
        return artistOffset;
    }

    public Integer setAlbumNextPageValues(){
        albumOffset += albumLimit;
        return albumOffset;
    }

    public Integer setAlbumPreviousPageValues(){
        albumOffset -= albumLimit;
        return albumOffset;
    }

    public Integer setSongNextPageValues(){
        songOffset += songLimit;
        return songOffset;
    }

    public Integer setSongPreviousPageValues(){
        songOffset -= songLimit;
        return songOffset;
    }

    public Integer getArtistLimit() {
        return artistLimit;
    }

    public void setArtistLimit(Integer artistLimit) {
        this.artistLimit = artistLimit;
    }

    public Integer getArtistOffset() {
        return artistOffset;
    }

    public void setArtistOffset(Integer artistOffset) {
        this.artistOffset = artistOffset;
    }

    public Integer getAlbumLimit() {
        return albumLimit;
    }

    public void setAlbumLimit(Integer albumLimit) {
        this.albumLimit = albumLimit;
    }

    public Integer getAlbumOffset() {
        return albumOffset;
    }

    public void setAlbumOffset(Integer albumOffset) {
        this.albumOffset = albumOffset;
    }

    public Integer getSongLimit() {
        return songLimit;
    }

    public void setSongLimit(Integer songLimit) {
        this.songLimit = songLimit;
    }

    public Integer getSongOffset() {
        return songOffset;
    }

    public void setSongOffset(Integer songOffset) {
        this.songOffset = songOffset;
    }

}
