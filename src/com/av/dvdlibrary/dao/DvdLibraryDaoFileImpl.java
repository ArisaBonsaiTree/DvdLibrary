package com.av.dvdlibrary.dao;

import com.av.dvdlibrary.dto.Dvd;

import java.io.*;
import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    public static final String DVD_LIBRARY = "library.txt";
    public static final String DELIMITER = "::";

    private Map<String, Dvd> dvds = new HashMap<>();

    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException{
        loadLibrary();
        Dvd newDvd = dvds.put(dvdId, dvd);
        writeRoster();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException{
        loadLibrary();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public Dvd getDvdById(String dvdId) throws DvdLibraryDaoException{
        loadLibrary();
        return dvds.get(dvdId);
    }

    @Override
    public Dvd getDvdByTitle(String dvdTitle) throws DvdLibraryDaoException {
        loadLibrary();
        return iterateOverMapAndFindTitle(dvdTitle);
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryDaoException{
        loadLibrary();
        Dvd removedDvd = dvds.remove(dvdId);
        writeRoster();
        return removedDvd;
    }

    private Dvd iterateOverMapAndFindTitle(String dvdTitle){
        String lowerCasedTitle = dvdTitle.toLowerCase();
        System.out.printf("We have passed %s\n", lowerCasedTitle);
        System.out.println("DVD has " + dvds.size());


        for(String key:dvds.keySet()){
            System.out.println(dvds.get(key).getTitle());
        }
        return null; // Didn't find the DVD
    }

    private Dvd unmarshallDvd(String dvdAsText) {
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


    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY)));
        }
        catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("-_- Could not load roster data into memory.", e);
        }

        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Dvd currentDvd;

        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();

            // unmarshall the line into a Student
            currentDvd = unmarshallDvd(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            dvds.put(currentDvd.getDvdId(), currentDvd);
        }
        // close scanner
        scanner.close();
    }


    private String marshallDvd(Dvd aDvd) {
        // We need to turn a Dvd object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer.

        // Start with the dvd id, since that's supposed to be first.
        String dvdAsText = aDvd.getDvdId() + DELIMITER;

        // Now append the rest of the values

        dvdAsText += aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRating();

        // We have now turned a student to text! Return it!
        return dvdAsText;
    }


    /**
     * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
     * for file format.
     *
     * @throws DvdLibraryDaoException if an error occurs writing to the file
     */
    private void writeRoster() throws DvdLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save dvd data.", e);
        }

        // Write out the Dvd objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of dvd and iterate over them but we've
        // already created a method that gets a List of Dvd so
        // we'll reuse it.

        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();

        for (Dvd currentDvd : dvdList) {
            // turn a Student into a String
            dvdAsText = marshallDvd(currentDvd);

            // write the Student object to the file
            out.println(dvdAsText);

            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
