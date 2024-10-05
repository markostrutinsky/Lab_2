package com.strutynskyi.api.controllers;

import com.strutynskyi.api.dto.director.CreateDirectorRequestDTO;
import com.strutynskyi.api.dto.director.DirectorDTO;
import com.strutynskyi.api.dto.director.UpdateDirectorRequestDTO;
import com.strutynskyi.api.exceptions.DirectorAlreadyExistsException;
import com.strutynskyi.api.exceptions.NoSuchDirectorException;
import com.strutynskyi.api.mappers.DirectorMappers;
import com.strutynskyi.api.models.Director;
import com.strutynskyi.api.services.interfaces.DirectorService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/directors")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<DirectorDTO> directors = directorService.findAll()
                .stream()
                .map(DirectorMappers::toDirectorDTO)
                .toList();
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Director existingDirector = directorService.findById(id);
        DirectorDTO directorDTO = DirectorMappers.toDirectorDTO(existingDirector);
        return ResponseEntity.ok(directorDTO);
    }

    @PostMapping
    public ResponseEntity<?> createDirector(@RequestBody CreateDirectorRequestDTO directorDTO) {
        Director directorModel = DirectorMappers.toDirectorFromCreateDTO(directorDTO);
        Director savedDirector = directorService.save(directorModel);
        return new ResponseEntity<>("New director was created with id: " + savedDirector.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDirector(@PathVariable Long id, @RequestBody UpdateDirectorRequestDTO updateDTO) {
        Director directorModel = DirectorMappers.toDirectorFromUpdateDTO(updateDTO);
        Director updatedDirector = directorService.update(directorModel, id);
        return new ResponseEntity<>(DirectorMappers.toDirectorDTO(updatedDirector), HttpStatus.OK);
    }
    //sdbcsdckjks
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable Long id) {
        Director deletedDirector = directorService.delete(id);
        return new ResponseEntity<>("Director was deleted by id: " + deletedDirector.getId(), HttpStatus.OK);
    }
}
