package com.av.dvdlibrary.dao;

import com.av.dvdlibrary.dto.Dvd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    public static final String DVD_LIBRARY = "library.txt";
    public static final String DELIMITER = "::";

    private Map<String, Dvd> dvds = new HashMap<>();

    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) {
        Dvd prevDvd = dvds.put(dvdId, dvd);
        return prevDvd;
    }

    @Override
    public List<Dvd> getAllDvds() {
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String dvdId) {
        return dvds.get(dvdId);
    }

    @Override
    public Dvd removeDvd(String dvdId) {
        Dvd removeDvd = dvds.remove(dvdId);
        return removeDvd;
    }



    private Dvd unmarshallDvd(String dvdAsText){
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // dvdId::title::releaseDate::mpaRating::directorName::studio::userRating
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in dvdTokens.
        // Which should look like this:
        // ______________________________________
        // |     |    |           |         |            |      |
        // dvdId|title|releaseDate|mpaRating|directorName|studio|userRating
        // |     |    |           |         |            |      |
        // --------------------------------------
        //  [0]    [1]   [2]         [3]        [4]         [5]      [6]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the dvd Id is in index 0 of the array.
        String dvdId = dvdTokens[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        Dvd dvdFromFile = new Dvd(dvdId);

        // However, there are 6 remaining tokens that need to be set into the
        // new DVD object. Do this manually by using the appropriate setters.

        // Index 1 - Title
        dvdFromFile.setTitle(dvdTokens[1]);

        // Index 2 - Release Date
        dvdFromFile.setTitle(dvdTokens[2]);

        // Index 3 - MPA rating
        dvdFromFile.setTitle(dvdTokens[3]);

        // Index 4 - Director's name
        dvdFromFile.setTitle(dvdTokens[4]);

        // Index 5 - Studio
        dvdFromFile.setTitle(dvdTokens[5]);

        // Index 6 - User rating
        dvdFromFile.setTitle(dvdTokens[6]);

        // We have now created a student! Return it!
        return dvdFromFile;
    }


}
