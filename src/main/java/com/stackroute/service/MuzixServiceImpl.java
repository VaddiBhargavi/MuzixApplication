package com.stackroute.service;

import com.stackroute.repository.MuzixRepository;
import com.stackroute.domain.Muzix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuzixServiceImpl implements MuzixService {
    MuzixRepository muzixRepository;
    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository)
    {
        this.muzixRepository= muzixRepository;
    }
    //saveMuzix() saves the track
    public Muzix saveMuzix(Muzix muzix)
    {
        Muzix savedMuzix= muzixRepository.save(muzix);
        return savedMuzix;
    }

    @Override
    //deleteMuzix deletes the track based on id
    public int deleteMuzix( int id) {
        if (muzixRepository.existsById(id)) {
            muzixRepository.deleteById(id);
            return id;
        }
        return id;
    }
    //getMuzixById() returns the track based on Id
    public Optional<Muzix> getMuzixById(int id)
    {
        return muzixRepository.findById(id);
    }
    @Override
    //updateMuzix() updates the track based on given id
    public Muzix updateMuzix(Muzix muzix, int id) {
        Optional<Muzix> userOptional = muzixRepository.findById(id);
            if (!userOptional.isPresent())
                return muzix;
            muzix.setId(id);
            muzixRepository.save(muzix);
            return muzix;
        }

    @Override
    public List<Muzix> getAllMuzixUsers() {
        return muzixRepository.findAll();
    }

}
