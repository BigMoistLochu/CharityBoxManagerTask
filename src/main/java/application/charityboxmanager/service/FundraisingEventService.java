package application.charityboxmanager.service;

import application.charityboxmanager.api.dto.FundraisingEventDto;
import application.charityboxmanager.exception.exceptions.*;
import application.charityboxmanager.model.CollectionBox;
import application.charityboxmanager.model.FundraisingEvent;
import application.charityboxmanager.repository.CollectionBoxRepository;
import application.charityboxmanager.repository.FundraisingEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FundraisingEventService {

    private final FundraisingEventRepository fundraisingEventRepository;
    private final CollectionBoxRepository collectingBoxRepository;


    public FundraisingEventService(FundraisingEventRepository fundraisingEventRepository, CollectionBoxRepository collectingBoxRepository) {
        this.fundraisingEventRepository = fundraisingEventRepository;
        this.collectingBoxRepository = collectingBoxRepository;
    }


    public FundraisingEventDto createFundraisingEvent(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be null or empty");
        }

        FundraisingEvent event = new FundraisingEvent(name);
        FundraisingEvent saved = fundraisingEventRepository.save(event);

        return new FundraisingEventDto(saved.getId(), saved.getName(),null);
    }

    @Transactional
    public void assignEventToCollectionBox(Long eventId,Long boxId){

        CollectionBox box = collectingBoxRepository
                .findById(boxId)
                .orElseThrow(() -> new CollectionBoxNotFoundException("Collection box not found"));

        if (!box.isEmpty()) {
            throw new CollectionBoxNotEmptyException();
        }

        if (box.isAssigned()) {
            throw new CollectionBoxAlreadyAssignedException();
        }

        FundraisingEvent event = fundraisingEventRepository
                .findById(eventId)
                .orElseThrow(() -> new FundraisingEventNotFoundException("Event not found"));

        if (event.getCollectionBox() != null) {
            throw new FundraisingEventAlreadyAssignedException();
        }

        box.setAssigned(true);
        event.setCollectionBox(box);

        fundraisingEventRepository.save(event);
        collectingBoxRepository.save(box);
    }

}
