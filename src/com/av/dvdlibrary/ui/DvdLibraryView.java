package com.av.dvdlibrary.ui;

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
}
