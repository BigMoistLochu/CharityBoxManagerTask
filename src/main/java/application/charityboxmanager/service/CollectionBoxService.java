package application.charityboxmanager.service;

import application.charityboxmanager.api.dto.CollectionBoxDto;
import application.charityboxmanager.exception.CollectionBoxNotFoundException;
import application.charityboxmanager.model.CollectionBox;
import application.charityboxmanager.repository.CollectionBoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionBoxService {

    private final CollectionBoxRepository boxRepo;


    public CollectionBoxService(CollectionBoxRepository boxRepo) {
        this.boxRepo = boxRepo;
    }

    public List<CollectionBoxDto> getAllCollectionBox(){
        return boxRepo.findAll().stream()
                .map(collectionBox -> CollectionBoxDto.fromEntity(collectionBox)).toList();
    }

    public CollectionBoxDto registerCollectionBox() {
        CollectionBox collectionBox = new CollectionBox(true, false);
        CollectionBox savedBox = boxRepo.save(collectionBox);
        return CollectionBoxDto.fromEntity(savedBox);
    }


    public void removeCollectionBox(Long id) {
        CollectionBox box = boxRepo.findById(id)
                .orElseThrow(() -> new CollectionBoxNotFoundException("Collection box not found"));
        boxRepo.delete(box);
    }



}
