package com.stackroute.Repository;

import com.stackroute.domain.Muzix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuzixRepository extends JpaRepository<Muzix, Integer> {
    @Query(
            value= "select * from Muzix where name=?1", nativeQuery = true)
    List<Muzix> findTitleByName(String name);
}
