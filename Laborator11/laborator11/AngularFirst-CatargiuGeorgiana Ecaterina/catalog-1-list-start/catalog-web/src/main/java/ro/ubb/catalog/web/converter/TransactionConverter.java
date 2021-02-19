package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Transaction;
import ro.ubb.catalog.web.dto.TransactionDto;

@Component
public class TransactionConverter extends BaseConverter<Transaction, TransactionDto> {
    @Override
    public Transaction convertDtoToModel(TransactionDto dto) {
        Transaction transaction = Transaction.builder()
                .clientID(dto.getClientID())
                .bookID(dto.getBookID())
                .transactionDate(dto.getTransactionDate())
                .price(dto.getPrice())
                .build();
        transaction.setId(dto.getId());
        return transaction;
    }

    @Override
    public TransactionDto convertModelToDto(Transaction transaction) {
        TransactionDto dto = TransactionDto.builder()
                .clientID(transaction.getClientID())
                .bookID(transaction.getBookID())
                .transactionDate(transaction.getTransactionDate())
                .price(transaction.getPrice())
                .build();
        dto.setId(transaction.getId());
        return dto;
    }
}
