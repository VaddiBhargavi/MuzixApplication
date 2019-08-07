package com.stackroute.service;

import com.stackroute.domain.Muzix;

import java.util.List;
import java.util.Optional;

public interface MuzixService {
    public Muzix saveMuzix(Muzix muzix);
    public int deleteMuzix(int id);
    public Optional<Muzix> getMuzixById(int id);
    public Muzix updateMuzix(Muzix muzix, int id);
    public List<Muzix> getAllMuzixUsers();
}
