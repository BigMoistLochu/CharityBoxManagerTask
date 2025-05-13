package application.charityboxmanager.service.utils;
import application.charityboxmanager.exception.exceptions.InvalidCurrencyException;
import application.charityboxmanager.model.common.CurrencyCode;
import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRates {

    private static final Map<CurrencyCode, BigDecimal> RATES = Map.of(
            CurrencyCode.GBP, BigDecimal.valueOf(1.00),
            CurrencyCode.EUR, BigDecimal.valueOf(0.91),
            CurrencyCode.PLN, BigDecimal.valueOf(4.29)
    );

    public static BigDecimal getRate(CurrencyCode currency) {
        if (!RATES.containsKey(currency)) {
            throw new InvalidCurrencyException("Unsupported currency conversion: " + currency);
        }
        return RATES.get(currency);
    }


}
