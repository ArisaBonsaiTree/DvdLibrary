package com.av.dvdlibrary.dao;

import com.av.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdLibraryDao {
    /**
     * Adds the given DVD to the library with dvd id.
     * If there is already a dvd associated with the given
     * dvd id it will return that student object, otherwise it will
     * return null.
     *
     * @param dvdId id with which dvd is to be associated
     * @param dvd   dvd to be added to the library
     * @return the Dvd object previously associated with the given
     * dvd id if it exists, null otherwise
     */
    Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException;

    /**
     * We pass the dvd object and override it
     *
     * @param dvd Dvd with which dvd is to be associated
     * @return The dvd object that we successfulyl edited
     * null otherwise
     */
    Dvd editDvd(Dvd dvd) throws DvdLibraryDaoException;


    /**
     * Returns a List of all dvds in the library
     *
     * @return List containing all dvds in the library
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    /**
     * Returns the dvd object associated with the given dvd id.
     * Returns null if no such student exists
     *
     * @param dvdId ID of the dvd to retrieve
     * @return the dvd object associated with the given dvd id,
     * null if no such dvd exists
     */
    Dvd getDvdById(String dvdId) throws DvdLibraryDaoException;

    /**
     * Returns a Dvd object
     *
     * @param dvdTitle Title of the dvd to retrieve
     * @return the dvd object associated with the given dvd string
     * null if no such dvd exist
     */
    Dvd getDvdByTitle(String dvdTitle) throws DvdLibraryDaoException;
    /**
     * Removes from the library the dvd associated with the given id.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given id
     *
     * @param dvdId id of dvd to be removed
     * @return Dvd object that was removed or null if no dvd
     * was associated with the given dvd id
     */
    Dvd removeDvd(String dvdId) throws DvdLibraryDaoException;
}
