package com.strutynskyi.api.services.impl;

import com.strutynskyi.api.exceptions.DirectorAlreadyExistsException;
import com.strutynskyi.api.exceptions.NoSuchDirectorException;
import com.strutynskyi.api.models.Director;
import com.strutynskyi.api.repositories.DirectorRepository;
import com.strutynskyi.api.services.interfaces.DirectorService;
import com.strutynskyi.api.validators.EntityValidator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final EntityValidator<Director> directorValidator;

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director findById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchDirectorException(id));
    }

    @Override
    public Director save(Director directorToSave) {
        directorValidator.validate(directorToSave);
        Optional<Director> existingDirector = directorRepository.findAll()
                .stream()
                .filter(d -> d.equals(directorToSave))
                .findFirst();

        if (existingDirector.isPresent())
            throw new DirectorAlreadyExistsException(existingDirector.get().getId());
        return directorRepository.save(directorToSave);
    }

    @Override
    public Director update(Director directorToUpdate, Long id) {
        directorValidator.validate(directorToUpdate);

        Director foundDirector = directorRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchDirectorException(id));

        foundDirector.setFirstName(directorToUpdate.getFirstName());
        foundDirector.setLastName(directorToUpdate.getLastName());
        foundDirector.setBiography(directorToUpdate.getBiography());
        foundDirector.setBirthDate(directorToUpdate.getBirthDate());

        return directorRepository.save(foundDirector);
    }

    @Override
    public Director delete(Long id) {
        Director directorToDelete = directorRepository.findById(id)
                .orElseThrow(() -> new NoSuchDirectorException(id));
        directorRepository.deleteById(id);
        return directorToDelete;
    }
}
