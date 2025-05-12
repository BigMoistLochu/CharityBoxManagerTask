package application.charityboxmanager.service;

import application.charityboxmanager.api.dto.FundraisingEventDto;
import application.charityboxmanager.api.dto.FundraisingEventInputDto;
import application.charityboxmanager.exception.exceptions.*;
import application.charityboxmanager.model.CollectionBox;
import application.charityboxmanager.model.FundraisingEvent;
import application.charityboxmanager.model.FundraisingEventAccount;
import application.charityboxmanager.model.common.CurrencyCode;
import application.charityboxmanager.model.common.Money;
import application.charityboxmanager.repository.CollectionBoxRepository;
import application.charityboxmanager.repository.FundraisingEventAccountRepository;
import application.charityboxmanager.repository.FundraisingEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class FundraisingEventService {

    private final FundraisingEventRepository fundraisingEventRepository;
    private final CollectionBoxRepository collectingBoxRepository;
    private final FundraisingEventAccountRepository fundraisingEventAccountRepository;

    public FundraisingEventService(FundraisingEventRepository fundraisingEventRepository, CollectionBoxRepository collectingBoxRepository, FundraisingEventAccountRepository fundraisingEventAccountRepository) {
        this.fundraisingEventRepository = fundraisingEventRepository;
        this.collectingBoxRepository = collectingBoxRepository;
        this.fundraisingEventAccountRepository = fundraisingEventAccountRepository;
    }


    public FundraisingEventDto createFundraisingEvent(FundraisingEventInputDto dto) {
        if (dto.name() == null || dto.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be null or empty");
        }

        FundraisingEvent event = new FundraisingEvent(dto.name());

        CurrencyCode currencyCode;

        try {
            currencyCode = CurrencyCode.valueOf(dto.currency());
        } catch (IllegalArgumentException ex) {
            throw new InvalidCurrencyException("Unsupported currency: " + dto.currency());
        }
        Money money = new Money(new BigDecimal(0),currencyCode);


        FundraisingEvent saved = fundraisingEventRepository.save(event);

        FundraisingEventAccount account = new FundraisingEventAccount(saved,money);
        fundraisingEventAccountRepository.save(account);

        return new FundraisingEventDto(saved.getId(), saved.getName(), null);
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
