package com.av.dvdlibrary.dao;

import com.av.dvdlibrary.dto.Dvd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
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
}
