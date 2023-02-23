package com.av.dvdlibrary;

import com.av.dvdlibrary.controller.DvdLibraryController;
import com.av.dvdlibrary.dao.DvdLibraryDao;
import com.av.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.av.dvdlibrary.ui.DvdLibraryView;
import com.av.dvdlibrary.ui.UserIO;
import com.av.dvdlibrary.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {

        UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);

        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();

        DvdLibraryController controller = new DvdLibraryController(myDao, myView);

        controller.run();
    }
}
