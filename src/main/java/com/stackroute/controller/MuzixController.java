package com.stackroute.controller;

import com.stackroute.Repository.MuzixRepository;
import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundExeption;
import com.stackroute.service.MuzixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class MuzixController {
    MuzixService muzixService;
    @Autowired
    public MuzixController(MuzixService muzixService) {
        this.muzixService = muzixService;
    }

    //To create the muzix details
    @PostMapping("muzix")
    public ResponseEntity<?> saveMuzix(@RequestBody Muzix muzix) throws TrackAlreadyExistsException
    {
        ResponseEntity responseEntity;
        try
        {
            muzixService.saveMuzix(muzix);
            responseEntity= new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //To get AllMuzixUsers details
    @GetMapping("muzix")
    public ResponseEntity<?> getAllMuzixUsers()
    {
        return new ResponseEntity<List<Muzix>>(muzixService.getAllMuzixUsers(),HttpStatus.OK);
    }
    //Update operation
    @PutMapping("update/{id}")
    public  ResponseEntity<?> updateMuzix(@RequestBody Muzix muzix, @PathVariable int id) throws TrackNotFoundExeption
    {
        ResponseEntity responseEntity;
        try
        {
            muzixService.updateMuzix(muzix,id);
            responseEntity= new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        }
        catch (Exception e)
        {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //Delete operation
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<?> deleteMuzix(@PathVariable int id)
    {
        ResponseEntity responseEntity;
        try
        {
            muzixService.deleteMuzix(id);
            responseEntity= new ResponseEntity<String>("Successfully deleted", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //Add an endpoint to search trackByName. Understand @Query and parameter passing to
    //@Query
    @GetMapping("/names/{name}")
    public ResponseEntity<List<Muzix>> getByname(@PathVariable String name) {
        List<Muzix> musix = muzixService.getByName(name);
        return new ResponseEntity<List<Muzix>>(musix, HttpStatus.OK);
    }

}
