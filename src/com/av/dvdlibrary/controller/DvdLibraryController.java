package com.av.dvdlibrary.controller;

import com.av.dvdlibrary.dao.DvdLibraryDao;
import com.av.dvdlibrary.dao.DvdLibraryDaoException;
import com.av.dvdlibrary.dto.Dvd;
import com.av.dvdlibrary.ui.DvdLibraryView;

import java.util.List;

public class DvdLibraryController {

    private DvdLibraryDao dao;
    private DvdLibraryView view;


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
                        listDvds();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
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
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getDvdId(), newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getDvd(dvdId);
        view.displayDvd(dvd);
    }

    private void removeStudent() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd removedDvd = dao.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }


}
