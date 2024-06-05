package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.model.Anime;


public interface AnimeRepository extends JpaRepository<Anime, Long>{

}




