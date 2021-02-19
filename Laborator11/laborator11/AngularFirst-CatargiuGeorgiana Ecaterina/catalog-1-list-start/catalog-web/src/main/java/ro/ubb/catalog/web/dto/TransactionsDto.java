package ro.ubb.catalog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.catalog.core.model.Transaction;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionsDto {

    private Set<TransactionDto> transactions;
}
