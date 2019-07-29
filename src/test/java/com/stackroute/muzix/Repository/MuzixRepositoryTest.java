//package com.stackroute.muzix.Repository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class MuzixRepositoryTest {
//    @Autowired
//    MuzixRepository muzixRepository;
//    Muzix user;
//
//}
package com.stackroute.muzix.Repository;


import com.stackroute.Repository.MuzixRepository;
import com.stackroute.domain.Muzix;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RunWith(SpringRunner.class)
@DataJpaTest
public class MuzixRepositoryTest {

    @Autowired
    MuzixRepository muzixRepository;
    Muzix muzix;

    List<Muzix> list = null;


    @Before
    public void setUp() {
        muzix = new Muzix();
        muzix.setId(1);
        muzix.setName("bhargavi");
        muzix.setComments("good");
        list = new ArrayList<>();
        list.add(muzix);

    }

    @After
    public void tearDown() {

        muzixRepository.deleteAll();
    }

    @Test
    public void testSaveTrack() {
        muzixRepository.save(muzix);
        Muzix fetchTrack = muzixRepository.findById(muzix.getId()).get();
        Assert.assertEquals(1, fetchTrack.getId());

    }

    @Test
    public void testGetAllTracks() {
        muzixRepository.save(muzix);
        List<Muzix> fetchTrack = muzixRepository.findAll();
        Assert.assertEquals(list, fetchTrack);
    }

    @Test
    public void testUpdateTrack() {
// save one object in database
        muzixRepository.save(muzix);
// fetch that object from database
        Muzix fetchTrack = muzixRepository.findById(muzix.getId()).get();
//update any field of the track
        muzix.setComments("bad");
// save it again
        muzixRepository.save(muzix);
// fetch it again and verify that updated field is there or not .
        Assert.assertEquals("bad", fetchTrack.getComments());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteTrack() {
// save one object in database
        muzixRepository.save(muzix);
// fetch that object from database
        Muzix fetchTrack = muzixRepository.findById(muzix.getId()).get();
        // verify fetched data is not null
        Assert.assertEquals("bhargavi",fetchTrack.getName());
// delete the data
        muzixRepository.deleteById(1);
// fetch again and verify its null now
// Assert.assertEquals(null,muzixRepository.findById(track.getTrackId()).get());
        muzixRepository.findById(muzix.getId()).get();
    }
}

