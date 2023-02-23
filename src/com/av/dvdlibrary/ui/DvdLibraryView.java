package com.av.dvdlibrary.ui;

import com.av.dvdlibrary.dto.Dvd;

import java.util.List;

public class DvdLibraryView {
    private UserIO io = new UserIOConsoleImpl();

    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Add a DVD to the collection");
        io.print("2. List all Dvd in the collection");
        io.print("3. View a Dvd");
        io.print("4. Remove a DVD");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices", 1, 5);
    }

    public Dvd getNewDvdInfo(){
        String dvdId = io.readString("Please enter dvd id");
        String title = io.readString("Please enter title");
        String releaseDate = io.readString("Please enter release data");
        String mpaRating = io.readString("Please enter mpa rating");
        String directorName = io.readString("Please enter director name");
        String studio = io.readString("Please enter Studio name");
        String userRating = io.readString("Please enter rating or note");

        Dvd currentDvd = new Dvd(dvdId);
        currentDvd.setTitle(title);
        currentDvd.setReleaseData(releaseDate);
        currentDvd.setMpaRating(mpaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);

        return currentDvd;
    }

    public void displayCreateDvdBanner(){
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner(){
        io.readString("Student successfulyl created. Please hit enter to continue");
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for(Dvd currentDvd: dvdList){
            String dvdInfo = String.format("#%s : %s %s %s %s %s %s", currentDvd.getDvdId(), currentDvd.getTitle(), currentDvd.getReleaseData(),
            currentDvd.getMpaRating(), currentDvd.getDirectorName(), currentDvd.getStudio(), currentDvd.getUserRating());

            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
}
