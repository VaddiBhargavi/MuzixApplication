package com.stackroute.service;

import com.stackroute.Repository.MuzixRepository;
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
    public boolean saveMuzix(Muzix muzix)
    {
        Muzix savedMuzix= muzixRepository.save(muzix);
        return true;
    }

    @Override
    public boolean deleteMuzix( int id) {
        if (muzixRepository.existsById(id)) {
            muzixRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Optional<Muzix> getMuzixById(int id)
    {
        return muzixRepository.findById(id);
    }

    @Override
    public boolean updateMuzix(Muzix muzix, int id) {
        Optional<Muzix> userOptional = muzixRepository.findById(id);
            if (!userOptional.isPresent())
                return false;
            muzix.setId(id);
            muzixRepository.save(muzix);
            return true;
        }

    @Override
    public List<Muzix> getAllMuzixUsers() {
        return muzixRepository.findAll();
    }

}
