package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Note;

@Repository	//support save(), findOne(), findAll(), count(), delete()..etc
public interface NoteRepository extends JpaRepository<Note, Long> {
	
}

//Note that, we have annotated the interface with @Repository annotation. This tells Spring to bootstrap the repository during component scan.
//Great! That is all you have to do in the repository layer. You will now be able to use JpaRepository’s methods like save(), findOne(), findAll(), count(), delete() etc.
//You don’t need to implement these methods. They are already implemented by Spring Data JPA’s SimpleJpaRepository. This implementation is plugged in by Spring automatically at runtime.
//Checkout all the methods available from SimpleJpaRepository’s documentation.
//Spring Data JPA has a bunch of other interesting features like Query methods (dynamically creating queries based on method names), Criteria API, Specifications, QueryDsl etc.
//I strongly recommend you to checkout the Spring Data JPA’s documentation to learn more.
