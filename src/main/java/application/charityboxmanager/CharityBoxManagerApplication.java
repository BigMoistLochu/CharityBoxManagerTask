package application.charityboxmanager;

import application.charityboxmanager.model.common.CurrencyCode;
import application.charityboxmanager.service.utils.ExchangeRates;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class CharityBoxManagerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CharityBoxManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        BigDecimal amount = BigDecimal.valueOf(12.1);

        //jesli mam nwm euro to mam rate: teraz moje pln musze przemnozyc przez to


    }
}
