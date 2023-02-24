package com.av.dvdlibrary.dao;

import com.av.dvdlibrary.dto.Dvd;

import java.io.*;
import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    public static final String DVD_LIBRARY = "library.txt";
    public static final String DELIMITER = "::";

    private Map<String, Dvd> dvds = new HashMap<>();

    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException {
        placeFileDataIntoHashMap();

        Dvd newDvd = dvds.put(dvdId, dvd);

        writeMapIntoFile();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        placeFileDataIntoHashMap();
        return new ArrayList(dvds.values());
    }

    @Override
    public Dvd getDvdById(String dvdId) throws DvdLibraryDaoException {
        placeFileDataIntoHashMap();
        return dvds.get(dvdId);
    }

    @Override
    public Dvd getDvdByTitle(String dvdTitle) throws DvdLibraryDaoException {
        placeFileDataIntoHashMap();
        return iterateMapForTitle(dvdTitle);
    }

    @Override
    public Dvd editDvd(Dvd dvd) throws DvdLibraryDaoException {
        // Don't want to access null!
        if (dvd == null) return null;

        placeFileDataIntoHashMap();

        Dvd editDvd = dvds.put(dvd.getDvdId(), dvd);

        writeMapIntoFile();

        return editDvd;
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryDaoException {
        placeFileDataIntoHashMap();

        Dvd removedDvd = dvds.remove(dvdId);
        writeMapIntoFile();
        return removedDvd;
    }

    private Dvd iterateMapForTitle(String title) {
        String lowerCase = title.toLowerCase();

        for (String s : dvds.keySet()) {
            if (dvds.get(s).getTitle().toLowerCase().equals(lowerCase)) {
                return dvds.get(s);
            }
        }

        return null;
    }

    private void placeFileDataIntoHashMap() throws DvdLibraryDaoException {
        BufferedReader reader;


        try {
            reader = new BufferedReader(new FileReader(DVD_LIBRARY));
            String line;

            while ((line = reader.readLine()) != null) {
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
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Couldn't read the text data", e);
        }
    }

    private void writeMapIntoFile() throws DvdLibraryDaoException {
        FileWriter writer;
        BufferedWriter buffer;

        try {
            writer = new FileWriter(DVD_LIBRARY);
            buffer = new BufferedWriter(writer);


            for (String key : dvds.keySet()) {
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
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("Could not parse data into file", e);
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not parse data into file", e);
        }
    }

}
