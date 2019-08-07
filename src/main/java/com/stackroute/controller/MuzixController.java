package com.stackroute.controller;

import com.stackroute.domain.Muzix;
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
    @PostMapping("muzix")
    //saveMuzix() saves the track also handled exceptions if we save already exists track
    public ResponseEntity<?> saveMuzix(@RequestBody Muzix muzix)
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
    //getAllMuzixUsers() gives all tracks
    @GetMapping("muzix")
    public ResponseEntity<?> getAllMuzixUsers()
    {
        return new ResponseEntity<List<Muzix>>(muzixService.getAllMuzixUsers(),HttpStatus.OK);
    }
    //updateMuzix() updates the track based on given id
    @PutMapping("update/{id}")
    public  ResponseEntity<?> updateMuzix(@RequestBody Muzix muzix, @PathVariable int id)
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
    //deleteMuzix deletes the track based on id and also handled exceptions if we delete the deleted track
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
