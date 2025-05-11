package application.charityboxmanager.service;

import application.charityboxmanager.api.dto.CollectionBoxDto;
import application.charityboxmanager.exception.exceptions.CollectionBoxNotFoundException;
import application.charityboxmanager.model.CollectionBox;
import application.charityboxmanager.model.FundraisingEvent;
import application.charityboxmanager.repository.CollectionBoxRepository;
import application.charityboxmanager.repository.FundraisingEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CollectionBoxService {

    private final CollectionBoxRepository boxRepo;
    private final StoredMoneyService storedMoneyService;
    private final FundraisingEventRepository fundraisingEventRepository;

    public CollectionBoxService(CollectionBoxRepository boxRepo, StoredMoneyService storedMoneyService, FundraisingEventRepository fundraisingEventRepository) {
        this.boxRepo = boxRepo;
        this.storedMoneyService = storedMoneyService;
        this.fundraisingEventRepository = fundraisingEventRepository;
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


    @Transactional
    public void removeCollectionBox(Long boxId) {
        CollectionBox box = boxRepo.findById(boxId)
                .orElseThrow(() -> new CollectionBoxNotFoundException("Collection box not found"));

        FundraisingEvent event = fundraisingEventRepository.findByCollectionBoxId(boxId);

        if (event != null) {
            event.setCollectionBox(null);
            fundraisingEventRepository.save(event);
        }

        storedMoneyService.removeAllStoredMoneyByCollectionBoxId(box.getId());
        boxRepo.delete(box);
    }



}
