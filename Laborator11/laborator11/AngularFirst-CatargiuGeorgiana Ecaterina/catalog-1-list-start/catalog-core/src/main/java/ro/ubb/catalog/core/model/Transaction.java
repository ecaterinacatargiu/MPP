package ro.ubb.catalog.core.model;

import lombok.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Transaction extends BaseEntity<Long> implements Serializable
{
    private long clientID;
    private long bookID;
    private Date transactionDate;
    private int price;

}
