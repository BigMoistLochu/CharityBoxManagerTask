package application.charityboxmanager.service;

import application.charityboxmanager.model.StoredMoney;
import application.charityboxmanager.repository.StoredMoneyRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class StoredMoneyService {

    private final StoredMoneyRepository repository;

    public StoredMoneyService(StoredMoneyRepository repository) {
        this.repository = repository;
    }

    public List<StoredMoney> getAllStoredMoneyByCollectionBoxId(Long boxId) {
        return repository.findAllByCollectionBoxId(boxId);
    }

}
