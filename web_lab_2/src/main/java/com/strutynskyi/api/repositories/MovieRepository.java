package com.strutynskyi.api.repositories;

import com.strutynskyi.api.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT f FROM Movie f JOIN f.director d WHERE d.firstName = :firstName AND d.lastName = :lastName")
    List<Movie> findByDirector(String firstName, String lastName);
}
