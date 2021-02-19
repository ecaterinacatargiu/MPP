package ro.ubb.catalog.core.model;

import javax.persistence.Entity;

import lombok.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Book extends BaseEntity<Long> implements Serializable
{
    private long BID;
    private String author;
    private int year;

}
