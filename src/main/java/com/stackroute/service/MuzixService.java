package com.stackroute.service;

import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundExeption;

import java.util.List;
import java.util.Optional;

public interface MuzixService {
    public boolean saveMuzix(Muzix muzix) throws TrackAlreadyExistsException;
    public boolean deleteMuzix(int id)throws TrackNotFoundExeption;
    public Optional<Muzix> getMuzixById(int id) throws TrackNotFoundExeption;
    public boolean updateMuzix(Muzix muzix, int id) throws TrackNotFoundExeption;
    public List<Muzix> getAllMuzixUsers();
    public List<Muzix> getByName(String name);
}
