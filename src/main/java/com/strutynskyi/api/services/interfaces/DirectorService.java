package com.strutynskyi.api.services.interfaces;

import com.strutynskyi.api.models.Director;

import java.util.List;

public interface DirectorService {
    List<Director> findAll();
    Director findById(Long id);
    Director save(Director director);
    Director update(Director director, Long id);
    Director delete(Long id);
}
