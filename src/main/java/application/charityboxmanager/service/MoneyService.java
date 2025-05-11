package application.charityboxmanager.service;

import application.charityboxmanager.exception.exceptions.CollectionBoxNotAssignedToEventException;
import application.charityboxmanager.exception.exceptions.CollectionBoxNotFoundException;
import application.charityboxmanager.exception.exceptions.InvalidCurrencyException;
import application.charityboxmanager.model.CollectionBox;
import application.charityboxmanager.model.FundraisingEvent;
import application.charityboxmanager.model.StoredMoney;
import application.charityboxmanager.model.common.CurrencyCode;
import application.charityboxmanager.model.common.Money;
import application.charityboxmanager.repository.CollectionBoxRepository;
import application.charityboxmanager.repository.FundraisingEventRepository;
import application.charityboxmanager.repository.StoredMoneyRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class MoneyService {

    private final CollectionBoxRepository boxRepo;
    private final StoredMoneyRepository moneyRepo;
    private final FundraisingEventRepository eventRepo;

    public MoneyService(CollectionBoxRepository boxRepo, StoredMoneyRepository moneyRepo, FundraisingEventRepository eventRepo) {
        this.boxRepo = boxRepo;
        this.moneyRepo = moneyRepo;
        this.eventRepo = eventRepo;
    }

    public void addMoneyToBox(Long boxId, BigDecimal amount, String currency) {
        CollectionBox box = boxRepo.findById(boxId)
                .orElseThrow(() -> new CollectionBoxNotFoundException("Collection box not found"));


        FundraisingEvent event = eventRepo.findByCollectionBoxId(box.getId());

        if(event == null){
            throw new CollectionBoxNotAssignedToEventException();
        }

        CurrencyCode currencyCode;

        try {
            currencyCode = CurrencyCode.valueOf(currency);
        } catch (IllegalArgumentException ex) {
            throw new InvalidCurrencyException("Unsupported currency: " + currency);
        }

        Money money = new Money(amount, currencyCode);

        StoredMoney giftMoney = new StoredMoney(box,money);
        box.setEmpty(false);

        moneyRepo.save(giftMoney);
        boxRepo.save(box);
    }

}
