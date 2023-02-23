package com.av.dvdlibrary.dto;

public class Dvd {
    private String dvdId;
    private String title;
    private String releaseData;
    private String MpaRating;
    private String directorName;
    private String studio;
    private String userRating;

    public Dvd(String dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseData() {
        return releaseData;
    }

    public void setReleaseData(String releaseData) {
        this.releaseData = releaseData;
    }

    public String getMpaRating() {
        return MpaRating;
    }

    public void setMpaRating(String mpaRating) {
        MpaRating = mpaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
}
