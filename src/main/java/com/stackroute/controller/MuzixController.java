package com.stackroute.controller;
import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;
import com.stackroute.Muzix.services.Musicservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="api/v1")

public class TrackController {
Musicservice musicservice;
  
@Autowired
public TrackController(Musicservice musicservice) {
this.musicservice = musicservice;
}
  
//Post mapping to save the track
@PostMapping("track")
public ResponseEntity<?> saveTrack(@RequestBody Muzix muzix){
ResponseEntity responseEntity;
try{
musicservice.saveTrack(muzix);
responseEntity=new ResponseEntity<String>("successfully Created", HttpStatus.CREATED);
}
catch (TrackAlreadyExistsException ex){
responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
}
return responseEntity;
}
  
//Get mapping to get all the tracks
@GetMapping("track")
public ResponseEntity<?> getAllTracks() {
return new ResponseEntity<List<Muzix>>(musicservice.getAllTracks(), HttpStatus.OK);
  }
  
  
//Delete mapping to delete the track
@DeleteMapping("delete/{trackId}")
public ResponseEntity<?> deleteTrack(@PathVariable int id){
ResponseEntity responseEntity;
  try{
  musicservice.deleteTrack(id);
  responseEntity=new ResponseEntity<String>("successfully deleted", HttpStatus.OK);
    }
catch (Exception ex){
responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
}
return responseEntity;
}
  
//Put Mapping to update the existing track
@PutMapping("update/{id}")
public ResponseEntity<?> updateTrack(@RequestBody Muzix muzix,@PathVariable int id){
ResponseEntity responseEntity;
try{
musicservice.UpdateTrack(muzix,id);
responseEntity =new ResponseEntity<String>("successfully updated", HttpStatus.OK);
}
catch (TrackNotFoundException ex){
responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
}
return responseEntity;
}
  
  
//Get Mapping to get the Track by name
@GetMapping("names/{name}")
public ResponseEntity<List<Muzix>> getByName(@PathVariable String name) {
return new ResponseEntity<List<Muzix>>(musicservice.getByName(name), HttpStatus.OK);
}
}
