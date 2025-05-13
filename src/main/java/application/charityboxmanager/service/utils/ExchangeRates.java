package application.charityboxmanager.service.utils;
import application.charityboxmanager.exception.exceptions.InvalidCurrencyException;
import application.charityboxmanager.model.common.CurrencyCode;
import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRates {

    private static final Map<CurrencyCode, Map<CurrencyCode, BigDecimal>> RATES = Map.of(
            CurrencyCode.PLN, Map.of(
                    CurrencyCode.GBP, BigDecimal.valueOf(0.20),
                    CurrencyCode.EUR, BigDecimal.valueOf(0.24),
                    CurrencyCode.PLN, BigDecimal.valueOf(1.00)
            ),
            CurrencyCode.EUR, Map.of(
                    CurrencyCode.GBP, BigDecimal.valueOf(0.84),
                    CurrencyCode.EUR, BigDecimal.valueOf(1.00),
                    CurrencyCode.PLN, BigDecimal.valueOf(4.24)
            ),
            CurrencyCode.GBP, Map.of(
                    CurrencyCode.GBP, BigDecimal.valueOf(1.00),
                    CurrencyCode.EUR, BigDecimal.valueOf(1.19),
                    CurrencyCode.PLN, BigDecimal.valueOf(5.04)
            )
    );

    public static BigDecimal getRate(CurrencyCode from, CurrencyCode to) {
        if (!RATES.containsKey(from) || !RATES.get(from).containsKey(to)) {
            throw new InvalidCurrencyException("Unsupported currency conversion: " + from + " to " + to);
        }
        return RATES.get(from).get(to);
    }




}
