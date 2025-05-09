package application.charityboxmanager.model.common;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public record Money(BigDecimal amount, CurrencyCode currency) {

    public Money {
        Objects.requireNonNull(amount);
        Objects.requireNonNull(currency);
        if(amount.scale() > 2) throw new IllegalArgumentException("Too many decimal places");
    }
}
