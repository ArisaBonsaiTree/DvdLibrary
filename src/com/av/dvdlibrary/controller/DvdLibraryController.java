package com.av.dvdlibrary.controller;

import com.av.dvdlibrary.dao.DvdLibraryDao;
import com.av.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.av.dvdlibrary.dto.Dvd;
import com.av.dvdlibrary.ui.DvdLibraryView;
import com.av.dvdlibrary.ui.UserIO;
import com.av.dvdlibrary.ui.UserIOConsoleImpl;

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

    private void viewDvd(){
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getDvd(dvdId);
        view.displayDvd(dvd);
    }

    private void removeStudent(){
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd removedDvd = dao.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }

    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }

    private void exitMessage(){
        view.displayExitBanner();
    }


}
