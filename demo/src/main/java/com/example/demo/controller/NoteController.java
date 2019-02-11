package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;

@RestController	//= @Controller + @RespondseBody
@RequestMapping("/api")	//= @RequestMapping(value="/notes", method=RequestMethod.GET) 
public class NoteController {
	@Autowired
	NoteRepository noteRepository;
	
	//Get All Notes
	@GetMapping("/notes")
	public List<Note> getAllNotes(){
		return noteRepository.findAll();
	}
	
	//Create a new Note
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteRepository.save(note);
	}
	
//	The @Valid annotation makes sure that the request body is valid. Remember, we had marked Note’s title and content with @NotBlank annotation in the Note model?
//	If the request body doesn’t have a title or a content, then spring will return a 400 BadRequest error to the client.
	
	//Get a Single Note
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable Long id) {// = @PathVariable("id") Long noteId
		return noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));	
	}
	
	//Update a Note
	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable Long id, @Valid @RequestBody Note noteDetails) {
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());
		
		Note updatedNote = noteRepository.save(note);
		return updatedNote;
	}
	
	//Delete a Note
	@DeleteMapping("/note/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable Long id) {
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		
		noteRepository.delete(note);
		return ResponseEntity.ok().build();
	}
	
	
	
	
}
