package com.stackroute.service;

import com.stackroute.domain.Muzix;

import java.util.List;
import java.util.Optional;

public interface MuzixService {
    public boolean saveMuzix(Muzix muzix);
    public boolean deleteMuzix(int id);
    public Optional<Muzix> getMuzixById(int id);
    public boolean updateMuzix(Muzix muzix, int id);
    public List<Muzix> getAllMuzixUsers();
}
