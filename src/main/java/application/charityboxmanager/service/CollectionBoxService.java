package application.charityboxmanager.service;

import application.charityboxmanager.api.dto.CollectionBoxDto;
import application.charityboxmanager.exception.exceptions.*;
import application.charityboxmanager.model.CollectionBox;
import application.charityboxmanager.model.FundraisingEvent;
import application.charityboxmanager.model.FundraisingEventAccount;
import application.charityboxmanager.model.StoredMoney;
import application.charityboxmanager.model.common.CurrencyCode;
import application.charityboxmanager.model.common.Money;
import application.charityboxmanager.repository.CollectionBoxRepository;
import application.charityboxmanager.repository.FundraisingEventAccountRepository;
import application.charityboxmanager.repository.FundraisingEventRepository;
import application.charityboxmanager.repository.StoredMoneyRepository;
import application.charityboxmanager.service.utils.ExchangeRates;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CollectionBoxService {

    private final CollectionBoxRepository boxRepo;
    private final StoredMoneyRepository storedMoneyRepository;
    private final FundraisingEventRepository fundraisingEventRepository;
    private final FundraisingEventAccountRepository fundraisingEventAccountRepository;

    public CollectionBoxService(CollectionBoxRepository boxRepo, StoredMoneyRepository storedMoneyRepository, FundraisingEventRepository fundraisingEventRepository, FundraisingEventAccountRepository fundraisingEventAccountRepository) {
        this.boxRepo = boxRepo;
        this.storedMoneyRepository = storedMoneyRepository;
        this.fundraisingEventRepository = fundraisingEventRepository;
        this.fundraisingEventAccountRepository = fundraisingEventAccountRepository;
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

        storedMoneyRepository.deleteAllStoredMoneyByCollectionBoxId(box.getId());
        boxRepo.delete(box);
    }

    public void addMoneyToBox(Long boxId, BigDecimal amount, String currency) {
        CollectionBox box = boxRepo.findById(boxId)
                .orElseThrow(() -> new CollectionBoxNotFoundException("Collection box not found"));


        FundraisingEvent event = fundraisingEventRepository.findByCollectionBoxId(box.getId());

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

        storedMoneyRepository.save(giftMoney);
        boxRepo.save(box);
    }

    @Transactional
    public void transferCollectedMoneyToEventAccount(Long boxId) {
        CollectionBox box = boxRepo.findById(boxId)
                .orElseThrow(() -> new CollectionBoxNotFoundException("Collection box not found"));

        if(!box.isAssigned()){
            throw new CollectionBoxNotAssignedToEventException("Collection box must be assigned to a fundraising event before transfer money.");
        }

        List<StoredMoney> storedMonies = storedMoneyRepository.findAllByCollectionBoxId(boxId);

        if (storedMonies.isEmpty()) {
            throw new CollectionBoxIsEmptyException();
        }

        FundraisingEventAccount account = fundraisingEventAccountRepository.findAccountByCollectionBoxId(box.getId());

        if (account == null) {
            throw new FundraisingEventNotFoundException("No event account assigned to box");
        }

        CurrencyCode accountCurrency = account.getMoney().currency();

        BigDecimal totalConvertedAmount = storedMonies.stream()
                .map(storedMoney -> {
                    BigDecimal rate = ExchangeRates.getRate(storedMoney.getMoney().currency(), accountCurrency);
                    return storedMoney.getMoney().amount().multiply(rate);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        Money updatedBalance = account.getMoney().add(new Money(totalConvertedAmount, accountCurrency));
        account.setMoney(updatedBalance);

        fundraisingEventAccountRepository.save(account);
        storedMoneyRepository.deleteAllStoredMoneyByCollectionBoxId(boxId);
    }


}
