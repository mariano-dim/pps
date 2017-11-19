package edu.pps.integradorrs.model;


import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "puertas")
public class Puerta {

    @Id
    private String id;
    @Field
    private String publicIdentification;
    @Field
    private boolean isEnabled;
    @Field
    private Date creationDate;



    public Puerta() {
        super();
    }

    public String getPublicIdentification() {
        return publicIdentification;
    }

    public void setPublicIdentification(String publicIdentification) {
        this.publicIdentification = publicIdentification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Puerta{" +
                "id='" + id + '\'' +
                ", isEnabled=" + isEnabled +
                ", creationDate=" + creationDate +
                '}';
    }
}
