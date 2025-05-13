package application.charityboxmanager.api.dto;

import java.math.BigDecimal;

public record FundraisingEventsFinancialReportDto(String nameEvent, BigDecimal amount, String currency) {
}
