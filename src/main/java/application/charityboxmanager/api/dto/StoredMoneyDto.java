package application.charityboxmanager.api.dto;

import java.math.BigDecimal;

public record StoredMoneyDto(BigDecimal amount, String currency) {}
