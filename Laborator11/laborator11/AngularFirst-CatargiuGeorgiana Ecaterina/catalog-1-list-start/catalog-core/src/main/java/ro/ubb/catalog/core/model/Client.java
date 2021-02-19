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
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Long>
{
    private long CNP;
    private Date yob;
    private String gender;

}
