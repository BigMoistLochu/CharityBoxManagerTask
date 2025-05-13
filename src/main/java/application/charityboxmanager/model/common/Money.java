package application.charityboxmanager.model.common;

import application.charityboxmanager.exception.exceptions.InvalidCurrencyException;
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

    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new InvalidCurrencyException("Cannot add money with different currencies");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new InvalidCurrencyException("Cannot subtract money with different currencies");
        }
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(this.amount.multiply(multiplier), this.currency);
    }
}
