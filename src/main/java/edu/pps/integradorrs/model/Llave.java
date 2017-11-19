package edu.pps.integradorrs.model;


import java.util.Arrays;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "llaves")
public class Llave {


    @Id
    private String id;
    @Field
    private String [] puertas;
    @Field
    private String publicIdentification;
    @Field
    private boolean isEnabled;
    @Field
    private Date creationDate;


    public Llave() {
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

    public String[] getPuertas() {
        return puertas;
    }

    public void setPuertas(String[] puertas) {
        this.puertas = puertas;
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
        return "Llave{" +
                "id='" + id + '\'' +
                ", puertas=" + Arrays.toString(puertas) +
                ", isEnabled=" + isEnabled +
                ", creationDate=" + creationDate +
                '}';
    }
}
