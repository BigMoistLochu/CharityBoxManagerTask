package application.charityboxmanager.service;

import application.charityboxmanager.repository.StoredMoneyRepository;
import org.springframework.stereotype.Service;



@Service
public class StoredMoneyService {

    private final StoredMoneyRepository repository;

    public StoredMoneyService(StoredMoneyRepository repository) {
        this.repository = repository;
    }


}
