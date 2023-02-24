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
        placeFileDataIntoHashMap();

        Dvd newDvd = dvds.put(dvdId, dvd);

        writeMapIntoFile();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException{
        placeFileDataIntoHashMap();
        return new ArrayList(dvds.values());
    }

    @Override
    public Dvd getDvdById(String dvdId) throws DvdLibraryDaoException{
        placeFileDataIntoHashMap();
        return dvds.get(dvdId);
    }

    @Override
    public Dvd getDvdByTitle(String dvdTitle) throws DvdLibraryDaoException {
        placeFileDataIntoHashMap();
        return iterateMapForTitle(dvdTitle);
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryDaoException{
        placeFileDataIntoHashMap();

        Dvd removedDvd = dvds.remove(dvdId);
        writeMapIntoFile();
        return removedDvd;
    }

    private Dvd iterateMapForTitle(String title){
        Dvd found = null;

        String lowerCase = title.toLowerCase();

        for(String s: dvds.keySet()){
            if(dvds.get(s).getTitle().toLowerCase().equals(lowerCase)){
                return dvds.get(s);
            }
        }

        return found;
    }




    private void placeFileDataIntoHashMap() throws DvdLibraryDaoException {
        BufferedReader reader;


        try{
            reader = new BufferedReader(new FileReader(DVD_LIBRARY));
            String line;

            while((line = reader.readLine()) != null){
                String[] parts = line.split(DELIMITER);
                Dvd d = new Dvd(parts[0]);
                d.setTitle(parts[1]);
                d.setReleaseDate(parts[2]);
                d.setMpaRating(parts[3]);
                d.setDirectorName(parts[4]);
                d.setStudio(parts[5]);
                d.setUserRating(parts[6]);

                dvds.put(d.getDvdId(), d);
            }
        }
        catch (IOException e){
            throw new DvdLibraryDaoException("Couldn't read the text data", e);
        }
    }

    private void writeMapIntoFile() throws DvdLibraryDaoException{
        FileWriter writer;
        BufferedWriter buffer;

        try{
            writer = new FileWriter(DVD_LIBRARY);
            buffer = new BufferedWriter(writer);


            for(String key:dvds.keySet()){
                String writeMe = "";

                writeMe += dvds.get(key).getDvdId() + DELIMITER;
                writeMe += dvds.get(key).getTitle() + DELIMITER;
                writeMe += dvds.get(key).getReleaseDate() + DELIMITER;
                writeMe += dvds.get(key).getMpaRating() + DELIMITER;
                writeMe += dvds.get(key).getDirectorName() + DELIMITER;
                writeMe += dvds.get(key).getStudio() + DELIMITER;
                writeMe += dvds.get(key).getUserRating();

                buffer.write(writeMe);
                buffer.newLine();
            }

            buffer.close();
        }
        catch (FileNotFoundException e){
            throw new DvdLibraryDaoException("Could not parse data into file", e);
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not parse data into file", e);
        }
    }


    /*
        LEGACY CODE THAT WAS GIVEN TO US BY CLASS ROSTER CODE ALONG, BUT I GOT RID OFF, SINCE I DECIDED TO MAKE MY OWN
     */
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
