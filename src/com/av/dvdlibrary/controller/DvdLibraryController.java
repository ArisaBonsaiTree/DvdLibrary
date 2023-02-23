package com.av.dvdlibrary.controller;

import com.av.dvdlibrary.dao.DvdLibraryDao;
import com.av.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.av.dvdlibrary.dto.Dvd;
import com.av.dvdlibrary.ui.DvdLibraryView;
import com.av.dvdlibrary.ui.UserIO;
import com.av.dvdlibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class DvdLibraryController {

    private DvdLibraryView view = new DvdLibraryView();
    private UserIO io = new UserIOConsoleImpl();
    private DvdLibraryDao dao = new DvdLibraryDaoFileImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        while (keepGoing) {
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    createDvd();
                    break;
                case 2:
                    listDvds();
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


    private void createDvd() {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getDvdId(), newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDvds() {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }


}
