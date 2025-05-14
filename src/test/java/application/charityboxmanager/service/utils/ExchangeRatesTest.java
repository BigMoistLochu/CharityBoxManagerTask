package application.charityboxmanager.service.utils;

import application.charityboxmanager.model.common.CurrencyCode;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
public class ExchangeRatesTest {



    @Test
    void testRatePlnToEur() {
        BigDecimal rate = ExchangeRates.getRate(CurrencyCode.PLN, CurrencyCode.EUR);
        assertEquals(BigDecimal.valueOf(0.24), rate);
    }

    @Test
    void testRateEurToPln() {
        BigDecimal rate = ExchangeRates.getRate(CurrencyCode.EUR, CurrencyCode.PLN);
        assertEquals(BigDecimal.valueOf(4.24), rate);
    }

    @Test
    void testRateGbpToEur() {
        BigDecimal rate = ExchangeRates.getRate(CurrencyCode.GBP, CurrencyCode.EUR);
        assertEquals(BigDecimal.valueOf(1.19), rate);
    }

    @Test
    void testRateSameCurrency() {
        BigDecimal rate = ExchangeRates.getRate(CurrencyCode.EUR, CurrencyCode.EUR);
        assertEquals(BigDecimal.valueOf(1.00), rate);
    }



}
