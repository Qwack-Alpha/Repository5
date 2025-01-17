package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Anime;

import jakarta.validation.Valid;

import com.example.repo.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//import com.example.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/anime")
public class AnimeController {

	@Autowired
	AnimeRepository ar;

	@GetMapping("/readP")
	public List<Anime> read() {
		return ar.findAll();
	}

	@GetMapping("/readOneP/{id}")
	public Optional<Anime> readOne(@PathVariable long id) { // Optional is covering the Null part exception.
		return ar.findById(id);
	}

	@GetMapping("/readOne1P/{id}")
	public ResponseEntity<Anime> readAnime(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
		Anime anime = ar.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Anime not found for the ID: " + id));
		return ResponseEntity.ok(anime);
	}

	// Add
	@PostMapping("/addP")
	public Anime add(@RequestBody Anime anime) {
		return ar.save(anime);
	}

	// Update
//	@PutMapping("/updateP/{id}")
//	public void update(@RequestBody Anime animeNew, @PathVariable long id) {
//		Optional<Anime> animeOld = ar.findById(id);
//		animeOld.get().setName(animeNew.getName());
//		animeOld.get().setSynopsis(animeNew.getSynopsis());
//		animeOld.get().setRating(animeNew.getRating());
//		ar.save(animeOld.get());
//		ar.save(animeOld.get());
//	}
	
	@PutMapping("/updateP/{id}")
	public ResponseEntity<Void> update(@RequestBody Anime animeNew, @PathVariable long id) {
	    Optional<Anime> animeOld = ar.findById(id);
	    if (animeOld.isPresent()) {
	        animeOld.get().setName(animeNew.getName());
	        animeOld.get().setSynopsis(animeNew.getSynopsis());
	        animeOld.get().setRating(animeNew.getRating());
	        ar.save(animeOld.get());
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PutMapping("/update1P/{id}") // GET HTTP Method public Product
	public ResponseEntity<Optional<Anime>> updateAnime(@PathVariable long id, @RequestBody Anime new1anime)
			throws ResourceNotFoundException {
		Optional<Anime> anime = Optional.ofNullable(ar.findById(id)// this is the existing data
				.orElseThrow(() -> new ResourceNotFoundException("Anime not found for the ID: " + id)));
		anime.get().setName(new1anime.getName());
		ar.save(anime.get());
		return ResponseEntity.ok().body(anime);
	}

//	// Delete
//	@DeleteMapping("/deleteP/{id}")
//	public void delete(@PathVariable long id) {
//		Optional<Anime> animeOld = ar.findById(id);
//		ar.deleteById(id);
//
//	} 
	
	
	@DeleteMapping("/deleteP/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
	    Optional<Anime> animeOld = ar.findById(id);
	    if (animeOld.isPresent()) {
	        ar.deleteById(id);
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@DeleteMapping("/delete1P/{id}")
	public Map<String, Boolean> deleteAnime(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
		Optional<Anime> anime = Optional.ofNullable(
				ar.findById(id).orElseThrow(() -> new ResourceNotFoundException("Anime not found for the ID: " + id)));
		ar.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response; 

	}

}
