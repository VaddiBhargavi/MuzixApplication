package com.stackroute.service;

import com.stackroute.Repository.MuzixRepository;
import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Implementing ApplicationListener<ContextRefreshedEvent> and CommandLineRunner
@Service
public class MuzixServiceImpl implements MuzixService, ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
    @Value("${muzix.1.name:default}")
    String name1;
    @Value("${muzix.1.rating:default}")
    int rating1;
    @Value("${muzix.1.comments:default}")
    String comments1;
    @Value("${muzix.2.name:default}")
    String name2;
    @Value("${muzix.2.rating:default}")
    int rating2;
    @Value("${muzix.2.comments:default}")
    String comments2;

    MuzixRepository muzixRepository;
    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository)
    {
        this.muzixRepository= muzixRepository;
    }
    public boolean saveMuzix(Muzix muzix) throws TrackAlreadyExistsException {
//        if (muzixRepository.existsById(muzix.getId())) {
//            throw new TrackAlreadyExistsException("Track already exists with id  : " + muzix.getId());
//        }
        Muzix savedMuzix= muzixRepository.save(muzix);
        return true;
    }
    @Override
    public boolean deleteMuzix(int id) throws TrackNotFoundExeption{
//        if(!muzixRepository.findById(id).isPresent())
//        {
//            throw new TrackNotFoundExeption("Record doesnt exists");
//        }
            muzixRepository.deleteById(id);
            return true;
        }
    public Optional<Muzix> getMuzixById(int id) throws TrackNotFoundExeption {
        Optional<Muzix> user_id = muzixRepository.findById(id);
        if (!user_id.isPresent())
            throw new TrackNotFoundExeption("Record not found");
        return muzixRepository.findById(id);
    }

    @Override
    public boolean updateMuzix(Muzix muzix, int id) throws TrackNotFoundExeption{
        Optional<Muzix> userOptional = muzixRepository.findById(id);
            if (!userOptional.isPresent()) {
                throw new TrackNotFoundExeption("Record doesn't exists");
            }
            muzix.setId(id);
            muzixRepository.save(muzix);
            return true;
        }

    @Override
    public List<Muzix> getAllMuzixUsers() {
        return muzixRepository.findAll();
    }
    public List<Muzix> getByName(String name) {
        List<Muzix> user_name = muzixRepository.findTitleByName(name);

        return user_name;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("command line running before application starts");

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        muzixRepository.save(new Muzix(1, name1, rating1, comments1));
        muzixRepository.save(new Muzix(2, name2, rating2, comments2));
    }
}
