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
    private String sector;
    @Field
    private boolean isEnabled;
    @Field
    private Date creationDate;

    public Puerta() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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
                ", sector='" + sector + '\'' +
                ", isEnabled=" + isEnabled +
                ", creationDate=" + creationDate +
                '}';
    }
}
