package com.av.dvdlibrary.controller;

import com.av.dvdlibrary.dao.DvdLibraryDao;
import com.av.dvdlibrary.dao.DvdLibraryDaoException;
import com.av.dvdlibrary.dto.Dvd;
import com.av.dvdlibrary.ui.DvdLibraryView;
import com.av.dvdlibrary.ui.UserIO;
import com.av.dvdlibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class DvdLibraryController {

    private DvdLibraryDao dao;
    private DvdLibraryView view;

    private UserIO io = new UserIOConsoleImpl();

    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        io.print("Editing Info about Dvd");
                        break;
                    case 4:
                        listAllDvds();
                        break;
                    case 5:
                        viewDvdById();
                        break;
                    case 6:
                        viewDvdByTitle();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }

            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }


    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();

        Dvd newDvd = view.getNewDvdInfo(); // gather info for the dvd and put it in this dvd object
        dao.addDvd(newDvd.getDvdId(), newDvd);

        view.displayCreateSuccessBanner();
    }

    private void listAllDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();

        List<Dvd> dvdList = dao.getAllDvds();

        view.displayDvdList(dvdList);
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void viewDvdById() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getDvdById(dvdId);
        view.displayDvd(dvd);
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd removedDvd = dao.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }

    private void viewDvdByTitle() throws DvdLibraryDaoException{
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvdByTitle(dvdTitle);
        view.displayDvd(dvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
