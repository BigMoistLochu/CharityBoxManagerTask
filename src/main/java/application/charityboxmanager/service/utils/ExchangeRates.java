package application.charityboxmanager.service.utils;
import application.charityboxmanager.exception.exceptions.InvalidCurrencyException;
import application.charityboxmanager.model.common.CurrencyCode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class ExchangeRates {

    private static final Map<CurrencyCode, BigDecimal> RATES = Map.of(
            CurrencyCode.GBP, BigDecimal.valueOf(1.00),
            CurrencyCode.EUR, BigDecimal.valueOf(0.91),
            CurrencyCode.PLN, BigDecimal.valueOf(4.29)
    );

    public static BigDecimal getRate(CurrencyCode from, CurrencyCode to) {
        if (!RATES.containsKey(from) || !RATES.containsKey(to)) {
            throw new InvalidCurrencyException("Unsupported currency conversion: " + from + " -> " + to);
        }

        BigDecimal fromRate = RATES.get(from);
        BigDecimal toRate = RATES.get(to);

        return fromRate.divide(toRate, 2, RoundingMode.HALF_UP);
    }


}
