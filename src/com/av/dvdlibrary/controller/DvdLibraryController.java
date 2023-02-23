package com.av.dvdlibrary.controller;

import com.av.dvdlibrary.ui.DvdLibraryView;
import com.av.dvdlibrary.ui.UserIO;
import com.av.dvdlibrary.ui.UserIOConsoleImpl;

public class DvdLibraryController {

    private DvdLibraryView view = new DvdLibraryView();
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while(keepGoing){
            menuSelection = getMenuSelection();

            switch (menuSelection){
                case 1:
                    io.print("ADD DVD");
                    break;
                case 2:
                    io.print("LISTING ALL DVD");
                    break;
                case 3:
                    io.print("VIEW DVD");
                    break;
                case 4:
                    io.print("REMOVE DVD");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }
        }
        io.print("Good bye");
    }


    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
}
