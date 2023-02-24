package com.av.dvdlibrary.ui;

import com.av.dvdlibrary.dto.Dvd;

import java.util.List;

public class DvdLibraryView {

    private static final int LOWEST_NUMBER = 1;
    private static final int HIGHEST_NUMBER = 7;

    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Add a DVD to the collection");
        io.print("2. Remove a DVD from the collection");
        io.print("3. Edit information for an existing DVD in the collection");
        io.print("4. List the DVDs in the collection");
        io.print("5. Display information for a particular DVD (requires ID)");
        io.print("6. Search for DVD by title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices", LOWEST_NUMBER, HIGHEST_NUMBER);
    }

    public Dvd getNewDvdInfo(){
        String dvdId = io.readString("Please enter dvd id:");
        String title = io.readString("Please enter title:");
        String releaseDate = io.readString("Please enter release date:");
        String mpaRating = io.readString("Please enter mpa rating:");
        String directorName = io.readString("Please enter director name:");
        String studio = io.readString("Please enter Studio name:");
        String userRating = io.readString("Please enter rating or note:");

        Dvd currentDvd = new Dvd(dvdId);

        currentDvd.setTitle(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaRating(mpaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);

        return currentDvd;
    }

    public Dvd editDvdInfo(Dvd oldDvd){
        if(oldDvd == null) return null;
        io.print("Old title: " + oldDvd.getTitle());
        String title = io.readString("Please enter new title:");

        io.print("Old release date: " + oldDvd.getReleaseDate());
        String releaseDate = io.readString("Please enter new release date:");

        io.print("Old MPA Rating: " + oldDvd.getMpaRating());
        String mpaRating = io.readString("Please enter new mpa rating:");

        io.print("Old director name: " + oldDvd.getDirectorName());
        String directorName = io.readString("Please enter new director name:");

        io.print("Old studio name: " + oldDvd.getStudio());
        String studio = io.readString("Please enter new Studio name:");

        io.print("Old user rating: " + oldDvd.getUserRating());
        String userRating = io.readString("Please enter new rating or note:");


        oldDvd.setTitle(title);
        oldDvd.setReleaseDate(releaseDate);
        oldDvd.setMpaRating(mpaRating);
        oldDvd.setDirectorName(directorName);
        oldDvd.setStudio(studio);
        oldDvd.setUserRating(userRating);

        return oldDvd;
    }

    public void displayCreateDvdBanner(){
        io.print("=== Create DVD ===");
    }

    public void displayEditDvdBanner(){
        io.print("=== Edit DVD ===");
    }

    public void displayCreateSuccessBanner(){
        io.readString("DVD successfully created. Please hit enter to continue");
    }

    public void displayEditSuccessBanner(){
        io.readString("DVD successfully edited. Please hit enter to continue");
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for(Dvd currentDvd: dvdList){
            String dvdInfo = String.format("#%s : %s %s %s %s %s %s", currentDvd.getDvdId(), currentDvd.getTitle(), currentDvd.getReleaseDate(),
            currentDvd.getMpaRating(), currentDvd.getDirectorName(), currentDvd.getStudio(), currentDvd.getUserRating());

            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDvdBanner(){
        io.print("=== Display DVD ===");
    }



    public String getDvdIdChoice(){
        return io.readString("Please enter the DVD Id");
    }

    public String getDvdTitleChoice(){
        return io.readString("Please enter the DVD title");
    }

    public void displayDvd(Dvd dvd){
        if(dvd != null){
            io.print("Dvd ID: " + dvd.getDvdId());
            io.print("DvD Title: " + dvd.getTitle());
            io.print("Dvd Release Date: " + dvd.getReleaseDate());
            io.print("Dvd Mpa Rating: " + dvd.getMpaRating());
            io.print("Dvd Director Name: " + dvd.getDirectorName());
            io.print("Dvd Studio: " + dvd.getStudio());
            io.print("Dvd User Rating: " + dvd.getUserRating());
        }
        else{
            io.print("No such dvd");
        }

        io.readString("Please hit enter to continue");
    }

    public void displayRemoveDvdBanner(){
        io.print("=== Remove Dvd ===");
    }

    public void displayRemoveResult(Dvd dvdRecord){
        if(dvdRecord != null){
            io.print("Dvd successfully removed");
        } else{
            io.print("No such dvd");
        }

        io.readString("Please hit enter to continue");
    }

    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg){
        io.print("== ERROR ===");
        io.print(errorMsg);
    }
}
