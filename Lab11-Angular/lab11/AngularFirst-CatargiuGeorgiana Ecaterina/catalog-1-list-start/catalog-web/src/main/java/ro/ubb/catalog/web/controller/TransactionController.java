package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.model.Transaction;
import ro.ubb.catalog.core.service.TransactionServiceInterface;
import ro.ubb.catalog.web.converter.TransactionConverter;
import ro.ubb.catalog.web.dto.TransactionDto;
import ro.ubb.catalog.web.dto.TransactionsDto;

import java.util.Collection;

@RestController
public class TransactionController {

    public static final Logger log= LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionServiceInterface transactionService;

    @Autowired
    private TransactionConverter transactionConverter;

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    TransactionsDto getAllTransactions() {
        return new TransactionsDto(transactionConverter
                .convertModelsToDtos((Collection<Transaction>) transactionService.getAllTransactions()));

    }

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    TransactionDto addTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionConverter.convertModelToDto(transactionService.addTransaction(
                transactionConverter.convertDtoToModel(transactionDto)
        ));
    }

}
