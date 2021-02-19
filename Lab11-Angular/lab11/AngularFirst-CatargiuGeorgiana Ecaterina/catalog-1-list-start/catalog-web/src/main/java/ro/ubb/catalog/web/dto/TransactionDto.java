package ro.ubb.catalog.web.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class TransactionDto extends BaseDto{

    private long clientID;
    private long bookID;
    private Date transactionDate;
    private int price;

}
