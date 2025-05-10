package application.charityboxmanager;

import application.charityboxmanager.api.dto.CollectionBoxDto;
import application.charityboxmanager.model.common.CurrencyCode;
import application.charityboxmanager.service.CollectionBoxService;
import application.charityboxmanager.service.MoneyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class CharityBoxManagerApplication{
    public static void main(String[] args) {
        SpringApplication.run(CharityBoxManagerApplication.class, args);
    }
}
